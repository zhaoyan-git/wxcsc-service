package com.ruoyi.wxcxc.netty;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

import com.ruoyi.wxcxc.domain.WxcxcDevice;
import com.ruoyi.wxcxc.domain.WxcxcDeviceGateway;
import com.ruoyi.wxcxc.domain.WxcxcDeviceSensor;
import com.ruoyi.wxcxc.domain.WxcxcNetty;
import com.ruoyi.wxcxc.dto.ModbusRtuOrderDto;
import com.ruoyi.wxcxc.service.IWxcxcDeviceGatewayService;
import com.ruoyi.wxcxc.service.IWxcxcDeviceSensorService;
import com.ruoyi.wxcxc.service.IWxcxcDeviceService;
import com.ruoyi.wxcxc.service.IWxcxcNettyService;
import com.ruoyi.wxcxc.util.CRCUtils;
import com.ruoyi.wxcxc.util.HexUtil;
import com.ruoyi.wxcxc.util.ModbusRtuOrderList;
import com.ruoyi.wxcxc.util.SpringContextUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class BootNettyChannelInboundHandlerAdapter extends ChannelInboundHandlerAdapter {


    /**
     * 从客户端收到新的数据时，这个方法会在收到消息时被调用
     *
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception, IOException {
        // 读取数据
        ByteBuf buf = (ByteBuf) msg;
        byte[] msgByte = new byte[buf.readableBytes()];
        buf.readBytes(msgByte);

        // 获取IP
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();

        // DTU 首次登录包
        // 长度21B
        if (21 == msgByte.length) {
            // DTU ID
            String dtuId = "";
            // 反转得到正确DTU ID
            dtuId += HexUtil.byteToHex(Arrays.copyOfRange(msgByte, 3, 4));
            dtuId += HexUtil.byteToHex(Arrays.copyOfRange(msgByte, 2, 3));
            dtuId += HexUtil.byteToHex(Arrays.copyOfRange(msgByte, 1, 2));
            dtuId += HexUtil.byteToHex(Arrays.copyOfRange(msgByte, 0, 1));
            // PHONE_NUMBER
            String phoneNumber = new String(Arrays.copyOfRange(msgByte, 4, 15), "gb2312");
            // BLANK
            String blank = HexUtil.byteToHex(Arrays.copyOfRange(msgByte, 15, 16));
            // IP_ADD
            String ipAdd = "";
            ipAdd += Integer.parseInt(HexUtil.byteToHex(Arrays.copyOfRange(msgByte, 16, 17)), 16) + ".";
            ipAdd += Integer.parseInt(HexUtil.byteToHex(Arrays.copyOfRange(msgByte, 17, 18)), 16) + ".";
            ipAdd += Integer.parseInt(HexUtil.byteToHex(Arrays.copyOfRange(msgByte, 18, 19)), 16) + ".";
            ipAdd += Integer.parseInt(HexUtil.byteToHex(Arrays.copyOfRange(msgByte, 19, 20)), 16);
            // ETX
            String etx = HexUtil.byteToHex(Arrays.copyOfRange(msgByte, 20, 21));

            System.out.println(HexUtil.byteToHex(msgByte));
            System.out.println(dtuId);
            System.out.println(phoneNumber);
            System.out.println(ipAdd);

            if (!"00".equals(blank) || !"00".equals(etx)) {
                // 不符合结构
                ctx.close();
                return;
            }

            IWxcxcDeviceGatewayService wxcxcDeviceGatewayService = (IWxcxcDeviceGatewayService) SpringContextUtil.getBean(IWxcxcDeviceGatewayService.class);
            WxcxcDeviceGateway wxcxcDeviceGatewayCondition = new WxcxcDeviceGateway();
            wxcxcDeviceGatewayCondition.setDtuId(dtuId);
            wxcxcDeviceGatewayCondition.setSimNumber(phoneNumber);
            List<WxcxcDeviceGateway> wxcxcDeviceGatewayList = wxcxcDeviceGatewayService.selectWxcxcDeviceGatewayList(wxcxcDeviceGatewayCondition);

            // 在系统中查询是否有该DTU
            if (null != wxcxcDeviceGatewayList) {
                WxcxcDeviceGateway wxcxcDeviceGateway = null;

                for (WxcxcDeviceGateway item : wxcxcDeviceGatewayList) {
                    wxcxcDeviceGateway = item;
                }

                if (null != wxcxcDeviceGateway) {
                    wxcxcDeviceGateway.setIp(clientIp);
                    wxcxcDeviceGateway.setOnlineFlag("1");
                    wxcxcDeviceGatewayService.updateWxcxcDeviceGateway(wxcxcDeviceGateway);

                    return;
                } else {
                    // 不符合结构
                    ctx.close();
                    return;
                }
            } else {
                // 不符合结构
                ctx.close();
                return;
            }

        }

        // DTU 心跳包
        else if (1 == msgByte.length) {
            if ("fe".equals(HexUtil.byteToHex(Arrays.copyOfRange(msgByte, 0, 1)))) {
                IWxcxcDeviceGatewayService wxcxcDeviceGatewayService = (IWxcxcDeviceGatewayService) SpringContextUtil.getBean(IWxcxcDeviceGatewayService.class);
                IWxcxcDeviceService wxcxcDeviceService = (IWxcxcDeviceService) SpringContextUtil.getBean(IWxcxcDeviceService.class);
                IWxcxcDeviceSensorService wxcxcDeviceSensorService = (IWxcxcDeviceSensorService) SpringContextUtil.getBean(IWxcxcDeviceSensorService.class);

                // 根据Ip获取DTU
                WxcxcDeviceGateway wxcxcDeviceGateway = getDeviceGatewayByIp(clientIp);

                // 查询该DTU下连接的全部设备
                WxcxcDevice wxcxcDeviceCondition = new WxcxcDevice();
                wxcxcDeviceCondition.setGatewayId(wxcxcDeviceGateway.getId());
                List<WxcxcDevice> wxcxcDeviceList = wxcxcDeviceService.selectWxcxcDeviceList(wxcxcDeviceCondition);

                if (null != wxcxcDeviceList) {
                    for (WxcxcDevice deviceItem : wxcxcDeviceList) {
                        // 获取该设备全部传感器
                        WxcxcDeviceSensor wxcxcDeviceSensorCondition = new WxcxcDeviceSensor();
                        wxcxcDeviceSensorCondition.setDeviceId(deviceItem.getId());
                        List<WxcxcDeviceSensor> wxcxcDeviceSensorList = wxcxcDeviceSensorService.selectWxcxcDeviceSensorList(wxcxcDeviceSensorCondition);

                        if (null != wxcxcDeviceSensorList) {
                            //待发送的指令
                            ModbusRtuOrderList modbusRtuOrderList = new ModbusRtuOrderList();

                            for (WxcxcDeviceSensor deviceSensorItem : wxcxcDeviceSensorList) {
                                // 添加指令
                                modbusRtuOrderList.addOrder(deviceSensorItem.getSlaveUnitId(), deviceSensorItem.getSlaveCode(),
                                        deviceSensorItem.getSlaveAddressHi(), deviceSensorItem.getSlaveAddressLo(),
                                        deviceSensorItem.getSlaveAmountHi(), deviceSensorItem.getSlaveAmountLo());

//                                String requestByte = "";
//                                // 设备地址
//                                requestByte += deviceSensorItem.getSlaveUnitId();
//                                // 功能码
//                                requestByte += deviceSensorItem.getSlaveCode();
//                                // 起始寄存器地址
//                                requestByte += deviceSensorItem.getSlaveAddressHi() + deviceSensorItem.getSlaveAddressLo();
//                                // 读取寄存器个数
//                                requestByte += deviceSensorItem.getSlaveAmountHi() + deviceSensorItem.getSlaveAmountLo();
//
//                                requestByte += CRCUtils.getCRC(HexUtil.hexToByte(requestByte));
                            }

                            for (ModbusRtuOrderDto item : modbusRtuOrderList.getModbusRtuOrderDtoList()) {
                                System.out.println("send:" + item.toString());
                                ctx.write(item.toString());
                                ctx.flush();
                            }
                        }

                        return;
                    }
                }
            }
        } else {
            System.out.println("read:" + HexUtil.byteToHex(msgByte));
            // 接收
            // 根据IP判断网关

            // 在系统中查询是否有该DTU
//            IWxcxcDeviceGatewayService wxcxcDeviceGatewayService = (IWxcxcDeviceGatewayService) SpringContextUtil.getBean(IWxcxcDeviceGatewayService.class);
//            WxcxcDeviceGateway wxcxcDeviceGatewayCondition = new WxcxcDeviceGateway();
//            wxcxcDeviceGatewayCondition.setIp(clientIp);
//            wxcxcDeviceGatewayCondition.setOnlineFlag("1");
//            List<WxcxcDeviceGateway> wxcxcDeviceGatewayList = wxcxcDeviceGatewayService.selectWxcxcDeviceGatewayList(wxcxcDeviceGatewayCondition);
//
//            IWxcxcDeviceSensorService wxcxcDeviceSensorService = (IWxcxcDeviceSensorService) SpringContextUtil.getBean(IWxcxcDeviceSensorService.class);
//
//            if (null != wxcxcDeviceGatewayList) {
//                WxcxcDeviceGateway wxcxcDeviceGateway = null;
//
//                for (WxcxcDeviceGateway item : wxcxcDeviceGatewayList) {
//                    wxcxcDeviceGateway = item;
//                }
//
//                if (null != wxcxcDeviceGateway) {
//                    // 根据【设备地址】及【功能码】判断传感器
//                    String slaveUnitId = byteToHex(Arrays.copyOfRange(msgByte, 0, 2));
//                    String slaveCode = byteToHex(Arrays.copyOfRange(msgByte, 2, 4));
//
//                    // 根据【设备地址】【功能码】获取传感器
//                    WxcxcDeviceSensor wxcxcDeviceSensor = new WxcxcDeviceSensor();
//                    List<WxcxcDeviceSensor> wxcxcDeviceSensorList = wxcxcDeviceSensorService.selectWxcxcDeviceSensorList(wxcxcDeviceSensor);
//
//                    // 根据寄存器地址和数量获取信息
//
//                    // ModBus 功能码0x01
//                    // ModBus 功能码0x02
//                    // ModBus 功能码0x03
//                    // ModBus 功能码0x04
//                    // ModBus 功能码0x05
//                    // ModBus 功能码0x06
//
//                    return;
//                } else {
//                    // 不符合结构
//                    ctx.close();
//                    return;
//                }
//            } else {
//                // 不符合结构
//                ctx.close();
//                return;
//            }


        }

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
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();
        cause.printStackTrace();
        ctx.close();//抛出异常，断开与客户端的连接

        // 根据IP获取DTU信息
        IWxcxcDeviceGatewayService wxcxcDeviceGatewayService = (IWxcxcDeviceGatewayService) SpringContextUtil.getBean(IWxcxcDeviceGatewayService.class);
        WxcxcDeviceGateway wxcxcDeviceGateway = getDeviceGatewayByIp(clientIp);
        wxcxcDeviceGateway.setOnlineFlag("0");
        wxcxcDeviceGatewayService.updateWxcxcDeviceGateway(wxcxcDeviceGateway);

        System.out.println("exceptionCaught:" + clientIp + ctx.name());
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
        ctx.channel().read();
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();
        //此处不能使用ctx.close()，否则客户端始终无法与服务端建立连接
        System.out.println("channelActive:" + clientIp + ctx.name());
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
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();
        ctx.close(); //断开连接时，必须关闭，否则造成资源浪费，并发量很大情况下可能造成宕机
        System.out.println("channelInactive:" + clientIp);

        // 根据IP获取DTU信息
        IWxcxcDeviceGatewayService wxcxcDeviceGatewayService = (IWxcxcDeviceGatewayService) SpringContextUtil.getBean(IWxcxcDeviceGatewayService.class);
        WxcxcDeviceGateway wxcxcDeviceGateway = getDeviceGatewayByIp(clientIp);
        wxcxcDeviceGateway.setOnlineFlag("0");
        wxcxcDeviceGatewayService.updateWxcxcDeviceGateway(wxcxcDeviceGateway);

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
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();
        ctx.close();//超时时断开连接
        System.out.println("userEventTriggered:" + clientIp);

        // 根据IP获取DTU信息
        IWxcxcDeviceGatewayService wxcxcDeviceGatewayService = (IWxcxcDeviceGatewayService) SpringContextUtil.getBean(IWxcxcDeviceGatewayService.class);
        WxcxcDeviceGateway wxcxcDeviceGateway = getDeviceGatewayByIp(clientIp);
        wxcxcDeviceGateway.setOnlineFlag("0");
        wxcxcDeviceGatewayService.updateWxcxcDeviceGateway(wxcxcDeviceGateway);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered");
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelWritabilityChanged");
    }

    public WxcxcDeviceGateway getDeviceGatewayByIp(String ip) {
        // 根据IP获取DTU信息
        IWxcxcDeviceGatewayService wxcxcDeviceGatewayService = (IWxcxcDeviceGatewayService) SpringContextUtil.getBean(IWxcxcDeviceGatewayService.class);
        WxcxcDeviceGateway wxcxcDeviceGatewayCondition = new WxcxcDeviceGateway();
        wxcxcDeviceGatewayCondition.setIp(ip);
        List<WxcxcDeviceGateway> wxcxcDeviceGatewayList = wxcxcDeviceGatewayService.selectWxcxcDeviceGatewayList(wxcxcDeviceGatewayCondition);

        if (null != wxcxcDeviceGatewayList) {
            for (WxcxcDeviceGateway item : wxcxcDeviceGatewayList) {
                return item;
            }
        }

        return null;
    }
}
