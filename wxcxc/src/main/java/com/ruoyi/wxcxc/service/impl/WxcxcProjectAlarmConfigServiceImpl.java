package com.ruoyi.wxcxc.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxcxc.mapper.WxcxcProjectAlarmConfigMapper;
import com.ruoyi.wxcxc.domain.WxcxcProjectAlarmConfig;
import com.ruoyi.wxcxc.service.IWxcxcProjectAlarmConfigService;

/**
 * 报警配置Service业务层处理
 * 
 * @author l62202
 * @date 2021-11-11
 */
@Service
public class WxcxcProjectAlarmConfigServiceImpl implements IWxcxcProjectAlarmConfigService 
{
    @Autowired
    private WxcxcProjectAlarmConfigMapper wxcxcProjectAlarmConfigMapper;

    /**
     * 查询报警配置
     * 
     * @param id 报警配置主键
     * @return 报警配置
     */
    @Override
    public WxcxcProjectAlarmConfig selectWxcxcProjectAlarmConfigById(Long id)
    {
        return wxcxcProjectAlarmConfigMapper.selectWxcxcProjectAlarmConfigById(id);
    }

    /**
     * 查询报警配置列表
     * 
     * @param wxcxcProjectAlarmConfig 报警配置
     * @return 报警配置
     */
    @Override
    public List<WxcxcProjectAlarmConfig> selectWxcxcProjectAlarmConfigList(WxcxcProjectAlarmConfig wxcxcProjectAlarmConfig)
    {
        return wxcxcProjectAlarmConfigMapper.selectWxcxcProjectAlarmConfigList(wxcxcProjectAlarmConfig);
    }

    /**
     * 新增报警配置
     * 
     * @param wxcxcProjectAlarmConfig 报警配置
     * @return 结果
     */
    @Override
    public int insertWxcxcProjectAlarmConfig(WxcxcProjectAlarmConfig wxcxcProjectAlarmConfig)
    {
        wxcxcProjectAlarmConfig.setCreateTime(DateUtils.getNowDate());
        return wxcxcProjectAlarmConfigMapper.insertWxcxcProjectAlarmConfig(wxcxcProjectAlarmConfig);
    }

    /**
     * 修改报警配置
     * 
     * @param wxcxcProjectAlarmConfig 报警配置
     * @return 结果
     */
    @Override
    public int updateWxcxcProjectAlarmConfig(WxcxcProjectAlarmConfig wxcxcProjectAlarmConfig)
    {
        wxcxcProjectAlarmConfig.setUpdateTime(DateUtils.getNowDate());
        return wxcxcProjectAlarmConfigMapper.updateWxcxcProjectAlarmConfig(wxcxcProjectAlarmConfig);
    }

    /**
     * 批量删除报警配置
     * 
     * @param ids 需要删除的报警配置主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcProjectAlarmConfigByIds(Long[] ids)
    {
        return wxcxcProjectAlarmConfigMapper.deleteWxcxcProjectAlarmConfigByIds(ids);
    }

    /**
     * 删除报警配置信息
     * 
     * @param id 报警配置主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcProjectAlarmConfigById(Long id)
    {
        return wxcxcProjectAlarmConfigMapper.deleteWxcxcProjectAlarmConfigById(id);
    }
}
