package com.ruoyi.wxcxc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 聚集配置对象 wxcxc_project_point_gather_config
 * 
 * @author l62202
 * @date 2021-12-06
 */
public class WxcxcProjectPointGatherConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 所属测点 */
    @Excel(name = "所属测点")
    private Long pointId;

    /** 计算方式 */
    @Excel(name = "计算方式")
    private String calcType;

    /** 周期 */
    @Excel(name = "周期")
    private String cycle;

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
    public void setCalcType(String calcType) 
    {
        this.calcType = calcType;
    }

    public String getCalcType() 
    {
        return calcType;
    }
    public void setCycle(String cycle)
    {
        this.cycle = cycle;
    }

    public String getCycle()
    {
        return cycle;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pointId", getPointId())
            .append("calcType", getCalcType())
            .append("cycle ", getCycle())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
