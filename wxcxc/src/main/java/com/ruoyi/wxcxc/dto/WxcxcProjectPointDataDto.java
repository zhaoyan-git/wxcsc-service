package com.ruoyi.wxcxc.dto;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class WxcxcProjectPointDataDto extends BaseEntity {
    private Long id;

    //所属结构物
    private Long structureId;

    // 所属测点
    private Long pointId;

    // 数据
    private String data;

    // 数据类型 【1-实时数据】 【2-聚集数据】
    private String gatherType;

    // 测点
    private List<Long> pointIds;
}
