package com.ruoyi.wxcxc.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxcxc.mapper.WxcxcNettyMapper;
import com.ruoyi.wxcxc.domain.WxcxcNetty;
import com.ruoyi.wxcxc.service.IWxcxcNettyService;

/**
 * nettyService业务层处理
 * 
 * @author l62202
 * @date 2021-10-26
 */
@Service
public class WxcxcNettyServiceImpl implements IWxcxcNettyService 
{
    @Autowired
    private WxcxcNettyMapper wxcxcNettyMapper;

    /**
     * 查询netty
     * 
     * @param id netty主键
     * @return netty
     */
    @Override
    public WxcxcNetty selectWxcxcNettyById(Integer id)
    {
        return wxcxcNettyMapper.selectWxcxcNettyById(id);
    }

    /**
     * 查询netty列表
     * 
     * @param wxcxcNetty netty
     * @return netty
     */
    @Override
    public List<WxcxcNetty> selectWxcxcNettyList(WxcxcNetty wxcxcNetty)
    {
        return wxcxcNettyMapper.selectWxcxcNettyList(wxcxcNetty);
    }

    /**
     * 新增netty
     * 
     * @param wxcxcNetty netty
     * @return 结果
     */
    @Override
    public int insertWxcxcNetty(WxcxcNetty wxcxcNetty)
    {
        wxcxcNetty.setCreateTime(DateUtils.getNowDate());
        return wxcxcNettyMapper.insertWxcxcNetty(wxcxcNetty);
    }

    /**
     * 修改netty
     * 
     * @param wxcxcNetty netty
     * @return 结果
     */
    @Override
    public int updateWxcxcNetty(WxcxcNetty wxcxcNetty)
    {
        wxcxcNetty.setUpdateTime(DateUtils.getNowDate());
        return wxcxcNettyMapper.updateWxcxcNetty(wxcxcNetty);
    }

    /**
     * 批量删除netty
     * 
     * @param ids 需要删除的netty主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcNettyByIds(Integer[] ids)
    {
        return wxcxcNettyMapper.deleteWxcxcNettyByIds(ids);
    }

    /**
     * 删除netty信息
     * 
     * @param id netty主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcNettyById(Integer id)
    {
        return wxcxcNettyMapper.deleteWxcxcNettyById(id);
    }
}
