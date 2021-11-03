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
import com.ruoyi.wxcxc.domain.WxcxcNetty;
import com.ruoyi.wxcxc.service.IWxcxcNettyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * nettyController
 * 
 * @author l62202
 * @date 2021-10-26
 */
@RestController
@RequestMapping("/wxcxc/netty")
public class WxcxcNettyController extends BaseController
{
    @Autowired
    private IWxcxcNettyService wxcxcNettyService;

    /**
     * 查询netty列表
     */
    @PreAuthorize("@ss.hasPermi('wxcxc:netty:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxcxcNetty wxcxcNetty)
    {
        startPage();
        List<WxcxcNetty> list = wxcxcNettyService.selectWxcxcNettyList(wxcxcNetty);
        return getDataTable(list);
    }

    /**
     * 导出netty列表
     */
    @PreAuthorize("@ss.hasPermi('wxcxc:netty:export')")
    @Log(title = "netty", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WxcxcNetty wxcxcNetty)
    {
        List<WxcxcNetty> list = wxcxcNettyService.selectWxcxcNettyList(wxcxcNetty);
        ExcelUtil<WxcxcNetty> util = new ExcelUtil<WxcxcNetty>(WxcxcNetty.class);
        return util.exportExcel(list, "netty数据");
    }

    /**
     * 获取netty详细信息
     */
    @PreAuthorize("@ss.hasPermi('wxcxc:netty:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(wxcxcNettyService.selectWxcxcNettyById(id));
    }

    /**
     * 新增netty
     */
    @PreAuthorize("@ss.hasPermi('wxcxc:netty:add')")
    @Log(title = "netty", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxcxcNetty wxcxcNetty)
    {
        return toAjax(wxcxcNettyService.insertWxcxcNetty(wxcxcNetty));
    }

    /**
     * 修改netty
     */
    @PreAuthorize("@ss.hasPermi('wxcxc:netty:edit')")
    @Log(title = "netty", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxcxcNetty wxcxcNetty)
    {
        return toAjax(wxcxcNettyService.updateWxcxcNetty(wxcxcNetty));
    }

    /**
     * 删除netty
     */
    @PreAuthorize("@ss.hasPermi('wxcxc:netty:remove')")
    @Log(title = "netty", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(wxcxcNettyService.deleteWxcxcNettyByIds(ids));
    }
}
