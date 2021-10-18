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
import com.ruoyi.wxcxc.domain.WxcxcArticleChannel;
import com.ruoyi.wxcxc.service.IWxcxcArticleChannelService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 栏目Controller
 * 
 * @author l2202
 * @date 2021-10-16
 */
@RestController
@RequestMapping("/iot/channel")
public class WxcxcArticleChannelController extends BaseController
{
    @Autowired
    private IWxcxcArticleChannelService wxcxcArticleChannelService;

    /**
     * 查询栏目列表
     */
    @PreAuthorize("@ss.hasPermi('iot:channel:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxcxcArticleChannel wxcxcArticleChannel)
    {
        startPage();
        List<WxcxcArticleChannel> list = wxcxcArticleChannelService.selectWxcxcArticleChannelList(wxcxcArticleChannel);
        return getDataTable(list);
    }

    /**
     * 导出栏目列表
     */
    @PreAuthorize("@ss.hasPermi('iot:channel:export')")
    @Log(title = "栏目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WxcxcArticleChannel wxcxcArticleChannel)
    {
        List<WxcxcArticleChannel> list = wxcxcArticleChannelService.selectWxcxcArticleChannelList(wxcxcArticleChannel);
        ExcelUtil<WxcxcArticleChannel> util = new ExcelUtil<WxcxcArticleChannel>(WxcxcArticleChannel.class);
        return util.exportExcel(list, "栏目数据");
    }

    /**
     * 获取栏目详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:channel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wxcxcArticleChannelService.selectWxcxcArticleChannelById(id));
    }

    /**
     * 新增栏目
     */
    @PreAuthorize("@ss.hasPermi('iot:channel:add')")
    @Log(title = "栏目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxcxcArticleChannel wxcxcArticleChannel)
    {
        return toAjax(wxcxcArticleChannelService.insertWxcxcArticleChannel(wxcxcArticleChannel));
    }

    /**
     * 修改栏目
     */
    @PreAuthorize("@ss.hasPermi('iot:channel:edit')")
    @Log(title = "栏目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxcxcArticleChannel wxcxcArticleChannel)
    {
        return toAjax(wxcxcArticleChannelService.updateWxcxcArticleChannel(wxcxcArticleChannel));
    }

    /**
     * 删除栏目
     */
    @PreAuthorize("@ss.hasPermi('iot:channel:remove')")
    @Log(title = "栏目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxcxcArticleChannelService.deleteWxcxcArticleChannelByIds(ids));
    }
}
