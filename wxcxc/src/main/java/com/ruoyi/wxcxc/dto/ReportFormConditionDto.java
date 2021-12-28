package com.ruoyi.wxcxc.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ReportFormConditionDto {
    // 项目ID
    private Long projectId;

    // 表头
    private List<String> attrDataList;

    // 数据类型
    private String gatherType;

    // 时间段
    private String beginTime;
    private String endTime;
}
