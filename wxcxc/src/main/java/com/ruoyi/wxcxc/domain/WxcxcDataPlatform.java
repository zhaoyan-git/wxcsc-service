package com.ruoyi.wxcxc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 平台数据对象 wxcxc_data_platform
 * 
 * @author l62202
 * @date 2021-11-03
 */
public class WxcxcDataPlatform extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 入驻企业数量 */
    @Excel(name = "入驻企业数量")
    private String businessCount;

    /** 平台总项目数 */
    @Excel(name = "平台总项目数")
    private String projectCount;

    /** 平台设备数 */
    @Excel(name = "平台设备数")
    private String deviceCount;

    /** 平台DTU数 */
    @Excel(name = "平台DTU数")
    private String dtuCount;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBusinessCount(String businessCount) 
    {
        this.businessCount = businessCount;
    }

    public String getBusinessCount() 
    {
        return businessCount;
    }
    public void setProjectCount(String projectCount) 
    {
        this.projectCount = projectCount;
    }

    public String getProjectCount() 
    {
        return projectCount;
    }
    public void setDeviceCount(String deviceCount) 
    {
        this.deviceCount = deviceCount;
    }

    public String getDeviceCount() 
    {
        return deviceCount;
    }
    public void setDtuCount(String dtuCount) 
    {
        this.dtuCount = dtuCount;
    }

    public String getDtuCount() 
    {
        return dtuCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("businessCount", getBusinessCount())
            .append("projectCount", getProjectCount())
            .append("deviceCount", getDeviceCount())
            .append("dtuCount", getDtuCount())
            .append("createTime", getCreateTime())
            .toString();
    }
}
