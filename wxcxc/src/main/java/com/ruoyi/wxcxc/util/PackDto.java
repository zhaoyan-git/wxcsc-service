package com.ruoyi.wxcxc.util;

import com.ruoyi.wxcxc.domain.WxcxcBusinessMember;
import com.ruoyi.wxcxc.dto.BusinessMemberDto;
import com.ruoyi.wxcxc.service.IWxcxcBusinessMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PackDto {
    @Autowired
    private IWxcxcBusinessMemberService wxcxcBusinessMemberService;

    public BusinessMemberDto generateBusinessMemberDtoById(Long id) {
        if (null == id) return null;

        WxcxcBusinessMember wxcxcBusinessMember = wxcxcBusinessMemberService.selectWxcxcBusinessMemberById(id);

        if (null == wxcxcBusinessMember) return null;

        BusinessMemberDto businessMemberDto = new BusinessMemberDto();

        businessMemberDto.setId(wxcxcBusinessMember.getId());
        businessMemberDto.setBusinessId(wxcxcBusinessMember.getBusinessId());
        businessMemberDto.setSysUserId(wxcxcBusinessMember.getSysUserId());

        return businessMemberDto;
    }
}
