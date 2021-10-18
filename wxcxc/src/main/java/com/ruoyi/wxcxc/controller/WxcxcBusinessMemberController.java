package com.ruoyi.wxcxc.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.wxcxc.dto.BusinessMemberDto;
import com.ruoyi.wxcxc.util.PackDto;
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
import com.ruoyi.wxcxc.domain.WxcxcBusinessMember;
import com.ruoyi.wxcxc.service.IWxcxcBusinessMemberService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 企业人员Controller
 *
 * @author l62202
 * @date 2021-10-17
 */
@RestController
@RequestMapping("/iot/businessMember")
public class WxcxcBusinessMemberController extends BaseController {
    @Autowired
    private IWxcxcBusinessMemberService wxcxcBusinessMemberService;
    @Autowired
    private PackDto packDto;
    @Autowired
    private ISysUserService userService;

    /**
     * 查询企业人员列表
     */
    @PreAuthorize("@ss.hasPermi('iot:businessMember:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxcxcBusinessMember wxcxcBusinessMember) {
        startPage();
        List<WxcxcBusinessMember> list = wxcxcBusinessMemberService.selectWxcxcBusinessMemberList(wxcxcBusinessMember);
        return getDataTable(list);
    }

    /**
     * 导出企业人员列表
     */
    @PreAuthorize("@ss.hasPermi('iot:businessMember:export')")
    @Log(title = "企业人员", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WxcxcBusinessMember wxcxcBusinessMember) {
        List<WxcxcBusinessMember> list = wxcxcBusinessMemberService.selectWxcxcBusinessMemberList(wxcxcBusinessMember);
        ExcelUtil<WxcxcBusinessMember> util = new ExcelUtil<WxcxcBusinessMember>(WxcxcBusinessMember.class);
        return util.exportExcel(list, "企业人员数据");
    }

    /**
     * 获取企业人员详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:businessMember:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(wxcxcBusinessMemberService.selectWxcxcBusinessMemberById(id));
    }

    /**
     * 新增企业人员
     */
    @PreAuthorize("@ss.hasPermi('iot:businessMember:add')")
    @Log(title = "企业人员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxcxcBusinessMember wxcxcBusinessMember) {
        return toAjax(wxcxcBusinessMemberService.insertWxcxcBusinessMember(wxcxcBusinessMember));
    }

    /**
     * 修改企业人员
     */
    @PreAuthorize("@ss.hasPermi('iot:businessMember:edit')")
    @Log(title = "企业人员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxcxcBusinessMember wxcxcBusinessMember) {
        return toAjax(wxcxcBusinessMemberService.updateWxcxcBusinessMember(wxcxcBusinessMember));
    }

    /**
     * 删除企业人员
     */
    @PreAuthorize("@ss.hasPermi('iot:businessMember:remove')")
    @Log(title = "企业人员", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(wxcxcBusinessMemberService.deleteWxcxcBusinessMemberByIds(ids));
    }


    // 获取用户列表（分页）
    @PreAuthorize("@ss.hasPermi('iot:businessMember:list')")
    @GetMapping("/businessMemberDto/list")
    public TableDataInfo list(BusinessMemberDto businessMemberDto) {
        startPage();

        if (null == businessMemberDto || null == businessMemberDto.getBusinessId()) {
            WxcxcBusinessMember wxcxcBusinessMember = new WxcxcBusinessMember();
            wxcxcBusinessMember.setBusinessId(businessMemberDto.getBusinessId());
            List<WxcxcBusinessMember> list = wxcxcBusinessMemberService.selectWxcxcBusinessMemberList(wxcxcBusinessMember);

            List<BusinessMemberDto> returnList = new ArrayList<>();

            for (WxcxcBusinessMember item : list) {
                returnList.add(packDto.generateBusinessMemberDtoById(item.getId()));
            }
            return getDataTable(list);
        } else {
            return getDataTable(new ArrayList<>());
        }
    }

    /**
     * 新增企业人员
     */
    @PreAuthorize("@ss.hasPermi('iot:businessMember:add')")
    @Log(title = "企业人员", businessType = BusinessType.INSERT)
    @PostMapping("/businessMemberDto")
    public AjaxResult add(@RequestBody BusinessMemberDto businessMemberDto) {
        SysUser user = businessMemberDto.getUser();
        userService.checkUserAllowed(user);
        if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setUpdateBy(getUsername());

        return toAjax(wxcxcBusinessMemberService.insertWxcxcBusinessMember(businessMemberDto));
    }

}
