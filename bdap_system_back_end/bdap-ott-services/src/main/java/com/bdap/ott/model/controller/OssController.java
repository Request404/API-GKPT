package com.bdap.ott.model.controller;

import com.bdap.common.exception.FeignInvokeException;
import com.bdap.common.utils.Q;
import com.bdap.ott.model.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Api(value = "腾讯云对象存储服务")
@RestController
@RequestMapping("/oss")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OssController {

  @Value("${feign.SecretKey}")
  private String FEIGN_SECRET_KEY;

  private final OssService ossService;

  private final PasswordEncoder passwordEncoder;

  @PostMapping("/token")
  @ApiOperation(value = "获取对象存储密钥", notes = "有效期为半小时,需要传入远程调用密匙")
  public String getOssToken(@RequestBody Q q) {
    if (passwordEncoder.matches(FEIGN_SECRET_KEY, String.valueOf(q.get("secretKey")))) {
      return ossService.getOssToken();
    } else {
      throw new FeignInvokeException();
    }
  }
}
