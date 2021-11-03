package com.ruoyi.wxcxc.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品管理对象 wxcxc_product
 * 
 * @author l62202
 * @date 2021-10-30
 */
public class WxcxcProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private String type;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String name;

    /** 厂商 */
    @Excel(name = "厂商")
    private String manufacturer;

    /** 型号 */
    @Excel(name = "型号")
    private String modelNumber;

    /** 产品图片 */
    @Excel(name = "产品图片")
    private String photoFile;

    /** 产品说明 */
    @Excel(name = "产品说明")
    private String detail;

    /** 产品传感器管理信息 */
    private List<WxcxcProductSensor> wxcxcProductSensorList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
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

    public List<WxcxcProductSensor> getWxcxcProductSensorList()
    {
        return wxcxcProductSensorList;
    }

    public void setWxcxcProductSensorList(List<WxcxcProductSensor> wxcxcProductSensorList)
    {
        this.wxcxcProductSensorList = wxcxcProductSensorList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("name", getName())
            .append("manufacturer", getManufacturer())
            .append("modelNumber", getModelNumber())
            .append("photoFile", getPhotoFile())
            .append("detail", getDetail())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("wxcxcProductSensorList", getWxcxcProductSensorList())
            .toString();
    }
}
