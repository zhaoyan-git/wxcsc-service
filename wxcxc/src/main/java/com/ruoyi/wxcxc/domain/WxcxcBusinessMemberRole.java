package com.ruoyi.wxcxc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 企业人员权限对象 wxcxc_business_member_role
 * 
 * @author l62202
 * @date 2021-10-17
 */
public class WxcxcBusinessMemberRole extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 企业人员id */
    @Excel(name = "企业人员id")
    private Long businessMemberId;

    /** 项目id */
    @Excel(name = "项目id")
    private Long projectId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBusinessMemberId(Long businessMemberId) 
    {
        this.businessMemberId = businessMemberId;
    }

    public Long getBusinessMemberId() 
    {
        return businessMemberId;
    }
    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("businessMemberId", getBusinessMemberId())
            .append("projectId", getProjectId())
            .toString();
    }
}
