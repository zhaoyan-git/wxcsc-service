package com.ruoyi.wxcxc.service;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxcDataBusiness;

/**
 * 企业数据Service接口
 * 
 * @author l62202
 * @date 2021-11-03
 */
public interface IWxcxcDataBusinessService 
{
    /**
     * 查询企业数据
     * 
     * @param id 企业数据主键
     * @return 企业数据
     */
    public WxcxcDataBusiness selectWxcxcDataBusinessById(Long id);

    /**
     * 查询企业数据列表
     * 
     * @param wxcxcDataBusiness 企业数据
     * @return 企业数据集合
     */
    public List<WxcxcDataBusiness> selectWxcxcDataBusinessList(WxcxcDataBusiness wxcxcDataBusiness);

    /**
     * 新增企业数据
     * 
     * @param wxcxcDataBusiness 企业数据
     * @return 结果
     */
    public int insertWxcxcDataBusiness(WxcxcDataBusiness wxcxcDataBusiness);

    /**
     * 修改企业数据
     * 
     * @param wxcxcDataBusiness 企业数据
     * @return 结果
     */
    public int updateWxcxcDataBusiness(WxcxcDataBusiness wxcxcDataBusiness);

    /**
     * 批量删除企业数据
     * 
     * @param ids 需要删除的企业数据主键集合
     * @return 结果
     */
    public int deleteWxcxcDataBusinessByIds(Long[] ids);

    /**
     * 删除企业数据信息
     * 
     * @param id 企业数据主键
     * @return 结果
     */
    public int deleteWxcxcDataBusinessById(Long id);
}
