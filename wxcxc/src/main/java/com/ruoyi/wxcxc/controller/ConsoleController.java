package com.ruoyi.wxcxc.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.wxcxc.domain.*;
import com.ruoyi.wxcxc.dto.BusinessMemberDto;
import com.ruoyi.wxcxc.dto.ReportFormConditionDto;
import com.ruoyi.wxcxc.dto.ReportFormDto;
import com.ruoyi.wxcxc.mapper.*;
import com.ruoyi.wxcxc.service.*;
import com.ruoyi.wxcxc.util.PackDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
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
    @Autowired
    private IWxcxcProjectAlarmRecordService wxcxcProjectAlarmRecordService;
    @Autowired
    private WxcxcProjectAlarmRecordMapper wxcxcProjectAlarmRecordMapper;
    @Autowired
    private WxcxcProjectPointMapper wxcxcProjectPointMapper;
    @Autowired
    private WxcxcProjectStructureMapper wxcxcProjectStructureMapper;
    @Autowired
    private WxcxcProjectMapper wxcxcProjectMapper;
    @Autowired
    private WxcxcProjectPointDataMapper wxcxcProjectPointDataMapper;
    @Autowired
    private WxcxcProjectAlarmConfigMapper wxcxcProjectAlarmConfigMapper;

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


        params.put("beginTime", new SimpleDateFormat("yyMMdd").format(new Date().getTime() - (1000 * 60 * 60 * 24 * 7)));
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


    /**
     * 查询报警记录列表
     */
    @PreAuthorize("@ss.hasPermi('iot:console')")
    @GetMapping("/projectAlarmRecord/list/byUser")
    public TableDataInfo listByUser(WxcxcProjectAlarmRecord wxcxcProjectAlarmRecord) {
        startPage();

        List<WxcxcProjectAlarmRecord> list = null;

        if (null == wxcxcProjectAlarmRecord.getProjectId()) {
            SysUser user = SecurityUtils.getLoginUser().getUser();

            // 根据用户id获取企业id
            WxcxcBusinessMember wxcxcBusinessMember = new WxcxcBusinessMember();
            wxcxcBusinessMember.setSysUserId(user.getUserId());
            List<WxcxcBusinessMember> businessMemberList = wxcxcBusinessMemberService.selectWxcxcBusinessMemberList(wxcxcBusinessMember);
            if (null != businessMemberList && 1 == businessMemberList.size()) {
                boolean isEnterpriseMng = false;

                // 判断是否有管理员角色
                for (SysRole roleItem : user.getRoles()) {
                    if (roleItem.getRoleId().equals(4l)) {
                        isEnterpriseMng = true;
                        break;
                    }
                }

                if (isEnterpriseMng) {
                    // 获取该企业下全部项目报警
                    list = wxcxcProjectAlarmRecordMapper.selectWxcxcProjectAlarmRecordListByMemberIdProject(businessMemberList.get(0).getBusinessId(), wxcxcProjectAlarmRecord);
                } else {
                    // 获取该用户下全部项目报警
                    list = wxcxcProjectAlarmRecordMapper.selectWxcxcProjectAlarmRecordListByMemberId(businessMemberList.get(0).getBusinessId(), wxcxcProjectAlarmRecord);
                }

            } else {
                list = new ArrayList<>();
//                list = wxcxcProjectAlarmRecordMapper.selectWxcxcProjectAlarmRecordListByMemberId(null, wxcxcProjectAlarmRecord);
            }


        } else {
            list = wxcxcProjectAlarmRecordMapper.selectWxcxcProjectAlarmRecordListByMemberId(null, wxcxcProjectAlarmRecord);
        }

        return getDataTable(list);
    }

    // 生成报表
    @PreAuthorize("@ss.hasPermi('iot:console')")
    @GetMapping("/reportForm")
    public AjaxResult listByUser(ReportFormConditionDto reportFormConditionDto) {
        // 根据项目id获取所有测点
        List<WxcxcProjectPoint> wxcxcProjectPointList = wxcxcProjectPointMapper.selectWxcxcProjectPointByProjectId(reportFormConditionDto.getProjectId());

        List<ReportFormDto> list = new ArrayList<>();

        if (null != wxcxcProjectPointList) {
            if ("1".equals(reportFormConditionDto.getGatherType())) {
                for (WxcxcProjectPoint item : wxcxcProjectPointList) {
                    ReportFormDto reportFormDto = new ReportFormDto();

                    // 获取结构物名
                    WxcxcProjectStructure wxcxcProjectStructureItem = wxcxcProjectStructureMapper.selectWxcxcProjectStructureById(item.getProjectStructureId());
                    if (null == wxcxcProjectStructureItem) continue;

                    // 获取项目名
                    WxcxcProject wxcxcProjectItem = wxcxcProjectMapper.selectWxcxcProjectById(wxcxcProjectStructureItem.getProjectId());
                    if (null == wxcxcProjectItem) continue;

                    reportFormDto.setProjectName(wxcxcProjectItem.getName());
                    reportFormDto.setStructureName(wxcxcProjectStructureItem.getName());
                    reportFormDto.setPointName(item.getName());

                    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH-mm");

                    // 获取测点数据
                    List<WxcxcProjectPointData> wxcxcProjectPointDataList = wxcxcProjectPointDataMapper.selectPointDataList(wxcxcProjectStructureItem.getId(), item.getId(),
                            reportFormConditionDto.getBeginTime(), reportFormConditionDto.getEndTime());

                    // 获取控制值累计变化值（mm）
                    WxcxcProjectAlarmConfig wxcxcProjectAlarmConfigCondition = new WxcxcProjectAlarmConfig();
                    wxcxcProjectAlarmConfigCondition.setPointId(item.getId());
                    List<WxcxcProjectAlarmConfig> wxcxcProjectAlarmConfigList = wxcxcProjectAlarmConfigMapper.selectWxcxcProjectAlarmConfigList(wxcxcProjectAlarmConfigCondition);
                    if (null != wxcxcProjectAlarmConfigList) {
                        for (WxcxcProjectAlarmConfig wxcxcProjectAlarmConfigItem : wxcxcProjectAlarmConfigList) {
                            if (null == reportFormDto.getControlValue() || "".equals(reportFormDto.getControlValue())) {
                                reportFormDto.setControlValue(wxcxcProjectAlarmConfigItem.getThreshold().toString());
                            } else {
                                reportFormDto.setControlValue("," + wxcxcProjectAlarmConfigItem.getThreshold());
                            }
                        }
                    }

                    // 封装数据
                    if (null != reportFormConditionDto.getAttrDataList() && 0 != reportFormConditionDto.getAttrDataList().size() &&
                            null != wxcxcProjectPointDataList && 0 != wxcxcProjectPointDataList.size()) {
                        for (int i = 0; i < wxcxcProjectPointDataList.size(); i++) {
                            WxcxcProjectPointData projectPointDataItem = wxcxcProjectPointDataList.get(i);
                            ReportFormDto reportFormDtoItem = new ReportFormDto();

                            reportFormDtoItem.setProjectName(reportFormDto.getProjectName());
                            reportFormDtoItem.setStructureName(reportFormDto.getStructureName());
                            reportFormDtoItem.setPointName(reportFormDto.getPointName());

                            // 上一条数据
                            WxcxcProjectPointData pointDataPrevious = null;
                            if (0 == i)
                                pointDataPrevious = wxcxcProjectPointDataMapper.selectPointDataPrevious(wxcxcProjectStructureItem.getId(), projectPointDataItem.getPointId());
                            else
                                wxcxcProjectPointDataList.get(i - 1);


                            for (String attrStr : reportFormConditionDto.getAttrDataList()) {
                                switch (attrStr) {
                                    case "1":
                                        // 上次沉降值（mm）
                                        if (null != pointDataPrevious)
                                            reportFormDtoItem.setLastValue(pointDataPrevious.getData());
                                        break;
                                    case "2":
                                        // 当前沉降值（mm）
                                        reportFormDtoItem.setCurrentValue(projectPointDataItem.getData());
                                        break;
                                    case "4":
                                        // 控制值累计变化值（mm）
                                        reportFormDtoItem.setControlValue(reportFormDto.getCurrentValue());
                                        break;
                                    case "6":
                                        // 本次监测时间
                                        reportFormDtoItem.setCurrentDataTime(sdf.format(projectPointDataItem.getCreateTime()));
                                        break;
                                    case "7":
                                        // 上次监测时间
                                        if (null != pointDataPrevious)
                                            reportFormDtoItem.setLastDataTime(sdf.format(pointDataPrevious.getCreateTime()));
                                        break;
                                }
                            }

                            list.add(reportFormDtoItem);
                        }
                    }

//                    if (null != reportFormConditionDto.getAttrDataList()) {
//                        for (String attrStr : reportFormConditionDto.getAttrDataList()) {
//                            switch (attrStr) {
//                                case "1":
//                                    // 上次沉降值（mm）
//                                    WxcxcProjectPointData wxcxcProjectPointData1 = wxcxcProjectPointDataMapper.selectWxcxcProjectPointDataListReportForm(wxcxcProjectStructureItem.getId(), item.getId(),
//                                            reportFormConditionDto.getBeginTime(), reportFormConditionDto.getEndTime(), 1l);
//
//                                    if (null != wxcxcProjectPointData1)
//                                        reportFormDto.setLastValue(wxcxcProjectPointData1.getData());
//                                    break;
//                                case "2":
//                                    // 当前沉降值（mm）
//                                    WxcxcProjectPointData wxcxcProjectPointData2 = wxcxcProjectPointDataMapper.selectWxcxcProjectPointDataListReportForm(wxcxcProjectStructureItem.getId(), item.getId(),
//                                            reportFormConditionDto.getBeginTime(), reportFormConditionDto.getEndTime(), 0l);
//
//                                    if (null != wxcxcProjectPointData2)
//                                        reportFormDto.setCurrentValue(wxcxcProjectPointData2.getData());
//                                    break;
//                                case "3":
//                                    // 变化速率（mm/d）
//                                    break;
////                                case "4":
////                                    // 控制值累计变化值（mm）
////                                    WxcxcProjectAlarmConfig wxcxcProjectAlarmConfigCondition = new WxcxcProjectAlarmConfig();
////                                    wxcxcProjectAlarmConfigCondition.setPointId(item.getId());
////                                    List<WxcxcProjectAlarmConfig> wxcxcProjectAlarmConfigList = wxcxcProjectAlarmConfigMapper.selectWxcxcProjectAlarmConfigList(wxcxcProjectAlarmConfigCondition);
////                                    if (null != wxcxcProjectAlarmConfigList) {
////                                        for (WxcxcProjectAlarmConfig wxcxcProjectAlarmConfigItem : wxcxcProjectAlarmConfigList) {
////                                            if (null == reportFormDto.getControlValue() || "".equals(reportFormDto.getControlValue())) {
////                                                reportFormDto.setControlValue(wxcxcProjectAlarmConfigItem.getThreshold().toString());
////                                            } else {
////                                                reportFormDto.setControlValue("," + wxcxcProjectAlarmConfigItem.getThreshold());
////                                            }
////                                        }
////                                    }
////                                    break;
//                                case "5":
//                                    // 控制值变化速率值（mm/d）
//                                    break;
//                                case "6":
//                                    // 本次监测时间
//                                    WxcxcProjectPointData wxcxcProjectPointData6 = wxcxcProjectPointDataMapper.selectWxcxcProjectPointDataListReportForm(wxcxcProjectStructureItem.getId(), item.getId(),
//                                            reportFormConditionDto.getBeginTime(), reportFormConditionDto.getEndTime(), 0l);
//
//                                    if (null != wxcxcProjectPointData6)
//                                        reportFormDto.setCurrentDataTime(sdf.format(wxcxcProjectPointData6.getCreateTime()));
//                                    break;
//                                case "7":
//                                    // 上次监测时间
//                                    WxcxcProjectPointData wxcxcProjectPointData7 = wxcxcProjectPointDataMapper.selectWxcxcProjectPointDataListReportForm(wxcxcProjectStructureItem.getId(), item.getId(),
//                                            reportFormConditionDto.getBeginTime(), reportFormConditionDto.getEndTime(), 1l);
//
//                                    if (null != wxcxcProjectPointData7)
//                                        reportFormDto.setLastDataTime(sdf.format(wxcxcProjectPointData7.getCreateTime()));
//                                    break;
//                            }
//                        }
//                    }

                }
            } else if ("2".equals(reportFormConditionDto.getGatherType())) {

            }
        }

        ExcelUtil<ReportFormDto> util = new ExcelUtil<ReportFormDto>(ReportFormDto.class);
        return util.exportExcel(list, "沉降数据报表");
    }

    // 根据当前企业获取项目列表
    @PreAuthorize("@ss.hasPermi('iot:integrate:project')")
    @GetMapping("/getListByBusinessId")
    public List<WxcxcProject> getListByBusinessId() {
        WxcxcProject wxcxcProject = new WxcxcProject();
        wxcxcProject.setBusinessId(getBusinessId());

        List<WxcxcProject> list = wxcxcProjectService.selectWxcxcProjectList(wxcxcProject);
        return list;
    }

    /**
     * 获取报警记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:projectAlarmRecord')")
    @GetMapping(value = "/projectAlarmRecord/{id}")
    public AjaxResult getProjectAlarmRecordInfo(@PathVariable("id") Long id) {
        WxcxcProjectAlarmRecord wxcxcProjectAlarmRecord = wxcxcProjectAlarmRecordService.selectWxcxcProjectAlarmRecordById(id);

        //设置成已读
        wxcxcProjectAlarmRecord.setReadFlag("1");
        wxcxcProjectAlarmRecordService.updateWxcxcProjectAlarmRecord(wxcxcProjectAlarmRecord);

        return AjaxResult.success(wxcxcProjectAlarmRecord);
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
