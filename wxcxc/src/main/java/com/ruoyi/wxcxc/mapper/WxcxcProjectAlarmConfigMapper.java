package com.ruoyi.wxcxc.mapper;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxcProjectAlarmConfig;
import com.ruoyi.wxcxc.dto.WxcxcProjectAlarmConfigDto;

/**
 * 报警配置Mapper接口
 * 
 * @author l62202
 * @date 2021-11-11
 */
public interface WxcxcProjectAlarmConfigMapper 
{
    /**
     * 查询报警配置
     * 
     * @param id 报警配置主键
     * @return 报警配置
     */
    public WxcxcProjectAlarmConfig selectWxcxcProjectAlarmConfigById(Long id);

    /**
     * 查询报警配置列表
     * 
     * @param wxcxcProjectAlarmConfig 报警配置
     * @return 报警配置集合
     */
    public List<WxcxcProjectAlarmConfig> selectWxcxcProjectAlarmConfigList(WxcxcProjectAlarmConfig wxcxcProjectAlarmConfig);

    /**
     * 新增报警配置
     * 
     * @param wxcxcProjectAlarmConfig 报警配置
     * @return 结果
     */
    public int insertWxcxcProjectAlarmConfig(WxcxcProjectAlarmConfig wxcxcProjectAlarmConfig);

    /**
     * 修改报警配置
     * 
     * @param wxcxcProjectAlarmConfig 报警配置
     * @return 结果
     */
    public int updateWxcxcProjectAlarmConfig(WxcxcProjectAlarmConfig wxcxcProjectAlarmConfig);

    /**
     * 删除报警配置
     * 
     * @param id 报警配置主键
     * @return 结果
     */
    public int deleteWxcxcProjectAlarmConfigById(Long id);

    /**
     * 批量删除报警配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxcxcProjectAlarmConfigByIds(Long[] ids);

    // 根据WxcxcProjectAlarmConfigDto获取数据
    public List<WxcxcProjectAlarmConfig> selectWxcxcProjectAlarmConfigListByDto(WxcxcProjectAlarmConfigDto wxcxcProjectAlarmConfigDto);
}
