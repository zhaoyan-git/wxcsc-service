package com.ruoyi.wxcxc.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxcxc.mapper.WxcxcArticleMapper;
import com.ruoyi.wxcxc.domain.WxcxcArticle;
import com.ruoyi.wxcxc.service.IWxcxcArticleService;

/**
 * 文章Service业务层处理
 * 
 * @author l2202
 * @date 2021-10-16
 */
@Service
public class WxcxcArticleServiceImpl implements IWxcxcArticleService 
{
    @Autowired
    private WxcxcArticleMapper wxcxcArticleMapper;

    /**
     * 查询文章
     * 
     * @param id 文章主键
     * @return 文章
     */
    @Override
    public WxcxcArticle selectWxcxcArticleById(Long id)
    {
        return wxcxcArticleMapper.selectWxcxcArticleById(id);
    }

    /**
     * 查询文章列表
     * 
     * @param wxcxcArticle 文章
     * @return 文章
     */
    @Override
    public List<WxcxcArticle> selectWxcxcArticleList(WxcxcArticle wxcxcArticle)
    {
        return wxcxcArticleMapper.selectWxcxcArticleList(wxcxcArticle);
    }

    /**
     * 新增文章
     * 
     * @param wxcxcArticle 文章
     * @return 结果
     */
    @Override
    public int insertWxcxcArticle(WxcxcArticle wxcxcArticle)
    {
        wxcxcArticle.setCreateTime(DateUtils.getNowDate());

        // 是否发布
        if ("0".equals(wxcxcArticle.getReleaseFlag()))
            wxcxcArticle.setReleaseTime(null);
        else
            wxcxcArticle.setReleaseTime(DateUtils.getNowDate());

        return wxcxcArticleMapper.insertWxcxcArticle(wxcxcArticle);
    }

    /**
     * 修改文章
     * 
     * @param wxcxcArticle 文章
     * @return 结果
     */
    @Override
    public int updateWxcxcArticle(WxcxcArticle wxcxcArticle)
    {
        wxcxcArticle.setUpdateTime(DateUtils.getNowDate());

        // 是否发布
        if ("0".equals(wxcxcArticle.getReleaseFlag()))
            wxcxcArticle.setReleaseTime(null);
        else
            wxcxcArticle.setReleaseTime(DateUtils.getNowDate());

        return wxcxcArticleMapper.updateWxcxcArticle(wxcxcArticle);
    }

    /**
     * 批量删除文章
     * 
     * @param ids 需要删除的文章主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcArticleByIds(Long[] ids)
    {
        return wxcxcArticleMapper.deleteWxcxcArticleByIds(ids);
    }

    /**
     * 删除文章信息
     * 
     * @param id 文章主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcArticleById(Long id)
    {
        return wxcxcArticleMapper.deleteWxcxcArticleById(id);
    }
}
