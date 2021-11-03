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
import com.ruoyi.wxcxc.domain.WxcxcBusinessMemberRole;
import com.ruoyi.wxcxc.service.IWxcxcBusinessMemberRoleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 企业人员权限Controller
 * 
 * @author l62202
 * @date 2021-10-17
 */
@RestController
@RequestMapping("/iot/businessMemberRole")
public class WxcxcBusinessMemberRoleController extends BaseController
{
    @Autowired
    private IWxcxcBusinessMemberRoleService wxcxcBusinessMemberRoleService;

    /**
     * 查询企业人员权限列表
     */
    @PreAuthorize("@ss.hasPermi('iot:businessMemberRole')")
    @GetMapping("/list")
    public TableDataInfo list(WxcxcBusinessMemberRole wxcxcBusinessMemberRole)
    {
        startPage();
        List<WxcxcBusinessMemberRole> list = wxcxcBusinessMemberRoleService.selectWxcxcBusinessMemberRoleList(wxcxcBusinessMemberRole);
        return getDataTable(list);
    }

    /**
     * 导出企业人员权限列表
     */
    @PreAuthorize("@ss.hasPermi('iot:businessMemberRole')")
    @Log(title = "企业人员权限", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WxcxcBusinessMemberRole wxcxcBusinessMemberRole)
    {
        List<WxcxcBusinessMemberRole> list = wxcxcBusinessMemberRoleService.selectWxcxcBusinessMemberRoleList(wxcxcBusinessMemberRole);
        ExcelUtil<WxcxcBusinessMemberRole> util = new ExcelUtil<WxcxcBusinessMemberRole>(WxcxcBusinessMemberRole.class);
        return util.exportExcel(list, "企业人员权限数据");
    }

    /**
     * 获取企业人员权限详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:businessMemberRole')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wxcxcBusinessMemberRoleService.selectWxcxcBusinessMemberRoleById(id));
    }

    /**
     * 新增企业人员权限
     */
    @PreAuthorize("@ss.hasPermi('iot:businessMemberRole')")
    @Log(title = "企业人员权限", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxcxcBusinessMemberRole wxcxcBusinessMemberRole)
    {
        return toAjax(wxcxcBusinessMemberRoleService.insertWxcxcBusinessMemberRole(wxcxcBusinessMemberRole));
    }

    /**
     * 修改企业人员权限
     */
    @PreAuthorize("@ss.hasPermi('iot:businessMemberRole')")
    @Log(title = "企业人员权限", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxcxcBusinessMemberRole wxcxcBusinessMemberRole)
    {
        return toAjax(wxcxcBusinessMemberRoleService.updateWxcxcBusinessMemberRole(wxcxcBusinessMemberRole));
    }

    /**
     * 删除企业人员权限
     */
    @PreAuthorize("@ss.hasPermi('iot:businessMemberRole')")
    @Log(title = "企业人员权限", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxcxcBusinessMemberRoleService.deleteWxcxcBusinessMemberRoleByIds(ids));
    }
}
