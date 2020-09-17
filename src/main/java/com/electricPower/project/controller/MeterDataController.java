package com.electricPower.project.controller;


import com.electricPower.project.entity.MeterData;
import com.electricPower.project.mapper.MeterDataMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.beans.Transient;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author com.chaFan
 * @since 2020-09-17
 */
@Api("电表数据")
@Configuration
@RestController
@RequestMapping("//meter-data")
public class MeterDataController {

    @Autowired
    private MeterDataMapper meterDataMapper;
    @ApiOperation("查询所有数据")
    @PostMapping("/selectMeterData")
    public List<MeterData> selectMeterData(){

        return meterDataMapper.selectList(null);
    }

    @ApiOperation("添加所有数据")
    @PostMapping("/insertMeterData")
    public int insertMeterData(MeterData meterData){

        return meterDataMapper.insert(meterData);
    }


}
