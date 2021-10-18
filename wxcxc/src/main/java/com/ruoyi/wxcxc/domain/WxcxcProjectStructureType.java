package com.ruoyi.wxcxc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 结构物选项对象 wxcx_project_structure_type
 * 
 * @author l62202
 * @date 2021-10-16
 */
public class WxcxcProjectStructureType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 结构物选项ID */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 示意图 */
    @Excel(name = "示意图")
    private String photo;

    /** 是否禁用（0表示未禁用 1表示禁用） */
    @Excel(name = "是否禁用", readConverterExp = "0=表示未禁用,1=表示禁用")
    private String disableFlag;

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
    public void setPhoto(String photo) 
    {
        this.photo = photo;
    }

    public String getPhoto() 
    {
        return photo;
    }
    public void setDisableFlag(String disableFlag) 
    {
        this.disableFlag = disableFlag;
    }

    public String getDisableFlag() 
    {
        return disableFlag;
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
            .append("photo", getPhoto())
            .append("disableFlag", getDisableFlag())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
