package com.ruoyi.wxcxc.util;

import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.wxcxc.domain.WxcxcDeviceSensor;
import com.ruoyi.wxcxc.mapper.WxcxcDeviceSensorMapper;
import com.ruoyi.wxcxc.netty.BootNettyChannelInboundHandlerAdapter;
import io.netty.channel.Channel;

import java.util.List;

public class SendDataUtil {
    // 发送请求
    public static void sendRequest(Long gateWayId) {
        WxcxcDeviceSensorMapper wxcxcDeviceSensorMapper = (WxcxcDeviceSensorMapper) SpringContextUtil.getBean(WxcxcDeviceSensorMapper.class);
        RedisCache redisCache = (RedisCache) SpringContextUtil.getBean(RedisCache.class);

        if (null != gateWayId) {
            // 根据网关ID获取从站地址列表
            List<String> slaveUnitIdList = wxcxcDeviceSensorMapper.selectSlaveUnitIdByGatewayId(gateWayId);

            if (null != slaveUnitIdList) {
                CustomChannelId channelId = new CustomChannelId(redisCache.getCacheObject(gateWayId.toString()));

                if (null != channelId) {
                    for (String slaveUnitIdItem : slaveUnitIdList) {
                        if (null != slaveUnitIdItem) {
                            Integer amount = 0;


                            // 根据网关ID和从站地址获取传感器列表
                            List<WxcxcDeviceSensor> wxcxcDeviceSensorList = wxcxcDeviceSensorMapper.selectWxcxcDeviceSensorByDtuAndSlaveUnitId(slaveUnitIdItem, gateWayId);
                            if (null != wxcxcDeviceSensorList && 0 < wxcxcDeviceSensorList.size()) {
                                // 找到最大的
                                for (WxcxcDeviceSensor wxcxcDeviceSensorItem : wxcxcDeviceSensorList) {
                                    Integer addressItem = Integer.parseInt(wxcxcDeviceSensorItem.getSlaveAddressHi() + wxcxcDeviceSensorItem.getSlaveAddressLo(), 16);
                                    Integer amountItem = Integer.parseInt(wxcxcDeviceSensorItem.getSlaveAmountHi() + wxcxcDeviceSensorItem.getSlaveAmountLo(), 16);

                                    if (amount < addressItem + amountItem) {
                                        amount = addressItem + amountItem;
                                    }
                                }

                                // 发送
                                for (Channel channel : BootNettyChannelInboundHandlerAdapter.users) {
                                    if (channel.id().asLongText().equals(channelId.asLongText())) {
                                        String str = "";

                                        str += slaveUnitIdItem;
                                        str += "03";
                                        str += "0000";

                                        for (int i = 0; i < 4 - Integer.toHexString(amount).length(); i++) {
                                            str += "0";
                                        }

                                        str += Integer.toHexString(amount);
                                        str += CRCUtils.getCRC(HexUtil.hexToByte(str));

                                        channel.writeAndFlush(HexUtil.hexToByte(str));
                                        System.out.println("          send Ok! content:" + str);
                                    }
                                }


//                                Channel channel = BootNettyChannelInboundHandlerAdapter.users.find(channelId);
//                                if (null == channel) {
//                                    System.out.println("channel is null");
//                                } else {
//                                    channel.writeAndFlush(CRCUtils.getCRC((slaveUnitIdItem + "03" + "0000" + HexUtil.hexToByte(Integer.toHexString(amount))).getBytes()));
//
//                                }

                            }

                        }


                    }

                }


            }
        }

    }
}
