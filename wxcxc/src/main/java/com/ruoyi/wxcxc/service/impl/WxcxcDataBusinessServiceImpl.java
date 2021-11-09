package com.ruoyi.wxcxc.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxcxc.mapper.WxcxcDataBusinessMapper;
import com.ruoyi.wxcxc.domain.WxcxcDataBusiness;
import com.ruoyi.wxcxc.service.IWxcxcDataBusinessService;

/**
 * 企业数据Service业务层处理
 * 
 * @author l62202
 * @date 2021-11-03
 */
@Service
public class WxcxcDataBusinessServiceImpl implements IWxcxcDataBusinessService 
{
    @Autowired
    private WxcxcDataBusinessMapper wxcxcDataBusinessMapper;

    /**
     * 查询企业数据
     * 
     * @param id 企业数据主键
     * @return 企业数据
     */
    @Override
    public WxcxcDataBusiness selectWxcxcDataBusinessById(Long id)
    {
        return wxcxcDataBusinessMapper.selectWxcxcDataBusinessById(id);
    }

    /**
     * 查询企业数据列表
     * 
     * @param wxcxcDataBusiness 企业数据
     * @return 企业数据
     */
    @Override
    public List<WxcxcDataBusiness> selectWxcxcDataBusinessList(WxcxcDataBusiness wxcxcDataBusiness)
    {
        return wxcxcDataBusinessMapper.selectWxcxcDataBusinessList(wxcxcDataBusiness);
    }

    /**
     * 新增企业数据
     * 
     * @param wxcxcDataBusiness 企业数据
     * @return 结果
     */
    @Override
    public int insertWxcxcDataBusiness(WxcxcDataBusiness wxcxcDataBusiness)
    {
        wxcxcDataBusiness.setCreateTime(DateUtils.getNowDate());
        return wxcxcDataBusinessMapper.insertWxcxcDataBusiness(wxcxcDataBusiness);
    }

    /**
     * 修改企业数据
     * 
     * @param wxcxcDataBusiness 企业数据
     * @return 结果
     */
    @Override
    public int updateWxcxcDataBusiness(WxcxcDataBusiness wxcxcDataBusiness)
    {
        return wxcxcDataBusinessMapper.updateWxcxcDataBusiness(wxcxcDataBusiness);
    }

    /**
     * 批量删除企业数据
     * 
     * @param ids 需要删除的企业数据主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcDataBusinessByIds(Long[] ids)
    {
        return wxcxcDataBusinessMapper.deleteWxcxcDataBusinessByIds(ids);
    }

    /**
     * 删除企业数据信息
     * 
     * @param id 企业数据主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcDataBusinessById(Long id)
    {
        return wxcxcDataBusinessMapper.deleteWxcxcDataBusinessById(id);
    }
}
