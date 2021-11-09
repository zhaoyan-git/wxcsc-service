package com.ruoyi.wxcxc.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.wxcxc.domain.*;
import com.ruoyi.wxcxc.dto.BusinessMemberDto;
import com.ruoyi.wxcxc.mapper.WxcxcConsoleMapper;
import com.ruoyi.wxcxc.service.*;
import com.ruoyi.wxcxc.util.PackDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.logging.Handler;

@RestController
@RequestMapping("/iot/console")
public class ConsoleController extends BaseController {
    @Autowired
    private IWxcxcBusinessMemberService wxcxcBusinessMemberService;
    @Autowired
    private IWxcxcProjectService wxcxcProjectService;
    @Autowired
    private IWxcxcProjectStructureTypeService wxcxcProjectStructureTypeService;
    @Autowired
    private IWxcxcProjectStructureService wxcxcProjectStructureService;
    @Autowired
    private IWxcxcBusinessService wxcxcBusinessService;
    @Autowired
    private PackDto packDto;
    @Autowired
    private IWxcxcDeviceGatewayService wxcxcDeviceGatewayService;
    @Autowired
    private IWxcxcProjectPointGroupService wxcxcProjectPointGroupService;
    @Autowired
    private IWxcxcDeviceService wxcxcDeviceService;
    @Autowired
    private IWxcxcDataBusinessService wxcxcDataBusinessService;

    // 获取企业全部项目
    @PreAuthorize("@ss.hasPermi('iot:console')")
    @GetMapping("/projectListBusiness")
    public List<WxcxcProject> projectListBusiness() {
        WxcxcProject wxcxcProject = new WxcxcProject();
        wxcxcProject.setBusinessId(getBusinessId());

        List<WxcxcProject> list = wxcxcProjectService.selectWxcxcProjectList(wxcxcProject);

        return list;
    }

    // 根据用户权限获取项目
    @PreAuthorize("@ss.hasPermi('iot:console')")
    @GetMapping("/projectListByUserRole")
    public List<WxcxcProject> projectListByUserRole() {
        SysUser user = SecurityUtils.getLoginUser().getUser();

        List<WxcxcProject> list = wxcxcProjectService.selectWxcxcProjectByUserId(user.getUserId());

        return list;
    }

    @PreAuthorize("@ss.hasPermi('iot:console')")
    @GetMapping("/projectStructureTypeList")
    public List<WxcxcProjectStructureType> projectStructureTypeList() {
        WxcxcProjectStructureType wxcxcProjectStructureType = new WxcxcProjectStructureType();
        wxcxcProjectStructureType.setDisableFlag("0");
        List<WxcxcProjectStructureType> list = wxcxcProjectStructureTypeService.selectWxcxcProjectStructureTypeList(wxcxcProjectStructureType);

        return list;
    }

    @PreAuthorize("@ss.hasPermi('iot:console')")
    @GetMapping("/projectStructureListByProjectId")
    public List<WxcxcProjectStructure> projectStructureListByProjectId(WxcxcProjectStructure wxcxcProjectStructure) {
        if (null == wxcxcProjectStructure)
            wxcxcProjectStructure = new WxcxcProjectStructure();

        List<WxcxcProjectStructure> list = wxcxcProjectStructureService.selectWxcxcProjectStructureList(wxcxcProjectStructure);

        return list;
    }

    @PreAuthorize("@ss.hasPermi('iot:console')")
    @GetMapping("/business/get")
    public AjaxResult getBusiness() {
        return AjaxResult.success(wxcxcBusinessService.selectWxcxcBusinessById(getBusinessId()));
    }

    @PreAuthorize("@ss.hasPermi('iot:console')")
    @PostMapping("/business/update")
    public AjaxResult updateBusiness(@RequestBody WxcxcBusiness wxcxcBusiness) {
        return toAjax(wxcxcBusinessService.updateWxcxcBusiness(wxcxcBusiness));
    }

    @PreAuthorize("@ss.hasPermi('iot:console')")
    @PostMapping("/businessMemeber/listDto")
    public TableDataInfo consoleListBusinessMemberDto(BusinessMemberDto businessMemberDto) {
        if (null == businessMemberDto)
            businessMemberDto = new BusinessMemberDto();
        businessMemberDto.setBusinessId(getBusinessId());

        startPage();

        if (null == businessMemberDto || null == businessMemberDto.getBusinessId()) {
            return getDataTable(new ArrayList<>());
        } else {
            WxcxcBusinessMember wxcxcBusinessMember = new WxcxcBusinessMember();
            wxcxcBusinessMember.setBusinessId(businessMemberDto.getBusinessId());
            List<WxcxcBusinessMember> list = wxcxcBusinessMemberService.selectWxcxcBusinessMemberList(wxcxcBusinessMember);

            List<BusinessMemberDto> returnList = new ArrayList<>();

            for (WxcxcBusinessMember item : list) {
                returnList.add(packDto.generateBusinessMemberDtoById(item.getId()));
            }
            return getDataTable(returnList);
        }
    }

    // 获取网关列表
    @PreAuthorize("@ss.hasPermi('iot:console')")
    @GetMapping("/deviceGatewayList")
    public List<WxcxcDeviceGateway> deviceGatewayList(WxcxcDeviceGateway wxcxcDeviceGateway) {
        List<WxcxcDeviceGateway> list = wxcxcDeviceGatewayService.selectWxcxcDeviceGatewayList(wxcxcDeviceGateway);

        return list;
    }

    // 根据结构物ID获取测点分组
    @PreAuthorize("@ss.hasPermi('iot:console')")
    @GetMapping("/projectPointGroup")
    public List<WxcxcProjectPointGroup> projectPointGroup(WxcxcProjectPointGroup wxcxcProjectPointGroup) {
        List<WxcxcProjectPointGroup> list = wxcxcProjectPointGroupService.selectWxcxcProjectPointGroupList(wxcxcProjectPointGroup);

        return list;
    }

    // 根据项目ID获取全部设备
    @PreAuthorize("@ss.hasPermi('iot:console')")
    @GetMapping("/projectDeviceList")
    public List<WxcxcDevice> projectDeviceList(WxcxcDevice wxcxcDevice) {
        List<WxcxcDevice> list = wxcxcDeviceService.selectWxcxcDeviceList(wxcxcDevice);

        return list;
    }

    // 获取仪表盘数据
    @PreAuthorize("@ss.hasPermi('iot:console')")
    @GetMapping("/dashboardBusiness")
    public Map<String, Object> dashboardBusiness() {
        Map<String, Object> map = new HashMap<>();
        WxcxcDataBusiness wxcxcDataBusiness = new WxcxcDataBusiness();
        wxcxcDataBusiness.setBusinessId(getBusinessId());

        Map<String, Object> params = new HashMap<>();
        params.put("beginTime", new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 7)));
        wxcxcDataBusiness.setParams(params);

        // 返回数据列表，包含数据产生
        List<WxcxcDataBusiness> list = wxcxcDataBusinessService.selectWxcxcDataBusinessList(wxcxcDataBusiness);
        if (null == list || 0 == list.size()) {
            list = new ArrayList<>();
            wxcxcDataBusiness.setDataCount("0");
            wxcxcDataBusiness.setProjectCount("0");
            wxcxcDataBusiness.setStructureCount("0");
            wxcxcDataBusiness.setDeviceCount("0");

            list.add(wxcxcDataBusiness);
        }
        map.put("businessData", list);

        // 项目列表
        WxcxcProject wxcxcProject = new WxcxcProject();
        wxcxcProject.setBusinessId(getBusinessId());
        List<WxcxcProject> wxcxcProjectList = wxcxcProjectService.selectWxcxcProjectList(wxcxcProject);
        map.put("projectList", wxcxcProjectList);

        return map;
    }

    // 获取设备数据

    // 获取测点数据

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
