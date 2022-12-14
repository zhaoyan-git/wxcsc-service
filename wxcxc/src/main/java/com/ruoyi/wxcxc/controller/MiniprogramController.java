package com.ruoyi.wxcxc.controller;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.sign.RsaUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.wxcxc.domain.*;
import com.ruoyi.wxcxc.dto.MiniProgramProjectDto;
import com.ruoyi.wxcxc.dto.ProjectPointGroupDto;
import com.ruoyi.wxcxc.mapper.WxcxcConsoleMapper;
import com.ruoyi.wxcxc.mapper.WxcxcMiniProgramMapper;
import com.ruoyi.wxcxc.service.*;
import com.ruoyi.wxcxc.util.PackDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/miniprogram")
public class MiniprogramController extends BaseController {
    @Autowired
    private TokenService tokenService;
    @Resource
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ISysUserService userService;
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
    private WxcxcMiniProgramMapper wxcxcMiniProgramMapper;
    @Autowired
    private IWxcxcProjectPointService wxcxcProjectPointService;
    @Autowired
    private WxcxcConsoleMapper wxcxcConsoleMapper;
    @Autowired
    private ISysRoleService sysRoleService;

    // ??????
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        String username = loginBody.getUsername();
        String password = loginBody.getPassword();

        AjaxResult ajax = AjaxResult.success();

        // ????????????
        Authentication authentication = null;
        try {
            // ?????????????????????UserDetailsServiceImpl.loadUserByUsername
            // ???????????? RsaUtils.decryptByPrivateKey(password)
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // ????????????
        String token = tokenService.createToken(loginUser);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    // ??????????????????
    @PreAuthorize("@ss.hasPermi('iot:console')")
    @GetMapping("/projectDataList")
    public List<MiniProgramProjectDto> projectDataList() {
        List<MiniProgramProjectDto> list = new ArrayList<>();

        SysUser user = SecurityUtils.getLoginUser().getUser();

        List<WxcxcProject> wxcxcProjectList = wxcxcProjectService.selectWxcxcProjectByUserId(user.getUserId());

        for (WxcxcProject item : wxcxcProjectList) {
            MiniProgramProjectDto miniProgramProjectDto = new MiniProgramProjectDto();

            miniProgramProjectDto.setId(item.getId());
            miniProgramProjectDto.setName(item.getName());
            miniProgramProjectDto.setBusinessId(item.getBusinessId());
            miniProgramProjectDto.setAim(item.getAim());
            miniProgramProjectDto.setPhotoFile(item.getPhotoFile());
            miniProgramProjectDto.setDetails(item.getDetails());
            miniProgramProjectDto.setPositionLat(item.getPositionLat());
            miniProgramProjectDto.setPositionLng(item.getPositionLng());
            miniProgramProjectDto.setAlarmFlag(item.getAlarmFlag());
            miniProgramProjectDto.setDelFlag(item.getDelFlag());

            miniProgramProjectDto.setStructureCount(wxcxcMiniProgramMapper.getProjectStructureCount(item.getId()));
            miniProgramProjectDto.setPointCount(wxcxcMiniProgramMapper.getProjectPointCount(item.getId()));
            miniProgramProjectDto.setDtoCount(wxcxcMiniProgramMapper.getProjectDtoCount(item.getId()));
            miniProgramProjectDto.setAlarmCount(wxcxcMiniProgramMapper.getProjectAlarmCount(item.getId()));

            list.add(miniProgramProjectDto);
        }

        return list;
    }

    // ????????????ID?????????
    @PreAuthorize("@ss.hasPermi('iot:console')")
    @GetMapping("/projectStructureList")
    public List<WxcxcProjectStructure> projectStructureList(Long projectId) {
        WxcxcProjectStructure wxcxcProjectStructurecondition = new WxcxcProjectStructure();
        wxcxcProjectStructurecondition.setProjectId(projectId);
        return wxcxcProjectStructureService.selectWxcxcProjectStructureList(wxcxcProjectStructurecondition);
    }

    // ???????????????ID????????????
    @PreAuthorize("@ss.hasPermi('iot:console')")
    @GetMapping("/projectPointList")
    public List<ProjectPointGroupDto> projectPointList(WxcxcProjectPoint wxcxcProjectPoint) {
        if (null == wxcxcProjectPoint.getProjectStructureId()) return new ArrayList<>();

        List<ProjectPointGroupDto> returnList = new ArrayList<>();

        // ????????????????????????
        List<WxcxcProjectPoint> groupisNullList = wxcxcProjectPointService.selectWxcxcProjectPointByPointGroupIdIsNull(wxcxcProjectPoint.getProjectStructureId());
        if (null != groupisNullList && 0 < groupisNullList.size()) {
            ProjectPointGroupDto projectPointGroupDto = new ProjectPointGroupDto();
            projectPointGroupDto.setId(-1l);
            projectPointGroupDto.setName("?????????");
            projectPointGroupDto.setStructureId(wxcxcProjectPoint.getProjectStructureId());

            projectPointGroupDto.setChildren(groupisNullList);
            returnList.add(projectPointGroupDto);
        }

        // ??????????????????
        WxcxcProjectPointGroup wxcxcProjectPointGroup = new WxcxcProjectPointGroup();
        wxcxcProjectPointGroup.setStructureId(wxcxcProjectPoint.getProjectStructureId());
        List<WxcxcProjectPointGroup> wxcxcProjectPointGroupList = wxcxcProjectPointGroupService.selectWxcxcProjectPointGroupList(wxcxcProjectPointGroup);
        if (null != wxcxcProjectPointGroupList && 0 < wxcxcProjectPointGroupList.size()) {
            for (WxcxcProjectPointGroup item : wxcxcProjectPointGroupList) {
                ProjectPointGroupDto projectPointGroupDto = new ProjectPointGroupDto();
                projectPointGroupDto.setId(item.getId());
                projectPointGroupDto.setName(item.getName());
                projectPointGroupDto.setStructureId(item.getStructureId());

                // ????????????????????????
                WxcxcProjectPoint wxcxcProjectPointCondition = new WxcxcProjectPoint();
                wxcxcProjectPointCondition.setProjectStructureId(wxcxcProjectPoint.getProjectStructureId());
                wxcxcProjectPointCondition.setPointGroupId(item.getId());
                projectPointGroupDto.setChildren(wxcxcProjectPointService.selectWxcxcProjectPointList(wxcxcProjectPointCondition));
                returnList.add(projectPointGroupDto);
            }
        }

        return returnList;
    }

    // ????????????ID??????????????????
    @PreAuthorize("@ss.hasPermi('iot:console')")
    @GetMapping("/deviceList")
    public List<WxcxcDevice> deviceList(Long projectId) {
        WxcxcDevice wxcxcDevice = new WxcxcDevice();
        wxcxcDevice.setProjectId(projectId);
        return wxcxcDeviceService.selectWxcxcDeviceList(wxcxcDevice);
    }

    // ??????????????????
    @PreAuthorize("@ss.hasPermi('iot:console')")
    @GetMapping("/userData")
    public Map<String, Object> userData() {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // ??????MemberId
        WxcxcBusinessMember wxcxcBusinessMember = new WxcxcBusinessMember();
        wxcxcBusinessMember.setSysUserId(user.getUserId());
        List<WxcxcBusinessMember> list = wxcxcBusinessMemberService.selectWxcxcBusinessMemberList(wxcxcBusinessMember);
        if (null != list && 1 == list.size()) {
            Long memberId = list.get(0).getId();
            Long businessId = list.get(0).getBusinessId();
            Map<String, Object> map = new HashMap<>();

            // ??????????????????????????????
            List<SysRole> sysRoleList = sysRoleService.selectRolesByUserId(user.getUserId());
            boolean hasEnterpriseMng = false;

            for (SysRole item : sysRoleList) {
                if ("enterprise_mng".equals(item.getRoleKey())) {
                    hasEnterpriseMng = true;
                    break;
                }
            }
            // ????????????
            WxcxcBusiness wxcxcBusiness = wxcxcBusinessService.selectWxcxcBusinessById(businessId);
            map.put("business", wxcxcBusiness);

            if (hasEnterpriseMng) {
                // ???????????????????????????
                // ????????????
                map.put("structureCount", wxcxcConsoleMapper.getStructureCount(businessId));
                // ?????????
                map.put("pointCount", wxcxcMiniProgramMapper.getBusinessPointCount(businessId));
                // ?????????
                map.put("deviceCount", wxcxcConsoleMapper.getDeviceCount(businessId));
            } else {
                // ??????????????????????????????
                // ????????????
                map.put("structureCount", wxcxcMiniProgramMapper.getProjectStructureCountByMemberId(businessId));
                // ?????????
                map.put("pointCount", wxcxcMiniProgramMapper.getProjectPointCountByMemberId(businessId));
                // ?????????
                map.put("deviceCount", wxcxcMiniProgramMapper.getProjectDeviceCountByMemberId(businessId));
            }

            return map;

        } else {
            return null;
        }
    }

}
