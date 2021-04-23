package com.bdap.traffic.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

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
@ApiModel(value = "UserTraffic对象", description = "用户流量")
public class UserTraffic implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "id编号")
  @TableId(value = "id", type = IdType.AUTO)
  @Null(message = "id编号不可指定", groups = {CreateValidGroup.class})
  private Integer id;

  @ApiModelProperty(value = "IP地址")
  @Pattern(regexp = "^((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)$",
      message = "ip地址格式不正确",
      groups = {CreateValidGroup.class})
  @NotBlank(message = "ip地址为必要字段", groups = {CreateValidGroup.class})
  private String ipAddress;

  @ApiModelProperty(value = "地区")
  @NotBlank(message = "地区为必要字段", groups = {CreateValidGroup.class})
  private String region;

  @ApiModelProperty(value = "访问时间")
  @Null(message = "访问时间为系统自动生成", groups = {CreateValidGroup.class})
  private String visitTime;

}
