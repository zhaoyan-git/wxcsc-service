package com.ruoyi.wxcxc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 企业数据对象 wxcxc_data_business
 * 
 * @author l62202
 * @date 2021-11-03
 */
public class WxcxcDataBusiness extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 企业ID */
    @Excel(name = "企业ID")
    private Long businessId;

    /** 数据总量 */
    @Excel(name = "数据总量")
    private String dataCount;

    /** 发布项目数 */
    @Excel(name = "发布项目数")
    private String projectCount;

    /** 结构物数 */
    @Excel(name = "结构物数")
    private String structureCount;

    /** 设备总数 */
    @Excel(name = "设备总数")
    private String deviceCount;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBusinessId(Long businessId) 
    {
        this.businessId = businessId;
    }

    public Long getBusinessId() 
    {
        return businessId;
    }
    public void setDataCount(String dataCount) 
    {
        this.dataCount = dataCount;
    }

    public String getDataCount() 
    {
        return dataCount;
    }
    public void setProjectCount(String projectCount) 
    {
        this.projectCount = projectCount;
    }

    public String getProjectCount() 
    {
        return projectCount;
    }
    public void setStructureCount(String structureCount) 
    {
        this.structureCount = structureCount;
    }

    public String getStructureCount() 
    {
        return structureCount;
    }
    public void setDeviceCount(String deviceCount) 
    {
        this.deviceCount = deviceCount;
    }

    public String getDeviceCount() 
    {
        return deviceCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("businessId", getBusinessId())
            .append("dataCount", getDataCount())
            .append("projectCount", getProjectCount())
            .append("structureCount", getStructureCount())
            .append("deviceCount", getDeviceCount())
            .append("createTime", getCreateTime())
            .toString();
    }
}
