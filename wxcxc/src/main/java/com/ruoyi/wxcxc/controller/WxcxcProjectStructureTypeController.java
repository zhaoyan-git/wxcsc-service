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
import com.ruoyi.wxcxc.domain.WxcxcProjectStructureType;
import com.ruoyi.wxcxc.service.IWxcxcProjectStructureTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 结构物选项Controller
 * 
 * @author l62202
 * @date 2021-10-16
 */
@RestController
@RequestMapping("/iot/projectStructureType")
public class WxcxcProjectStructureTypeController extends BaseController
{
    @Autowired
    private IWxcxcProjectStructureTypeService wxcxProjectStructureTypeService;

    /**
     * 查询结构物选项列表
     */
    @PreAuthorize("@ss.hasPermi('iot:projectStructureType:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxcxcProjectStructureType wxcxcProjectStructureType)
    {
        startPage();
        List<WxcxcProjectStructureType> list = wxcxProjectStructureTypeService.selectWxcxProjectStructureTypeList(wxcxcProjectStructureType);
        return getDataTable(list);
    }

    /**
     * 导出结构物选项列表
     */
    @PreAuthorize("@ss.hasPermi('iot:projectStructureType:export')")
    @Log(title = "结构物选项", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WxcxcProjectStructureType wxcxcProjectStructureType)
    {
        List<WxcxcProjectStructureType> list = wxcxProjectStructureTypeService.selectWxcxProjectStructureTypeList(wxcxcProjectStructureType);
        ExcelUtil<WxcxcProjectStructureType> util = new ExcelUtil<WxcxcProjectStructureType>(WxcxcProjectStructureType.class);
        return util.exportExcel(list, "结构物选项数据");
    }

    /**
     * 获取结构物选项详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:projectStructureType:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wxcxProjectStructureTypeService.selectWxcxProjectStructureTypeById(id));
    }

    /**
     * 新增结构物选项
     */
    @PreAuthorize("@ss.hasPermi('iot:projectStructureType:add')")
    @Log(title = "结构物选项", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxcxcProjectStructureType wxcxcProjectStructureType)
    {
        return toAjax(wxcxProjectStructureTypeService.insertWxcxProjectStructureType(wxcxcProjectStructureType));
    }

    /**
     * 修改结构物选项
     */
    @PreAuthorize("@ss.hasPermi('iot:projectStructureType:edit')")
    @Log(title = "结构物选项", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxcxcProjectStructureType wxcxcProjectStructureType)
    {
        return toAjax(wxcxProjectStructureTypeService.updateWxcxProjectStructureType(wxcxcProjectStructureType));
    }

    /**
     * 删除结构物选项
     */
    @PreAuthorize("@ss.hasPermi('iot:projectStructureType:remove')")
    @Log(title = "结构物选项", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxcxProjectStructureTypeService.deleteWxcxProjectStructureTypeByIds(ids));
    }
}
