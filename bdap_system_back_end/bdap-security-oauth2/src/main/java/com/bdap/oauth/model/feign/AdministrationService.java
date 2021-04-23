package com.bdap.oauth.model.feign;

import com.bdap.common.dto.BadpUserDto;
import com.bdap.common.dto.BdapUserConnectDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("bdap-administration")
public interface AdministrationService {

  @RequestMapping("/admin-account/login")
  BadpUserDto login(BdapUserConnectDto bdapConnectDto);
}
