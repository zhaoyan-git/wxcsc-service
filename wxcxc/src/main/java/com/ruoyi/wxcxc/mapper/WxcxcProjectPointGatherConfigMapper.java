package com.ruoyi.wxcxc.mapper;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxcProjectPointGatherConfig;
import com.ruoyi.wxcxc.dto.WxcxcProjectPointGatherConfigDto;

/**
 * 聚集配置Mapper接口
 * 
 * @author l62202
 * @date 2021-12-06
 */
public interface WxcxcProjectPointGatherConfigMapper 
{
    /**
     * 查询聚集配置
     * 
     * @param id 聚集配置主键
     * @return 聚集配置
     */
    public WxcxcProjectPointGatherConfig selectWxcxcProjectPointGatherConfigById(Long id);

    /**
     * 查询聚集配置列表
     * 
     * @param wxcxcProjectPointGatherConfig 聚集配置
     * @return 聚集配置集合
     */
    public List<WxcxcProjectPointGatherConfig> selectWxcxcProjectPointGatherConfigList(WxcxcProjectPointGatherConfigDto wxcxcProjectPointGatherConfig);

    /**
     * 新增聚集配置
     * 
     * @param wxcxcProjectPointGatherConfig 聚集配置
     * @return 结果
     */
    public int insertWxcxcProjectPointGatherConfig(WxcxcProjectPointGatherConfig wxcxcProjectPointGatherConfig);

    /**
     * 修改聚集配置
     * 
     * @param wxcxcProjectPointGatherConfig 聚集配置
     * @return 结果
     */
    public int updateWxcxcProjectPointGatherConfig(WxcxcProjectPointGatherConfig wxcxcProjectPointGatherConfig);

    /**
     * 删除聚集配置
     * 
     * @param id 聚集配置主键
     * @return 结果
     */
    public int deleteWxcxcProjectPointGatherConfigById(Long id);

    /**
     * 批量删除聚集配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxcxcProjectPointGatherConfigByIds(Long[] ids);
}
