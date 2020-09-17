package com.electricPower.project.entity;

import java.math.BigDecimal;
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
 * @since 2020-09-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Terminal对象", description="")
public class Terminal implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "终端资产编号，每个终端出厂时都由一个唯一编号")
    private Long serialNumber;

    @ApiModelProperty(value = "终端型号")
    private String model;

    @ApiModelProperty(value = "终端类型")
    private String type;

    @ApiModelProperty(value = "通讯规约")
    private String commProtocol;

    @ApiModelProperty(value = "终端通讯地址")
    private String commAddress;

    @ApiModelProperty(value = "SIM卡号(物联网卡)")
    private Integer simNumber;

    @ApiModelProperty(value = "额定电压")
    private BigDecimal ratedVoltage;

    @ApiModelProperty(value = "采集点地址，如果没有使用，则为空")
    private String location;

    @ApiModelProperty(value = "供电单位，如果没有使用，则为空")
    private String department;

    @ApiModelProperty(value = "供电线路，该终端用于哪条线路，如果没有使用，则为空")
    private String line;

    @ApiModelProperty(value = "台区名称，如果没有使用，则为空")
    private String subStationAreas;

    @ApiModelProperty(value = "该终端采集的总表编号")
    private String trackMeterSn;

    @ApiModelProperty(value = "该终端采集的表箱编号")
    private String houseHoldMeterSn;

    @ApiModelProperty(value = "表的唯一编号")
    private String sn;


}
