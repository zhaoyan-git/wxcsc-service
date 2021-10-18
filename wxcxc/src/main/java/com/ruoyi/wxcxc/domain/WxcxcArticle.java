package com.ruoyi.wxcxc.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文章对象 wxcxc_article
 * 
 * @author l2202
 * @date 2021-10-16
 */
public class WxcxcArticle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 简介 */
    @Excel(name = "简介")
    private String details;

    /** 文章内容 */
    @Excel(name = "文章内容")
    private String content;

    /** 标题图片 */
    @Excel(name = "标题图片")
    private String titlePhoto;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 是否发布 */
    @Excel(name = "是否发布")
    private String releaseFlag;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date releaseTime;

    /** 所属栏目id */
    @Excel(name = "所属栏目id")
    private Long channelId;

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
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setDetails(String details) 
    {
        this.details = details;
    }

    public String getDetails() 
    {
        return details;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setTitlePhoto(String titlePhoto) 
    {
        this.titlePhoto = titlePhoto;
    }

    public String getTitlePhoto() 
    {
        return titlePhoto;
    }
    public void setSort(Long sort)
    {
        this.sort = sort;
    }

    public Long getSort()
    {
        return sort;
    }
    public void setReleaseFlag(String releaseFlag) 
    {
        this.releaseFlag = releaseFlag;
    }

    public String getReleaseFlag() 
    {
        return releaseFlag;
    }
    public void setReleaseTime(Date releaseTime) 
    {
        this.releaseTime = releaseTime;
    }

    public Date getReleaseTime() 
    {
        return releaseTime;
    }
    public void setChannelId(Long channelId) 
    {
        this.channelId = channelId;
    }

    public Long getChannelId() 
    {
        return channelId;
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
            .append("title", getTitle())
            .append("details", getDetails())
            .append("content", getContent())
            .append("titlePhoto", getTitlePhoto())
            .append("sort ", getSort())
            .append("releaseFlag", getReleaseFlag())
            .append("releaseTime", getReleaseTime())
            .append("channelId", getChannelId())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
