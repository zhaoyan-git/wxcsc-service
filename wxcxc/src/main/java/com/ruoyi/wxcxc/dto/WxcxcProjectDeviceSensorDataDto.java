package com.ruoyi.wxcxc.dto;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

@Data
public class WxcxcProjectDeviceSensorDataDto extends BaseEntity {
    private Long id;
    // 所属设备
    private Long deviceId;
    // 所属传感器
    private Long sensorId;
    // 原始数据
    private String originalData;
    // 转义数据
    private String data;
}
