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
@ApiModel(value="SysException对象", description="")
public class SysException implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "sys_exce_id")
    @ApiModelProperty(value = "系统异常表ID")
    private Integer sysExceId;

    @ApiModelProperty(value = "功能模块")
    private String model;

    @ApiModelProperty(value = "错误代码")
    private String code;

    @ApiModelProperty(value = "错误信息")
    private String sysMsg;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime creatTime;


}
