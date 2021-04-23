package com.bdap.traffic.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.bdap.common.utils.group.CreateValidGroup;
import com.bdap.common.utils.group.UpdateValidGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.URL;

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
@ApiModel(value = "LectureInfo对象", description = "讲师信息")
public class LectureInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "id编号")
  @TableId(value = "id", type = IdType.AUTO)
  @Null(message = "id编号不可指定", groups = {CreateValidGroup.class})
  @NotNull(message = "请指定要修改报名信息的id编号", groups = {UpdateValidGroup.class})
  private Integer id;

  @ApiModelProperty(value = "姓名")
  @NotBlank(message = "讲师姓名不能为空", groups = {CreateValidGroup.class})
  private String name;

  @ApiModelProperty(value = "描述")
  private String description;

  @ApiModelProperty(value = "研究方向")
  private String researchOrientation;

  @ApiModelProperty(value = "备注")
  private String mark;

  @ApiModelProperty(value = "照片")
  @URL(message = "图片路径格式不正确", groups = {CreateValidGroup.class, UpdateValidGroup.class})
  private String photo;

  @ApiModelProperty(value = "逻辑删除")
  @TableLogic(value = "1", delval = "0")
  @Null(message = "该字段为系统占用字段，不可指定", groups = {UpdateValidGroup.class, CreateValidGroup.class})
  private Integer removeState;


}
