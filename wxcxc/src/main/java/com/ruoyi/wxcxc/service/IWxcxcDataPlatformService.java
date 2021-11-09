package com.ruoyi.wxcxc.service;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxcDataPlatform;

/**
 * 平台数据Service接口
 * 
 * @author l62202
 * @date 2021-11-03
 */
public interface IWxcxcDataPlatformService 
{
    /**
     * 查询平台数据
     * 
     * @param id 平台数据主键
     * @return 平台数据
     */
    public WxcxcDataPlatform selectWxcxcDataPlatformById(Long id);

    /**
     * 查询平台数据列表
     * 
     * @param wxcxcDataPlatform 平台数据
     * @return 平台数据集合
     */
    public List<WxcxcDataPlatform> selectWxcxcDataPlatformList(WxcxcDataPlatform wxcxcDataPlatform);

    /**
     * 新增平台数据
     * 
     * @param wxcxcDataPlatform 平台数据
     * @return 结果
     */
    public int insertWxcxcDataPlatform(WxcxcDataPlatform wxcxcDataPlatform);

    /**
     * 修改平台数据
     * 
     * @param wxcxcDataPlatform 平台数据
     * @return 结果
     */
    public int updateWxcxcDataPlatform(WxcxcDataPlatform wxcxcDataPlatform);

    /**
     * 批量删除平台数据
     * 
     * @param ids 需要删除的平台数据主键集合
     * @return 结果
     */
    public int deleteWxcxcDataPlatformByIds(Long[] ids);

    /**
     * 删除平台数据信息
     * 
     * @param id 平台数据主键
     * @return 结果
     */
    public int deleteWxcxcDataPlatformById(Long id);
}
