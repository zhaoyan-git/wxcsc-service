package com.ruoyi.wxcxc.netty;

import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.wxcxc.domain.WxcxcDeviceGateway;
import com.ruoyi.wxcxc.domain.WxcxcDeviceSensor;
import com.ruoyi.wxcxc.domain.WxcxcProjectDeviceSensorData;
import com.ruoyi.wxcxc.mapper.WxcxcDeviceSensorMapper;
import com.ruoyi.wxcxc.mapper.WxcxcProjectDeviceSensorDataMapper;
import com.ruoyi.wxcxc.service.IWxcxcDeviceGatewayService;
import com.ruoyi.wxcxc.service.IWxcxcDeviceSensorService;
import com.ruoyi.wxcxc.service.IWxcxcDeviceService;
import com.ruoyi.wxcxc.util.HexUtil;
import com.ruoyi.wxcxc.util.SpringContextUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestHandlerAdapter extends ChannelInboundHandlerAdapter {
    /**
     * 从客户端收到新的数据时，这个方法会在收到消息时被调用
     *
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception, IOException {
        ctx.write("123");
    }

    /**
     * 从客户端收到新的数据、读取完成时调用
     *
     * @param ctx
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws IOException {
        ctx.flush();
    }

    /**
     * 当出现 Throwable 对象才会被调用，即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws IOException {

    }

    /**
     * 客户端与服务端第一次建立连接时 执行
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception, IOException {
        super.channelActive(ctx);
    }

    /**
     * 客户端与服务端 断连时 执行
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception, IOException {
        super.channelInactive(ctx);

    }

    /**
     * 服务端当read超时, 会调用这个方法
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception, IOException {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {

    }
}
