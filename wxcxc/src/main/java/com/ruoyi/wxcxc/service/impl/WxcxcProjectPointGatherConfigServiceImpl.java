package com.ruoyi.wxcxc.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.wxcxc.dto.WxcxcProjectPointGatherConfigDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxcxc.mapper.WxcxcProjectPointGatherConfigMapper;
import com.ruoyi.wxcxc.domain.WxcxcProjectPointGatherConfig;
import com.ruoyi.wxcxc.service.IWxcxcProjectPointGatherConfigService;

/**
 * 聚集配置Service业务层处理
 * 
 * @author l62202
 * @date 2021-12-06
 */
@Service
public class WxcxcProjectPointGatherConfigServiceImpl implements IWxcxcProjectPointGatherConfigService 
{
    @Autowired
    private WxcxcProjectPointGatherConfigMapper wxcxcProjectPointGatherConfigMapper;

    /**
     * 查询聚集配置
     * 
     * @param id 聚集配置主键
     * @return 聚集配置
     */
    @Override
    public WxcxcProjectPointGatherConfig selectWxcxcProjectPointGatherConfigById(Long id)
    {
        return wxcxcProjectPointGatherConfigMapper.selectWxcxcProjectPointGatherConfigById(id);
    }

    /**
     * 查询聚集配置列表
     * 
     * @param wxcxcProjectPointGatherConfig 聚集配置
     * @return 聚集配置
     */
    @Override
    public List<WxcxcProjectPointGatherConfig> selectWxcxcProjectPointGatherConfigList(WxcxcProjectPointGatherConfigDto wxcxcProjectPointGatherConfig)
    {
        return wxcxcProjectPointGatherConfigMapper.selectWxcxcProjectPointGatherConfigList(wxcxcProjectPointGatherConfig);
    }

    /**
     * 新增聚集配置
     * 
     * @param wxcxcProjectPointGatherConfig 聚集配置
     * @return 结果
     */
    @Override
    public int insertWxcxcProjectPointGatherConfig(WxcxcProjectPointGatherConfig wxcxcProjectPointGatherConfig)
    {
        wxcxcProjectPointGatherConfig.setCreateTime(DateUtils.getNowDate());
        return wxcxcProjectPointGatherConfigMapper.insertWxcxcProjectPointGatherConfig(wxcxcProjectPointGatherConfig);
    }

    /**
     * 修改聚集配置
     * 
     * @param wxcxcProjectPointGatherConfig 聚集配置
     * @return 结果
     */
    @Override
    public int updateWxcxcProjectPointGatherConfig(WxcxcProjectPointGatherConfig wxcxcProjectPointGatherConfig)
    {
        wxcxcProjectPointGatherConfig.setUpdateTime(DateUtils.getNowDate());
        return wxcxcProjectPointGatherConfigMapper.updateWxcxcProjectPointGatherConfig(wxcxcProjectPointGatherConfig);
    }

    /**
     * 批量删除聚集配置
     * 
     * @param ids 需要删除的聚集配置主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcProjectPointGatherConfigByIds(Long[] ids)
    {
        return wxcxcProjectPointGatherConfigMapper.deleteWxcxcProjectPointGatherConfigByIds(ids);
    }

    /**
     * 删除聚集配置信息
     * 
     * @param id 聚集配置主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcProjectPointGatherConfigById(Long id)
    {
        return wxcxcProjectPointGatherConfigMapper.deleteWxcxcProjectPointGatherConfigById(id);
    }
}
