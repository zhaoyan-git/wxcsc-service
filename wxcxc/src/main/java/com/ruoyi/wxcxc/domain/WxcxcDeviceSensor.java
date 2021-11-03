package com.ruoyi.wxcxc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备传感器对象 wxcxc_device_sensor
 * 
 * @author l62202
 * @date 2021-10-31
 */
public class WxcxcDeviceSensor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 所属设备 */
    @Excel(name = "所属设备")
    private Long deviceId;

    /** 传感器名称 */
    @Excel(name = "传感器名称")
    private String name;

    /** 传感器图标 */
    @Excel(name = "传感器图标")
    private String iconFile;

    /** 排序编号 */
    @Excel(name = "排序编号")
    private Long sort;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 传感器类型 */
    @Excel(name = "传感器类型")
    private String type;

    /** 从站地址 */
    @Excel(name = "从站地址")
    private String slaveUnitId;

    /** 功能码 */
    @Excel(name = "功能码")
    private String slaveCode;

    /** 地址Hi */
    @Excel(name = "地址Hi")
    private String slaveAddressHi;

    /** 地址Lo */
    @Excel(name = "地址Lo")
    private String slaveAddressLo;

    /** 数量Hi */
    @Excel(name = "数量Hi")
    private String slaveAmountHi;

    /** 数量Lo */
    @Excel(name = "数量Lo")
    private String slaveAmountLo;

    /** 采集周期 */
    @Excel(name = "采集周期")
    private Long cycleTime;

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
    public void setDeviceId(Long deviceId) 
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId() 
    {
        return deviceId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setIconFile(String iconFile) 
    {
        this.iconFile = iconFile;
    }

    public String getIconFile() 
    {
        return iconFile;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setSlaveUnitId(String slaveUnitId) 
    {
        this.slaveUnitId = slaveUnitId;
    }

    public String getSlaveUnitId() 
    {
        return slaveUnitId;
    }
    public void setSlaveCode(String slaveCode) 
    {
        this.slaveCode = slaveCode;
    }

    public String getSlaveCode() 
    {
        return slaveCode;
    }
    public void setSlaveAddressHi(String slaveAddressHi) 
    {
        this.slaveAddressHi = slaveAddressHi;
    }

    public String getSlaveAddressHi() 
    {
        return slaveAddressHi;
    }
    public void setSlaveAddressLo(String slaveAddressLo) 
    {
        this.slaveAddressLo = slaveAddressLo;
    }

    public String getSlaveAddressLo() 
    {
        return slaveAddressLo;
    }
    public void setSlaveAmountHi(String slaveAmountHi) 
    {
        this.slaveAmountHi = slaveAmountHi;
    }

    public String getSlaveAmountHi() 
    {
        return slaveAmountHi;
    }
    public void setSlaveAmountLo(String slaveAmountLo) 
    {
        this.slaveAmountLo = slaveAmountLo;
    }

    public String getSlaveAmountLo() 
    {
        return slaveAmountLo;
    }
    public void setCycleTime(Long cycleTime) 
    {
        this.cycleTime = cycleTime;
    }

    public Long getCycleTime() 
    {
        return cycleTime;
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
            .append("deviceId", getDeviceId())
            .append("name", getName())
            .append("iconFile", getIconFile())
            .append("sort", getSort())
            .append("unit", getUnit())
            .append("type", getType())
            .append("slaveUnitId", getSlaveUnitId())
            .append("slaveCode", getSlaveCode())
            .append("slaveAddressHi", getSlaveAddressHi())
            .append("slaveAddressLo", getSlaveAddressLo())
            .append("slaveAmountHi", getSlaveAmountHi())
            .append("slaveAmountLo", getSlaveAmountLo())
            .append("cycleTime", getCycleTime())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
