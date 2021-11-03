package com.ruoyi.wxcxc.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.wxcxc.dto.BusinessMemberDto;
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
        return wxcxcBusinessMemberMapper.insertWxcxcBusinessMember(wxcxcBusinessMember);
    }

    @Override
    public int updateWxcxcBusinessMember(BusinessMemberDto businessMemberDto) {
        SysUser user = businessMemberDto.getUser();
        return userService.updateUser(user);
    }

}
