package com.electricPower.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * tcp流量统计
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tcp_flow")
@ApiModel(value="TcpFlow对象", description="")
public class TcpFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer tcpFlowId;

    @ApiModelProperty(value = "终端地址")
    private String address;

    @ApiModelProperty(value = "字节数")
    private Integer byteNum;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime creatTime;

    public TcpFlow(){
        this.byteNum = 0;
    }

    public void connLogin(){
        this.byteNum += 700;
    }

    public void setTime(){
        this.creatTime = LocalDateTime.now();
    }

    public void transferData(int dataLength){
        this.byteNum += 45 + dataLength;
    }
}
