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
@ApiModel(value="OperExceptionLog对象", description="")
public class OperExceptionLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "oper_exce_id")
    @ApiModelProperty(value = "操作异常表ID")
    private String operExceId;

    @ApiModelProperty(value = "请求参数")
    private String reqParam;

    @ApiModelProperty(value = "请求名称")
    private String reqName;

    @ApiModelProperty(value = "异常信息")
    private String message;

    @ApiModelProperty(value = "操作员ID")
    private String userId;

    @ApiModelProperty(value = "操作员名称")
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

    @ApiModelProperty(value = "异常名称")
    private String exceptionName;


}
