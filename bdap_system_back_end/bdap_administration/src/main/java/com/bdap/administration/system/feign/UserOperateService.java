package com.bdap.administration.system.feign;

import com.bdap.common.utils.Q;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("bdap-user-operate")
public interface UserOperateService {

  @PostMapping("/user-data/feign/list")
  Q getAllUser(Q q);

  @PostMapping("/registration-information/feign/open/registration")
  Q openRegistration(Q q);

  @PostMapping("/registration-information/feign/root/update")
  Q updateRegistration(Q q);

  @PostMapping("/registration-information/feign/open/update")
  Q updateOpenRegionRegistration(Q q);

  @PostMapping("/registration-information/feign/root/list")
  Q getRootRegistration(Q q);

  @PostMapping("/registration-information/feign/region/list")
  Q getRegionRegistration(Q q);

  @PostMapping("/registration-information/feign/company/list")
  Q getCompanyRegistration(Q q);

  @PostMapping("/registration-information/feign/base/list")
  Q getBaseRegistration(Q q);

  @PostMapping("/registration-information/feign/root/delete")
  Q removeRegistration(Q q);

  @PostMapping("/registration-information/feign/open/delete")
  Q removeOpenRegistration(Q q);

  @PostMapping("/user-commit/feign/list")
  Q getUserCommit(Q q);

  @PostMapping("/user-commit/feign/delete")
  Q removeUserCommit(Q q);

  @PostMapping("/user-forecast-name/feign/list")
  Q getUserForecastName(Q q);

  @PostMapping("/user-forecast-name/feign/delete")
  Q removeUserForecastName(Q q);

}
