package com.ruoyi.wxcxc.dto;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

@Data
public class WxcxcProjectPointDataDto extends BaseEntity {
    private Long id;

    //所属结构物
    private Long structureId;

    // 所属测点
    private Long pointId;

    // 数据
    private String data;
}
