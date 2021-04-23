package com.bdap.user.system.controller;


import com.bdap.common.exception.FeignInvokeException;
import com.bdap.common.utils.Q;
import com.bdap.common.utils.group.CreateValidGroup;
import com.bdap.user.system.entity.UserForecastName;
import com.bdap.user.system.service.UserForecastNameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author GuoKeGD
 * @since 2020-12-01
 */
@Api(value = "用户预报名API")
@RestController
@RequestMapping("/user-forecast-name")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserForecastNameController {

  @Value("${feign.SecretKey}")
  private String FEIGN_SECRET_KEY;

  private final PasswordEncoder passwordEncoder;

  private final UserForecastNameService userForecastNameService;

  @PreAuthorize("hasAuthority('root')")
  @PostMapping("/feign/list")
  @ApiOperation(value = "获取所有用户提交的预报名信息", notes = "需要传入页码:page，每个页面需要的数据条数:size，远程调用密钥:feignSecretKey")
  public Q getUserForecastName(@RequestBody Q q) {
    String feignSecretKey = (String) q.get("feignSecretKey");
    if (passwordEncoder.matches(FEIGN_SECRET_KEY, feignSecretKey)) {
      Integer page = (Integer) q.get("page");
      Integer size = (Integer) q.get("size");
      return Q.ok().put("data", userForecastNameService.getUserForecastName(page, size));
    } else {
      throw new FeignInvokeException();
    }
  }

  @PutMapping("/save")
  @ApiOperation(value = "保存用户提交的预报名信息", notes = "需要UserForecastName对象，返回值：-1表示之前已经提交，0表示添加失败，1表示提交成功")
  public Q saveUserForecastName(@Validated({CreateValidGroup.class}) @RequestBody UserForecastName userForecastName) {
    Integer forecastName = userForecastNameService.saveUserForecastName(userForecastName);
    if (-1 == forecastName) {
      return Objects.requireNonNull(Q.ok().put("data", forecastName)).put("msg", "预报名失败,请勿重复提交");
    } else if (0 == forecastName) {
      return Objects.requireNonNull(Q.ok().put("data", forecastName)).put("msg", "预报名失败,发生未知异常");
    } else {
      return Objects.requireNonNull(Q.ok().put("data", forecastName)).put("msg", "预报名成功,我们会及时联系您");
    }
  }

  @PreAuthorize("hasAuthority('root')")
  @PostMapping("/feign/delete")
  @ApiOperation(value = "获取用户提交的预报名信息", notes = "需传入int类型的Id数组，远程调用密钥:feignSecretKey，返回删除成功的条数")
  public Q removeUserForecastName(@RequestBody Q q) {
    String feignSecretKey = (String) q.get("feignSecretKey");
    if (passwordEncoder.matches(FEIGN_SECRET_KEY, feignSecretKey)) {
      List<Integer> Ids = (List<Integer>) q.get("Ids");
      Integer remove = userForecastNameService.removeUserForecastName(Ids);
      return Objects.requireNonNull(Q.ok().put("data", remove)).put("msg", "提交成功,删除数据:成功" + remove + "条,失败" + (Ids.size() - remove) + "条!");
    } else {
      throw new FeignInvokeException();
    }
  }

}

