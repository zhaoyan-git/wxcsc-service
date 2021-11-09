package com.ruoyi.wxcxc.job;

import com.ruoyi.wxcxc.domain.WxcxcBusiness;
import com.ruoyi.wxcxc.domain.WxcxcDataBusiness;
import com.ruoyi.wxcxc.domain.WxcxcDevice;
import com.ruoyi.wxcxc.mapper.WxcxcConsoleMapper;
import com.ruoyi.wxcxc.mapper.WxcxcDeviceMapper;
import com.ruoyi.wxcxc.mapper.WxcxcProjectDeviceSensorDataMapper;
import com.ruoyi.wxcxc.service.IWxcxcBusinessService;
import com.ruoyi.wxcxc.service.IWxcxcDataBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("wxcxcDataBusinessJob")
public class WxcxcDataBusinessJob {
    @Autowired
    private IWxcxcBusinessService wxcxcBusinessService;
    @Autowired
    private IWxcxcDataBusinessService wxcxcDataBusinessService;
    @Autowired
    private WxcxcConsoleMapper wxcxcConsoleMapper;
    @Autowired
    private WxcxcDeviceMapper wxcxcDeviceMapper;
    @Autowired
    private WxcxcProjectDeviceSensorDataMapper wxcxcProjectDeviceSensorDataMapper;

    public void getData() {
        // 获取全部企业
        List<WxcxcBusiness> businessList = wxcxcBusinessService.selectWxcxcBusinessList(new WxcxcBusiness());

        if (null != businessList) {
            for (WxcxcBusiness item : businessList) {
                WxcxcDataBusiness wxcxcDataBusiness = new WxcxcDataBusiness();
                wxcxcDataBusiness.setBusinessId(item.getId());

                int dataCount = 0;
                List<WxcxcDevice> wxcxcDevicesList = wxcxcDeviceMapper.selectWxcxcDeviceListByBusinessId(item.getId());
                if (null != wxcxcDevicesList) {
                    for (WxcxcDevice wxcxcDeviceItem : wxcxcDevicesList) {
                        dataCount += wxcxcProjectDeviceSensorDataMapper.getDataCount(wxcxcDeviceItem.getId());
                    }
                }

                // 获取数据总数
                wxcxcDataBusiness.setDataCount(String.valueOf(dataCount));
                // 获取发布项目数
                wxcxcDataBusiness.setProjectCount(String.valueOf(wxcxcConsoleMapper.getProjectCount(item.getId())));
                // 获取结构物数
                wxcxcDataBusiness.setStructureCount(String.valueOf(wxcxcConsoleMapper.getStructureCount(item.getId())));
                // 获取设备总数
                wxcxcDataBusiness.setDeviceCount(String.valueOf(wxcxcConsoleMapper.getDeviceCount(item.getId())));

                wxcxcDataBusinessService.insertWxcxcDataBusiness(wxcxcDataBusiness);
            }
        }
    }
}
