package com.electricPower.project.entity;

import java.io.Serializable;

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
@ApiModel(value="PowerSupply对象", description="")
public class PowerSupply implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "power_supply_id")
    @ApiModelProperty(value = "电力所表ID")
    private Integer powerSupplyId;

    @ApiModelProperty(value = "电力所名称")
    private String pSName;

    @ApiModelProperty(value = "电力所地址")
    private String address;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "所属电力局ID")
    private Integer powerBureauId;


}
