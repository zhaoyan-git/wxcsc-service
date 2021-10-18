package com.ruoyi.wxcxc.mapper;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxcArticle;

/**
 * 文章Mapper接口
 * 
 * @author l2202
 * @date 2021-10-16
 */
public interface WxcxcArticleMapper 
{
    /**
     * 查询文章
     * 
     * @param id 文章主键
     * @return 文章
     */
    public WxcxcArticle selectWxcxcArticleById(Long id);

    /**
     * 查询文章列表
     * 
     * @param wxcxcArticle 文章
     * @return 文章集合
     */
    public List<WxcxcArticle> selectWxcxcArticleList(WxcxcArticle wxcxcArticle);

    /**
     * 新增文章
     * 
     * @param wxcxcArticle 文章
     * @return 结果
     */
    public int insertWxcxcArticle(WxcxcArticle wxcxcArticle);

    /**
     * 修改文章
     * 
     * @param wxcxcArticle 文章
     * @return 结果
     */
    public int updateWxcxcArticle(WxcxcArticle wxcxcArticle);

    /**
     * 删除文章
     * 
     * @param id 文章主键
     * @return 结果
     */
    public int deleteWxcxcArticleById(Long id);

    /**
     * 批量删除文章
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxcxcArticleByIds(Long[] ids);
}
