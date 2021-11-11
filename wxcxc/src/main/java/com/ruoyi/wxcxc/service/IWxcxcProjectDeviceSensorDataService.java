package com.ruoyi.wxcxc.service;

import java.util.List;

import com.ruoyi.wxcxc.domain.WxcxcProjectDeviceSensorData;
import org.apache.ibatis.annotations.Param;

/**
 * 项目设备数据表Service接口
 *
 * @author l62202
 * @date 2021-11-02
 */
public interface IWxcxcProjectDeviceSensorDataService {
    List<WxcxcProjectDeviceSensorData> selectWxcxcProjectDeviceSensorDataList(Long deviceId, WxcxcProjectDeviceSensorData wxcxcProjectDeviceSensorData);

    int insertWxcxcProjectDeviceSensorData(Long deviceId, WxcxcProjectDeviceSensorData wxcxcProjectDeviceSensorData);

    int dropTable(Long deviceId);

    int createTable(Long deviceId);
}
