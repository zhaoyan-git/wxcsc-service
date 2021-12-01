package com.ruoyi.wxcxc.dto;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.wxcxc.domain.WxcxcProjectDeviceSensorData;
import lombok.Data;

import java.util.List;

@Data
public class WxcxcProjectDeviceSensorDataListDto extends BaseEntity {
    private Long id;

    /** 所属设备 */
    private Long deviceId;

    /** 传感器名称 */
    private String name;

    /** 传感器图标 */
    private String iconFile;

    /** 排序编号 */
    private Long sort;

    /** 单位 */
    private String unit;

    /** 传感器类型 */
    private String type;

    // 传感器数据
    private List<WxcxcProjectDeviceSensorData> dataList;
}
