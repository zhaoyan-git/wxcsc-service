package com.ruoyi.wxcxc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 报警配置对象 wxcxc_project_alarm_config
 * 
 * @author l62202
 * @date 2021-11-11
 */
public class WxcxcProjectAlarmConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 所属测点 */
    @Excel(name = "所属测点")
    private Long pointId;

    // 告警等级
    private String alarmLevel;

    /** 阈值 */
    @Excel(name = "阈值")
    private Long threshold;

    /** 计算方式 */
    @Excel(name = "计算方式")
    private String computeType;

    /** 报警标题 */
    @Excel(name = "报警标题")
    private String title;

    /** 报警内容 */
    @Excel(name = "报警内容")
    private String content;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPointId(Long pointId) 
    {
        this.pointId = pointId;
    }

    public Long getPointId() 
    {
        return pointId;
    }
    public void setThreshold(Long threshold) 
    {
        this.threshold = threshold;
    }

    public Long getThreshold() 
    {
        return threshold;
    }
    public void setComputeType(String computeType) 
    {
        this.computeType = computeType;
    }

    public String getComputeType() 
    {
        return computeType;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pointId", getPointId())
            .append("alarmLevel", getAlarmLevel())
            .append("threshold", getThreshold())
            .append("computeType", getComputeType())
            .append("title", getTitle())
            .append("content", getContent())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
