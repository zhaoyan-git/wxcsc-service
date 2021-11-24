package com.ruoyi.wxcxc.util;

import io.netty.channel.ChannelId;

public class CustomChannelId implements ChannelId {
    private String shortValue;
    private String longValue;


    @Override
    public String asShortText() {
        return this.shortValue;
    }

    @Override
    public String asLongText() {
        return this.longValue;
    }

    @Override
    public int compareTo(ChannelId o) {
        return 0;
    }

    public CustomChannelId(String shortValue, String longValue) {
        this.shortValue = shortValue;
        this.longValue = longValue;
    }

    public CustomChannelId(String longValue) {
        this.longValue = longValue;
    }

    public CustomChannelId() {
    }
}
