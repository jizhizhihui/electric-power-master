package com.electricPower.project.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
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
 * @since 2020-10-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(autoResultMap = true)
@ApiModel(value="AlarmInfo对象", description="")
public class AlarmInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "alarm_info_id",type = IdType.AUTO)
    @ApiModelProperty(value = "报警信息表ID")
    private Integer alarmInfoId;

    @ApiModelProperty(value = "终端编号")
    private String terminalNum;

    @ApiModelProperty(value = "报警数量，报警数量=0时，后面无户表数据")
    private Integer alarmCount;

    @ApiModelProperty(value = "户表报警信息")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private JSONObject alarmInfo;

    @ApiModelProperty(value = "终端报警标识")
    private String alarmSign;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "是否已读(true:已读  false:未读)")
    private Boolean isReade;
}
