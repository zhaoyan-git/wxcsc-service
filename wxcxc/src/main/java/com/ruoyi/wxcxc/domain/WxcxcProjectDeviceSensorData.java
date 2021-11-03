package com.ruoyi.wxcxc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目设备数据表对象 wxcxc_project_device_sensor_data
 * 
 * @author l62202
 * @date 2021-11-02
 */
public class WxcxcProjectDeviceSensorData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 所属传感器 */
    @Excel(name = "所属传感器")
    private Long sensorId;

    /** 原始数据 */
    @Excel(name = "原始数据")
    private String originalData;

    /** 转义后数据 */
    @Excel(name = "转义后数据")
    private String data;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSensorId(Long sensorId) 
    {
        this.sensorId = sensorId;
    }

    public Long getSensorId() 
    {
        return sensorId;
    }
    public void setOriginalData(String originalData) 
    {
        this.originalData = originalData;
    }

    public String getOriginalData() 
    {
        return originalData;
    }
    public void setData(String data) 
    {
        this.data = data;
    }

    public String getData() 
    {
        return data;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sensorId", getSensorId())
            .append("originalData", getOriginalData())
            .append("data", getData())
            .append("createTime", getCreateTime())
            .toString();
    }
}
