package com.ruoyi.wxcxc.mapper;

import java.util.List;

import com.ruoyi.wxcxc.domain.WxcxcProjectAlarmRecord;
import org.apache.ibatis.annotations.Param;

/**
 * 报警记录Mapper接口
 *
 * @author l62202
 * @date 2021-11-11
 */
public interface WxcxcProjectAlarmRecordMapper {
    /**
     * 查询报警记录
     *
     * @param id 报警记录主键
     * @return 报警记录
     */
    public WxcxcProjectAlarmRecord selectWxcxcProjectAlarmRecordById(Long id);

    /**
     * 查询报警记录列表
     *
     * @param wxcxcProjectAlarmRecord 报警记录
     * @return 报警记录集合
     */
    public List<WxcxcProjectAlarmRecord> selectWxcxcProjectAlarmRecordList(WxcxcProjectAlarmRecord wxcxcProjectAlarmRecord);

    /**
     * 新增报警记录
     *
     * @param wxcxcProjectAlarmRecord 报警记录
     * @return 结果
     */
    public int insertWxcxcProjectAlarmRecord(WxcxcProjectAlarmRecord wxcxcProjectAlarmRecord);

    /**
     * 修改报警记录
     *
     * @param wxcxcProjectAlarmRecord 报警记录
     * @return 结果
     */
    public int updateWxcxcProjectAlarmRecord(WxcxcProjectAlarmRecord wxcxcProjectAlarmRecord);

    /**
     * 删除报警记录
     *
     * @param id 报警记录主键
     * @return 结果
     */
    public int deleteWxcxcProjectAlarmRecordById(Long id);

    /**
     * 批量删除报警记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxcxcProjectAlarmRecordByIds(Long[] ids);

    List<WxcxcProjectAlarmRecord> selectWxcxcProjectAlarmRecordListByMemberId(@Param("businessMemberId") Long businessMemberId, @Param("wxcxcProjectAlarmRecord") WxcxcProjectAlarmRecord wxcxcProjectAlarmRecord);
}
