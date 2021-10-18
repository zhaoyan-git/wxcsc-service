package com.ruoyi.wxcxc.dto;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.wxcxc.domain.WxcxcBusinessMemberRole;
import lombok.Data;

import java.util.List;

@Data
public class BusinessMemberDto extends BaseEntity {
    private Long id;
    /**
     * 企业id
     */
    private Long businessId;

    /**
     * 用户id
     */
    private Long sysUserId;

    // 用户信息
    private SysUser user;

    //项目权限
    private List<WxcxcBusinessMemberRole> businessMemberRoleList;
}
