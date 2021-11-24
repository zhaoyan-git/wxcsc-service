package com.ruoyi.wxcxc.dto;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

@Data
public class ReportFormDto {
    // 所属项目
    @Excel(name = "所属项目")
    private String projectName;

    // 所属结构物
    @Excel(name = "所属结构物")
    private String structureName;

    // 测点名称
    @Excel(name = "测点名称")
    private String pointName;

    // 上次沉降值（mm）
    @Excel(name = "上次沉降值（mm）")
    private String lastValue;

    // 当前沉降值（mm）
    @Excel(name = "当前沉降值（mm）")
    private String currentValue;

    // 变化速率（mm/d）
    // 控制值累计变化值（mm）
    @Excel(name = "控制值累计变化值（mm）")
    private String controlValue;

    // 控制值变化速率值（mm/d）
    // 本次监测时间
    @Excel(name = "本次监测时间")
    private String lastDataTime;

    // 上次检测时间
    @Excel(name = "上次检测时间")
    private String currentDataTime;
}
