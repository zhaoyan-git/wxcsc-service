package com.ruoyi.wxcxc.util;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.wxcxc.domain.WxcxcBusinessMember;
import com.ruoyi.wxcxc.domain.WxcxcBusinessMemberRole;
import com.ruoyi.wxcxc.domain.WxcxcProjectAlarmConfig;
import com.ruoyi.wxcxc.domain.WxcxcProjectStructure;
import com.ruoyi.wxcxc.dto.BusinessMemberDto;
import com.ruoyi.wxcxc.dto.WxcxcProjectAlarmConfigDto;
import com.ruoyi.wxcxc.mapper.WxcxcBusinessMemberRoleMapper;
import com.ruoyi.wxcxc.service.IWxcxcBusinessMemberService;
import com.ruoyi.wxcxc.service.IWxcxcProjectAlarmConfigService;
import com.ruoyi.wxcxc.service.IWxcxcProjectPointService;
import com.ruoyi.wxcxc.service.IWxcxcProjectStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PackDto {
    @Autowired
    private IWxcxcBusinessMemberService wxcxcBusinessMemberService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private IWxcxcProjectAlarmConfigService wxcxcProjectAlarmConfigService;
    @Autowired
    private IWxcxcProjectPointService wxcxcProjectPointService;
    @Autowired
    private IWxcxcProjectStructureService wxcxcProjectStructureService;
    @Autowired
    private WxcxcBusinessMemberRoleMapper wxcxcBusinessMemberRoleMapper;

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

        // 获取项目权限
        List<WxcxcBusinessMemberRole> businessMemberRoleList = wxcxcBusinessMemberRoleMapper.selectWxcxcBusinessMemberRoleBybusinessMemberId(wxcxcBusinessMember.getId());
        businessMemberDto.setBusinessMemberRoleList(businessMemberRoleList);

        return businessMemberDto;
    }

    public WxcxcProjectAlarmConfigDto generateWxcxcProjectAlarmConfigDtoById(Long id) {
        if (null == id) return null;

        WxcxcProjectAlarmConfig wxcxcProjectAlarmConfig = wxcxcProjectAlarmConfigService.selectWxcxcProjectAlarmConfigById(id);

        if (null == wxcxcProjectAlarmConfig) return null;

        WxcxcProjectAlarmConfigDto wxcxcProjectAlarmConfigDto = new WxcxcProjectAlarmConfigDto();

        wxcxcProjectAlarmConfigDto.setId(wxcxcProjectAlarmConfig.getId());
        wxcxcProjectAlarmConfigDto.setPointId(wxcxcProjectAlarmConfig.getPointId());
        wxcxcProjectAlarmConfigDto.setAlarmLevel(wxcxcProjectAlarmConfig.getAlarmLevel());
        wxcxcProjectAlarmConfigDto.setThreshold(wxcxcProjectAlarmConfig.getThreshold());
        wxcxcProjectAlarmConfigDto.setComputeType(wxcxcProjectAlarmConfig.getComputeType());
        wxcxcProjectAlarmConfigDto.setTitle(wxcxcProjectAlarmConfig.getTitle());
        wxcxcProjectAlarmConfigDto.setContent(wxcxcProjectAlarmConfig.getContent());
        wxcxcProjectAlarmConfigDto.setPoint(wxcxcProjectPointService.selectWxcxcProjectPointById(wxcxcProjectAlarmConfig.getPointId()));
        wxcxcProjectAlarmConfigDto.setStructureId(wxcxcProjectAlarmConfigDto.getPoint().getProjectStructureId());

        WxcxcProjectStructure wxcxcProjectStructure = wxcxcProjectStructureService.selectWxcxcProjectStructureById(wxcxcProjectAlarmConfigDto.getStructureId());
        if (null != wxcxcProjectStructure)
            wxcxcProjectAlarmConfigDto.setProjectId(wxcxcProjectStructure.getProjectId());

        return wxcxcProjectAlarmConfigDto;
    }
}
