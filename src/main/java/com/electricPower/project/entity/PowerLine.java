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
@ApiModel(value="PowerLine对象", description="")
public class PowerLine implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "高压线路唯一编号")
    private String lineSn;

    @ApiModelProperty(value = "供电线路说明，如“10KV铁光线”")
    private String line;

    @ApiModelProperty(value = "供电单位，如“盘龙供电锁”")
    private String department;

    @ApiModelProperty(value = "台区名称，“如长马沟村1#公变”")
    private String subStationAreas;

    @ApiModelProperty(value = "用户名")
    private String username;


}
