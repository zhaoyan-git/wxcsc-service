package com.ruoyi.wxcxc.dto;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

@Data
public class WxcxcProjectPointGatherConfigDto extends BaseEntity {
    private Long id;

    private Long projectId;
    private Long structureId;

    /**
     * 所属测点
     */
    private Long pointId;

    /**
     * 计算方式
     */
    private String calcType;

    /**
     * 周期
     */
    private String cycle;
}
