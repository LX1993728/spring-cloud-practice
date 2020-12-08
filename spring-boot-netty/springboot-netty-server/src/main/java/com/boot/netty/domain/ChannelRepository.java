package com.boot.netty.domain;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用HashMap作为channel的仓库(存储)
 */
public class ChannelRepository {
    private ConcurrentHashMap<String, Channel> channelCache = new ConcurrentHashMap<>();

    public void put(String key, Channel value){
        channelCache.put(key, value);
    }

    public Channel get(String key){
        return channelCache.get(key);
    }

    public void remove(String key){
        this.channelCache.remove(key);
    }

    public int size(){
        return this.channelCache.size();
    }
}
