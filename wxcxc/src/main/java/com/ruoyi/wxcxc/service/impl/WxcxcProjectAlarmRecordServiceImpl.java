package com.ruoyi.wxcxc.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxcxc.mapper.WxcxcProjectAlarmRecordMapper;
import com.ruoyi.wxcxc.domain.WxcxcProjectAlarmRecord;
import com.ruoyi.wxcxc.service.IWxcxcProjectAlarmRecordService;

/**
 * 报警记录Service业务层处理
 * 
 * @author l62202
 * @date 2021-11-11
 */
@Service
public class WxcxcProjectAlarmRecordServiceImpl implements IWxcxcProjectAlarmRecordService 
{
    @Autowired
    private WxcxcProjectAlarmRecordMapper wxcxcProjectAlarmRecordMapper;

    /**
     * 查询报警记录
     * 
     * @param id 报警记录主键
     * @return 报警记录
     */
    @Override
    public WxcxcProjectAlarmRecord selectWxcxcProjectAlarmRecordById(Long id)
    {
        return wxcxcProjectAlarmRecordMapper.selectWxcxcProjectAlarmRecordById(id);
    }

    /**
     * 查询报警记录列表
     * 
     * @param wxcxcProjectAlarmRecord 报警记录
     * @return 报警记录
     */
    @Override
    public List<WxcxcProjectAlarmRecord> selectWxcxcProjectAlarmRecordList(WxcxcProjectAlarmRecord wxcxcProjectAlarmRecord)
    {
        return wxcxcProjectAlarmRecordMapper.selectWxcxcProjectAlarmRecordList(wxcxcProjectAlarmRecord);
    }

    /**
     * 新增报警记录
     * 
     * @param wxcxcProjectAlarmRecord 报警记录
     * @return 结果
     */
    @Override
    public int insertWxcxcProjectAlarmRecord(WxcxcProjectAlarmRecord wxcxcProjectAlarmRecord)
    {
        wxcxcProjectAlarmRecord.setCreateTime(DateUtils.getNowDate());
        return wxcxcProjectAlarmRecordMapper.insertWxcxcProjectAlarmRecord(wxcxcProjectAlarmRecord);
    }

    /**
     * 修改报警记录
     * 
     * @param wxcxcProjectAlarmRecord 报警记录
     * @return 结果
     */
    @Override
    public int updateWxcxcProjectAlarmRecord(WxcxcProjectAlarmRecord wxcxcProjectAlarmRecord)
    {
        wxcxcProjectAlarmRecord.setUpdateTime(DateUtils.getNowDate());
        return wxcxcProjectAlarmRecordMapper.updateWxcxcProjectAlarmRecord(wxcxcProjectAlarmRecord);
    }

    /**
     * 批量删除报警记录
     * 
     * @param ids 需要删除的报警记录主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcProjectAlarmRecordByIds(Long[] ids)
    {
        return wxcxcProjectAlarmRecordMapper.deleteWxcxcProjectAlarmRecordByIds(ids);
    }

    /**
     * 删除报警记录信息
     * 
     * @param id 报警记录主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcProjectAlarmRecordById(Long id)
    {
        return wxcxcProjectAlarmRecordMapper.deleteWxcxcProjectAlarmRecordById(id);
    }

    // TODO 发送告警
    @Override
    public void sendAlarm(Long pointId,Long alarmConfigId){

    }
}
