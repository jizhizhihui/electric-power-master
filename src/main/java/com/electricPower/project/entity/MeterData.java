package com.electricPower.project.entity;

import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value="MeterData对象", description="")
public class MeterData implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "电表编号；台变编号")
    private String meterSn;

    @ApiModelProperty(value = "采集时间")
    private LocalDate acquitionTime;

    @ApiModelProperty(value = "保存时间")
    private LocalDate saveTime;

    @ApiModelProperty(value = "A相电压")
    @TableField("vp_haseA")
    private String vpHasea;

    @ApiModelProperty(value = "B相电压")
    @TableField("vp_haseB")
    private String vpHaseb;

    @ApiModelProperty(value = "C相电压")
    @TableField("vp_haseC")
    private String vpHasec;

    @ApiModelProperty(value = "A相电流")
    @TableField("cp_haseA")
    private String cpHasea;

    @ApiModelProperty(value = "B相电流")
    @TableField("cp_haseB")
    private String cpHaseb;

    @ApiModelProperty(value = "C相电流")
    @TableField("cp_haseC")
    private String cpHasec;

    @ApiModelProperty(value = "零线电流")
    private String czeroLine;

    @ApiModelProperty(value = "剩余电流")
    private String aftercurrent;

    @ApiModelProperty(value = "A相有功功率")
    @TableField("active_powerA")
    private String activePowera;

    @ApiModelProperty(value = "B相有功功率")
    @TableField("active_powerB")
    private String activePowerb;

    @ApiModelProperty(value = "C相有功功率")
    @TableField(" active_powerC")
    private String  activePowerc;

    @ApiModelProperty(value = "总有功功率")
    private String activePowerTotal;

    @ApiModelProperty(value = "A相无功功率")
    @TableField("reactive_powerA")
    private String reactivePowera;

    @ApiModelProperty(value = "B相无功功率")
    @TableField("reactive_powerB")
    private String reactivePowerb;

    @ApiModelProperty(value = "C相无功功率")
    @TableField("reactive_powerC")
    private String reactivePowerc;

    @ApiModelProperty(value = "总无功功率")
    private String reactivePowerTotal;

    @ApiModelProperty(value = "A相功率因素")
    @TableField("power_factorA")
    private String powerFactora;

    @ApiModelProperty(value = "B相功率因素")
    @TableField("power_factorB")
    private String powerFactorb;

    @ApiModelProperty(value = "C相功率因素")
    @TableField("power_factorC")
    private String powerFactorc;

    @ApiModelProperty(value = "变压器温度（前端）/表箱内温度（后端），注意：前端不是总表）")
    private String transformerTemperature;

    @ApiModelProperty(value = "运行状态字4（总表才有）")
    private String state4;

    @ApiModelProperty(value = "运行状态字5（总表才有）")
    private String state5;

    @ApiModelProperty(value = "运行状态字6（总表才有）")
    private String state6;

    @ApiModelProperty(value = "运行状态字7（总表才有）")
    private String state7;


}
