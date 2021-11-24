package com.ruoyi.wxcxc.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.wxcxc.domain.WxcxcBusinessMember;
import com.ruoyi.wxcxc.service.IWxcxcBusinessMemberService;
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
import com.ruoyi.wxcxc.domain.WxcxcProject;
import com.ruoyi.wxcxc.service.IWxcxcProjectService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目Controller
 *
 * @author l62202
 * @date 2021-10-17
 */
@RestController
@RequestMapping("/iot/project")
public class WxcxcProjectController extends BaseController {
    @Autowired
    private IWxcxcProjectService wxcxcProjectService;
    @Autowired
    private IWxcxcBusinessMemberService wxcxcBusinessMemberService;

    /**
     * 查询项目列表
     */
    @PreAuthorize("@ss.hasPermi('iot:integrate:project')")
    @GetMapping("/list")
    public TableDataInfo list(WxcxcProject wxcxcProject) {
        if (null == wxcxcProject)
            wxcxcProject = new WxcxcProject();

        wxcxcProject.setBusinessId(getBusinessId());

        startPage();
        List<WxcxcProject> list = wxcxcProjectService.selectWxcxcProjectList(wxcxcProject);
        return getDataTable(list);
    }

    /**
     * 导出项目列表
     */
    @PreAuthorize("@ss.hasPermi('iot:integrate:project')")
    @Log(title = "项目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WxcxcProject wxcxcProject) {
        List<WxcxcProject> list = wxcxcProjectService.selectWxcxcProjectList(wxcxcProject);
        ExcelUtil<WxcxcProject> util = new ExcelUtil<WxcxcProject>(WxcxcProject.class);
        return util.exportExcel(list, "项目数据");
    }

    /**
     * 获取项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:integrate:project')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(wxcxcProjectService.selectWxcxcProjectById(id));
    }

    /**
     * 新增项目
     */
    @PreAuthorize("@ss.hasPermi('iot:integrate:project')")
    @Log(title = "项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxcxcProject wxcxcProject) {
        if (null == wxcxcProject)
            wxcxcProject = new WxcxcProject();

        wxcxcProject.setBusinessId(getBusinessId());

        return toAjax(wxcxcProjectService.insertWxcxcProject(wxcxcProject));
    }

    /**
     * 修改项目
     */
    @PreAuthorize("@ss.hasPermi('iot:integrate:project')")
    @Log(title = "项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxcxcProject wxcxcProject) {
        return toAjax(wxcxcProjectService.updateWxcxcProject(wxcxcProject));
    }

    /**
     * 删除项目
     */
    @PreAuthorize("@ss.hasPermi('iot:integrate:project')")
    @Log(title = "项目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(wxcxcProjectService.deleteWxcxcProjectByIds(ids));
    }

    // 根据企业ID获取项目列表
    @PreAuthorize("@ss.hasPermi('iot:integrate:project')")
    @GetMapping("/getListByBusinessId")
    public List<WxcxcProject> getListByBusinessId(WxcxcProject wxcxcProject) {
        if (null == wxcxcProject || null == wxcxcProject.getBusinessId())
            return new ArrayList<>();

        wxcxcProject.setBusinessId(wxcxcProject.getBusinessId());

        List<WxcxcProject> list = wxcxcProjectService.selectWxcxcProjectList(wxcxcProject);
        return list;
    }


    private Long getBusinessId() {
        // 获取用户
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 获取企业信息
        WxcxcBusinessMember wxcxcBusinessMember = new WxcxcBusinessMember();
        wxcxcBusinessMember.setSysUserId(user.getUserId());
        List<WxcxcBusinessMember> wxcxcBusinessMemberList = wxcxcBusinessMemberService.selectWxcxcBusinessMemberList(wxcxcBusinessMember);

        Long businessId = null;

        for (WxcxcBusinessMember item : wxcxcBusinessMemberList) {
            businessId = item.getBusinessId();
        }

        return businessId;
    }
}

