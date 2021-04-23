package com.bdap.administration.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.bdap.common.utils.group.CreateValidGroup;
import com.bdap.common.utils.valid.StringListValid;
import com.bdap.common.utils.group.UpdateValidGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;

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
@ApiModel(value = "AdminAccount对象", description = "管理员账号信息")
public class AdminAccount implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "id编号")
  @TableId(value = "id", type = IdType.AUTO)
  @Null(message = "id编号不可指定", groups = {UpdateValidGroup.class, CreateValidGroup.class})
  private Integer id;

  @ApiModelProperty(value = "用户")
  @NotBlank(message = "用户名不能为空", groups = {CreateValidGroup.class, UpdateValidGroup.class})
  private String user;

  @ApiModelProperty(value = "密码")
  @NotBlank(message = "密码不能为空", groups = {CreateValidGroup.class})
  private String password;

  @ApiModelProperty(value = "手机号码")
  @Pattern(regexp = "^1(?:3\\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\\d|9\\d)\\d{8}$",
      message = "手机号码格式不正确",
      groups = {CreateValidGroup.class, UpdateValidGroup.class})
  @NotBlank(message = "手机号码不能为空", groups = {CreateValidGroup.class})
  private String phone;

  @ApiModelProperty(value = "邮箱")
  @Email(message = "邮箱格式不正确", groups = {CreateValidGroup.class, UpdateValidGroup.class})
  private String mail;

  @ApiModelProperty(value = "权限")
  @StringListValid(values = {"root", "region", "company", "base"},
      message = "必须提交指定的权限值",
      groups = {CreateValidGroup.class, UpdateValidGroup.class})
  private String authority;

  @ApiModelProperty(value = "操作时间")
  @Null(message = "操作时间为系统自动生成，不可指定", groups = {CreateValidGroup.class, UpdateValidGroup.class})
  private String operateTime;

  @ApiModelProperty(value = "操作人")
  @Null(message = "操作人为系统自动辨识，不可指定", groups = {CreateValidGroup.class, UpdateValidGroup.class})
  private String operate;

  @ApiModelProperty(value = "逻辑删除")
  @TableLogic(value = "1", delval = "0")
  @Null(message = "该字段为系统占用字段，不可指定", groups = {CreateValidGroup.class, UpdateValidGroup.class,})
  private Integer removeState;

  @NotNull(message = "必须提交管理员用户信息", groups = {CreateValidGroup.class, UpdateValidGroup.class})
  @TableField(exist = false)
  AdminInfo adminInfo;
}
