package com.bdap.administration.system.controller;


import com.bdap.administration.system.entity.AdminInfo;
import com.bdap.administration.system.service.AdminInfoService;
import com.bdap.common.utils.Q;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author GuoKeGD
 * @since 2020-12-01
 */
@Api(value = "管理员信息")
@RestController
@RequestMapping("/admin-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminInfoController {

  private final AdminInfoService adminInfoService;

  @PreAuthorize("hasAnyAuthority('root,region,company,base')")
  @GetMapping("/info")
  @ApiOperation(value = "获取管理员用户信息", notes = "需要管理员账号token")
  public Q getAdministrationInfo(Authentication authentication) {
    return Q.ok().put("data", adminInfoService.getAdministrationInfo(authentication.getName()));
  }

  @PreAuthorize("hasAnyAuthority('root,region,company')")
  @GetMapping("/tree")
  @ApiOperation(value = "root权限查询的管理员", notes = "展示一个管理员信息树")
  public Q rootAdministrationTree(Authentication authentication) {
    AdminInfo info = adminInfoService.getAdministrationInfo(authentication.getName());
    if ("root".equals(info.getAuthority())) {
      List<AdminInfo> listTree = adminInfoService.rootListTree();
      return Q.ok().put("data", listTree);
    } else if ("region".equals(info.getAuthority())) {
      List<AdminInfo> listTree = adminInfoService.regionListTree(info.getRegion(), authentication);
      return Q.ok().put("data", listTree);
    } else if ("company".equals(info.getAuthority())) {
      AdminInfo listTree = adminInfoService.companyListTree(info.getRegion(), info.getCompany(), authentication);
      return Q.ok().put("data", listTree);
    } else {
      return Q.ok().put("data", null);
    }
  }
}

