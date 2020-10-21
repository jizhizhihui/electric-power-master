package com.electricPower.project.controller;


import com.electricPower.common.result.CommonResult;
import com.electricPower.project.entity.PowerBureau;
import com.electricPower.project.entity.PowerSupply;
import com.electricPower.project.service.IPowerBureauService;
import com.electricPower.project.service.IPowerSupplyService;
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
@Api(tags = "供电所数据表")
@RestController
@RequestMapping("/power-supply")
@Slf4j
public class PowerSupplyController {

    @Autowired
    private IPowerSupplyService PowerSupplyService;

    @ApiOperation("查询某个一个供电所下所有的供电线路")
    @GetMapping("/getSupplyAndLine")
    public CommonResult getSupplyAndLine(String powerSupplyName){
        return CommonResult.success(PowerSupplyService.getSupplyAndLine(powerSupplyName));
    }

    @ApiOperation("查询供电所所有数据")
    @GetMapping("/list")
    public CommonResult getList() {
        return CommonResult.success(PowerSupplyService.list());
    }

    @ApiOperation("根据Id查询供电所数据")
    @GetMapping("/getPowerSupplyId")
    public CommonResult get(Integer powerSupplyId) {
        return CommonResult.success(PowerSupplyService.getById(powerSupplyId));
    }

    @ApiOperation("添加供电所数据")
    @PostMapping("/add")
    public CommonResult add(@RequestBody PowerSupply powerSupply) {
        return CommonResult.success(PowerSupplyService.save(powerSupply));
    }

    @ApiOperation("更新供电所数据")
    @PutMapping("/update")
    public CommonResult update(@RequestBody PowerSupply powerSupply) {

        return CommonResult.success(PowerSupplyService.updateById(powerSupply));
    }

    @ApiOperation("通过id删除供电所数据")
    @DeleteMapping("/delete")
    public CommonResult delete(String id) {

        return CommonResult.success(PowerSupplyService.removeById(id));
    }





}
