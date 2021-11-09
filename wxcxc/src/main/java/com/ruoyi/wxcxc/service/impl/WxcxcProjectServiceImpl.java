package com.ruoyi.wxcxc.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.wxcxc.domain.WxcxcBusiness;
import com.ruoyi.wxcxc.domain.WxcxcBusinessMember;
import com.ruoyi.wxcxc.service.IWxcxcBusinessMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxcxc.mapper.WxcxcProjectMapper;
import com.ruoyi.wxcxc.domain.WxcxcProject;
import com.ruoyi.wxcxc.service.IWxcxcProjectService;

/**
 * 项目Service业务层处理
 *
 * @author l62202
 * @date 2021-10-17
 */
@Service
public class WxcxcProjectServiceImpl implements IWxcxcProjectService {
    @Autowired
    private WxcxcProjectMapper wxcxcProjectMapper;
    @Autowired
    private IWxcxcBusinessMemberService wxcxcBusinessMemberService;
    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * 查询项目
     *
     * @param id 项目主键
     * @return 项目
     */
    @Override
    public WxcxcProject selectWxcxcProjectById(Long id) {
        return wxcxcProjectMapper.selectWxcxcProjectById(id);
    }

    /**
     * 查询项目列表
     *
     * @param wxcxcProject 项目
     * @return 项目
     */
    @Override
    public List<WxcxcProject> selectWxcxcProjectList(WxcxcProject wxcxcProject) {
        return wxcxcProjectMapper.selectWxcxcProjectList(wxcxcProject);
    }

    /**
     * 新增项目
     *
     * @param wxcxcProject 项目
     * @return 结果
     */
    @Override
    public int insertWxcxcProject(WxcxcProject wxcxcProject) {
        wxcxcProject.setCreateTime(DateUtils.getNowDate());
        return wxcxcProjectMapper.insertWxcxcProject(wxcxcProject);
    }

    /**
     * 修改项目
     *
     * @param wxcxcProject 项目
     * @return 结果
     */
    @Override
    public int updateWxcxcProject(WxcxcProject wxcxcProject) {
        wxcxcProject.setUpdateTime(DateUtils.getNowDate());
        return wxcxcProjectMapper.updateWxcxcProject(wxcxcProject);
    }

    /**
     * 批量删除项目
     *
     * @param ids 需要删除的项目主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcProjectByIds(Long[] ids) {
        return wxcxcProjectMapper.deleteWxcxcProjectByIds(ids);
    }

    /**
     * 删除项目信息
     *
     * @param id 项目主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcProjectById(Long id) {
        return wxcxcProjectMapper.deleteWxcxcProjectById(id);
    }

    // 根据用户ID获取项目列表
    @Override
    public List<WxcxcProject> selectWxcxcProjectByUserId(Long userId) {
        // 查看用户权限
        // 为管理员时可以查询全部数据
        List<SysRole> sysRoleList = sysRoleService.selectRolesByUserId(userId);
        boolean hasEnterpriseMng = false;

        for (SysRole item : sysRoleList) {
            if ("enterprise_mng".equals(item.getRoleKey())) {
                hasEnterpriseMng = true;
                break;
            }
        }

        WxcxcBusinessMember wxcxcBusinessMemberCondition = new WxcxcBusinessMember();
        wxcxcBusinessMemberCondition.setSysUserId(userId);
        List<WxcxcBusinessMember> wxcxcBusinessMemberList = wxcxcBusinessMemberService.selectWxcxcBusinessMemberList(wxcxcBusinessMemberCondition);

        if (null != wxcxcBusinessMemberList && 1 == wxcxcBusinessMemberList.size()) {
            WxcxcBusinessMember itemMember = wxcxcBusinessMemberList.get(0);

            // 为管理员时可以查询全部数据
            if (hasEnterpriseMng) {
                WxcxcProject wxcxcProjectCondition = new WxcxcProject();
                wxcxcProjectCondition.setBusinessId(itemMember.getBusinessId());
                return selectWxcxcProjectList(wxcxcProjectCondition);
            }

            // 查询该用户可查看的项目列表

            return wxcxcProjectMapper.selectWxcxcProjectByUserId(itemMember.getId());
        } else {
            return null;
        }
    }
}
