package com.electricPower.project.controller;


import com.electricPower.common.result.CommonResult;
import com.electricPower.project.entity.PowerLine;
import com.electricPower.project.entity.TruckMeters;
import com.electricPower.project.service.IPowerLineService;
import com.electricPower.project.service.ITruckMetersService;
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
@Api(tags = "主干总表")
@RestController
@RequestMapping("/truck-meters")
@Slf4j
public class TruckMetersController {

    @Autowired
    private ITruckMetersService iTruckMetersService;

    @ApiOperation("查询所有数据")
    @GetMapping("/list")
    public CommonResult getList(){
        return CommonResult.success(iTruckMetersService.list());
    }

    @ApiOperation("添加数据")
    @PostMapping("/add")
    public CommonResult add(TruckMeters truckMeters){
        return CommonResult.success(iTruckMetersService.save(truckMeters));
    }

    @ApiOperation("根据采集终端ID跟新数据")
    @PutMapping("/update")
    public CommonResult update(String sn){
        return CommonResult.success(iTruckMetersService.updateById(sn));
    }

    @ApiOperation("根据采集终端ID跟新数据")
    @DeleteMapping("/delete")
    public CommonResult delete(String sn){
        return CommonResult.success(iTruckMetersService.removeById(sn));
    }

}
