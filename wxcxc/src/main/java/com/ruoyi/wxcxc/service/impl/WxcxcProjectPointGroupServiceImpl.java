package com.ruoyi.wxcxc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxcxc.mapper.WxcxcProjectPointGroupMapper;
import com.ruoyi.wxcxc.domain.WxcxcProjectPointGroup;
import com.ruoyi.wxcxc.service.IWxcxcProjectPointGroupService;

/**
 * 测点分组Service业务层处理
 * 
 * @author l62202
 * @date 2021-10-17
 */
@Service
public class WxcxcProjectPointGroupServiceImpl implements IWxcxcProjectPointGroupService
{
    @Autowired
    private WxcxcProjectPointGroupMapper wxcxcProjectPointGroupMapper;

    /**
     * 查询测点分组
     * 
     * @param id 测点分组主键
     * @return 测点分组
     */
    @Override
    public WxcxcProjectPointGroup selectWxcxcProjectPointGroupById(Long id)
    {
        return wxcxcProjectPointGroupMapper.selectWxcxcProjectPointGroupById(id);
    }

    /**
     * 查询测点分组列表
     * 
     * @param wxcxcProjectPointGroup 测点分组
     * @return 测点分组
     */
    @Override
    public List<WxcxcProjectPointGroup> selectWxcxcProjectPointGroupList(WxcxcProjectPointGroup wxcxcProjectPointGroup)
    {
        return wxcxcProjectPointGroupMapper.selectWxcxcProjectPointGroupList(wxcxcProjectPointGroup);
    }

    /**
     * 新增测点分组
     * 
     * @param wxcxcProjectPointGroup 测点分组
     * @return 结果
     */
    @Override
    public int insertWxcxcProjectPointGroup(WxcxcProjectPointGroup wxcxcProjectPointGroup)
    {
        return wxcxcProjectPointGroupMapper.insertWxcxcProjectPointGroup(wxcxcProjectPointGroup);
    }

    /**
     * 修改测点分组
     * 
     * @param wxcxcProjectPointGroup 测点分组
     * @return 结果
     */
    @Override
    public int updateWxcxcProjectPointGroup(WxcxcProjectPointGroup wxcxcProjectPointGroup)
    {
        return wxcxcProjectPointGroupMapper.updateWxcxcProjectPointGroup(wxcxcProjectPointGroup);
    }

    /**
     * 批量删除测点分组
     * 
     * @param ids 需要删除的测点分组主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcProjectPointGroupByIds(Long[] ids)
    {
        return wxcxcProjectPointGroupMapper.deleteWxcxcProjectPointGroupByIds(ids);
    }

    /**
     * 删除测点分组信息
     * 
     * @param id 测点分组主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcProjectPointGroupById(Long id)
    {
        return wxcxcProjectPointGroupMapper.deleteWxcxcProjectPointGroupById(id);
    }
}
