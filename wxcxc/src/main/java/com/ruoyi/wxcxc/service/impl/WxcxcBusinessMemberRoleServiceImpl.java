package com.ruoyi.wxcxc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxcxc.mapper.WxcxcBusinessMemberRoleMapper;
import com.ruoyi.wxcxc.domain.WxcxcBusinessMemberRole;
import com.ruoyi.wxcxc.service.IWxcxcBusinessMemberRoleService;

/**
 * 企业人员权限Service业务层处理
 * 
 * @author l62202
 * @date 2021-10-17
 */
@Service
public class WxcxcBusinessMemberRoleServiceImpl implements IWxcxcBusinessMemberRoleService 
{
    @Autowired
    private WxcxcBusinessMemberRoleMapper wxcxcBusinessMemberRoleMapper;

    /**
     * 查询企业人员权限
     * 
     * @param id 企业人员权限主键
     * @return 企业人员权限
     */
    @Override
    public WxcxcBusinessMemberRole selectWxcxcBusinessMemberRoleById(Long id)
    {
        return wxcxcBusinessMemberRoleMapper.selectWxcxcBusinessMemberRoleById(id);
    }

    /**
     * 查询企业人员权限列表
     * 
     * @param wxcxcBusinessMemberRole 企业人员权限
     * @return 企业人员权限
     */
    @Override
    public List<WxcxcBusinessMemberRole> selectWxcxcBusinessMemberRoleList(WxcxcBusinessMemberRole wxcxcBusinessMemberRole)
    {
        return wxcxcBusinessMemberRoleMapper.selectWxcxcBusinessMemberRoleList(wxcxcBusinessMemberRole);
    }

    /**
     * 新增企业人员权限
     * 
     * @param wxcxcBusinessMemberRole 企业人员权限
     * @return 结果
     */
    @Override
    public int insertWxcxcBusinessMemberRole(WxcxcBusinessMemberRole wxcxcBusinessMemberRole)
    {
        return wxcxcBusinessMemberRoleMapper.insertWxcxcBusinessMemberRole(wxcxcBusinessMemberRole);
    }

    /**
     * 修改企业人员权限
     * 
     * @param wxcxcBusinessMemberRole 企业人员权限
     * @return 结果
     */
    @Override
    public int updateWxcxcBusinessMemberRole(WxcxcBusinessMemberRole wxcxcBusinessMemberRole)
    {
        return wxcxcBusinessMemberRoleMapper.updateWxcxcBusinessMemberRole(wxcxcBusinessMemberRole);
    }

    /**
     * 批量删除企业人员权限
     * 
     * @param ids 需要删除的企业人员权限主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcBusinessMemberRoleByIds(Long[] ids)
    {
        return wxcxcBusinessMemberRoleMapper.deleteWxcxcBusinessMemberRoleByIds(ids);
    }

    /**
     * 删除企业人员权限信息
     * 
     * @param id 企业人员权限主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcBusinessMemberRoleById(Long id)
    {
        return wxcxcBusinessMemberRoleMapper.deleteWxcxcBusinessMemberRoleById(id);
    }
}
