package com.electricPower.project.controller;


import com.electricPower.common.result.CommonResult;
import com.electricPower.project.entity.HouseHoldMeters;
import com.electricPower.project.service.IHouseHoldMetersService;
import com.electricPower.project.service.ITcpFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tcp-flow")
@Api(tags = "终端TCP上传流量统计")
public class TcpFlowController {
    @Autowired
    private ITcpFlowService tcpFlowService;

    @ApiOperation("查询所有")
    @GetMapping("/list")
    public CommonResult getList() {
        return CommonResult.success(tcpFlowService.list());
    }

    @ApiOperation("根据终端编号查询")
    @GetMapping("/get")
    public CommonResult get(String id) {
        return CommonResult.success(tcpFlowService.getById(id));
    }

}
