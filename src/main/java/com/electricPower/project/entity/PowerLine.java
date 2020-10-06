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
@ApiModel(value="PowerLine对象", description="")
public class PowerLine implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "供电线路说明（10kv铁光线）")
    private String line;

    @ApiModelProperty(value = "供电所表ID")
    private Integer powerSupplyId;

    @ApiModelProperty(value = "台区名称，“如长马沟村1#公变")
    private String subStationAreas;

    @ApiModelProperty(value = "使用单位")
    private String customer;

    @TableId(value = "line_sn")
    @ApiModelProperty(value = "供电线路唯一编号")
    private String lineSn;

    @ApiModelProperty(value = "总表通讯地址")
    private String commAddress;

    @ApiModelProperty(value = "总表额定电压")
    private String ratedVoltage;

    @ApiModelProperty(value = "总表计量点地址")
    private String meterAddress;

    @ApiModelProperty(value = "总表综合倍率")
    private String ratio;

    @ApiModelProperty(value = "总表额定电流")
    private String ratedCurrent;

    @ApiModelProperty(value = "总表容量（VA)")
    private String capacity;

    @ApiModelProperty(value = "总表负责人")
    private String principal;
}
