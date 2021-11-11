package com.ruoyi.wxcxc.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxcxc.mapper.WxcxcProjectDeviceSensorDataMapper;
import com.ruoyi.wxcxc.domain.WxcxcProjectDeviceSensorData;
import com.ruoyi.wxcxc.service.IWxcxcProjectDeviceSensorDataService;

/**
 * 项目设备数据表Service业务层处理
 *
 * @author l62202
 * @date 2021-11-02
 */
@Service
public class WxcxcProjectDeviceSensorDataServiceImpl implements IWxcxcProjectDeviceSensorDataService {
    @Autowired
    private WxcxcProjectDeviceSensorDataMapper wxcxcProjectDeviceSensorDataMapper;

    @Override
    public List<WxcxcProjectDeviceSensorData> selectWxcxcProjectDeviceSensorDataList(Long deviceId, WxcxcProjectDeviceSensorData wxcxcProjectDeviceSensorData) {
        return wxcxcProjectDeviceSensorDataMapper.selectWxcxcProjectDeviceSensorDataList(deviceId, wxcxcProjectDeviceSensorData);
    }

    @Override
    public int insertWxcxcProjectDeviceSensorData(Long deviceId, WxcxcProjectDeviceSensorData wxcxcProjectDeviceSensorData) {
        return wxcxcProjectDeviceSensorDataMapper.insertWxcxcProjectDeviceSensorData(deviceId, wxcxcProjectDeviceSensorData);
    }

    @Override
    public int dropTable(Long deviceId) {
        return wxcxcProjectDeviceSensorDataMapper.dropTable(deviceId);
    }

    @Override
    public int createTable(Long deviceId) {
        return wxcxcProjectDeviceSensorDataMapper.createTable(deviceId);
    }
}
