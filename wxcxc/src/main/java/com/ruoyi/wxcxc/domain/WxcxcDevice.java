package com.ruoyi.wxcxc.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备表对象 wxcxc_device
 * 
 * @author l62202
 * @date 2021-10-31
 */
public class WxcxcDevice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 所属项目 */
    @Excel(name = "所属项目")
    private Long projectId;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String name;

    /** 厂商 */
    @Excel(name = "厂商")
    private String manufacturer;

    /** 网关ID */
    @Excel(name = "网关ID")
    private Long gatewayId;

    /** 型号 */
    @Excel(name = "型号")
    private String modelNumber;

    /** 设备图片 */
    @Excel(name = "设备图片")
    private String photoFile;

    /** 设备说明 */
    @Excel(name = "设备说明")
    private String detail;

    /** 是否在线 */
    @Excel(name = "是否在线")
    private String onlineFlag;

    /** 是否报警 */
    @Excel(name = "是否报警")
    private String alarmFlag;

    /** 设备在地图上的纬度 */
    @Excel(name = "设备在地图上的纬度")
    private String positionLat;

    /** 设备在地图上的经度 */
    @Excel(name = "设备在地图上的经度")
    private String positionLng;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 设备传感器信息 */
    private List<WxcxcDeviceSensor> wxcxcDeviceSensorList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setManufacturer(String manufacturer) 
    {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() 
    {
        return manufacturer;
    }
    public void setGatewayId(Long gatewayId) 
    {
        this.gatewayId = gatewayId;
    }

    public Long getGatewayId() 
    {
        return gatewayId;
    }
    public void setModelNumber(String modelNumber) 
    {
        this.modelNumber = modelNumber;
    }

    public String getModelNumber() 
    {
        return modelNumber;
    }
    public void setPhotoFile(String photoFile) 
    {
        this.photoFile = photoFile;
    }

    public String getPhotoFile() 
    {
        return photoFile;
    }
    public void setDetail(String detail) 
    {
        this.detail = detail;
    }

    public String getDetail() 
    {
        return detail;
    }
    public void setOnlineFlag(String onlineFlag) 
    {
        this.onlineFlag = onlineFlag;
    }

    public String getOnlineFlag() 
    {
        return onlineFlag;
    }
    public void setAlarmFlag(String alarmFlag) 
    {
        this.alarmFlag = alarmFlag;
    }

    public String getAlarmFlag() 
    {
        return alarmFlag;
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
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public List<WxcxcDeviceSensor> getWxcxcDeviceSensorList()
    {
        return wxcxcDeviceSensorList;
    }

    public void setWxcxcDeviceSensorList(List<WxcxcDeviceSensor> wxcxcDeviceSensorList)
    {
        this.wxcxcDeviceSensorList = wxcxcDeviceSensorList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectId", getProjectId())
            .append("name", getName())
            .append("manufacturer", getManufacturer())
            .append("gatewayId", getGatewayId())
            .append("modelNumber", getModelNumber())
            .append("photoFile", getPhotoFile())
            .append("detail", getDetail())
            .append("onlineFlag", getOnlineFlag())
            .append("alarmFlag", getAlarmFlag())
            .append("positionLat", getPositionLat())
            .append("positionLng", getPositionLng())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("wxcxcDeviceSensorList", getWxcxcDeviceSensorList())
            .toString();
    }
}
