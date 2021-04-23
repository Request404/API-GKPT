package com.bdap.traffic.system.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bdap.common.exception.FeignInvokeException;
import com.bdap.common.utils.Q;
import com.bdap.traffic.system.entity.LectureInfo;
import com.bdap.traffic.system.service.LectureInfoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
@RestController
@RequestMapping("/lecture-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LectureInfoController {

  @Value("${feign.SecretKey}")
  private String FEIGN_SECRET_KEY;

  private final PasswordEncoder passwordEncoder;

  private final LectureInfoService lectureInfoService;


  @GetMapping("/list")
  @ApiOperation(value = "讲师信息列表", notes = "返回所有讲师信息列表")
  public Q lectureInfoList() {
    List<LectureInfo> lectureInfos = lectureInfoService.getLectureInfos();
    if (lectureInfos.size() != 0) {
      return Objects.requireNonNull(Q.ok().put("data", lectureInfos)).put("msg", "查询成功");
    } else {
      return Objects.requireNonNull(Q.ok().put("data", lectureInfos)).put("msg", "查询结果为空");
    }
  }

  @PreAuthorize("hasAuthority('root')")
  @ApiOperation(value = "讲师信息添加", notes = "传入一个讲师信息对象,返回值大于0表示添加成功,数据格式错误、权限不足会触发异常")
  @PostMapping("/feign/add")
  public Q lectureInfoAdd(@RequestBody Q q) {
    String feignSecretKey = (String) q.get("feignSecretKey");
    if (passwordEncoder.matches(FEIGN_SECRET_KEY, feignSecretKey)) {
      final LectureInfo lectureInfo = JSON.toJavaObject(JSONObject.parseObject(JSON.toJSONString(q.get("lectureInfo"))), LectureInfo.class);
      Integer i = lectureInfoService.createLectureInfo(lectureInfo);
      if (0 != i) {
        return Objects.requireNonNull(Q.ok().put("data", i)).put("msg", "讲师信息添加成功");
      } else {
        return Objects.requireNonNull(Q.ok().put("data", i)).put("msg", "讲师信息添加失败");
      }
    } else {
      throw new FeignInvokeException();
    }
  }

  @PreAuthorize("hasAuthority('root')")
  @ApiOperation(value = "讲师信息删除", notes = "传入一个讲师信息ID,返回值为true表示删除成功,权限不足会触发异常")
  @PostMapping("/feign/delete")
  public Q lectureInfoRemove(@RequestBody Q q) {
    String feignSecretKey = (String) q.get("feignSecretKey");
    if (passwordEncoder.matches(FEIGN_SECRET_KEY, feignSecretKey)) {
      Integer id = (Integer) q.get("id");
      Integer i = lectureInfoService.removeLectureInfo(id);
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
  @ApiOperation(value = "讲师信息修改", notes = "传入一个讲师信息对象,返回值大于0表示修改成功,数据格式错误、权限不足会触发异常")
  public Q lectureInfoUpdate(@RequestBody Q q) {
    String feignSecretKey = (String) q.get("feignSecretKey");
    if (passwordEncoder.matches(FEIGN_SECRET_KEY, feignSecretKey)) {
      final LectureInfo lectureInfo = JSON.toJavaObject(JSONObject.parseObject(JSON.toJSONString(q.get("lectureInfo"))), LectureInfo.class);
      Integer i = lectureInfoService.updateLectureInfo(lectureInfo);
      if (0 != i) {
        return Objects.requireNonNull(Q.ok().put("data", i)).put("msg", "讲师信息修改成功");
      } else {
        return Objects.requireNonNull(Q.ok().put("data", i)).put("msg", "讲师信息修改失败");
      }
    } else {
      throw new FeignInvokeException();
    }

  }
}

