package com.ruoyi.wxcxc.service;

import java.util.List;

import com.ruoyi.wxcxc.domain.WxcxcBusinessMember;
import com.ruoyi.wxcxc.dto.BusinessMemberDto;

/**
 * 企业人员Service接口
 *
 * @author l62202
 * @date 2021-10-17
 */
public interface IWxcxcBusinessMemberService {
    /**
     * 查询企业人员
     *
     * @param id 企业人员主键
     * @return 企业人员
     */
    public WxcxcBusinessMember selectWxcxcBusinessMemberById(Long id);

    /**
     * 查询企业人员列表
     *
     * @param wxcxcBusinessMember 企业人员
     * @return 企业人员集合
     */
    public List<WxcxcBusinessMember> selectWxcxcBusinessMemberList(WxcxcBusinessMember wxcxcBusinessMember);

    /**
     * 新增企业人员
     *
     * @param wxcxcBusinessMember 企业人员
     * @return 结果
     */
    public int insertWxcxcBusinessMember(WxcxcBusinessMember wxcxcBusinessMember);

    /**
     * 修改企业人员
     *
     * @param wxcxcBusinessMember 企业人员
     * @return 结果
     */
    public int updateWxcxcBusinessMember(WxcxcBusinessMember wxcxcBusinessMember);

    /**
     * 批量删除企业人员
     *
     * @param ids 需要删除的企业人员主键集合
     * @return 结果
     */
    public int deleteWxcxcBusinessMemberByIds(Long[] ids);

    /**
     * 删除企业人员信息
     *
     * @param id 企业人员主键
     * @return 结果
     */
    public int deleteWxcxcBusinessMemberById(Long id);

    public int insertWxcxcBusinessMember(BusinessMemberDto businessMemberDto);

    public int updateWxcxcBusinessMember(BusinessMemberDto businessMemberDto);

}
