package com.bdap.administration.system.controller;

import com.bdap.administration.system.feign.UserTrafficService;
import com.bdap.administration.system.feign.entity.CertificateInfo;
import com.bdap.administration.system.feign.entity.LectureInfo;
import com.bdap.common.utils.Q;
import com.bdap.common.utils.group.CreateValidGroup;
import com.bdap.common.utils.group.UpdateValidGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Api(value = "用户流量信息API")
@RestController
@RequestMapping("/user-operate")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminUserOperateController {

  @Value("${feign.SecretKey}")
  private String FEIGN_SECRET_KEY;

  private final PasswordEncoder passwordEncoder;

  private final UserTrafficService userTrafficService;

  @PreAuthorize("hasAuthority('root')")
  @PutMapping("/certificate-info")
  @ApiOperation(value = "证书信息添加", notes = "传入一个证书对象,返回值大于0表示添加成功,权限、数据格式错误会触发异常")
  public Q certificateAdd(@Validated({CreateValidGroup.class}) @RequestBody CertificateInfo certificateInfo) {
    return userTrafficService.certificateAdd(Objects.requireNonNull(Q.ok().put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY))).put("certificateInfo", certificateInfo));
  }

  @PreAuthorize("hasAuthority('root')")
  @DeleteMapping("/certificate-info")
  @ApiOperation(value = "证书信息删除", notes = "传入一个证书id,返回值为data>0表示删除成功,权限不足会触发异常")
  public Q certificateRemove(Integer id) {
    return userTrafficService.certificateRemove(Objects.requireNonNull(Q.ok().put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY))).put("id", id));
  }

  @PreAuthorize("hasAuthority('root')")
  @PostMapping("/certificate-info")
  @ApiOperation(value = "证书信息修改", notes = "传入一个证书对象,返回值大于0表示修改成功,数据格式错误、权限不足会触发异常")
  public Q certificateUpdate(@Validated({UpdateValidGroup.class}) @RequestBody CertificateInfo certificateInfo) {
    return userTrafficService.certificateUpdate(Objects.requireNonNull(Q.ok().put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY))).put("certificateInfo", certificateInfo));
  }

  @PreAuthorize("hasAuthority('root')")
  @PutMapping("/lecture-info")
  @ApiOperation(value = "讲师信息添加", notes = "传入一个讲师信息对象,返回值大于0表示添加成功,数据格式错误、权限不足会触发异常")
  public Q lectureInfoAdd(@Validated({CreateValidGroup.class}) @RequestBody LectureInfo lectureInfo) {
    return userTrafficService.lectureInfoAdd(Objects.requireNonNull(Q.ok().put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY))).put("lectureInfo", lectureInfo));
  }

  @PreAuthorize("hasAuthority('root')")
  @DeleteMapping("/lecture-info")
  @ApiOperation(value = "讲师信息删除", notes = "传入一个讲师信息id,返回值为true表示删除成功,权限不足会触发异常")
  public Q lectureInfoRemove(Integer id) {
    return userTrafficService.lectureInfoRemove(Objects.requireNonNull(Q.ok().put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY))).put("id", id));
  }

  @PreAuthorize("hasAuthority('root')")
  @PostMapping("/lecture-info")
  @ApiOperation(value = "讲师信息修改", notes = "传入一个讲师信息对象,返回值大于0表示修改成功,数据格式错误、权限不足会触发异常")
  public Q lectureInfoUpdate(@Validated({UpdateValidGroup.class}) @RequestBody LectureInfo lectureInfo) {
    return userTrafficService.lectureInfoUpdate(Objects.requireNonNull(Q.ok().put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY))).put("lectureInfo", lectureInfo));
  }

  @PreAuthorize("hasAuthority('root')")
  @DeleteMapping("/user-traffic")
  @ApiOperation(value = "用户流量删除", notes = "传入一个用户流量Ids整形数组,返回值为>0表示删除成功,权限不足会触发异常")
  public Q userTrafficRemove(Integer[] Ids) {
    return userTrafficService.userTrafficRemove(Objects.requireNonNull(Q.ok().put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY))).put("Ids", Ids));
  }

  @PreAuthorize("hasAuthority('root')")
  @GetMapping("/user-traffic")
  @ApiOperation(value = "用户流量列表", notes = "返回用户流量列表,page需要查询的页数，size每页大写，like模糊查询")
  public Q userTrafficList(Integer page, Integer size, String like) {
    return userTrafficService.userTrafficList(Objects.requireNonNull(Objects.requireNonNull(Objects.requireNonNull(Q.ok().put("feignSecretKey", passwordEncoder.encode(FEIGN_SECRET_KEY))).put("page", page)).put("size", size)).put("like", like));
  }

}
