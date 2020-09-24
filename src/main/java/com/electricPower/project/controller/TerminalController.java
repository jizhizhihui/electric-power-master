package com.electricPower.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.electricPower.common.result.CommonResult;
import com.electricPower.project.entity.MeterData;
import com.electricPower.project.entity.Terminal;
import com.electricPower.project.mapper.TerminalMapper;
import com.electricPower.project.service.ITerminalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author com.chaFan
 * @since 2020-09-17
 */
@Api(tags = "采集终端表")
@RestController
@RequestMapping("/terminal")
@Slf4j
public class TerminalController {

    @Autowired
    private ITerminalService iTerminalService;

    @ApiOperation("查询采集终端表数据")
    @GetMapping("/list")
    public CommonResult getList(){

        return CommonResult.success(iTerminalService.list());
    }

//    @ApiOperation("分页查询采集终端表数据")
//    @GetMapping("/list")


    @ApiOperation("添加采集终端表数据")
    @PostMapping("/add")
    public CommonResult add( @RequestBody Terminal terminal) {

        return CommonResult.success(iTerminalService.save(terminal));
    }

    @ApiOperation("更新采集终端表数据")
    @PutMapping("/update")
    public CommonResult update( @RequestBody Terminal terminal) {
        return CommonResult.success(iTerminalService.updateById(terminal));
    }

    @ApiOperation("通过id删除采集终端表数据")
    @DeleteMapping("/delete")
    public CommonResult delete(String id) {

        return CommonResult.success(iTerminalService.removeById(id));
    }
}
