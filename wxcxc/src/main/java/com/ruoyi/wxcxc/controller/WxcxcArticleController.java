package com.ruoyi.wxcxc.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.wxcxc.domain.WxcxcArticle;
import com.ruoyi.wxcxc.service.IWxcxcArticleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文章Controller
 * 
 * @author l2202
 * @date 2021-10-16
 */
@RestController
@RequestMapping("/iot/article")
public class WxcxcArticleController extends BaseController
{
    @Autowired
    private IWxcxcArticleService wxcxcArticleService;

    /**
     * 查询文章列表
     */
    @PreAuthorize("@ss.hasPermi('iot:article:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxcxcArticle wxcxcArticle)
    {
        startPage();
        List<WxcxcArticle> list = wxcxcArticleService.selectWxcxcArticleList(wxcxcArticle);
        return getDataTable(list);
    }

    /**
     * 导出文章列表
     */
    @PreAuthorize("@ss.hasPermi('iot:article:export')")
    @Log(title = "文章", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WxcxcArticle wxcxcArticle)
    {
        List<WxcxcArticle> list = wxcxcArticleService.selectWxcxcArticleList(wxcxcArticle);
        ExcelUtil<WxcxcArticle> util = new ExcelUtil<WxcxcArticle>(WxcxcArticle.class);
        return util.exportExcel(list, "文章数据");
    }

    /**
     * 获取文章详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:article:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wxcxcArticleService.selectWxcxcArticleById(id));
    }

    /**
     * 新增文章
     */
    @PreAuthorize("@ss.hasPermi('iot:article:add')")
    @Log(title = "文章", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxcxcArticle wxcxcArticle)
    {
        return toAjax(wxcxcArticleService.insertWxcxcArticle(wxcxcArticle));
    }

    /**
     * 修改文章
     */
    @PreAuthorize("@ss.hasPermi('iot:article:edit')")
    @Log(title = "文章", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxcxcArticle wxcxcArticle)
    {
        return toAjax(wxcxcArticleService.updateWxcxcArticle(wxcxcArticle));
    }

    /**
     * 删除文章
     */
    @PreAuthorize("@ss.hasPermi('iot:article:remove')")
    @Log(title = "文章", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxcxcArticleService.deleteWxcxcArticleByIds(ids));
    }
}
