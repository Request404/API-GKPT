package com.bdap.user.system.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bdap.common.exception.CertificateNotFoundException;
import com.bdap.common.exception.FeignInvokeException;
import com.bdap.common.exception.UserLoginException;
import com.bdap.common.utils.Q;
import com.bdap.common.utils.group.CreateValidGroup;
import com.bdap.user.system.entity.RegistrationInformation;
import com.bdap.user.system.feign.OttService;
import com.bdap.user.system.feign.UserTrafficService;
import com.bdap.user.system.service.RegistrationInformationService;
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

import java.util.ArrayList;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author GuoKeGD
 * @since 2020-12-01
 */
@Api(value = "用户报名API")
@RestController
@RequestMapping("/registration-information")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegistrationInformationController {

  @Value("${feign.SecretKey}")
  private String FEIGN_SECRET_KEY;

  private final PasswordEncoder passwordEncoder;

  private final OttService ottService;

  private final UserTrafficService userTrafficService;

  private final UserDataService userDataService;

  private final RegistrationInformationService registrationInformationService;

  @GetMapping("/oss/token")
  @ApiOperation(value = "获取照片对象存储token", notes = "需要用户登录后方可获取")
  public String getOssToken(String phone, String code) {
    if (userDataService.userMatch(phone, code)) {
      return ottService.getOssToken(Q.ok().put("secretKey", passwordEncoder.encode(FEIGN_SECRET_KEY)));
    } else {
      throw new UserLoginException();
    }
  }

  @PutMapping("/website/registration")
  @ApiOperation(value = "网站用户报名", notes = "传入用户报名信息，成功返回1，失败返回0")
  public Q webSiteUserRegistration(@Validated({CreateValidGroup.class}) @RequestBody RegistrationInformation registrationInformation) {
    Q q = userTrafficService.certificateListName();
    ArrayList<String> listName = (ArrayList<String>) q.get("data");
    if (listName.contains(registrationInformation.getCertificateName())) {
      Integer registration = registrationInformationService.webSiteUserRegistration(registrationInformation);
      if (0 != registration) {
        return Objects.requireNonNull(Q.ok().put("data", registration)).put("msg", "报名成功");
      } else {
        return Objects.requireNonNull(Q.ok().put("data", registration)).put("msg", "报名失败");
      }
    } else {
      throw new CertificateNotFoundException();
    }
  }

  @PreAuthorize("hasAuthority('root')")
  @GetMapping("/feign/user/list")
  @ApiOperation(value = "获取用户报名信息", notes = "传入用户手机号，用户码")
  public Q getUserRegistration(String phone, String userCode) {
    return Q.ok().put("data", registrationInformationService.getUserRegistration(phone, userCode));
  }

  @PostMapping("/feign/open/registration")
  @ApiOperation(value = "对外用户报名接口", notes = "传入用户报名信息registrationInformation，远程调用密匙 feignSecretKey，成功返回1，失败返回0")
  public Q openRegistration(@RequestBody Q q) {
    String feignSecretKey = q.get("feignSecretKey").toString();
    if (passwordEncoder.matches(FEIGN_SECRET_KEY, feignSecretKey)) {
      String jsonString = JSON.toJSONString(q.get("registrationInformation"));
      JSONObject information = JSONObject.parseObject(jsonString);
      RegistrationInformation registrationInformation = JSON.toJavaObject(information, RegistrationInformation.class);
      Integer registration = registrationInformationService.openRegistration(registrationInformation);
      if (0 != registration) {
        return Objects.requireNonNull(Q.ok().put("data", registration)).put("msg", "报名成功");
      } else {
        return Objects.requireNonNull(Q.ok().put("data", registration)).put("msg", "报名失败");
      }
    } else {
      throw new FeignInvokeException();
    }
  }

  @PreAuthorize("hasAuthority('root')")
  @PostMapping("/feign/root/update")
  @ApiOperation(value = "用户报名信息修改接口", notes = "传入用户新的报名信息registrationInformation，远程调用密匙 feignSecretKey，成功返回1，失败返回0")
  public Q updateRegistration(@RequestBody Q q) {
    String feignSecretKey = q.get("feignSecretKey").toString();
    if (passwordEncoder.matches(FEIGN_SECRET_KEY, feignSecretKey)) {
      String jsonString = JSON.toJSONString(q.get("registrationInformation"));
      JSONObject information = JSONObject.parseObject(jsonString);
      RegistrationInformation registrationInformation = JSON.toJavaObject(information, RegistrationInformation.class);
      Integer registration = registrationInformationService.updateRegistration(registrationInformation);
      if (0 != registration) {
        return Objects.requireNonNull(Q.ok().put("data", registration)).put("msg", "修改成功");
      } else {
        return Objects.requireNonNull(Q.ok().put("data", registration)).put("msg", "修改失败");
      }
    } else {
      throw new FeignInvokeException();
    }
  }

  @PreAuthorize("hasAnyAuthority('root,region,company,base')")
  @PostMapping("/feign/open/update")
  @ApiOperation(value = "对外报名信息修改接口", notes = "传入用户新的报名信息registrationInformation（必须携带字段：id,reference），远程调用密匙 feignSecretKey，成功返回1，失败返回0")
  public Q updateOpenRegionRegistration(@RequestBody Q q) {
    String feignSecretKey = q.get("feignSecretKey").toString();
    if (passwordEncoder.matches(FEIGN_SECRET_KEY, feignSecretKey)) {
      String jsonString = JSON.toJSONString(q.get("registrationInformation"));
      JSONObject information = JSONObject.parseObject(jsonString);
      RegistrationInformation registrationInformation = JSON.toJavaObject(information, RegistrationInformation.class);
      Integer registration = registrationInformationService.updateOpenRegionRegistration(registrationInformation);
      if (0 != registration) {
        return Objects.requireNonNull(Q.ok().put("data", registration)).put("msg", "修改成功");
      } else {
        return Objects.requireNonNull(Q.ok().put("data", registration)).put("msg", "修改失败");
      }
    } else {
      throw new FeignInvokeException();
    }
  }

  @PreAuthorize("hasAuthority('root')")
  @PostMapping("/feign/root/list")
  @ApiOperation(value = "获取全部报名信息", notes = "仅限root管理员调用")
  public Q getRootRegistration(@RequestBody Q q) {
    String feignSecretKey = q.get("feignSecretKey").toString();
    if (passwordEncoder.matches(FEIGN_SECRET_KEY, feignSecretKey)) {
      String like = q.get("like").toString();
      return Q.ok().put("data", registrationInformationService.getRootRegistration(like));
    } else {
      throw new FeignInvokeException();
    }
  }

  @PreAuthorize("hasAuthority('region')")
  @PostMapping("/feign/region/list")
  @ApiOperation(value = "获取地域报名信息", notes = "仅限region管理员调用")
  public Q getRegionRegistration(@RequestBody Q q) {
    String feignSecretKey = q.get("feignSecretKey").toString();
    if (passwordEncoder.matches(FEIGN_SECRET_KEY, feignSecretKey)) {
      String region = q.get("region").toString();
      String like = q.get("like").toString();
      return Q.ok().put("data", registrationInformationService.getRegionRegistration(region, like));
    } else {
      throw new FeignInvokeException();
    }
  }

  @PreAuthorize("hasAuthority('company')")
  @PostMapping("/feign/company/list")
  @ApiOperation(value = "获取公司/机构报名信息", notes = "仅限company管理员调用")
  public Q getCompanyRegistration(@RequestBody Q q) {
    String feignSecretKey = q.get("feignSecretKey").toString();
    if (passwordEncoder.matches(FEIGN_SECRET_KEY, feignSecretKey)) {
      String region = q.get("region").toString();
      String company = q.get("company").toString();
      String like = q.get("like").toString();
      return Q.ok().put("data", registrationInformationService.getCompanyRegistration(region, company, like));
    } else {
      throw new FeignInvokeException();
    }
  }

  @PreAuthorize("hasAuthority('base')")
  @PostMapping("/feign/base/list")
  @ApiOperation(value = "获取公司机构内部用户报名信息或推荐人报名信息", notes = "仅限base管理员调用")
  public Q getBaseRegistration(@RequestBody Q q) {
    String feignSecretKey = q.get("feignSecretKey").toString();
    if (passwordEncoder.matches(FEIGN_SECRET_KEY, feignSecretKey)) {
      String reference = q.get("reference").toString();
      String like = q.get("like").toString();
      return Q.ok().put("data", registrationInformationService.getBaseRegistration(reference, like));
    } else {
      throw new FeignInvokeException();
    }
  }

  @PreAuthorize("hasAuthority('root')")
  @PostMapping("/feign/root/delete")
  @ApiOperation(value = "用户报名信息删除", notes = "仅限root管理员调用")
  public Q removeRegistration(@RequestBody Q q) {
    String feignSecretKey = q.get("feignSecretKey").toString();
    if (passwordEncoder.matches(FEIGN_SECRET_KEY, feignSecretKey)) {
      Integer id = (Integer) q.get("id");
      Integer remove = registrationInformationService.removeRegistration(id);
      if (0 != remove) {
        return Objects.requireNonNull(Q.ok().put("data", remove)).put("msg", "用户编号：" + id + ",删除成功");
      } else {
        return Objects.requireNonNull(Q.ok().put("data", remove)).put("msg", "用户删除失败");
      }
    } else {
      throw new FeignInvokeException();
    }
  }

  @PreAuthorize("hasAnyAuthority('root,region,company,base')")
  @PostMapping("/feign/open/delete")
  @ApiOperation(value = "用户报名信息删除", notes = "需传入id和推荐人reference")
  public Q removeOpenRegistration(@RequestBody Q q) {
    String feignSecretKey = q.get("feignSecretKey").toString();
    if (passwordEncoder.matches(FEIGN_SECRET_KEY, feignSecretKey)) {
      Integer id = (Integer) q.get("id");
      String reference = (String) q.get("reference");
      Integer remove = registrationInformationService.removeOpenRegistration(id, reference);
      if (0 != remove) {
        return Objects.requireNonNull(Q.ok().put("data", remove)).put("msg", "用户编号：" + id + ",删除成功");
      } else {
        return Objects.requireNonNull(Q.ok().put("data", remove)).put("msg", "用户删除失败");
      }
    } else {
      throw new FeignInvokeException();
    }
  }

}

