package com.ruoyi.wxcxc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxcxc.mapper.WxcxcArticleChannelMapper;
import com.ruoyi.wxcxc.domain.WxcxcArticleChannel;
import com.ruoyi.wxcxc.service.IWxcxcArticleChannelService;

/**
 * 栏目Service业务层处理
 * 
 * @author l2202
 * @date 2021-10-16
 */
@Service
public class WxcxcArticleChannelServiceImpl implements IWxcxcArticleChannelService 
{
    @Autowired
    private WxcxcArticleChannelMapper wxcxcArticleChannelMapper;

    /**
     * 查询栏目
     * 
     * @param id 栏目主键
     * @return 栏目
     */
    @Override
    public WxcxcArticleChannel selectWxcxcArticleChannelById(Long id)
    {
        return wxcxcArticleChannelMapper.selectWxcxcArticleChannelById(id);
    }

    /**
     * 查询栏目列表
     * 
     * @param wxcxcArticleChannel 栏目
     * @return 栏目
     */
    @Override
    public List<WxcxcArticleChannel> selectWxcxcArticleChannelList(WxcxcArticleChannel wxcxcArticleChannel)
    {
        return wxcxcArticleChannelMapper.selectWxcxcArticleChannelList(wxcxcArticleChannel);
    }

    /**
     * 新增栏目
     * 
     * @param wxcxcArticleChannel 栏目
     * @return 结果
     */
    @Override
    public int insertWxcxcArticleChannel(WxcxcArticleChannel wxcxcArticleChannel)
    {
        return wxcxcArticleChannelMapper.insertWxcxcArticleChannel(wxcxcArticleChannel);
    }

    /**
     * 修改栏目
     * 
     * @param wxcxcArticleChannel 栏目
     * @return 结果
     */
    @Override
    public int updateWxcxcArticleChannel(WxcxcArticleChannel wxcxcArticleChannel)
    {
        return wxcxcArticleChannelMapper.updateWxcxcArticleChannel(wxcxcArticleChannel);
    }

    /**
     * 批量删除栏目
     * 
     * @param ids 需要删除的栏目主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcArticleChannelByIds(Long[] ids)
    {
        return wxcxcArticleChannelMapper.deleteWxcxcArticleChannelByIds(ids);
    }

    /**
     * 删除栏目信息
     * 
     * @param id 栏目主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcArticleChannelById(Long id)
    {
        return wxcxcArticleChannelMapper.deleteWxcxcArticleChannelById(id);
    }
}
