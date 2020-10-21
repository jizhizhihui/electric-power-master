package com.electricPower.project.controller;


import com.electricPower.common.result.CommonResult;
import com.electricPower.project.entity.PowerLine;
import com.electricPower.project.entity.PowerSupply;
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
 * @author
 * @since 2020-10-04
 */
@Api(tags = "供电线路表")
@RestController
@RequestMapping("/power-line")
@Slf4j
public class PowerLineController {

    @Autowired
    private IPowerLineService powerLineService;

    @ApiOperation("查询供电线路所有数据")
    @GetMapping("/list")
    public CommonResult getList() {
        return CommonResult.success(powerLineService.list());
    }

    @ApiOperation("根据Id查询供电线路数据")
    @GetMapping("/getLineSn")
    public CommonResult get(String lineSn) {
        return CommonResult.success(powerLineService.getById(lineSn));
    }

    @ApiOperation("添加供电线路数据")
    @PostMapping("/add")
    public CommonResult add(@RequestBody PowerLine powerLine) {
        return CommonResult.success(powerLineService.save(powerLine));
    }

    @ApiOperation("更新供电线路数据")
    @PutMapping("/update")
    public CommonResult update(@RequestBody PowerLine powerLine) {

        return CommonResult.success(powerLineService.updateById(powerLine));
    }

    @ApiOperation("通过id删除供电线路数据")
    @DeleteMapping("/delete")
    public CommonResult delete(String id) {

        return CommonResult.success(powerLineService.removeById(id));
    }

}
