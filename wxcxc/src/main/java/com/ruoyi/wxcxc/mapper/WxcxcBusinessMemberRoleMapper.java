package com.ruoyi.wxcxc.mapper;

import java.util.List;

import com.ruoyi.wxcxc.domain.WxcxcBusinessMemberRole;

/**
 * 企业人员权限Mapper接口
 *
 * @author l62202
 * @date 2021-10-17
 */
public interface WxcxcBusinessMemberRoleMapper {
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

    // 查询企业人员权限列表
    public List<WxcxcBusinessMemberRole> selectWxcxcBusinessMemberRoleBybusinessMemberId(Long businessMemberId);

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
     * 删除企业人员权限
     *
     * @param id 企业人员权限主键
     * @return 结果
     */
    public int deleteWxcxcBusinessMemberRoleById(Long id);

    /**
     * 批量删除企业人员权限
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxcxcBusinessMemberRoleByIds(Long[] ids);

    // 根据用户ID删除权限
    public int deleteWxcxcBusinessMemberRoleByBusinessMemberId(Long businessMemberId);
}
