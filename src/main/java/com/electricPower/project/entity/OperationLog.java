package com.electricPower.project.entity;

import java.time.LocalDateTime;
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
@ApiModel(value="OperationLog对象", description="")
public class OperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "oper_id")
    @ApiModelProperty(value = "操作日志表ID")
    private Integer operId;

    @ApiModelProperty(value = "请求参数")
    private String reqParam;

    @ApiModelProperty(value = "返回参数")
    private String resParam;

    @ApiModelProperty(value = "操作员ID")
    private String userId;

    @ApiModelProperty(value = "操作员姓名")
    private String username;

    @ApiModelProperty(value = "操作方法")
    private String method;

    @ApiModelProperty(value = "请求URL")
    private String url;

    @ApiModelProperty(value = "请求IP")
    private String ip;

    @ApiModelProperty(value = "操作版本")
    private String ver;

    @ApiModelProperty(value = "操作时间")
    private LocalDateTime operationTime;

    @ApiModelProperty(value = "功能模块")
    private String model;

    @ApiModelProperty(value = "操作类型")
    private String type;

    @ApiModelProperty(value = "操作描述")
    private String operDesc;


}
