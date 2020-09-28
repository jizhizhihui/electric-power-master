package com.electricPower.project.controller;


import com.electricPower.common.result.CommonResult;
import com.electricPower.project.entity.LineMeters;
import com.electricPower.project.service.ILineMetersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
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
@Api(tags = "线路总表")
@RestController
@RequestMapping("/line-meters")
@Log4j2
public class LineMetersController {

    @Autowired
    private ILineMetersService iLineMetersService;

    @ApiOperation("查询所有数据")
    @GetMapping("/list")
    public CommonResult getList() {
        return CommonResult.success(iLineMetersService.list());
    }

    @ApiOperation("添加数据")
    @PostMapping("/add")
    public CommonResult add(@RequestBody LineMeters lineMeters){
        return CommonResult.success(iLineMetersService.save(lineMeters));
    }

    @ApiOperation("根据线路line更新数据")
    @PutMapping("/update")
    public CommonResult update(@RequestBody LineMeters lineMeters){
        return CommonResult.success(iLineMetersService.updateById(lineMeters));
    }

    @ApiOperation("根据线路line删除数据数据")
    @DeleteMapping("/delete")
    public CommonResult delete(@RequestBody String line){
        return CommonResult.success(iLineMetersService.removeById(line));
    }









}
