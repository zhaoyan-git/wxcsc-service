package com.ruoyi.wxcxc.service;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxcProjectPointGroup;

/**
 * 测点分组Service接口
 * 
 * @author l62202
 * @date 2021-10-17
 */
public interface IWxcxcProjectPointGroupService
{
    /**
     * 查询测点分组
     * 
     * @param id 测点分组主键
     * @return 测点分组
     */
    public WxcxcProjectPointGroup selectWxcxcProjectPointGroupById(Long id);

    /**
     * 查询测点分组列表
     * 
     * @param wxcxcProjectPointGroup 测点分组
     * @return 测点分组集合
     */
    public List<WxcxcProjectPointGroup> selectWxcxcProjectPointGroupList(WxcxcProjectPointGroup wxcxcProjectPointGroup);

    /**
     * 新增测点分组
     * 
     * @param wxcxcProjectPointGroup 测点分组
     * @return 结果
     */
    public int insertWxcxcProjectPointGroup(WxcxcProjectPointGroup wxcxcProjectPointGroup);

    /**
     * 修改测点分组
     * 
     * @param wxcxcProjectPointGroup 测点分组
     * @return 结果
     */
    public int updateWxcxcProjectPointGroup(WxcxcProjectPointGroup wxcxcProjectPointGroup);

    /**
     * 批量删除测点分组
     * 
     * @param ids 需要删除的测点分组主键集合
     * @return 结果
     */
    public int deleteWxcxcProjectPointGroupByIds(Long[] ids);

    /**
     * 删除测点分组信息
     * 
     * @param id 测点分组主键
     * @return 结果
     */
    public int deleteWxcxcProjectPointGroupById(Long id);
}
