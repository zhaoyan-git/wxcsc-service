package com.ruoyi.wxcxc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目结构物对象 wxcxc_project_structure
 * 
 * @author l62202
 * @date 2021-10-17
 */
public class WxcxcProjectStructure extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 结构物类型 */
    @Excel(name = "结构物类型")
    private Long typeId;

    /** 图片 */
    @Excel(name = "图片")
    private Long photoFile;

    /** 所属项目 */
    @Excel(name = "所属项目")
    private Long projectId;

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
    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }
    public void setPhotoFile(Long photoFile) 
    {
        this.photoFile = photoFile;
    }

    public Long getPhotoFile() 
    {
        return photoFile;
    }
    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
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
            .append("typeId", getTypeId())
            .append("photoFile", getPhotoFile())
            .append("projectId", getProjectId())
            .append("alarmFlag", getAlarmFlag())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
