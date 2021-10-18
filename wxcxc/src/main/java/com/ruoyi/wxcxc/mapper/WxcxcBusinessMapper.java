package com.ruoyi.wxcxc.mapper;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxcBusiness;

/**
 * 企业信息Mapper接口
 * 
 * @author l62202
 * @date 2021-10-17
 */
public interface WxcxcBusinessMapper 
{
    /**
     * 查询企业信息
     * 
     * @param id 企业信息主键
     * @return 企业信息
     */
    public WxcxcBusiness selectWxcxcBusinessById(Long id);

    /**
     * 查询企业信息列表
     * 
     * @param wxcxcBusiness 企业信息
     * @return 企业信息集合
     */
    public List<WxcxcBusiness> selectWxcxcBusinessList(WxcxcBusiness wxcxcBusiness);

    /**
     * 新增企业信息
     * 
     * @param wxcxcBusiness 企业信息
     * @return 结果
     */
    public int insertWxcxcBusiness(WxcxcBusiness wxcxcBusiness);

    /**
     * 修改企业信息
     * 
     * @param wxcxcBusiness 企业信息
     * @return 结果
     */
    public int updateWxcxcBusiness(WxcxcBusiness wxcxcBusiness);

    /**
     * 删除企业信息
     * 
     * @param id 企业信息主键
     * @return 结果
     */
    public int deleteWxcxcBusinessById(Long id);

    /**
     * 批量删除企业信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxcxcBusinessByIds(Long[] ids);
}
