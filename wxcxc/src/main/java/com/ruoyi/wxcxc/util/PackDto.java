package com.ruoyi.wxcxc.util;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.wxcxc.domain.WxcxcBusinessMember;
import com.ruoyi.wxcxc.dto.BusinessMemberDto;
import com.ruoyi.wxcxc.service.IWxcxcBusinessMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PackDto {
    @Autowired
    private IWxcxcBusinessMemberService wxcxcBusinessMemberService;
    @Autowired
    private ISysUserService userService;

    public BusinessMemberDto generateBusinessMemberDtoById(Long id) {
        if (null == id) return null;

        WxcxcBusinessMember wxcxcBusinessMember = wxcxcBusinessMemberService.selectWxcxcBusinessMemberById(id);
        if (null == wxcxcBusinessMember) return null;

        SysUser sysUser = userService.selectUserById(wxcxcBusinessMember.getSysUserId());
        if (null == sysUser) return null;

        BusinessMemberDto businessMemberDto = new BusinessMemberDto();

        businessMemberDto.setId(wxcxcBusinessMember.getId());
        businessMemberDto.setBusinessId(wxcxcBusinessMember.getBusinessId());
        businessMemberDto.setSysUserId(wxcxcBusinessMember.getSysUserId());
        businessMemberDto.setUser(sysUser);

        return businessMemberDto;
    }
}
