package com.ruoyi.wxcxc.service;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxcArticleChannel;

/**
 * 栏目Service接口
 * 
 * @author l2202
 * @date 2021-10-16
 */
public interface IWxcxcArticleChannelService 
{
    /**
     * 查询栏目
     * 
     * @param id 栏目主键
     * @return 栏目
     */
    public WxcxcArticleChannel selectWxcxcArticleChannelById(Long id);

    /**
     * 查询栏目列表
     * 
     * @param wxcxcArticleChannel 栏目
     * @return 栏目集合
     */
    public List<WxcxcArticleChannel> selectWxcxcArticleChannelList(WxcxcArticleChannel wxcxcArticleChannel);

    /**
     * 新增栏目
     * 
     * @param wxcxcArticleChannel 栏目
     * @return 结果
     */
    public int insertWxcxcArticleChannel(WxcxcArticleChannel wxcxcArticleChannel);

    /**
     * 修改栏目
     * 
     * @param wxcxcArticleChannel 栏目
     * @return 结果
     */
    public int updateWxcxcArticleChannel(WxcxcArticleChannel wxcxcArticleChannel);

    /**
     * 批量删除栏目
     * 
     * @param ids 需要删除的栏目主键集合
     * @return 结果
     */
    public int deleteWxcxcArticleChannelByIds(Long[] ids);

    /**
     * 删除栏目信息
     * 
     * @param id 栏目主键
     * @return 结果
     */
    public int deleteWxcxcArticleChannelById(Long id);
}
