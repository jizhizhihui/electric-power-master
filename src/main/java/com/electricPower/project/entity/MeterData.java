package com.electricPower.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

/**
 * <p>
 * 
 * </p>
 * @since 2020-09-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="MeterData对象", description="电力数据")
public class MeterData implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "meter_data_id",type = IdType.AUTO)
    @ApiModelProperty(value = "电力数据表ID")
    private long meterDataId;

    @ApiModelProperty(value = "电表编号；台变编号")
    private String meterSn;

    @ApiModelProperty(value = "采集时间")
    private LocalDateTime acquisitionTime;

    @ApiModelProperty(value = "保存时间")
    private OffsetDateTime saveTime;

    @ApiModelProperty(value = "A相电压")
    private float  voltageA;

    @ApiModelProperty(value = "B相电压")
    private float voltageB;

    @ApiModelProperty(value = "C相电压")
    private float voltageC;

    @ApiModelProperty(value = "A相电流")
    private float currentA;

    @ApiModelProperty(value = "B相电流")
    private float currentB;

    @ApiModelProperty(value = "C相电流")
    private float currentC;

    @ApiModelProperty(value = "零线电流")
    private float currentZeroLine;

    @ApiModelProperty(value = "剩余电流")
    private float currentRemain;

    @ApiModelProperty(value = "A相有功功率")
    private float activePowerA;

    @ApiModelProperty(value = "B相有功功率")
    private float activePowerB;

    @ApiModelProperty(value = "C相有功功率")
    private float  activePowerC;

    @ApiModelProperty(value = "总有功功率")
    private float activePowerTotal;

    @ApiModelProperty(value = "A相无功功率")
    private float reactivePowerA;

    @ApiModelProperty(value = "B相无功功率")
    private float reactivePowerB;

    @ApiModelProperty(value = "C相无功功率")
    private float reactivePowerC;

    @ApiModelProperty(value = "总无功功率")
    private float reactivePowerTotal;

    @ApiModelProperty(value = "A相功率因素")
    private float powerFactorA;

    @ApiModelProperty(value = "B相功率因素")
    private float powerFactorB;

    @ApiModelProperty(value = "C相功率因素")
    private float powerFactorC;

    @ApiModelProperty(value = "湿度")
    private Integer humidity;

    @ApiModelProperty(value = "变压器温度（前端）/表箱内温度（后端），注意：前端不是总表）")
    private Integer temperature;

    @ApiModelProperty(value = "运行状态字4（总表才有）")
    private String phaseFaultA;

    @ApiModelProperty(value = "运行状态字5（总表才有）")
    private String phaseFaultB;

    @ApiModelProperty(value = "运行状态字6（总表才有）")
    private String phaseFaultC;

    @ApiModelProperty(value = "运行状态字7（总表才有）")
    private String combinedPhaseFault;


    public void setSaveTime(){
        saveTime = OffsetDateTime.now();
    }

}
