package com.ruoyi.wxcxc.job;

import com.ruoyi.wxcxc.domain.WxcxcBusiness;
import com.ruoyi.wxcxc.domain.WxcxcDataBusiness;
import com.ruoyi.wxcxc.domain.WxcxcDataPlatform;
import com.ruoyi.wxcxc.domain.WxcxcDevice;
import com.ruoyi.wxcxc.mapper.WxcxcConsoleMapper;
import com.ruoyi.wxcxc.mapper.WxcxcDataPlatformMapper;
import com.ruoyi.wxcxc.mapper.WxcxcDeviceMapper;
import com.ruoyi.wxcxc.mapper.WxcxcProjectDeviceSensorDataMapper;
import com.ruoyi.wxcxc.service.IWxcxcBusinessService;
import com.ruoyi.wxcxc.service.IWxcxcDataBusinessService;
import com.ruoyi.wxcxc.service.IWxcxcDataPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("wxcxcDataPlatformJob")
public class WxcxcDataPlatformJob {
    @Autowired
    private WxcxcDataPlatformMapper wxcxcDataPlatformMapper;
    @Autowired
    private IWxcxcDataPlatformService wxcxcDataPlatformService;

    public void getData() {
        WxcxcDataPlatform wxcxcDataPlatform = new WxcxcDataPlatform();

        // 入驻企业数量
        wxcxcDataPlatform.setBusinessCount(String.valueOf(wxcxcDataPlatformMapper.getPlatformBusinessCount()));
        // 平台总项目数
        wxcxcDataPlatform.setProjectCount(String.valueOf(wxcxcDataPlatformMapper.getPlatformProjectCount()));
        // 平台设备数
        wxcxcDataPlatform.setDeviceCount(String.valueOf(wxcxcDataPlatformMapper.getPlatformDeviceCount()));
        // 平台DTU数
        wxcxcDataPlatform.setDtuCount(String.valueOf(wxcxcDataPlatformMapper.getPlatformDeviceGatewayCount()));

        wxcxcDataPlatformService.insertWxcxcDataPlatform(wxcxcDataPlatform);
    }
}
