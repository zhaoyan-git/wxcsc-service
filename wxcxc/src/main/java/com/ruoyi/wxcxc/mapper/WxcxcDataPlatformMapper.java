package com.ruoyi.wxcxc.mapper;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxcDataPlatform;

/**
 * 平台数据Mapper接口
 * 
 * @author l62202
 * @date 2021-11-03
 */
public interface WxcxcDataPlatformMapper 
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
     * 删除平台数据
     * 
     * @param id 平台数据主键
     * @return 结果
     */
    public int deleteWxcxcDataPlatformById(Long id);

    /**
     * 批量删除平台数据
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxcxcDataPlatformByIds(Long[] ids);

    // 入驻企业数量
    public int getPlatformBusinessCount();

    // 平台总项目数
    public int getPlatformProjectCount();

    // 平台设备数
    public int getPlatformDeviceCount();

    // 平台DTU数
    public int getPlatformDeviceGatewayCount();
}
