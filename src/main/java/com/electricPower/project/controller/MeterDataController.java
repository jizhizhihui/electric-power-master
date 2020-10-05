package com.electricPower.project.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.electricPower.common.result.CommonResult;
import com.electricPower.project.entity.MeterData;
import com.electricPower.project.service.IMeterDataService;
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
@Api(tags = "电力数据表")
@RestController
@RequestMapping("/meter-data")
@Slf4j
public class MeterDataController {

            @Autowired
            private IMeterDataService meterDataService;

            @ApiOperation("查询所有数据")
            @GetMapping("/list")
            public CommonResult getList() {
                return CommonResult.success(meterDataService.list());
            }

            @ApiOperation("通过id查询电力数据，测试使用的")
            @GetMapping("/get")
            public CommonResult get() {
                return CommonResult.success(meterDataService.list());
            }


            @ApiOperation("添加所有数据，测试使用的")
            @PostMapping("/add")
            public CommonResult add( @RequestBody MeterData meterData) {
                return CommonResult.success(meterDataService.save(meterData));
            }

            @ApiOperation("更新数据数据，测试使用的")
            @PutMapping("/update")
            public CommonResult update( @RequestBody MeterData meterData) {

                return CommonResult.success(meterDataService.updateById(meterData));
            }

            @ApiOperation("通过id删除电力数据，测试使用的")
            @DeleteMapping("/delete")
            public CommonResult delete(String id) {

                return CommonResult.success(meterDataService.removeById(id));
            }

            @ApiOperation("通过电表编号查询a,b,c三相电压")
            @GetMapping("/getVoltage")
            public CommonResult getVoltage(String lineSn ) {

                return CommonResult.success(meterDataService.getVoltage(lineSn));
            }

            @ApiOperation("通过电表编号查询a,b,c三相电流")
            @GetMapping("/getCurrent")
            public CommonResult getCurrent( String lineSn){
                return CommonResult.success(meterDataService.getCurrent(lineSn));
            }

            @ApiOperation("通过电表编号地址模糊查询")
            @GetMapping("/getLineSn")
            public CommonResult getLineSn(String lineSn){
                return CommonResult.success(meterDataService.getLineSn(lineSn));
            }

//            @ApiOperation("分页查询")
//            @GetMapping("/getPaging")
//            public CommonResult<Object> getPaging(Integer current, Integer size){
//                return CommonResult.success(meterDataService.getPaging(current,size));
//            }
            @ApiOperation("分页查询")
            @GetMapping("/getPaging")
            public Page<MeterData> getPaging(Integer current, Integer size){
                Page<MeterData> page = new Page<>(current,size); //current 是当前页数，size 是每页有多少条数据
                return meterDataService.page(page);
            }



}
