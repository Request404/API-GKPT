package com.bdap.user.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.bdap.common.utils.group.CreateValidGroup;
import com.bdap.common.utils.group.UpdateValidGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@ApiModel(value = "UserData对象", description = "用户信息")
public class UserData implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "id编号")
  @TableId(value = "id", type = IdType.AUTO)
  @Null(message = "id编号不可指定", groups = {UpdateValidGroup.class, CreateValidGroup.class})
  private Integer id;

  @ApiModelProperty(value = "用户手机号码")
  @Pattern(regexp = "^1(?:3\\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\\d|9\\d)\\d{8}$",
      message = "手机号码格式不正确",
      groups = {CreateValidGroup.class, UpdateValidGroup.class})
  @NotBlank(message = "手机号码不能为空", groups = {CreateValidGroup.class})
  private String phone;

  @ApiModelProperty(value = "用户密码")
  @NotBlank(message = "密码不能为空", groups = {UpdateValidGroup.class, CreateValidGroup.class})
  private String password;

  @ApiModelProperty(value = "用户认证码")
  @Null(message = "用户认证码必须由系统指定", groups = {CreateValidGroup.class, UpdateValidGroup.class})
  private String userCode;

  @ApiModelProperty(value = "状态码")
  @Null(message = "该字段为系统占用字段，不可指定", groups = {UpdateValidGroup.class, CreateValidGroup.class})
  private Integer state;

  @ApiModelProperty(value = "操作时间")
  @Null(message = "操作时间为系统自动生成，不可指定", groups = {UpdateValidGroup.class, CreateValidGroup.class})
  private String operateTime;

  @ApiModelProperty(value = "操作人")
  private String operate;

  @ApiModelProperty(value = "逻辑删除")
  @TableLogic(value = "1", delval = "0")
  @Null(message = "该字段为系统占用字段，不可指定", groups = {UpdateValidGroup.class, CreateValidGroup.class})
  private Integer removeState;

  @ApiModelProperty(value = "接收用户手机验证码，仅在用户更新创建时生效")
  @TableField(exist = false)
  @NotBlank(message = "验证操作不能为空", groups = {UpdateValidGroup.class, CreateValidGroup.class})
  private String verifyCode;

}
