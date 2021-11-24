package com.ruoyi.wxcxc.dto;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.wxcxc.domain.WxcxcProjectPoint;
import lombok.Data;

@Data
public class WxcxcProjectAlarmConfigDto extends BaseEntity {
    private Long id;

    /**
     * 所属测点
     */
    private Long pointId;

    // 告警等级
    private String alarmLevel;

    /**
     * 阈值
     */
    private Long threshold;

    /**
     * 计算方式
     */
    private String computeType;

    /**
     * 报警标题
     */
    private String title;

    /**
     * 报警内容
     */
    private String content;

    // 所属项目
    private Long projectId;

    // 所属结构物
    private Long structureId;

    // 所属测点
    private WxcxcProjectPoint point;

}
