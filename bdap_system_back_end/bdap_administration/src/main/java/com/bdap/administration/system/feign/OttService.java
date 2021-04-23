package com.bdap.administration.system.feign;

import com.bdap.common.utils.Q;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("bdap-ott-services")
public interface OttService {

//  @PostMapping("/oss/token")
//  String getOssToken(Q q);

  @PostMapping("/sms/verifyCode")
  String sendVerifyCode(Q q);
}
