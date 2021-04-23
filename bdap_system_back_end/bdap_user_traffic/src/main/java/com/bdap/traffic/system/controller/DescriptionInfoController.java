//package com.bdap.traffic.system.controller;
//
//
//import com.bdap.common.utils.Q;
//import com.bdap.traffic.system.entity.DescriptionInfo;
//import com.bdap.traffic.system.service.DescriptionInfoService;
//import io.swagger.annotations.ApiOperation;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Objects;
//
///**
// * <p>
// *  前端控制器
// * </p>
// *
// * @author GuoKeGD
// * @since 2020-12-01
// */
//@RestController
//@RequestMapping("/system/discription-info")
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//public class DescriptionInfoController {
//
//    private DescriptionInfoService descriptionInfoService;
//
//    @PutMapping("/add")
//    @ApiOperation(value = "描述信息添加", notes = "传入一个描述信息对象,返回值大于0表示添加成功,数据格式错误、权限不足会触发异常")
//    public Q descriptionInfoAdd(@RequestBody DescriptionInfo descriptionInfo, Authentication authentication) {
//        if (descriptionInfoService.descriptionInExist(descriptionInfo.getId())) {
//            return Q.error(11001, "描述信息模块数据校验异常").put("msg", "描述信息已存在");
//        } else {
//            Integer i = descriptionInfoService.createDescription(descriptionInfo);
//            if (i != 0) {
//                return Objects.requireNonNull(Q.ok().put("data", i)).put("msg", "描述信息添加成功");
//            } else {
//                return Objects.requireNonNull(Q.ok().put("data", i)).put("msg", "描述信息添加失败");
//            }
//        }
//    }
//
//    @ApiOperation(value = "描述信息删除", notes = "传入一个描述信息ID,返回值为true表示删除成功,权限不足会触发异常")
//    @DeleteMapping("/delete")
//    public Q descriptionInfoRemove(String descriptionInfoId, Authentication authentication) {
//        if ("".equals(descriptionInfoId.trim())) {
//            return Q.error(11001, "描述信息模块数据校验异常").put("msg", "缺少相关字段");
//        } else {
//            Boolean result = descriptionInfoService.removeDescription(Integer.parseInt(descriptionInfoId));
//            if (result) {
//                return Objects.requireNonNull(Q.ok().put("data", true)).put("msg", "删除成功");
//            } else {
//                return Objects.requireNonNull(Q.ok().put("data", false)).put("msg", "删除失败");
//            }
//        }
//    }
//
//    @PutMapping("/update")
//    @ApiOperation(value = "描述信息修改", notes = "传入一个描述信息对象,返回值大于0表示修改成功,数据格式错误、权限不足会触发异常")
//    public Q descriptionInfoUpdate(@RequestBody DescriptionInfo descriptionInfo, Authentication authentication) {
//        if (!descriptionInfoService.descriptionInExist(descriptionInfo.getId())) {
//            return Q.error(11001, "描述信息模块数据校验异常").put("msg", "描述信息不存在");
//        } else {
//            Integer i = descriptionInfoService.updateDescription(descriptionInfo);
//            if (i != 0) {
//                return Objects.requireNonNull(Q.ok().put("data", i)).put("msg", "描述信息修改成功");
//            } else {
//                return Objects.requireNonNull(Q.ok().put("data", i)).put("msg", "描述信息修改失败");
//            }
//        }
//    }
//
//    @GetMapping("/list")
//    @ApiOperation(value = "描述信息列表", notes = "返回所有描述信息列表")
//    public Q descriptionInfoList(Authentication authentication) {
//        List<DescriptionInfo> descriptionInfos = descriptionInfoService.getDescriptions();
//        if (descriptionInfos.size() != 0) {
//            return Objects.requireNonNull(Q.ok().put("data", descriptionInfos)).put("msg", "查询成功");
//        } else {
//            return Objects.requireNonNull(Q.ok().put("data", descriptionInfos)).put("msg", "查询结果为空");
//        }
//    }
//}
//
