package com.ruoyi.wxcxc.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxcxc.mapper.WxcxcBusinessMapper;
import com.ruoyi.wxcxc.domain.WxcxcBusiness;
import com.ruoyi.wxcxc.service.IWxcxcBusinessService;

/**
 * 企业信息Service业务层处理
 * 
 * @author l62202
 * @date 2021-10-17
 */
@Service
public class WxcxcBusinessServiceImpl implements IWxcxcBusinessService 
{
    @Autowired
    private WxcxcBusinessMapper wxcxcBusinessMapper;

    /**
     * 查询企业信息
     * 
     * @param id 企业信息主键
     * @return 企业信息
     */
    @Override
    public WxcxcBusiness selectWxcxcBusinessById(Long id)
    {
        return wxcxcBusinessMapper.selectWxcxcBusinessById(id);
    }

    /**
     * 查询企业信息列表
     * 
     * @param wxcxcBusiness 企业信息
     * @return 企业信息
     */
    @Override
    public List<WxcxcBusiness> selectWxcxcBusinessList(WxcxcBusiness wxcxcBusiness)
    {
        return wxcxcBusinessMapper.selectWxcxcBusinessList(wxcxcBusiness);
    }

    /**
     * 新增企业信息
     * 
     * @param wxcxcBusiness 企业信息
     * @return 结果
     */
    @Override
    public int insertWxcxcBusiness(WxcxcBusiness wxcxcBusiness)
    {
        wxcxcBusiness.setCreateTime(DateUtils.getNowDate());
        return wxcxcBusinessMapper.insertWxcxcBusiness(wxcxcBusiness);
    }

    /**
     * 修改企业信息
     * 
     * @param wxcxcBusiness 企业信息
     * @return 结果
     */
    @Override
    public int updateWxcxcBusiness(WxcxcBusiness wxcxcBusiness)
    {
        wxcxcBusiness.setUpdateTime(DateUtils.getNowDate());
        return wxcxcBusinessMapper.updateWxcxcBusiness(wxcxcBusiness);
    }

    /**
     * 批量删除企业信息
     * 
     * @param ids 需要删除的企业信息主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcBusinessByIds(Long[] ids)
    {
        return wxcxcBusinessMapper.deleteWxcxcBusinessByIds(ids);
    }

    /**
     * 删除企业信息信息
     * 
     * @param id 企业信息主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcBusinessById(Long id)
    {
        return wxcxcBusinessMapper.deleteWxcxcBusinessById(id);
    }
}
