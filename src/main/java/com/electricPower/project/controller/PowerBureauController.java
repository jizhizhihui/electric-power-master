package com.electricPower.project.controller;


import com.electricPower.common.result.CommonResult;
import com.electricPower.project.entity.MeterData;
import com.electricPower.project.entity.PowerBureau;
import com.electricPower.project.service.IPowerBureauService;
import com.electricPower.project.service.IPowerSupplyService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public CommonResult getBureau(){
        return CommonResult.success(powerBureauService.getBureau());
    }

    @ApiOperation("查询供电局所有数据")
    @GetMapping("/list")
    public CommonResult getList() {
        return CommonResult.success(powerBureauService.list());
    }

    @ApiOperation("更具Id查询供电局数据")
    @GetMapping("/getPowerBureauId")
    public CommonResult get(Integer powerBureauId) {
        return CommonResult.success(powerBureauService.getById(powerBureauId));
    }

    @ApiOperation("添加供电局数据")
    @PostMapping("/add")
    public CommonResult add( @RequestBody PowerBureau powerBureau) {
        return CommonResult.success(powerBureauService.save(powerBureau));
    }

    @ApiOperation("更新供电局数据")
    @PutMapping("/update")
    public CommonResult update(  @RequestBody PowerBureau powerBureau) {

        return CommonResult.success(powerBureauService.updateById(powerBureau));
    }

    @ApiOperation("通过id删除供电局数据")
    @DeleteMapping("/delete")
    public CommonResult delete(String id) {

        return CommonResult.success(powerBureauService.removeById(id));
    }











}
