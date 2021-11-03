package com.ruoyi.wxcxc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备网关对象 wxcxc_device_gateway
 * 
 * @author l62202
 * @date 2021-10-31
 */
public class WxcxcDeviceGateway extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String name;

    /** 所属项目 */
    @Excel(name = "所属项目")
    private Long projectId;

    /** 厂商 */
    @Excel(name = "厂商")
    private String manufacturer;

    /** 型号 */
    @Excel(name = "型号")
    private String modelNumber;

    /** 设备说明 */
    @Excel(name = "设备说明")
    private String detail;

    /** sim卡号 */
    @Excel(name = "sim卡号")
    private String simNumber;

    /** DTU ID */
    @Excel(name = "DTU ID")
    private String dtuId;

    /** 是否在线 */
    @Excel(name = "是否在线")
    private String onlineFlag;

    /** 是否在线 */
    @Excel(name = "ip")
    private String ip;

    /** 设备纬度 */
    @Excel(name = "设备纬度")
    private String positionLat;

    /** 设备经度 */
    @Excel(name = "设备经度")
    private String positionLng;

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
    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setManufacturer(String manufacturer) 
    {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() 
    {
        return manufacturer;
    }
    public void setModelNumber(String modelNumber) 
    {
        this.modelNumber = modelNumber;
    }

    public String getModelNumber() 
    {
        return modelNumber;
    }
    public void setDetail(String detail) 
    {
        this.detail = detail;
    }

    public String getDetail() 
    {
        return detail;
    }
    public void setSimNumber(String simNumber) 
    {
        this.simNumber = simNumber;
    }

    public String getSimNumber() 
    {
        return simNumber;
    }
    public void setDtuId(String dtuId) 
    {
        this.dtuId = dtuId;
    }

    public String getDtuId() 
    {
        return dtuId;
    }
    public void setOnlineFlag(String onlineFlag) 
    {
        this.onlineFlag = onlineFlag;
    }

    public String getOnlineFlag() 
    {
        return onlineFlag;
    }
    public void setPositionLat(String positionLat) 
    {
        this.positionLat = positionLat;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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
            .append("projectId", getProjectId())
            .append("manufacturer", getManufacturer())
            .append("modelNumber", getModelNumber())
            .append("detail", getDetail())
            .append("simNumber", getSimNumber())
            .append("dtuId", getDtuId())
            .append("onlineFlag", getOnlineFlag())
            .append("positionLat", getPositionLat())
            .append("positionLng", getPositionLng())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
