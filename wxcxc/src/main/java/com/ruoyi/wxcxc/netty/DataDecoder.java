package com.ruoyi.wxcxc.netty;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class DataDecoder extends ByteToMessageDecoder {
    private ParseData parseData;

    public DataDecoder(ParseData parseData) {
        this.parseData = parseData;
    }

    @Override
    protected void decode(ChannelHandlerContext arg0, ByteBuf in, List<Object> list) throws Exception {
        // TODO 自动生成的方法存根
        //System.err.println(parseData.getDrcProInfo().getParseDataName() + "--------解码器正在解码----------");
        try {
            in.retain();
            parseData.decoder(in, list);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
