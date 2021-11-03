package com.ruoyi.wxcxc.netty;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    private static final Logger logger = LoggerFactory.getLogger(ServerChannelInitializer.class);
    static final EventExecutorGroup group = new DefaultEventExecutorGroup(2);
    private ParseData parseData;

    public ServerChannelInitializer(ParseData parseData) throws InterruptedException {
        this.parseData = parseData;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //IdleStateHandler心跳机制,如果超时触发Handle中userEventTrigger()方法
        pipeline.addLast("idleStateHandler", new IdleStateHandler(15, 0, 0, TimeUnit.MINUTES));

        pipeline.addLast(new ByteArrayEncoder());

        pipeline.addLast(new DataDecoder(this.parseData));
        pipeline.addLast(new DataServerHandler(this.parseData));
    }
}
