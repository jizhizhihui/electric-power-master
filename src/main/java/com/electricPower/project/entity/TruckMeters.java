package com.electricPower.project.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author com.chaFan
 * @since 2020-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TruckMeters对象", description="")
public class TruckMeters implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "总表唯一编号")
    private String sn;

    @ApiModelProperty(value = "总表通讯地址")
    private String commAddress;

    @ApiModelProperty(value = "总表额定电压")
    private Long ratedVoltage;

    @ApiModelProperty(value = "总表计量点地址")
    private String meterAddress;

    @ApiModelProperty(value = "总表综合倍率")
    private String ratio;

    @ApiModelProperty(value = "该总表用在那条线路")
    private String lineSn;


}
