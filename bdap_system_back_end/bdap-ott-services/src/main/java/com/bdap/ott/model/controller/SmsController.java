package com.bdap.ott.model.controller;

import com.bdap.common.exception.FeignInvokeException;
import com.bdap.common.utils.Q;
import com.bdap.ott.model.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Api(value = "腾讯云短信服务")
@RestController
@RequestMapping("/sms")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SmsController {

  @Value("${feign.SecretKey}")
  private String FEIGN_SECRET_KEY;

  private final SmsService smsService;

  private final PasswordEncoder passwordEncoder;

  @PostMapping("/verifyCode")
  @ApiOperation(value = "腾讯云发送验证码短信", notes = "需要字段：手机号（number），验证码（code）,远程调用密匙")
  public String sendVerifyCode(@RequestBody Q q) {
    if (passwordEncoder.matches(FEIGN_SECRET_KEY, String.valueOf(q.get("secretKey")))) {
      return smsService.sendVerifyCode(String.valueOf(q.get("number")), String.valueOf(q.get("verifyCode")));
    } else {
      throw new FeignInvokeException();
    }
  }
}
