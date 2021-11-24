package com.ruoyi.wxcxc.dto;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

@Data
public class WxcxcProjectPointDto extends BaseEntity {

    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 图片地址
     */
    private Long photoFile;

    /**
     * 所属结构物
     */
    private Long projectStructureId;

    /**
     * 所属分组
     */
    private Long pointGroupId;

    /**
     * 警报标志（0表示未告警 1表示告警）
     */
    private String alarmFlag;

    /**
     * 监测因素
     */
    private String monitorType;

    /**
     * 监测因素所需数据
     */
    private JSONObject monitorData;

}
