package com.ruoyi.wxcxc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 测点分组对象 wxcx_project_point_group
 * 
 * @author l62202
 * @date 2021-10-17
 */
public class WxcxProjectPointGroup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 分组名称 */
    @Excel(name = "分组名称")
    private String name;

    /** 所属结构物 */
    @Excel(name = "所属结构物")
    private Long structureId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setStructureId(Long structureId) 
    {
        this.structureId = structureId;
    }

    public Long getStructureId() 
    {
        return structureId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("structureId", getStructureId())
            .toString();
    }
}
