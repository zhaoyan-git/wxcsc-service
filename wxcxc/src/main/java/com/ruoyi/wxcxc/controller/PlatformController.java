package com.ruoyi.wxcxc.controller;

import com.ruoyi.common.core.controller.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/iot/platform")
public class PlatformController extends BaseController {
    // TODO 获取仪表盘数据
    @PreAuthorize("@ss.hasPermi('iot:platform')")
    @GetMapping("/dashboard")
    public Map<String, Object> dashboard() {
        // 返回数据列表，包含数据产生
        // 入驻企业数量
        // 平台总项目数
        // 平台设备数
        // 平台DTU数
        // 项目列表
        return null;

//        Map<String, Object> map = new HashMap<>();
//        WxcxcDataBusiness wxcxcDataBusiness = new WxcxcDataBusiness();
//        wxcxcDataBusiness.setBusinessId(getBusinessId());
//
//        Map<String, Object> params = new HashMap<>();
//
//
//        params.put("beginTime", new SimpleDateFormat("yyMMdd").format(new Date().getTime() - (1000 * 60 * 60 * 24 * 7)));
//        wxcxcDataBusiness.setParams(params);
//
//        // 返回数据列表，包含数据产生
//        List<WxcxcDataBusiness> list = wxcxcDataBusinessService.selectWxcxcDataBusinessList(wxcxcDataBusiness);
//        if (null == list || 0 == list.size()) {
//            list = new ArrayList<>();
//            wxcxcDataBusiness.setDataCount("0");
//            wxcxcDataBusiness.setProjectCount("0");
//            wxcxcDataBusiness.setStructureCount("0");
//            wxcxcDataBusiness.setDeviceCount("0");
//
//            list.add(wxcxcDataBusiness);
//        }
//        map.put("businessData", list);
//
//        // 项目列表
//        WxcxcProject wxcxcProject = new WxcxcProject();
//        wxcxcProject.setBusinessId(getBusinessId());
//        List<WxcxcProject> wxcxcProjectList = wxcxcProjectService.selectWxcxcProjectList(wxcxcProject);
//        map.put("projectList", wxcxcProjectList);
//
//        return map;
    }


}
