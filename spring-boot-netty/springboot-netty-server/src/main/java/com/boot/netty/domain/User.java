package com.boot.netty.domain;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import lombok.Getter;
import lombok.NonNull;

/**
 * 模拟连接用户
 */
public class User {
    public static final AttributeKey<User> USER_ATTRIBUTE_KEY = AttributeKey.newInstance("USER");

    @Getter
    private final String username;
    private final Channel channel;

    private User(String username, Channel channel) {
        this.username = username;
        this.channel = channel;
    }

    /**
     * 由登录命令和当前channel 创建出一个User实例
     */
    public static User of(@NonNull String loginCommand, @NonNull Channel channel){
        if (loginCommand.startsWith("login ")){
            return new User(loginCommand.trim().substring("login ".length()), channel);
        }

        throw new IllegalArgumentException("loginCommand [" + loginCommand + "] can not be accepted");
    }

    /**
     * 登录的时候在为用户所在的channel的user属性设置为当前用户
     *  & 在channel存储库中将 username->channel 映射存储
     */
    public void login(ChannelRepository channelRepository, Channel channel){
        channel.attr(USER_ATTRIBUTE_KEY).set(this);
        channelRepository.put(this.username, channel);
    }

    /**
     * 退出的时候设置对应channel上的user属性为null
     *  & 在channel存储库中将 username->channel 映射删除
     */
    public void logout(ChannelRepository channelRepository, Channel channel){
        channel.attr(USER_ATTRIBUTE_KEY).getAndSet(null);
        channelRepository.remove(this.username);
    }

    /**
     * 获取指定channel上对应的用户
     */
    public static User current(Channel channel){
        User user = channel.attr(USER_ATTRIBUTE_KEY).get();
        if (user == null){
            throw new UserLoggedOutException();
        }
        return user;
    }

    public void tell(Channel targetChannel, @NonNull String username, @NonNull String message){
        if (targetChannel != null){
            targetChannel.write(this.username);
            targetChannel.write(">");
            targetChannel.writeAndFlush(message + "\n\r");
            this.channel.writeAndFlush("The message was sent to [" + username + "] successfully.\r\n");
        }else {
            this.channel.writeAndFlush("No user named with ["+ username +"] \r\n.");
        }
    }
}
