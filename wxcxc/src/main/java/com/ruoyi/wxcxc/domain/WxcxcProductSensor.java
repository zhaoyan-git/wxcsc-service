package com.ruoyi.wxcxc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品传感器管理对象 wxcxc_product_sensor
 * 
 * @author l62202
 * @date 2021-10-30
 */
public class WxcxcProductSensor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 所属产品id */
    @Excel(name = "所属产品id")
    private Long productId;

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

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productId", getProductId())
            .append("name", getName())
            .append("iconFile", getIconFile())
            .append("sort", getSort())
            .append("unit", getUnit())
            .append("type", getType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
