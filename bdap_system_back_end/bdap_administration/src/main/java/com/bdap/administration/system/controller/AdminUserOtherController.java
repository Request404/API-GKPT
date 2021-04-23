package com.bdap.administration.system.controller;

import com.bdap.administration.system.feign.UserOperateService;
import com.bdap.common.utils.Q;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Api(value = "用户引流信息API")
@RestController
@RequestMapping("/user-other")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminUserOtherController {

  @Value("${feign.SecretKey}")
  private String FEIGN_SECRET_KEY;

  private final PasswordEncoder passwordEncoder;

  private final UserOperateService userOperateService;

  @PreAuthorize("hasAuthority('root')")
  @GetMapping("/user-commit")
  @ApiOperation(value = "获取用户提交的测评信息", notes = "需要传入页码:page，每个页面需要的数据条数:size")
  public Q getUserCommit(Integer page, Integer size) {
    if (null != page && null != size) {
      return userOperateService.getUserCommit(Objects.requireNonNull(Objects.requireNonNull(Q.ok().put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY))).put("page", page)).put("size", size));
    } else {
      throw new NullPointerException();
    }
  }

  @PreAuthorize("hasAuthority('root')")
  @DeleteMapping("/user-commit")
  @ApiOperation(value = "删除用户提交的测评信息", notes = "需传入int类型的Id数组，返回删除成功的条数")
  public Q removeUserCommit(Integer[] Ids) {
    return userOperateService.removeUserCommit(Objects.requireNonNull(Objects.requireNonNull(Q.ok().put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY))).put("Ids", Ids)));
  }

  @PreAuthorize("hasAuthority('root')")
  @GetMapping("/user-forecast-name")
  @ApiOperation(value = "获取所有用户提交的预报名信息", notes = "需要传入页码:page，每个页面需要的数据条数:size")
  public Q getUserForecastName(Integer page, Integer size) {
    if (null != page && null != size) {
      return userOperateService.getUserForecastName(Objects.requireNonNull(Objects.requireNonNull(Q.ok().put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY))).put("page", page)).put("size", size));
    } else {
      throw new NullPointerException();
    }
  }

  @PreAuthorize("hasAuthority('root')")
  @DeleteMapping("/user-forecast-name")
  @ApiOperation(value = "删除用户提交的预报名信息", notes = "需传入int类型的Id数组，返回删除成功的条数")
  public Q removeUserForecastName(Integer[] Ids) {
    return userOperateService.removeUserForecastName(Objects.requireNonNull(Objects.requireNonNull(Q.ok().put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY))).put("Ids", Ids)));
  }

}
