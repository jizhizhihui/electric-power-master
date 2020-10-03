package com.electricPower.project.controller;


import com.electricPower.common.result.CommonResult;
import com.electricPower.project.entity.PowerLine;
import com.electricPower.project.service.IPowerLineService;
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
 * @author com.chaFan
 * @since 2020-09-17
 */
@Api(tags = "用户供电线路表")
@RestController
@RequestMapping("/power-line")
@Slf4j
public class PowerLineController {

    @Autowired
    private IPowerLineService iPowerLineService;

    @ApiOperation("查询所有数据")
    @GetMapping("/list")
    public CommonResult getList(){
        return CommonResult.success(iPowerLineService.list());
    }

    @ApiOperation("添加数据")
    @PostMapping("/add")
    public CommonResult add(PowerLine powerLine){
        return CommonResult.success(iPowerLineService.save(powerLine));
    }

    @ApiOperation("根据高压线路ID跟新数据")
    @PutMapping("/update")
    public CommonResult update(String lineSn){
        return CommonResult.success(iPowerLineService.updateById(lineSn));
    }

    @ApiOperation("根据高压线路ID跟新数据")
    @DeleteMapping("/delete")
    public CommonResult delete(String lineSn){
        return CommonResult.success(iPowerLineService.removeById(lineSn));
    }

}
