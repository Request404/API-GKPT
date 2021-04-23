package com.bdap.administration.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.bdap.common.utils.valid.NumberListValid;
import com.bdap.common.utils.valid.StringListValid;
import com.bdap.common.utils.group.CreateValidGroup;
import com.bdap.common.utils.group.UpdateValidGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
@ApiModel(value = "AdminInfo对象", description = "管理员用户信息")
public class AdminInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "id编号")
  @TableId(value = "id", type = IdType.AUTO)
  @Null(message = "id编号不可指定", groups = {CreateValidGroup.class, UpdateValidGroup.class})
  private Integer id;

  @ApiModelProperty(value = "用户名")
  @NotBlank(message = "用户名不能为空", groups = {CreateValidGroup.class, UpdateValidGroup.class})
  private String user;

  @ApiModelProperty(value = "姓名")
  @NotBlank(message = "姓名不能为空", groups = {CreateValidGroup.class})
  private String name;

  @ApiModelProperty(value = "性别")
  @StringListValid(values = {"男", "女"}, message = "性别必须指定为‘男’或‘女’", groups = {CreateValidGroup.class, UpdateValidGroup.class})
  @NotBlank(message = "性别字段不能为空’", groups = {CreateValidGroup.class, UpdateValidGroup.class})
  private String gender;

  @ApiModelProperty(value = "手机")
  @Pattern(regexp = "^1(?:3\\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\\d|9\\d)\\d{8}$",
      message = "手机号码格式不正确",
      groups = {CreateValidGroup.class, UpdateValidGroup.class})
  @NotBlank(message = "手机号码不能为空", groups = {CreateValidGroup.class})
  private String phone;

  @ApiModelProperty(value = "邮箱")
  @Email(message = "邮箱格式不正确", groups = {CreateValidGroup.class, UpdateValidGroup.class})
  private String mail;

  @ApiModelProperty(value = "备注")
  private String mark;

  @ApiModelProperty(value = "地区")
  @NotBlank(message = "地区不可以为空", groups = {CreateValidGroup.class})
  private String region;

  @ApiModelProperty(value = "公司")
  @NotBlank(message = "公司或机构必须指明", groups = {CreateValidGroup.class})
  private String company;

  @ApiModelProperty(value = "权限")
  @StringListValid(values = {"root", "region", "company", "base"},
      message = "必须提交指定的权限值",
      groups = {CreateValidGroup.class, UpdateValidGroup.class})
  private String authority;

  @ApiModelProperty(value = "级别")
  @NumberListValid(values = {0, 1, 2, 3},
      message = "必须提交指定的等级",
      groups = {CreateValidGroup.class, UpdateValidGroup.class})
  private Integer level;

  @ApiModelProperty(value = "管理员身份码（用于推荐用户报名填写）")
  @Null(message = "该字段为系统占用字段，不可指定", groups = {UpdateValidGroup.class, CreateValidGroup.class})
  private String adminCode;

  @ApiModelProperty(value = "操作时间")
  @Null(message = "操作时间为系统自动生成，不可指定", groups = {UpdateValidGroup.class, CreateValidGroup.class})
  private String operateTime;

  @ApiModelProperty(value = "操作人")
  @Null(message = "操作人为系统自动辨识，不可指定", groups = {UpdateValidGroup.class, CreateValidGroup.class})
  private String operate;

  @ApiModelProperty(value = "逻辑删除")
  @TableLogic(value = "1", delval = "0")
  @Null(message = "该字段为系统占用字段，不可指定", groups = {UpdateValidGroup.class, CreateValidGroup.class})
  private Integer removeState;

  @TableField(exist = false)
  private List<AdminInfo> subordinate;
}
