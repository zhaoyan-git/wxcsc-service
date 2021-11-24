package com.ruoyi.wxcxc.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.wxcxc.domain.WxcxcBusinessMemberRole;
import com.ruoyi.wxcxc.dto.BusinessMemberDto;
import com.ruoyi.wxcxc.mapper.WxcxcBusinessMemberRoleMapper;
import com.ruoyi.wxcxc.service.IWxcxcBusinessMemberRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxcxc.mapper.WxcxcBusinessMemberMapper;
import com.ruoyi.wxcxc.domain.WxcxcBusinessMember;
import com.ruoyi.wxcxc.service.IWxcxcBusinessMemberService;

/**
 * 企业人员Service业务层处理
 *
 * @author l62202
 * @date 2021-10-17
 */
@Service
public class WxcxcBusinessMemberServiceImpl implements IWxcxcBusinessMemberService {
    @Autowired
    private WxcxcBusinessMemberMapper wxcxcBusinessMemberMapper;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private IWxcxcBusinessMemberRoleService wxcxcBusinessMemberRoleService;
    @Autowired
    private WxcxcBusinessMemberRoleMapper wxcxcBusinessMemberRoleMapper;

    /**
     * 查询企业人员
     *
     * @param id 企业人员主键
     * @return 企业人员
     */
    @Override
    public WxcxcBusinessMember selectWxcxcBusinessMemberById(Long id) {
        return wxcxcBusinessMemberMapper.selectWxcxcBusinessMemberById(id);
    }

    /**
     * 查询企业人员列表
     *
     * @param wxcxcBusinessMember 企业人员
     * @return 企业人员
     */
    @Override
    public List<WxcxcBusinessMember> selectWxcxcBusinessMemberList(WxcxcBusinessMember wxcxcBusinessMember) {
        return wxcxcBusinessMemberMapper.selectWxcxcBusinessMemberList(wxcxcBusinessMember);
    }

    /**
     * 新增企业人员
     *
     * @param wxcxcBusinessMember 企业人员
     * @return 结果
     */
    @Override
    public int insertWxcxcBusinessMember(WxcxcBusinessMember wxcxcBusinessMember) {
        return wxcxcBusinessMemberMapper.insertWxcxcBusinessMember(wxcxcBusinessMember);
    }

    /**
     * 修改企业人员
     *
     * @param wxcxcBusinessMember 企业人员
     * @return 结果
     */
    @Override
    public int updateWxcxcBusinessMember(WxcxcBusinessMember wxcxcBusinessMember) {
        return wxcxcBusinessMemberMapper.updateWxcxcBusinessMember(wxcxcBusinessMember);
    }

    /**
     * 批量删除企业人员
     *
     * @param ids 需要删除的企业人员主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcBusinessMemberByIds(Long[] ids) {
        // 禁用账户
        for (Long id : ids) {
            WxcxcBusinessMember wxcxcBusinessMember = selectWxcxcBusinessMemberById(id);
            userService.deleteUserById(wxcxcBusinessMember.getSysUserId());
        }
        return wxcxcBusinessMemberMapper.deleteWxcxcBusinessMemberByIds(ids);
    }

    /**
     * 删除企业人员信息
     *
     * @param id 企业人员主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcBusinessMemberById(Long id) {
        return wxcxcBusinessMemberMapper.deleteWxcxcBusinessMemberById(id);
    }

    @Override
    public int insertWxcxcBusinessMember(BusinessMemberDto businessMemberDto) {
        SysUser user = businessMemberDto.getUser();
        if (0 == userService.insertUser(user)) return 0;

        WxcxcBusinessMember wxcxcBusinessMember = new WxcxcBusinessMember();
        wxcxcBusinessMember.setBusinessId(businessMemberDto.getBusinessId());
        wxcxcBusinessMember.setSysUserId(user.getUserId());
        wxcxcBusinessMemberMapper.insertWxcxcBusinessMember(wxcxcBusinessMember);

        if (null != businessMemberDto.getBusinessMemberRoleList()) {
            for (WxcxcBusinessMemberRole item : businessMemberDto.getBusinessMemberRoleList()) {
                item.setBusinessMemberId(wxcxcBusinessMember.getId());
                wxcxcBusinessMemberRoleMapper.insertWxcxcBusinessMemberRole(item);
            }
        }

        return 1;
    }

    @Override
    public int updateWxcxcBusinessMember(BusinessMemberDto businessMemberDto) {
        if (null == businessMemberDto.getId()) return 0;

        SysUser user = businessMemberDto.getUser();

        // 修改用户信息
        userService.updateUser(user);

        // 修改项目权限
        // 删除该用户权限
        wxcxcBusinessMemberRoleMapper.deleteWxcxcBusinessMemberRoleByBusinessMemberId(businessMemberDto.getId());

        if (null != businessMemberDto.getBusinessMemberRoleList()) {
            for (WxcxcBusinessMemberRole item : businessMemberDto.getBusinessMemberRoleList()) {
                item.setBusinessMemberId(businessMemberDto.getId());
                wxcxcBusinessMemberRoleMapper.insertWxcxcBusinessMemberRole(item);
            }
        }

        return 1;
    }

}
