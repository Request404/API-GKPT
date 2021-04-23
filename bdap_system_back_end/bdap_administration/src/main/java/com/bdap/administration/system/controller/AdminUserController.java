package com.bdap.administration.system.controller;

import com.bdap.administration.system.entity.AdminInfo;
import com.bdap.administration.system.feign.UserOperateService;
import com.bdap.administration.system.feign.UserTrafficService;
import com.bdap.administration.system.feign.entity.RegistrationInformation;
import com.bdap.administration.system.service.AdminInfoService;
import com.bdap.common.exception.CertificateNotFoundException;
import com.bdap.common.exception.PermissionDeniedException;
import com.bdap.common.exception.state.StateCode;
import com.bdap.common.utils.Q;
import com.bdap.common.utils.group.CreateValidGroup;
import com.bdap.common.utils.group.RootValidGroup;
import com.bdap.common.utils.group.UpdateValidGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@Api(value = "管理员对用户的操作API")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminUserController {

  @Value("${feign.SecretKey}")
  private String FEIGN_SECRET_KEY;

  private final PasswordEncoder passwordEncoder;

  private final UserOperateService userOperateService;

  private final UserTrafficService userTrafficService;

  private final AdminInfoService adminInfoService;

  @PreAuthorize("hasAuthority('root')")
  @GetMapping("/list")
  @ApiOperation(value = "查询注册所有用户的状况", notes = "仅限管理员调用")
  public Q getAllUser() {
    return userOperateService.getAllUser(Q.ok().put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY)));
  }

  /*@PreAuthorize("hasAnyAuthority('root,region,company,base')")*/
  @PutMapping("/open/registration")
  @ApiOperation(value = "对外用户报名接口", notes = "传入用户报名信息registrationInformation（其中：reference字段为推荐码），成功返回1，失败返回0")
  public Q openRegistration(@Validated({CreateValidGroup.class}) @RequestBody RegistrationInformation registrationInformation) {
    Q q = userTrafficService.certificateListName();
    ArrayList<String> listName = (ArrayList<String>) q.get("data");
    if (listName.contains(registrationInformation.getCertificateName())) {
      AdminInfo adminInfo = adminInfoService.getAdministrationInfoByAdminCode(registrationInformation.getReference());
      if (null == adminInfo) {
        return Q.error(StateCode.ADMINISTRATION_CODE_INVALID_EXCEPTION.getCode(), StateCode.ADMINISTRATION_CODE_INVALID_EXCEPTION.getMsg());
      } else {
        registrationInformation.setRegion(adminInfo.getRegion());
        registrationInformation.setCompany(adminInfo.getCompany());
        registrationInformation.setBase(registrationInformation.getName());
        registrationInformation.setReference(registrationInformation.getReference());
        registrationInformation.setOperate(adminInfo.getOperate());
        return userOperateService.openRegistration(Objects.requireNonNull(Q.ok().put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY))).put("registrationInformation", registrationInformation));
      }
    } else {
      throw new CertificateNotFoundException();
    }
  }

  @PreAuthorize("hasAuthority('root')")
  @PostMapping("/root/update")
  @ApiOperation(value = "用户报名信息修改接口", notes = "传入用户报名信息registrationInformation，按照id编号修改，成功返回1，失败返回0,该接口仅限有root权限的管理员调用!")
  public Q updateRegistration(@Validated({RootValidGroup.class}) @RequestBody RegistrationInformation registrationInformation, Authentication authentication) {
    registrationInformation.setOperate(authentication.getName());
    return userOperateService.updateRegistration(Objects.requireNonNull(Q.ok().put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY))).put("registrationInformation", registrationInformation));
  }

  @PreAuthorize("hasAnyAuthority('root,region,company,base')")
  @PostMapping("/open/update")
  @ApiOperation(value = "用户报名信息修改接口", notes = "传入用户报名信息registrationInformation（其中reference为必须字段），按照id编号修改，成功返回1，失败返回0,该接口仅限有root权限的管理员调用!")
  public Q updateOpenRegionRegistration(@Validated({UpdateValidGroup.class}) @RequestBody RegistrationInformation registrationInformation, Authentication authentication) {
    AdminInfo admin = adminInfoService.getAdministrationInfo(authentication.getName());
    AdminInfo reference = adminInfoService.getAdministrationInfoByAdminCode(registrationInformation.getReference());
    registrationInformation.setOperate(authentication.getName());
    if ("".equals(registrationInformation.getReference()) || null == reference) {
      return Q.error(StateCode.ADMINISTRATION_CODE_INVALID_EXCEPTION.getCode(), StateCode.ADMINISTRATION_CODE_INVALID_EXCEPTION.getMsg());
    } else {
      if ("root".equals(admin.getAuthority())) {
        return userOperateService.updateRegistration(Objects.requireNonNull(Q.ok().put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY))).put("registrationInformation", registrationInformation));
      } else if ("region".equals(admin.getAuthority())) {
        if (reference.getRegion().equals(admin.getRegion())) {
          return userOperateService.updateRegistration(Objects.requireNonNull(Q.ok().put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY))).put("registrationInformation", registrationInformation));
        } else {
          throw new PermissionDeniedException();
        }
      } else if ("company".equals(admin.getAuthority())) {
        if (reference.getRegion().equals(admin.getRegion()) && reference.getCompany().equals(admin.getCompany())) {
          return userOperateService.updateRegistration(Objects.requireNonNull(Q.ok().put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY))).put("registrationInformation", registrationInformation));
        } else {
          throw new PermissionDeniedException();
        }
      } else {
        if (reference.getAdminCode().equals(registrationInformation.getReference())) {
          return userOperateService.updateRegistration(Objects.requireNonNull(Q.ok().put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY))).put("registrationInformation", registrationInformation));
        } else {
          throw new PermissionDeniedException();
        }
      }
    }
  }

  @PreAuthorize("hasAnyAuthority('root,region,company,base')")
  @GetMapping("/registration/list")
  @ApiOperation(value = "获取用户报名信息", notes = "模糊查询需传入like字段")
  public Q getRegistration(String like, Authentication authentication) {
    AdminInfo adminInfo = adminInfoService.getAdministrationInfo(authentication.getName());
    if ("root".equals(adminInfo.getAuthority())) {
      return userOperateService.getRootRegistration(Objects.requireNonNull(Q.ok()
          .put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY)))
          .put("like", like));
    } else if ("region".equals(adminInfo.getAuthority())) {
      return userOperateService.getRegionRegistration(Objects.requireNonNull(Objects.requireNonNull(Q.ok()
          .put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY)))
          .put("region", adminInfo.getRegion()))
          .put("like", like));
    } else if ("company".equals(adminInfo.getAuthority())) {
      return userOperateService.getCompanyRegistration(Objects.requireNonNull(Objects.requireNonNull(Objects.requireNonNull(Q.ok()
          .put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY)))
          .put("region", adminInfo.getRegion()))
          .put("company", adminInfo.getCompany()))
          .put("like", like));
    } else if ("base".equals(adminInfo.getAuthority())) {
      return userOperateService.getBaseRegistration(Objects.requireNonNull(Objects.requireNonNull(Q.ok()
          .put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY)))
          .put("reference", adminInfo.getAdminCode()))
          .put("like", like));
    } else {
      throw new PermissionDeniedException();
    }
  }

  @PreAuthorize("hasAuthority('root')")
  @DeleteMapping("/root/delete")
  @ApiOperation(value = "删除用户报名信息", notes = "需要传入报名信息Id编号，仅限root管理员调用")
  public Q removeRegistration(Integer id) {
    return userOperateService.removeRegistration(Objects.requireNonNull(Q.ok().put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY))).put("id", id));
  }

  @PreAuthorize("hasAnyAuthority('root,region,company,base')")
  @DeleteMapping("/open/delete")
  @ApiOperation(value = "删除用户报名信息", notes = "需要传入报名信息Id编号，reference推荐码")
  public Q removeOpenRegistration(Integer id, String reference, Authentication authentication) {
    AdminInfo adminInfo = adminInfoService.getAdministrationInfo(authentication.getName());
    AdminInfo referenceInfo = adminInfoService.getAdministrationInfoByAdminCode(reference);
    if ("".equals(reference.trim()) || null == referenceInfo) {
      return Q.error(StateCode.ADMINISTRATION_CODE_INVALID_EXCEPTION.getCode(), StateCode.ADMINISTRATION_CODE_INVALID_EXCEPTION.getMsg());
    } else {
      if ("root".equals(adminInfo.getAuthority())) {
        return userOperateService.removeOpenRegistration(Objects.requireNonNull(Objects.requireNonNull(Q.ok()
            .put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY)))
            .put("id", id))
            .put("reference", reference));
      } else if ("region".equals(adminInfo.getAuthority())) {
        if (referenceInfo.getRegion().equals(adminInfo.getRegion())) {
          return userOperateService.removeOpenRegistration(Objects.requireNonNull(Objects.requireNonNull(Q.ok()
              .put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY)))
              .put("id", id))
              .put("reference", reference));
        } else {
          throw new PermissionDeniedException();
        }
      } else if ("company".equals(adminInfo.getAuthority())) {
        if (referenceInfo.getRegion().equals(adminInfo.getRegion()) && referenceInfo.getCompany().equals(adminInfo.getCompany())) {
          return userOperateService.removeOpenRegistration(Objects.requireNonNull(Objects.requireNonNull(Q.ok()
              .put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY)))
              .put("id", id))
              .put("reference", reference));
        } else {
          throw new PermissionDeniedException();
        }
      } else {
        if (reference.equals(adminInfo.getAdminCode())) {
          return userOperateService.removeOpenRegistration(Objects.requireNonNull(Objects.requireNonNull(Q.ok()
              .put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY)))
              .put("id", id))
              .put("reference", reference));
        } else {
          throw new PermissionDeniedException();
        }
      }
    }
  }
}
