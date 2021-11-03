package com.ruoyi.wxcxc.dto;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.wxcxc.domain.WxcxcProjectPoint;
import lombok.Data;

import java.util.List;

@Data
public class ProjectPointGroupDto extends BaseEntity {
    private Long id;

    private String name;

    private Long structureId;

    private List<WxcxcProjectPoint> children;
}
