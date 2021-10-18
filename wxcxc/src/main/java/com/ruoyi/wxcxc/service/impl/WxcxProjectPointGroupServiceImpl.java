package com.ruoyi.wxcxc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxcxc.mapper.WxcxProjectPointGroupMapper;
import com.ruoyi.wxcxc.domain.WxcxProjectPointGroup;
import com.ruoyi.wxcxc.service.IWxcxProjectPointGroupService;

/**
 * 测点分组Service业务层处理
 * 
 * @author l62202
 * @date 2021-10-17
 */
@Service
public class WxcxProjectPointGroupServiceImpl implements IWxcxProjectPointGroupService 
{
    @Autowired
    private WxcxProjectPointGroupMapper wxcxProjectPointGroupMapper;

    /**
     * 查询测点分组
     * 
     * @param id 测点分组主键
     * @return 测点分组
     */
    @Override
    public WxcxProjectPointGroup selectWxcxProjectPointGroupById(Long id)
    {
        return wxcxProjectPointGroupMapper.selectWxcxProjectPointGroupById(id);
    }

    /**
     * 查询测点分组列表
     * 
     * @param wxcxProjectPointGroup 测点分组
     * @return 测点分组
     */
    @Override
    public List<WxcxProjectPointGroup> selectWxcxProjectPointGroupList(WxcxProjectPointGroup wxcxProjectPointGroup)
    {
        return wxcxProjectPointGroupMapper.selectWxcxProjectPointGroupList(wxcxProjectPointGroup);
    }

    /**
     * 新增测点分组
     * 
     * @param wxcxProjectPointGroup 测点分组
     * @return 结果
     */
    @Override
    public int insertWxcxProjectPointGroup(WxcxProjectPointGroup wxcxProjectPointGroup)
    {
        return wxcxProjectPointGroupMapper.insertWxcxProjectPointGroup(wxcxProjectPointGroup);
    }

    /**
     * 修改测点分组
     * 
     * @param wxcxProjectPointGroup 测点分组
     * @return 结果
     */
    @Override
    public int updateWxcxProjectPointGroup(WxcxProjectPointGroup wxcxProjectPointGroup)
    {
        return wxcxProjectPointGroupMapper.updateWxcxProjectPointGroup(wxcxProjectPointGroup);
    }

    /**
     * 批量删除测点分组
     * 
     * @param ids 需要删除的测点分组主键
     * @return 结果
     */
    @Override
    public int deleteWxcxProjectPointGroupByIds(Long[] ids)
    {
        return wxcxProjectPointGroupMapper.deleteWxcxProjectPointGroupByIds(ids);
    }

    /**
     * 删除测点分组信息
     * 
     * @param id 测点分组主键
     * @return 结果
     */
    @Override
    public int deleteWxcxProjectPointGroupById(Long id)
    {
        return wxcxProjectPointGroupMapper.deleteWxcxProjectPointGroupById(id);
    }
}
