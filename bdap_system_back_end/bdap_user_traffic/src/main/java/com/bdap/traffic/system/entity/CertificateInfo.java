package com.bdap.traffic.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

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
@ApiModel(value = "CertificateInfo对象", description = "大数据资格证书")
public class CertificateInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "id编号")
  @TableId(value = "id", type = IdType.AUTO)
  @Null(message = "id编号不可指定", groups = {CreateValidGroup.class})
  @NotNull(message = "请指定要修改报名信息的id编号", groups = {UpdateValidGroup.class})
  private Integer id;

  @ApiModelProperty(value = "证书名")
  @NotBlank(message = "证书名不能为空", groups = {CreateValidGroup.class})
  private String certificateName;

  @ApiModelProperty(value = "等级")
  @StringListValid(values = {"初级", "中级", "高级"},
      message = "证书等级格式不规范", groups = {CreateValidGroup.class, UpdateValidGroup.class})
  @NotBlank(message = "证书等级不能为空", groups = {CreateValidGroup.class})
  private String certificateGrade;

  @ApiModelProperty(value = "价格")
  @NotNull(message = "证书价格未指定", groups = {CreateValidGroup.class})
  private Float price;

  @ApiModelProperty(value = "逻辑删除")
  @TableLogic(value = "1", delval = "0")
  @Null(message = "该字段为系统占用字段，不可指定", groups = {UpdateValidGroup.class, CreateValidGroup.class})
  private Integer removeState;


}
