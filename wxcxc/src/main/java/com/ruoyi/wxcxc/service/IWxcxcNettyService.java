package com.ruoyi.wxcxc.service;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxcNetty;

/**
 * nettyService接口
 * 
 * @author l62202
 * @date 2021-10-26
 */
public interface IWxcxcNettyService 
{
    /**
     * 查询netty
     * 
     * @param id netty主键
     * @return netty
     */
    public WxcxcNetty selectWxcxcNettyById(Integer id);

    /**
     * 查询netty列表
     * 
     * @param wxcxcNetty netty
     * @return netty集合
     */
    public List<WxcxcNetty> selectWxcxcNettyList(WxcxcNetty wxcxcNetty);

    /**
     * 新增netty
     * 
     * @param wxcxcNetty netty
     * @return 结果
     */
    public int insertWxcxcNetty(WxcxcNetty wxcxcNetty);

    /**
     * 修改netty
     * 
     * @param wxcxcNetty netty
     * @return 结果
     */
    public int updateWxcxcNetty(WxcxcNetty wxcxcNetty);

    /**
     * 批量删除netty
     * 
     * @param ids 需要删除的netty主键集合
     * @return 结果
     */
    public int deleteWxcxcNettyByIds(Integer[] ids);

    /**
     * 删除netty信息
     * 
     * @param id netty主键
     * @return 结果
     */
    public int deleteWxcxcNettyById(Integer id);
}
