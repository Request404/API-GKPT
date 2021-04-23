package com.bdap.traffic.system.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bdap.common.exception.FeignInvokeException;
import com.bdap.common.utils.Q;
import com.bdap.traffic.system.entity.CertificateInfo;
import com.bdap.traffic.system.service.CertificateInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author GuoKeGD
 * @since 2020-12-01
 */
@Api("证书信息")
@RestController
@RequestMapping("/certificate-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CertificateInfoController {

  @Value("${feign.SecretKey}")
  private String FEIGN_SECRET_KEY;

  private final PasswordEncoder passwordEncoder;

  private final CertificateInfoService certificateInfoService;

  @GetMapping("/list")
  @ApiOperation(value = "证书列表", notes = "返回所有证书列表")
  public Q certificateList() {
    List<CertificateInfo> certificateInfos = certificateInfoService.getCertificates();
    return Objects.requireNonNull(Q.ok().put("data", certificateInfos));
  }

  @GetMapping("/feign/certificate")
  @ApiOperation(value = "证书名列表", notes = "返回所有证书名列表")
  public Q certificateListName() {
    ArrayList<String> nameList = new ArrayList<>();
    List<CertificateInfo> certificateInfos = certificateInfoService.getCertificates();
    for (CertificateInfo certificateInfo : certificateInfos) {
      nameList.add(certificateInfo.getCertificateName());
    }
    return Q.ok().put("data", nameList);
  }

  @PreAuthorize("hasAuthority('root')")
  @PostMapping("/feign/put")
  @ApiOperation(value = "证书添加", notes = "传入一个证书对象,返回值大于0表示添加成功,权限、数据格式错误会触发异常")
  public Q certificateAdd(@RequestBody Q q) {
    String feign_secret_key = (String) q.get("feignSecretKey");
    if (passwordEncoder.matches(FEIGN_SECRET_KEY, feign_secret_key)) {
      final CertificateInfo certificateInfo = JSON.toJavaObject(JSONObject.parseObject(JSON.toJSONString(q.get("certificateInfo"))), CertificateInfo.class);
      Integer i = certificateInfoService.createCertificate(certificateInfo);
      if (0 != i) {
        return Objects.requireNonNull(Q.ok().put("data", i)).put("msg", "证书信息添加成功");
      } else {
        return Objects.requireNonNull(Q.ok().put("data", i)).put("msg", "证书信息添加失败");
      }
    } else {
      throw new FeignInvokeException();
    }
  }

  @PreAuthorize("hasAuthority('root')")
  @PostMapping("/feign/delete")
  @ApiOperation(value = "证书删除", notes = "传入一个证书id,返回值为data>0表示删除成功,权限不足会触发异常")
  public Q certificateRemove(@RequestBody Q q) {
    String feign_secret_key = (String) q.get("feignSecretKey");
    if (passwordEncoder.matches(FEIGN_SECRET_KEY, feign_secret_key)) {
      Integer id = (Integer) q.get("id");
      Integer i = certificateInfoService.removeCertificate(id);
      if (0 != i) {
        return Objects.requireNonNull(Q.ok().put("data", true)).put("msg", "删除成功");
      } else {
        return Objects.requireNonNull(Q.ok().put("data", false)).put("msg", "删除失败");
      }
    } else {
      throw new FeignInvokeException();
    }
  }

  @PreAuthorize("hasAuthority('root')")
  @PostMapping("/feign/update")
  @ApiOperation(value = "证书修改", notes = "传入一个证书对象,返回值大于0表示修改成功,数据格式错误、权限不足会触发异常")
  public Q certificateUpdate(@RequestBody Q q) {
    String feign_secret_key = (String) q.get("feignSecretKey");
    if (passwordEncoder.matches(FEIGN_SECRET_KEY, feign_secret_key)) {
      final CertificateInfo certificateInfo = JSON.toJavaObject(JSONObject.parseObject(JSON.toJSONString(q.get("certificateInfo"))), CertificateInfo.class);
      Integer i = certificateInfoService.updateCertificate(certificateInfo);
      if (0 != i) {
        return Objects.requireNonNull(Q.ok().put("data", i)).put("msg", "证书信息修改成功");
      } else {
        return Objects.requireNonNull(Q.ok().put("data", i)).put("msg", "证书信息修改失败");
      }
    } else {
      throw new FeignInvokeException();
    }

  }
}

