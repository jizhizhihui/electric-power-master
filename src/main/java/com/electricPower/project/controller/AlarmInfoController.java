package com.electricPower.project.controller;


import com.electricPower.common.aop.OperLog;
import com.electricPower.common.aop.OperationType;
import com.electricPower.common.result.CommonResult;
import com.electricPower.project.entity.AlarmInfo;
import com.electricPower.project.entity.MeterData;
import com.electricPower.project.service.IAlarmInfoService;
import com.electricPower.project.service.IMeterDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/alarm-info")
@Log4j2
@Api(tags = "报警信息")
public class AlarmInfoController {
    @Autowired
    private IAlarmInfoService alarmInfoService;

    @ApiOperation("查询所有数据")
    @GetMapping("/list")
    @OperLog(operationModel = "报警信息",operationType = OperationType.GET,operationDesc = "获取报警信息集合")
    public CommonResult getList() {
        return CommonResult.success(alarmInfoService.list());
    }

    @ApiOperation("通过id查询报警信息")
    @GetMapping("/get")
    @OperLog(operationModel = "报警信息",operationType = OperationType.GET,operationDesc = "通过主键获取报警信息")
    public CommonResult get(int id) {
        return CommonResult.success(alarmInfoService.getById(id));
    }

    @ApiOperation("添加报警信息")
    @PostMapping("/add")
    @OperLog(operationModel = "报警信息",operationType = OperationType.ADD,operationDesc = "添加报警信息")
    public CommonResult add( @RequestBody AlarmInfo alarmInfo) {
        alarmInfoService.save(alarmInfo);
        log.info(alarmInfo.getAlarmInfoId());
        return CommonResult.success("ok");
    }

    @ApiOperation("更新报警信息")
    @PutMapping("/update")
    @OperLog(operationModel = "报警信息",operationType = OperationType.PUT,operationDesc = "更新报警信息")
    public CommonResult update( @RequestBody AlarmInfo alarmInfo) {
        return CommonResult.success(alarmInfoService.updateById(alarmInfo));
    }

    @ApiOperation("通过id删除报警信息")
    @DeleteMapping("/delete")
    @OperLog(operationModel = "报警信息",operationType = OperationType.DEL,operationDesc = "通过id删除报警信息")
    public CommonResult delete(String id) {
        return CommonResult.success(alarmInfoService.removeById(id));
    }


    @ApiOperation("通过id查询报警信息")
    @GetMapping("/getAlarmInfoByHouse")
    @OperLog(operationModel = "报警信息",operationType = OperationType.GET,operationDesc = "通过户表终端地址获取户表报警信息")
    public CommonResult getAlarmInfoByHouse(String address) {
        return CommonResult.success(alarmInfoService);
    }

}
