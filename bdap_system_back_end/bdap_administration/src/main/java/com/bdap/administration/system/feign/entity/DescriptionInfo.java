package com.bdap.administration.system.feign.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.bdap.common.utils.group.CreateValidGroup;
import com.bdap.common.utils.group.UpdateValidGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

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
@ApiModel(value = "DescriptionInfo对象", description = "证书描述")
public class DescriptionInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "id编号")
  @Null(message = "id编号不可指定", groups = {CreateValidGroup.class})
  @NotNull(message = "请指定要修改报名信息的id编号", groups = {UpdateValidGroup.class})
  private Integer id;

  @ApiModelProperty(value = "标题")
  private String title;

  @ApiModelProperty(value = "内容")
  private String content;

  @ApiModelProperty(value = "图片")
  @URL(message = "图片路径格式不规范", groups = {CreateValidGroup.class, UpdateValidGroup.class})
  private String picture;


}
