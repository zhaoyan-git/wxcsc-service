package com.ruoyi.wxcxc.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DataRecvServer {
    private int port; //接收数据端口
    private String proName; // 接收数据名称
    private ParseData parseData; //解析数据对象

    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workerGroup = new NioEventLoopGroup();

    public DataRecvServer(int port, String proName, ParseData parseData) {
        this.port = port;
        this.proName = proName;
        this.parseData = parseData;
    }

    public void start() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        //创建ServerBootstrap实例
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //初始化ServerBootstrap的线程组
        serverBootstrap.group(bossGroup, workerGroup);
        //设置将要被实例化的ServerChannel类
        serverBootstrap.channel(NioServerSocketChannel.class);
        //serverBootstrap.handler(new LoggingHandler(LogLevel.ERROR));
        //在ServerChannelInitializer中初始化ChannelPipeline责任链，并添加到serverBootstrap中
        serverBootstrap.childHandler(new ServerChannelInitializer(this.parseData));
        //标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        // 是否启用心跳保活机机制
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        //绑定端口后，开启监听
        /*ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
        if(channelFuture.isSuccess()){
            System.out.println("TCP服务启动 成功---------------");
        }*/

        ChannelFuture f = serverBootstrap.bind(port);
        f.addListener(future -> {
            if (future.isSuccess()) {
                System.out.printf("%s 开启端口 %s 成功\n", proName, port);
            } else {
                System.out.printf("%s 开启端口 %s 失败\n", proName, port);
            }
        });

    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public void setParseData(ParseData parseData) {
        this.parseData = parseData;
    }
}
