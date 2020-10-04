package com.electricPower.project.controller;


import com.electricPower.common.result.CommonResult;
import com.electricPower.project.service.IPowerBureauService;
import com.electricPower.project.service.IPowerSupplyService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2020-10-04
 */
@Api(tags = "供电局数据表")
@RestController
@RequestMapping("/power-bureau")
@Slf4j
public class PowerBureauController {

    @Autowired
    private IPowerBureauService powerBureauService;

    @ApiOperation("查询某供电局下的所有供电所")
    @GetMapping("/getBureauAndSupply")
    public CommonResult getBureauAndSupply(String powerBureauName){
        return CommonResult.success(powerBureauService.getBureauAndSupply(powerBureauName));
    }

}
