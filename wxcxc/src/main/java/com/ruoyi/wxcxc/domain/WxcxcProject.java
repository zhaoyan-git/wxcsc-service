package com.ruoyi.wxcxc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目对象 wxcxc_project
 * 
 * @author l62202
 * @date 2021-10-17
 */
public class WxcxcProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String name;

    /** 所属企业 */
    @Excel(name = "所属企业")
    private Long businessId;

    /** 监控目的 */
    @Excel(name = "监控目的")
    private String aim;

    /** 项目图片 */
    @Excel(name = "项目图片")
    private String photoFile;

    /** 备注 */
    @Excel(name = "备注")
    private String details;

    /** 项目位置纬度 */
    @Excel(name = "项目位置纬度")
    private String positionLat;

    /** 项目位置经度 */
    @Excel(name = "项目位置经度")
    private String positionLng;

    /** 警报标志（0表示未告警 1表示告警） */
    @Excel(name = "警报标志", readConverterExp = "0=表示未告警,1=表示告警")
    private String alarmFlag;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

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
    public void setBusinessId(Long businessId) 
    {
        this.businessId = businessId;
    }

    public Long getBusinessId() 
    {
        return businessId;
    }
    public void setAim(String aim) 
    {
        this.aim = aim;
    }

    public String getAim() 
    {
        return aim;
    }
    public void setPhotoFile(String photoFile) 
    {
        this.photoFile = photoFile;
    }

    public String getPhotoFile() 
    {
        return photoFile;
    }
    public void setDetails(String details) 
    {
        this.details = details;
    }

    public String getDetails() 
    {
        return details;
    }
    public void setPositionLat(String positionLat) 
    {
        this.positionLat = positionLat;
    }

    public String getPositionLat() 
    {
        return positionLat;
    }
    public void setPositionLng(String positionLng) 
    {
        this.positionLng = positionLng;
    }

    public String getPositionLng() 
    {
        return positionLng;
    }
    public void setAlarmFlag(String alarmFlag) 
    {
        this.alarmFlag = alarmFlag;
    }

    public String getAlarmFlag() 
    {
        return alarmFlag;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("businessId", getBusinessId())
            .append("aim", getAim())
            .append("photoFile", getPhotoFile())
            .append("details", getDetails())
            .append("positionLat", getPositionLat())
            .append("positionLng", getPositionLng())
            .append("alarmFlag", getAlarmFlag())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
