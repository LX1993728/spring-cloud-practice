package com.boot.netty.handler;

import com.boot.netty.domain.ChannelRepository;
import com.boot.netty.domain.User;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@Slf4j
@RequiredArgsConstructor
@ChannelHandler.Sharable
public class SimpleChatServerHandler extends ChannelInboundHandlerAdapter {

    private final ChannelRepository channelRepository;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Assert.notNull(this.channelRepository,  "[Assertion failed] - ChannelRepository is required; it must not be null");

        ctx.fireChannelActive();

        if (log.isDebugEnabled()){
            log.debug(ctx.channel().remoteAddress() + "");
        }
        String remoteAddress = ctx.channel().remoteAddress().toString();

        ctx.writeAndFlush("Your remote address is " + remoteAddress + ".\r\n");

        if (log.isDebugEnabled()) {
            log.debug("Bound Channel Count is {}", this.channelRepository.size());
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String stringMessage = (String) msg;
        if (log.isDebugEnabled()){
            log.debug(stringMessage);
        }

        // 判断如果是用户登录相关 则直接调用下一个处理器执行, 不再执行下面聊天消息的逻辑处理
        if (stringMessage.startsWith("login ")){
            ctx.fireChannelRead(msg);
            return;
        }

        final String[] splitMessage = stringMessage.split("::");
        if (splitMessage.length != 2){
            ctx.channel().writeAndFlush(stringMessage + "\r\n");
            return;
        }

        User.current(ctx.channel()).tell(channelRepository.get(splitMessage[0]),splitMessage[0], splitMessage[1]);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.getMessage(), cause);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Assert.notNull(this.channelRepository, "[Assertion failed] - ChannelRepository is required; it must not be null");
        Assert.notNull(ctx, "[Assertion failed] - ChannelHandlerContext is required; it must not be null");

        User.current(ctx.channel()).logout(this.channelRepository, ctx.channel());
        if (log.isDebugEnabled()){
            log.debug("Channel Count is " + this.channelRepository.size());
        }
    }
}
