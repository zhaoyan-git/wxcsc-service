package com.ruoyi.wxcxc.mapper;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxProjectPointGroup;

/**
 * 测点分组Mapper接口
 * 
 * @author l62202
 * @date 2021-10-17
 */
public interface WxcxProjectPointGroupMapper 
{
    /**
     * 查询测点分组
     * 
     * @param id 测点分组主键
     * @return 测点分组
     */
    public WxcxProjectPointGroup selectWxcxProjectPointGroupById(Long id);

    /**
     * 查询测点分组列表
     * 
     * @param wxcxProjectPointGroup 测点分组
     * @return 测点分组集合
     */
    public List<WxcxProjectPointGroup> selectWxcxProjectPointGroupList(WxcxProjectPointGroup wxcxProjectPointGroup);

    /**
     * 新增测点分组
     * 
     * @param wxcxProjectPointGroup 测点分组
     * @return 结果
     */
    public int insertWxcxProjectPointGroup(WxcxProjectPointGroup wxcxProjectPointGroup);

    /**
     * 修改测点分组
     * 
     * @param wxcxProjectPointGroup 测点分组
     * @return 结果
     */
    public int updateWxcxProjectPointGroup(WxcxProjectPointGroup wxcxProjectPointGroup);

    /**
     * 删除测点分组
     * 
     * @param id 测点分组主键
     * @return 结果
     */
    public int deleteWxcxProjectPointGroupById(Long id);

    /**
     * 批量删除测点分组
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxcxProjectPointGroupByIds(Long[] ids);
}
