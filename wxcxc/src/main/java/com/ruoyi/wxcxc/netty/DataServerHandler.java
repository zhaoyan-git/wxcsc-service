package com.ruoyi.wxcxc.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DataServerHandler extends ChannelInboundHandlerAdapter {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    private ParseData parseData;

    public DataServerHandler(ParseData parseData) {
        this.parseData = parseData;
    }

    /**
     * 当我们通道进行激活的时候 触发的监听方法
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.err.println(parseData.getDrcProInfo().getParseDataName() + "--------通道激活------------");
    }

    /**
     * 当我们的通道里有数据进行读取的时候 触发的监听方法
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx /*NETTY服务上下文*/, Object msg /*实际的传输数据*/) throws Exception {
//        if (msg instanceof byte[]) {
//            byte[] bytes = (byte[]) msg;
//            CommTool.printInfo(parseData.getDrcProInfo().getParseDataName() + ",接收到数据 " + bytes.length + " bytes");
//
//            //解析 ,需要判定是否是心跳包
//            if (!parseData.parse(bytes)) {
//                log.info(parseData.getDrcProInfo().getParseDataName() + ",接收数据解析失败");
//                CommTool.printInfo(parseData.getDrcProInfo().getParseDataName() + ",数据解析失败");
//                return;
//            }
//
//            //存储
//            if (!parseData.save()) {
//                log.info(parseData.getDrcProInfo().getParseDataName() + ",数据存储失败");
//                CommTool.printInfo(parseData.getDrcProInfo().getParseDataName() + ",数据存储失败");
//                return;
//            }
//
//            if (!parseData.save2Txt()) {
//                log.info(parseData.getDrcProInfo().getParseDataName() + ",数据存储到TXT失败");
//                CommTool.printInfo(parseData.getDrcProInfo().getParseDataName() + ",数据存储到TXT失败");
//                return;
//            }
//
//            CommTool.printInfo(parseData.getDrcProInfo().getParseDataName() + ",数据解析存储成功");
//        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        System.err.println(parseData.getDrcProInfo().getParseDataName() + "--------数据读取完毕----------");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
//        System.err.println(parseData.getDrcProInfo().getParseDataName() + "--------数据读异常----------: ");
        cause.printStackTrace();
        ctx.close();
    }
}
