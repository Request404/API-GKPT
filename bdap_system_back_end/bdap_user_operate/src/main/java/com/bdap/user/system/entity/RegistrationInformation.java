package com.bdap.user.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.bdap.common.utils.group.CreateValidGroup;
import com.bdap.common.utils.valid.IdNumberValid;
import com.bdap.common.utils.valid.StringListValid;
import com.bdap.common.utils.group.UpdateValidGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

/**
 * <p>
 *
 * </p>
 *
 * @author GuoKeGD
 * @since 2020-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RegistrationInformation对象", description = "用户报名信息")
public class RegistrationInformation implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "id编号")
  @TableId(value = "id", type = IdType.AUTO)
  @Null(message = "id编号不可指定", groups = {CreateValidGroup.class})
  @NotNull(message = "请指定要修改报名信息的id编号", groups = {UpdateValidGroup.class})
  private Integer id;

  @ApiModelProperty(value = "姓名")
  @NotBlank(message = "姓名不能为空", groups = {CreateValidGroup.class})
  private String name;

  @ApiModelProperty(value = "性别")
  @StringListValid(values = {"男", "女"}, message = "性别必须指定为‘男’或‘女’", groups = {CreateValidGroup.class, UpdateValidGroup.class})
  @NotBlank(message = "性别不能为空’", groups = {CreateValidGroup.class})
  private String gender;

  @ApiModelProperty(value = "身份证号")
  @IdNumberValid(message = "身份证号格式不正确", groups = {CreateValidGroup.class, UpdateValidGroup.class})
  @NotBlank(message = "身份证号不能为空", groups = {CreateValidGroup.class})
  private String identityNumber;

  @ApiModelProperty(value = "手机号")
  @Pattern(regexp = "^1(?:3\\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\\d|9\\d)\\d{8}$",
      message = "手机号码格式不正确",
      groups = {CreateValidGroup.class, UpdateValidGroup.class})
  @NotBlank(message = "手机号码不能为空", groups = {CreateValidGroup.class})
  private String phone;

  @ApiModelProperty(value = "报考证书名")
  @NotBlank(message = "报考证书名不能为空", groups = {CreateValidGroup.class})
  private String certificateName;

  @ApiModelProperty(value = "报考证书等级")
  @StringListValid(values = {"初级", "中级", "高级"},
      message = "报考证书等级不存在", groups = {CreateValidGroup.class, UpdateValidGroup.class})
  @NotBlank(message = "报考证书等级不能为空", groups = {CreateValidGroup.class})
  private String certificateGrade;

  @ApiModelProperty(value = "照片")
  @URL(message = "照片路径格式不规范", groups = {CreateValidGroup.class, UpdateValidGroup.class})
  @NotBlank(message = "照片路径不能为空", groups = {CreateValidGroup.class})
  private String photo;

  @ApiModelProperty(value = "地区")
  private String region;

  @ApiModelProperty(value = "公司/机构名称")
  private String company;

  @ApiModelProperty(value = "推荐人")
  private String base;

  @ApiModelProperty(value = "推荐码")
  private String reference;

  @ApiModelProperty(value = "用户码")
  @NotBlank(message = "报名账号不能为空", groups = {CreateValidGroup.class})
  private String userCode;

  @ApiModelProperty(value = "用户名")
  @NotBlank(message = "用户码不能为空", groups = {CreateValidGroup.class})
  private String userPhone;

  @ApiModelProperty(value = "操作时间")
  @Null(message = "操作时间为系统自动生成，不可指定", groups = {UpdateValidGroup.class, CreateValidGroup.class})
  private String operateTime;

  @ApiModelProperty(value = "操作人")
  private String operate;

  @ApiModelProperty(value = "逻辑删除")
  @TableLogic(value = "1", delval = "0")
  @Null(message = "该字段为系统占用字段，不可指定", groups = {UpdateValidGroup.class, CreateValidGroup.class})
  private Integer removeState;

}
