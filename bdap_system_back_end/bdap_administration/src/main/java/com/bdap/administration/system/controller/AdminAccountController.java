package com.bdap.administration.system.controller;

import com.bdap.administration.system.entity.AdminAccount;
import com.bdap.administration.system.entity.AdminInfo;
import com.bdap.administration.system.feign.OttService;
import com.bdap.administration.system.service.AdminAccountService;
import com.bdap.administration.system.service.AdminCommonService;
import com.bdap.administration.system.service.AdminInfoService;
import com.bdap.common.dto.BadpUserDto;
import com.bdap.common.dto.BdapUserAlterDto;
import com.bdap.common.dto.BdapUserConnectDto;
import com.bdap.common.exception.ArgumentNotValidException;
import com.bdap.common.exception.FeignInvokeException;
import com.bdap.common.utils.Q;
import com.bdap.common.utils.group.CreateValidGroup;
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

import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author GuoKeGD
 * @since 2020-12-01
 */
@Api("管理员账户")
@RestController
@RequestMapping("/admin-account")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminAccountController {

  @Value("${feign.SecretKey}")
  private String FEIGN_SECRET_KEY;

  private final PasswordEncoder passwordEncoder;

  private final AdminAccountService adminAccountService;

  private final AdminInfoService adminInfoService;

  private final AdminCommonService adminCommonService;

  private final OttService ottService;

  @PostMapping("/login")
  @ApiOperation(value = "管理员用户登录", notes = "管理员登录")
  public BadpUserDto administrationLogin(@RequestBody BdapUserConnectDto bdapConnectDto) {
    if (passwordEncoder.matches(FEIGN_SECRET_KEY, bdapConnectDto.getFeignSecretKey())) {
      return adminAccountService.getAdministrationByUser(bdapConnectDto.getUsername());
    } else {
      throw new FeignInvokeException();
    }
  }

  @GetMapping("/sms/sendCode")
  @ApiOperation(value = "发送验证码", notes = "传入用户名,返回加密手机号")
  public Q sendVerificationCode(@RequestParam String user) {
    AdminInfo info = adminInfoService.getAdministrationInfo(user);
    String Number = info.getPhone();
    if (Number.matches("^1(?:3\\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\\d|9\\d)\\d{8}$")) {
      String verifyCode = adminAccountService.generatorVerifyCode(Number);
      ottService.sendVerifyCode(Objects.requireNonNull(Objects.requireNonNull(Q.ok().put("number", Number)).put("verifyCode", verifyCode)).put("secretKey", passwordEncoder.encode(FEIGN_SECRET_KEY)));
      String phone = Number.substring(0, 3) + ("****") + Number.substring(Number.length() - 4);
      return Objects.requireNonNull(Q.ok().put("data", phone)).put("msg", "验证码发送成功");
    } else {
      return Q.error(12001, "用户模块数据校验异常").put("msg", "手机号码格式不正确");
    }
  }

  @PostMapping("/password/update")
  @ApiOperation(value = "管理员修改密码", notes = "需要用户名、密码、验证码")
  public Q administrationLogin(@RequestBody BdapUserAlterDto bdapUserAlterDto) {
    AdminInfo info = adminInfoService.getAdministrationInfo(bdapUserAlterDto.getUser());
    if (adminAccountService.generatorVerifyCode(info.getPhone()).equals(bdapUserAlterDto.getVerifyCode())) {
      Integer alter = adminAccountService.administrationPasswordAlter(bdapUserAlterDto.getUser(), bdapUserAlterDto.getPassword());
      if (0 != alter) {
        return Objects.requireNonNull(Q.ok().put("data", alter)).put("msg", "密码修改成功");
      } else {
        return Objects.requireNonNull(Q.ok().put("data", alter)).put("msg", "密码修改失败");
      }
    } else {
      return Objects.requireNonNull(Q.ok().put("data", 0)).put("msg", "验证码不正确");
    }
  }

  @PreAuthorize("hasAnyAuthority('root,region,company')")
  @ApiOperation(value = "管理员用户添加", notes = "传入一个AdminAccount对象,返回值大于0表示添加成功,数据格式错误、权限不足会触发异常")
  @PutMapping("/add")
  public Q administrationAdd(@Validated({CreateValidGroup.class}) @RequestBody AdminAccount adminAccount, Authentication authentication) {
    if (this.validatedAdminInfo(adminAccount, adminAccount.getAdminInfo())) {
      throw new ArgumentNotValidException();
    } else {
      adminAccount.setPassword(passwordEncoder.encode(adminAccount.getPassword()));
      Integer i = adminCommonService.administrationAdd(adminAccount, authentication);
      if (i != 0) {
        return Objects.requireNonNull(Q.ok().put("data", i)).put("msg", "用户添加成功");
      } else {
        return Objects.requireNonNull(Q.ok().put("data", i)).put("msg", "用户添加失败");
      }
    }
  }

  @PreAuthorize("hasAnyAuthority('root,region,company')")
  @ApiOperation(value = "管理员用户修改", notes = "传入一个AdminAccount对象,返回值大于0表示添加成功,数据格式错误、权限不足会触发异常")
  @PostMapping("/update")
  public Q administrationAccountUpdate(@Validated({UpdateValidGroup.class}) @RequestBody AdminAccount adminAccount, Authentication authentication) {
    if (this.validatedAdminInfo(adminAccount, adminAccount.getAdminInfo())) {
      throw new ArgumentNotValidException();
    } else {
      if (adminAccount.getPassword() != null) {
        if (!"".equals(adminAccount.getPassword().trim())) {
          adminAccount.setPassword(passwordEncoder.encode(adminAccount.getPassword()));
        } else {
          adminAccount.setPassword(null);
        }
      }
      Integer i = adminCommonService.administrationUpdate(adminAccount, authentication);
      if (i != 0) {
        return Objects.requireNonNull(Q.ok().put("data", i)).put("msg", "用户修改成功");
      } else {
        return Objects.requireNonNull(Q.ok().put("data", i)).put("msg", "用户修改失败");
      }
    }
  }

  @PreAuthorize("hasAnyAuthority('root,region,company')")
  @ApiOperation(value = "管理员用户删除", notes = "传入一个User字段,返回值为true表示删除成功,权限不足会触发异常")
  @DeleteMapping("/delete")
  public Q administrationRemove(String user, Authentication authentication) {
    if ("".equals(user.trim())) {
      return Q.error(11001, "管理员模块数据校验异常").put("msg", "缺少相关字段");
    } else {
      Boolean result = adminCommonService.administrationRemove(user, authentication);
      if (result) {
        return Objects.requireNonNull(Q.ok().put("data", true)).put("msg", "删除成功");
      } else {
        return Objects.requireNonNull(Q.ok().put("data", false)).put("msg", "删除失败");
      }
    }
  }

  //校验信息合法性校验
  private boolean validatedAdminInfo(AdminAccount adminAccount, AdminInfo adminInfo) {
    return !adminAccount.getUser().equals(adminInfo.getUser())
        || "".equals(adminInfo.getName().trim()) || null == adminInfo.getName()
        || !(adminInfo.getGender().equals("男") || adminInfo.getGender().equals("女"))
        || !adminAccount.getPhone().equals(adminInfo.getPhone())
        || !adminAccount.getMail().equals(adminInfo.getMail())
        || "".equals(adminInfo.getRegion().trim()) || null == adminInfo.getRegion()
        || "".equals(adminInfo.getCompany().trim()) || null == adminInfo.getCompany()
        || !adminAccount.getAuthority().equals(adminInfo.getAuthority())
        || this.authorityLevelMatchValid(adminInfo.getAuthority(), adminInfo.getLevel())
        || null != adminInfo.getOperate() || null != adminInfo.getOperateTime()
        || null != adminInfo.getAdminCode() || null != adminInfo.getRemoveState();
  }

  private boolean authorityLevelMatchValid(String authority, Integer level) {
    return (!"root".equals(authority) || level != 0)
        && (!"region".equals(authority) || level != 1)
        && (!"company".equals(authority) || level != 2)
        && (!"base".equals(authority) || level != 3);
  }

}

