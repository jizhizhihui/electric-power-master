package com.electricPower.project.controller;


import com.electricPower.common.result.CommonResult;
import com.electricPower.project.entity.HouseHoldMeters;
import com.electricPower.project.service.IHouseHoldMetersService;
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
@Api(tags = "后端表箱")
@RestController
@RequestMapping("/house-hold-meters")
@Slf4j
public class HouseHoldMetersController {

    @Autowired
    private IHouseHoldMetersService iHouseHoldMetersService;

    @ApiOperation("查询所有数据")
    @GetMapping("/list")
    public CommonResult getList(){
        return CommonResult.success(iHouseHoldMetersService.list());
    }

    @ApiOperation("添加数据")
    @PostMapping("/add")
    public CommonResult add(HouseHoldMeters houseHoldMeters){
        return CommonResult.success(iHouseHoldMetersService.save(houseHoldMeters));
    }

    @ApiOperation("根据户表编号sn更新数据")
    @PutMapping("/update")
    public CommonResult update( String sn){
        return CommonResult.success(iHouseHoldMetersService.updateById(sn));
    }

    @ApiOperation("根据户表编号删除数据")
    @DeleteMapping("/delete")
    public CommonResult delete(String sn){
        return CommonResult.success(iHouseHoldMetersService.removeById(sn));
    }









}
