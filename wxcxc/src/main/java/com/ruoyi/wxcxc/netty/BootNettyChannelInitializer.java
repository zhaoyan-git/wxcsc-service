package com.ruoyi.wxcxc.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public class BootNettyChannelInitializer<SocketChannel> extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {

        // ChannelOutboundHandler，依照逆序执行
        ch.pipeline().addLast("encoder", new ByteArrayEncoder());
//        ch.pipeline().addLast("encoder", new StringEncoder());
//        ch.pipeline().addLast(new ChineseMessageEncoder(Charset.forName("UTF-8")));

        // 属于ChannelInboundHandler，依照顺序执行
//        ch.pipeline().addLast("decoder", new StringDecoder());
//        ch.pipeline().addLast("decoder", new ChineseMessageDecoder());

        ch.pipeline().addLast(new IdleStateHandler(2, 0, 0, TimeUnit.MINUTES));
//
//        ch.pipeline().addLast(new ByteArrayEncoder());

//        ch.pipeline().addLast(new DataDecoder(this.parseData));
//        ch.pipeline().addLast(new DataServerHandler(this.parseData));

        /**
         * 自定义ChannelInboundHandlerAdapter
         */
        ch.pipeline().addLast(new BootNettyChannelInboundHandlerAdapter());
    }
}
