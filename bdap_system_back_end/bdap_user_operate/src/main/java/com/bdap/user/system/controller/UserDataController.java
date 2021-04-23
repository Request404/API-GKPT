package com.bdap.user.system.controller;


import com.bdap.common.exception.FeignInvokeException;
import com.bdap.common.utils.Q;
import com.bdap.common.utils.group.CreateValidGroup;
import com.bdap.common.utils.group.UpdateValidGroup;
import com.bdap.user.system.entity.UserData;
import com.bdap.user.system.feign.OttService;
import com.bdap.user.system.service.UserDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author GuoKeGD
 * @since 2020-12-01
 */
@Api(value = "用户账户信息API")
@RestController
@RequestMapping("/user-data")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDataController {

  @Value("${feign.SecretKey}")
  private String FEIGN_SECRET_KEY;

  private final PasswordEncoder passwordEncoder;

  private final UserDataService userDataService;

  private final OttService ottService;

  @GetMapping("/sms/sendCode")
  @ApiOperation(value = "发送验证码", notes = "传入手机号码,成功返回true，失败返回false")
  public Q sendVerificationCode(@RequestParam String Number) {
    if (Number.matches("^1(?:3\\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\\d|9\\d)\\d{8}$")) {
      String verifyCode = userDataService.generatorVerifyCode(Number);
      ottService.sendVerifyCode(Objects.requireNonNull(Objects.requireNonNull(Q.ok().put("number", Number)).put("verifyCode", verifyCode)).put("secretKey", passwordEncoder.encode(FEIGN_SECRET_KEY)));
      return Objects.requireNonNull(Q.ok().put("data", true)).put("msg", "验证码发送成功");
    } else {
      return Q.error(12001, "用户模块数据校验异常").put("msg", "手机号码格式不正确");
    }
  }

  @PutMapping("/registration")
  @ApiOperation(value = "用户注册", notes = "传入用户对象（手机，密码），验证码，验证码错误返回-1，失败返回0，成功返回1，用户存在则抛出异常")
  public Q userRegistration(@Validated({CreateValidGroup.class}) @RequestBody UserData userData) {
    if (userData.getVerifyCode().equals(userDataService.generatorVerifyCode(userData.getPhone()))) {
      userData.setPassword(passwordEncoder.encode(userData.getPassword()));
      Integer registration = userDataService.userRegistration(userData);
      if (0 == registration) {
        return Objects.requireNonNull(Q.ok().put("data", registration)).put("msg", "发送未知错误：用户注册失败");
      } else {
        return Objects.requireNonNull(Q.ok().put("data", registration)).put("msg", "用户注册成功");
      }
    } else {
      return Objects.requireNonNull(Q.ok().put("data", -1)).put("msg", "验证码错误");
    }
  }

  @PostMapping("/update")
  @ApiOperation(value = "用户信息修改", notes = "传入用户对象（手机，密码），验证码错误返回-1，失败返回0，成功返回1，用户不存在则抛出异常")
  public Q userUpdate(@Validated({UpdateValidGroup.class}) @RequestBody UserData userData) {
    if (userData.getVerifyCode().equals(userDataService.generatorVerifyCode(userData.getPhone()))) {
      userData.setPassword(passwordEncoder.encode(userData.getPassword()));
      Integer update = userDataService.userUpdate(userData);
      if (0 == update) {
        return Objects.requireNonNull(Q.ok().put("data", update)).put("msg", "发送未知错误：用户信息重置失败");
      } else {
        return Objects.requireNonNull(Q.ok().put("data", update)).put("msg", "用户信息重置成功");
      }
    } else {
      return Objects.requireNonNull(Q.ok().put("data", -1)).put("msg", "验证码错误");
    }
  }

  @PreAuthorize("hasAuthority('root')")
  @PostMapping("/feign/list")
  @ApiOperation(value = "获取所有用户", notes = "仅限root管理员调用")
  public Q getAllUser(@RequestBody Q q) {
    System.out.println(q.get("feignSecretKey"));
    String feignSecretKey = q.get("feignSecretKey").toString();
    if (passwordEncoder.matches(FEIGN_SECRET_KEY, feignSecretKey)) {
      return Q.ok().put("data", userDataService.getAllUser());
    } else {
      throw new FeignInvokeException();
    }
  }

  @PostMapping("/login")
  @ApiOperation(value = "用户登录", notes = "传入用户手机，密码，成功返回用户数据，失败抛出登录异常")
  public Q userLogin(@RequestBody UserData userData) {
    return Q.ok().put("data", userDataService.userLogin(userData));
  }
}

