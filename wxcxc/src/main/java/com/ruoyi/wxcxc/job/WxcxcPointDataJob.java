package com.ruoyi.wxcxc.job;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.wxcxc.domain.*;
import com.ruoyi.wxcxc.mapper.WxcxcDataPlatformMapper;
import com.ruoyi.wxcxc.mapper.WxcxcProjectDeviceSensorDataMapper;
import com.ruoyi.wxcxc.mapper.WxcxcProjectPointDataMapper;
import com.ruoyi.wxcxc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("wxcxcPointDataJob")
public class WxcxcPointDataJob {
    @Autowired
    private IWxcxcProjectPointService wxcxcProjectPointService;
    @Autowired
    private WxcxcProjectDeviceSensorDataMapper wxcxcProjectDeviceSensorDataMapper;
    @Autowired
    private IWxcxcDeviceSensorService wxcxcDeviceSensorService;
    @Autowired
    private WxcxcProjectPointDataMapper wxcxcProjectPointDataMapper;
    @Autowired
    private IWxcxcProjectAlarmConfigService wxcxcProjectAlarmConfigService;
    @Autowired
    private IWxcxcProjectAlarmRecordService wxcxcProjectAlarmRecordService;


    // 获取测点数据
    public void getPointData() {
        // 获取全部测点
        List<WxcxcProjectPoint> pointList = wxcxcProjectPointService.selectWxcxcProjectPointList(new WxcxcProjectPoint());

        // 根据测点配置获取数据
        if (null != pointList) {
            for (WxcxcProjectPoint itemPoint : pointList) {
                WxcxcProjectPointData wxcxcProjectPointData = new WxcxcProjectPointData();
                // 计算测点数据
                switch (itemPoint.getMonitorType()) {
                    case "1":
                        // 沉降
                        JSONObject monitorData = JSONObject.parseObject(itemPoint.getMonitorData());

                        // 读取数据来源
                        Long dataSourceSensor = monitorData.getLong("data_source_sensor");

                        // 读取基准值数据来源
                        String type = monitorData.getString("type");

                        // 获取该传感器
                        WxcxcDeviceSensor wxcxcDeviceSensor = wxcxcDeviceSensorService.selectWxcxcDeviceSensorById(dataSourceSensor);
                        WxcxcProjectDeviceSensorData wxcxcProjectDeviceSensorData =
                                wxcxcProjectDeviceSensorDataMapper.selectWxcxcProjectDeviceSensorDataBySensorId(wxcxcDeviceSensor.getDeviceId(),
                                        wxcxcDeviceSensor.getId(), null, null, 0l);

                        switch (type) {
                            case "1":
                                // 基准点
                                // 读取基准点
                                Long dataSourceStandardSensor = monitorData.getLong("data_source_standard_sensor");
                                WxcxcDeviceSensor wxcxcDataSourceStandardSensor = wxcxcDeviceSensorService.selectWxcxcDeviceSensorById(dataSourceSensor);
                                WxcxcProjectDeviceSensorData wxcxcDataSourceStandardSensorData =
                                        wxcxcProjectDeviceSensorDataMapper.selectWxcxcProjectDeviceSensorDataBySensorId(wxcxcDeviceSensor.getDeviceId(),
                                                wxcxcDeviceSensor.getId(), null, null, 0l);

                                wxcxcProjectPointData.setData(
                                        String.valueOf(
                                                Long.parseLong(wxcxcProjectDeviceSensorData.getData()) - Long.parseLong(wxcxcDataSourceStandardSensorData.getData())
                                        )
                                );

                                break;
                            case "2":
                                // 初始值
                                // 读取初始值
                                Long dataSourceStandardValue = monitorData.getLong("data_source_standard_value");

                                wxcxcProjectPointData.setData(
                                        String.valueOf(Long.parseLong(wxcxcProjectDeviceSensorData.getData()) - dataSourceStandardValue)
                                );
                                break;
                        }
                        break;
                }

                // 数据获取异常
                if (null == wxcxcProjectPointData.getData()) continue;

                // 保存测点数据
                wxcxcProjectPointData.setPointId(itemPoint.getId());
                wxcxcProjectPointDataMapper.inserttWxcxcProjectPointData(itemPoint.getProjectStructureId(), wxcxcProjectPointData);

                // 计算报警策略
                // 获取报警策略
                WxcxcProjectAlarmConfig wxcxcProjectAlarmConfigCondition = new WxcxcProjectAlarmConfig();
                wxcxcProjectAlarmConfigCondition.setPointId(itemPoint.getId());
                List<WxcxcProjectAlarmConfig> wxcxcProjectAlarmConfigList =
                        wxcxcProjectAlarmConfigService.selectWxcxcProjectAlarmConfigList(wxcxcProjectAlarmConfigCondition);

                if (null != wxcxcProjectAlarmConfigList) {
                    for (WxcxcProjectAlarmConfig alarmConfigItem : wxcxcProjectAlarmConfigList) {
                        boolean flag = false;

                        switch (alarmConfigItem.getComputeType()) {
                            case "1":
                                if (Long.parseLong(wxcxcProjectPointData.getData()) >= alarmConfigItem.getThreshold()) {
                                    // 发送通知
                                    flag = true;
                                }
                                break;
                            case "2":
                                if (Long.parseLong(wxcxcProjectPointData.getData()) <= alarmConfigItem.getThreshold()) {
                                    // 发送通知
                                    flag = true;
                                }
                                break;
                        }

                        if (flag) {
                            // 发送通知
                            wxcxcProjectAlarmRecordService.sendAlarm(itemPoint.getId(), alarmConfigItem.getId());
                        }
                    }
                }
            }
        }
    }
}
