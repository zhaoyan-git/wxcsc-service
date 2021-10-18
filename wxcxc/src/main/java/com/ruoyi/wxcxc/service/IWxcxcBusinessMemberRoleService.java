package com.ruoyi.wxcxc.service;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxcBusinessMemberRole;

/**
 * 企业人员权限Service接口
 * 
 * @author l62202
 * @date 2021-10-17
 */
public interface IWxcxcBusinessMemberRoleService 
{
    /**
     * 查询企业人员权限
     * 
     * @param id 企业人员权限主键
     * @return 企业人员权限
     */
    public WxcxcBusinessMemberRole selectWxcxcBusinessMemberRoleById(Long id);

    /**
     * 查询企业人员权限列表
     * 
     * @param wxcxcBusinessMemberRole 企业人员权限
     * @return 企业人员权限集合
     */
    public List<WxcxcBusinessMemberRole> selectWxcxcBusinessMemberRoleList(WxcxcBusinessMemberRole wxcxcBusinessMemberRole);

    /**
     * 新增企业人员权限
     * 
     * @param wxcxcBusinessMemberRole 企业人员权限
     * @return 结果
     */
    public int insertWxcxcBusinessMemberRole(WxcxcBusinessMemberRole wxcxcBusinessMemberRole);

    /**
     * 修改企业人员权限
     * 
     * @param wxcxcBusinessMemberRole 企业人员权限
     * @return 结果
     */
    public int updateWxcxcBusinessMemberRole(WxcxcBusinessMemberRole wxcxcBusinessMemberRole);

    /**
     * 批量删除企业人员权限
     * 
     * @param ids 需要删除的企业人员权限主键集合
     * @return 结果
     */
    public int deleteWxcxcBusinessMemberRoleByIds(Long[] ids);

    /**
     * 删除企业人员权限信息
     * 
     * @param id 企业人员权限主键
     * @return 结果
     */
    public int deleteWxcxcBusinessMemberRoleById(Long id);
}
