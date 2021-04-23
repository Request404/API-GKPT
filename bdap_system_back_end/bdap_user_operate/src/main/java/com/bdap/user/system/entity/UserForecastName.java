package com.bdap.user.system.entity;

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

import javax.validation.constraints.NotBlank;
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
@ApiModel(value="UserForecastName对象", description="用户预报名信息")
public class UserForecastName implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "联系方式")
    @NotBlank(message = "联系方式不能为空", groups = {CreateValidGroup.class})
    private String phone;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic(value = "1",delval = "0")
    @Null(message = "该字段为系统占用字段，不可指定",groups = {UpdateValidGroup.class, CreateValidGroup.class})
    private Integer removeState;


}
