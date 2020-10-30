package com.electricPower.project.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.electricPower.common.result.CommonResult;
import com.electricPower.project.entity.MeterData;
import com.electricPower.project.entity.PowerLine;
import com.electricPower.project.entity.PowerSupply;
import com.electricPower.project.entity.Terminal;
import com.electricPower.project.entity.vo.PlT;
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

    @ApiOperation("查询所有的巡检机器人")
    @GetMapping("/getALLType1")
    public CommonResult getALLType1(){
        return CommonResult.success(powerLineService.getALLType1());
    }

    @ApiOperation("分页查询所有的巡检机器人")
    @GetMapping("/getALLType1page")
    public CommonResult queryList(Integer current, Integer size){
        return CommonResult.success((powerLineService.queryList(current, size)));
    }

    @ApiOperation("条件查询所有的巡检机器人")
    @GetMapping("/getType2")
    public CommonResult getType2(PowerLine powerLine, Terminal terminal){
        return CommonResult.success(powerLineService.getType2(powerLine,terminal));
    }



}





//@ApiOperation("查询供电线路所有数据")
//    @GetMapping("/list")
//    public CommonResult getList() {
//        return CommonResult.success(powerLineService.list());
//    }
//
//    @ApiOperation("根据Id查询供电线路数据")
//    @GetMapping("/getLineSn")
//    public CommonResult get(String lineSn) {
//        return CommonResult.success(powerLineService.getById(lineSn));
//    }
//
//    @ApiOperation("添加供电线路数据")
//    @PostMapping("/add")
//    public CommonResult add(@RequestBody PowerLine powerLine) {
//        return CommonResult.success(powerLineService.save(powerLine));
//    }
//
//    @ApiOperation("更新供电线路数据")
//    @PutMapping("/update")
//    public CommonResult update(@RequestBody PowerLine powerLine) {
//
//        return CommonResult.success(powerLineService.updateById(powerLine));
//    }
//
//    @ApiOperation("通过id删除供电线路数据")
//    @DeleteMapping("/delete")
//    public CommonResult delete(String id) {
//
//        return CommonResult.success(powerLineService.removeById(id));
//    }