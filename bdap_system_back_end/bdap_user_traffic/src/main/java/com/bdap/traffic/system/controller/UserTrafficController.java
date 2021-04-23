package com.bdap.traffic.system.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdap.common.exception.FeignInvokeException;
import com.bdap.common.utils.Q;
import com.bdap.common.utils.Sql;
import com.bdap.common.utils.group.CreateValidGroup;
import com.bdap.traffic.system.entity.UserTraffic;
import com.bdap.traffic.system.service.UserTrafficService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
@RestController
@RequestMapping("/user-traffic")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserTrafficController {

  @Value("${feign.SecretKey}")
  private String FEIGN_SECRET_KEY;

  private final PasswordEncoder passwordEncoder;

  private final UserTrafficService userTrafficService;

  @ApiOperation(value = "用户流量添加", notes = "传入一个UserTraffic对象,返回值大于0表示添加成功,数据格式错误、权限不足会触发异常")
  @PutMapping("/add")
  public Q userTrafficAdd(@Validated({CreateValidGroup.class}) @RequestBody UserTraffic userTraffic) {
    userTraffic.setVisitTime(Sql.Datetime());
    Integer i = userTrafficService.createUserTraffic(userTraffic);
    if (i != 0) {
      return Objects.requireNonNull(Q.ok().put("data", i)).put("msg", "用户流量添加成功");
    } else {
      return Objects.requireNonNull(Q.ok().put("data", i)).put("msg", "用户流量添加失败");
    }
  }

  @PreAuthorize("hasAuthority('root')")
  @PostMapping("/feign/delete")
  @ApiOperation(value = "用户流量删除", notes = "传入一个用户流量Ids列表,返回值为>0表示删除成功,权限不足会触发异常")
  public Q userTrafficRemove(@RequestBody Q q) {
    String feignScretKey = (String) q.get("feignSecretKey");
    if (passwordEncoder.matches(FEIGN_SECRET_KEY, feignScretKey)) {
      List<Integer> Ids = (List<Integer>) q.get("Ids");
      Integer result = userTrafficService.removeUserTraffic(Ids);
      return Objects.requireNonNull(Q.ok().put("data", result)).put("msg", "提交成功,删除数据:成功" + result + "条,失败" + (Ids.size() - result) + "条!");
    } else {
      throw new FeignInvokeException();
    }
  }

  @PreAuthorize("hasAuthority('root')")
  @PostMapping("/feign/list")
  @ApiOperation(value = "用户流量列表", notes = "返回用户流量列表,page需要查询的页数，size每页大写，like模糊查询")
  public Q userTrafficList(@RequestBody Q q) {
    String feignScretKey = (String) q.get("feignSecretKey");
    if (passwordEncoder.matches(FEIGN_SECRET_KEY, feignScretKey)) {
      Integer page = (Integer) q.get("page");
      Integer size = (Integer) q.get("size");
      String like = (String) q.get("like");
      Page<UserTraffic> userTraffics = userTrafficService.getUserTraffics(page, size, like);
      return Objects.requireNonNull(Q.ok().put("data", userTraffics));
    } else {
      throw new FeignInvokeException();
    }
  }
}

