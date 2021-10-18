package com.ruoyi.wxcxc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 企业信息对象 wxcxc_business
 * 
 * @author l62202
 * @date 2021-10-17
 */
public class WxcxcBusiness extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String name;

    /** 企业LOGO地址 */
    private String logoFile;

    /** 企业域名 */
    @Excel(name = "企业域名")
    private String domainName;

    /** 企业所在地区 */
    @Excel(name = "企业所在地区")
    private String region;

    /** 企业规模 */
    @Excel(name = "企业规模")
    private String scale;

    /** 联系人 */
    @Excel(name = "联系人")
    private String liaisonMan;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

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
    public void setLogoFile(String logoFile)
    {
        this.logoFile = logoFile;
    }

    public String getLogoFile()
    {
        return logoFile;
    }
    public void setDomainName(String domainName) 
    {
        this.domainName = domainName;
    }

    public String getDomainName() 
    {
        return domainName;
    }
    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getRegion()
    {
        return region;
    }
    public void setScale(String scale) 
    {
        this.scale = scale;
    }

    public String getScale() 
    {
        return scale;
    }
    public void setLiaisonMan(String liaisonMan) 
    {
        this.liaisonMan = liaisonMan;
    }

    public String getLiaisonMan() 
    {
        return liaisonMan;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
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
            .append("logoFile", getLogoFile())
            .append("domainName", getDomainName())
            .append("region", getRegion())
            .append("scale", getScale())
            .append("liaisonMan", getLiaisonMan())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
