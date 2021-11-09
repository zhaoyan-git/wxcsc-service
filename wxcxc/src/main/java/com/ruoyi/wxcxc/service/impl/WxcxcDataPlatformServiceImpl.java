package com.ruoyi.wxcxc.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxcxc.mapper.WxcxcDataPlatformMapper;
import com.ruoyi.wxcxc.domain.WxcxcDataPlatform;
import com.ruoyi.wxcxc.service.IWxcxcDataPlatformService;

/**
 * 平台数据Service业务层处理
 * 
 * @author l62202
 * @date 2021-11-03
 */
@Service
public class WxcxcDataPlatformServiceImpl implements IWxcxcDataPlatformService 
{
    @Autowired
    private WxcxcDataPlatformMapper wxcxcDataPlatformMapper;

    /**
     * 查询平台数据
     * 
     * @param id 平台数据主键
     * @return 平台数据
     */
    @Override
    public WxcxcDataPlatform selectWxcxcDataPlatformById(Long id)
    {
        return wxcxcDataPlatformMapper.selectWxcxcDataPlatformById(id);
    }

    /**
     * 查询平台数据列表
     * 
     * @param wxcxcDataPlatform 平台数据
     * @return 平台数据
     */
    @Override
    public List<WxcxcDataPlatform> selectWxcxcDataPlatformList(WxcxcDataPlatform wxcxcDataPlatform)
    {
        return wxcxcDataPlatformMapper.selectWxcxcDataPlatformList(wxcxcDataPlatform);
    }

    /**
     * 新增平台数据
     * 
     * @param wxcxcDataPlatform 平台数据
     * @return 结果
     */
    @Override
    public int insertWxcxcDataPlatform(WxcxcDataPlatform wxcxcDataPlatform)
    {
        wxcxcDataPlatform.setCreateTime(DateUtils.getNowDate());
        return wxcxcDataPlatformMapper.insertWxcxcDataPlatform(wxcxcDataPlatform);
    }

    /**
     * 修改平台数据
     * 
     * @param wxcxcDataPlatform 平台数据
     * @return 结果
     */
    @Override
    public int updateWxcxcDataPlatform(WxcxcDataPlatform wxcxcDataPlatform)
    {
        return wxcxcDataPlatformMapper.updateWxcxcDataPlatform(wxcxcDataPlatform);
    }

    /**
     * 批量删除平台数据
     * 
     * @param ids 需要删除的平台数据主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcDataPlatformByIds(Long[] ids)
    {
        return wxcxcDataPlatformMapper.deleteWxcxcDataPlatformByIds(ids);
    }

    /**
     * 删除平台数据信息
     * 
     * @param id 平台数据主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcDataPlatformById(Long id)
    {
        return wxcxcDataPlatformMapper.deleteWxcxcDataPlatformById(id);
    }
}
