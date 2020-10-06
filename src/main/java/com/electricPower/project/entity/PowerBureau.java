package com.electricPower.project.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-10-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PowerBureau对象", description="")
public class PowerBureau implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "power_bureau_id")
    @ApiModelProperty(value = "电力局表ID")
    private Integer powerBureauId;

    @ApiModelProperty(value = "电力局名称")
    private String pBName;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

}
