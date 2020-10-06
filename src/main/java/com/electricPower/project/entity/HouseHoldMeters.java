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
@ApiModel(value="HouseHoldMeters对象", description="")
public class HouseHoldMeters implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "household_sn")
    @ApiModelProperty(value = "户表编号")
    private String householdSn;

    @ApiModelProperty(value = "户表通讯地址")
    private String commAddress;

    @ApiModelProperty(value = "户表额定电压")
    private String ratedVoltage;

    @ApiModelProperty(value = "户表计量点地址")
    private String meterAddress;

    @ApiModelProperty(value = "该用户表用于那条线路")
    private String lineSn;


}
