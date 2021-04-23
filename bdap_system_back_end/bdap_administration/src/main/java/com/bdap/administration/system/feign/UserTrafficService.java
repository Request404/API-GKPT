package com.bdap.administration.system.feign;

import com.bdap.common.utils.Q;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("bdap-user-traffic")
public interface UserTrafficService {

  @RequestMapping("/certificate-info/feign/certificate")
  Q certificateListName();

  @PostMapping("/certificate-info/feign/put")
  Q certificateAdd(Q q);

  @PostMapping("/certificate-info/feign/delete")
  Q certificateRemove(Q q);

  @PostMapping("/certificate-info/feign/update")
  Q certificateUpdate(Q q);

  @PostMapping("/lecture-info/feign/add")
  Q lectureInfoAdd(Q q);

  @PostMapping("/lecture-info/feign/delete")
  Q lectureInfoRemove(Q q);

  @PostMapping("/lecture-info/feign/update")
  Q lectureInfoUpdate(Q q);

  @PostMapping("/user-traffic/feign/delete")
  Q userTrafficRemove(Q q);

  @PostMapping("/user-traffic/feign/list")
  Q userTrafficList(Q q);
}
