package com.electricPower.project.entity;

import java.time.LocalDate;
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
 * @since 2020-10-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Terminal对象", description="")
public class Terminal implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "terminal_num")
    @ApiModelProperty(value = "终端编号，每个终端出厂时都由一个唯一资产编号")
    private String terminalNum;

    @ApiModelProperty(value = "终端型号")
    private String model;

    @ApiModelProperty(value = "终端类型（t 前端采集器，f 后端采集器）")
    private Boolean type;

    @ApiModelProperty(value = "通讯规约")
    private String commProtocol;

    @ApiModelProperty(value = "终端通讯地址")
    private String commAddress;

    @ApiModelProperty(value = "SIM卡号(物联网卡)")
    private String simNumber;

    @ApiModelProperty(value = "额定电压")
    private String ratedVoltage;

    @ApiModelProperty(value = "安装地点地址，如果没有使用，则为空")
    private String location;

    @ApiModelProperty(value = "供电单位，如果没有使用，则为空")
    private String department;

    @ApiModelProperty(value = "该终端采集的表箱编号；后端采集器；管理单元")
    private String householdSn;

    @ApiModelProperty(value = "表的唯一编号")
    private String sn;

    @ApiModelProperty(value = "终端是否在线( 1,在线；0，掉线)")
    private Boolean isAlive;

    @ApiModelProperty(value = "供电线路唯一编号；线路id")
    private String lineSn;

    @ApiModelProperty(value = "投运日期")
    private LocalDate putOperationDate;
//
//    @ApiModelProperty(value = "供电线路说明（10kv铁光线）")
//    private String line;
//
//    @ApiModelProperty(value = "台区名称，“如长马沟村1#公变")
//    private String subStationAreas;
}
