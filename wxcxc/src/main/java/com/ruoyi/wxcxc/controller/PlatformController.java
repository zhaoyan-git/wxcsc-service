package com.ruoyi.wxcxc.controller;

import com.ruoyi.common.core.controller.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
    }
}
