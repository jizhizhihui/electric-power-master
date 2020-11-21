package com.electricPower.project.controller;


import com.electricPower.common.result.CommonResult;
import com.electricPower.project.entity.HouseHoldMeters;
import com.electricPower.project.entity.Terminal;
import com.electricPower.project.service.IHouseHoldMetersService;
import com.electricPower.project.service.ITerminalService;
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
@Api(tags = "后端表箱")
@RestController
@RequestMapping("/house-hold-meters")
@Slf4j
public class HouseHoldMetersController {

    @Autowired
    private IHouseHoldMetersService houseHoldMetersService;

}
//    @ApiOperation("查询所有的管理单元")
//    @GetMapping("/getList")
//    public CommonResult getList() {
//        return CommonResult.success(houseHoldMetersService.list());
//    }
//
//    @ApiOperation("根据Id查询所有的管理单元")
//    @GetMapping("/getHouseholdSn")
//    public CommonResult get(String householdSn) {
//        return CommonResult.success(houseHoldMetersService.getById(householdSn));
//    }
//
//    @ApiOperation("添加查管理单元")
//    @PostMapping("/add")
//    public CommonResult add(@RequestBody HouseHoldMeters houseHoldMeters) {
//        return CommonResult.success(houseHoldMetersService.save(houseHoldMeters));
//    }
//
//    @ApiOperation("更新查管理单元")
//    @PutMapping("/update")
//    public CommonResult update(@RequestBody HouseHoldMeters houseHoldMeters) {
//
//        return CommonResult.success(houseHoldMetersService.updateById(houseHoldMeters));
//    }
//
//    @ApiOperation("通过id删除查管理单元")
//    @DeleteMapping("/delete")
//    public CommonResult delete(String householdSn) {
//
//        return CommonResult.success(houseHoldMetersService.removeById(householdSn));
//    }
