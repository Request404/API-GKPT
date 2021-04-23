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
@ApiModel(value="UserCommit对象", description="用户测评信息")
public class UserCommit implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id编号")
    @TableId(value = "id", type = IdType.AUTO)
    @Null(message = "id编号不可指定", groups = {UpdateValidGroup.class, CreateValidGroup.class})
    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "电话号码")
    @Pattern(regexp = "^1(?:3\\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\\d|9\\d)\\d{8}$",
        message = "手机号码格式不正确",
        groups = {CreateValidGroup.class, UpdateValidGroup.class})
    @NotBlank(message = "手机号码不能为空", groups = {CreateValidGroup.class})
    private String phone;

    @ApiModelProperty(value = "学历")
    @NotBlank(message = "该字段不能为空", groups = {CreateValidGroup.class})
    private String graduate;

    @ApiModelProperty(value = "年龄")
    @NotBlank(message = "该字段不能为空", groups = {CreateValidGroup.class})
    private String age;

    @ApiModelProperty(value = "目的")
    @NotBlank(message = "该字段不能为空", groups = {CreateValidGroup.class})
    private String goal;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic(value = "1",delval = "0")
    @Null(message = "该字段为系统占用字段，不可指定",groups = {UpdateValidGroup.class, CreateValidGroup.class})
    private Integer removeState;


}
