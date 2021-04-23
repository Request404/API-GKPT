package com.bdap.user.system.feign;

import com.bdap.common.utils.Q;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("bdap-user-traffic")
public interface UserTrafficService {

  @RequestMapping("/certificate-info/feign/certificate")
  Q certificateListName();
}
