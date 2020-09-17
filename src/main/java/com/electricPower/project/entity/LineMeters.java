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
 * @since 2020-09-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="LineMeters对象", description="")
public class LineMeters implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "供电线路")
    private String line;

    @ApiModelProperty(value = "台区名称，“如长马沟村1#公变")
    private String subStationAreas;

    @ApiModelProperty(value = "采集点地址")
    private String location;

    @ApiModelProperty(value = "该线路总表终端")
    private String terminalSn;


}
