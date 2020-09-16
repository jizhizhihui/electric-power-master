package com.electricPower.project.controller;


import com.electricPower.project.entity.LineMeters;
import com.electricPower.project.mapper.LineMetersMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author com.chaFan
 * @since 2020-09-16
 */
@RestController
@RequestMapping("//line-meters")
public class LineMetersController {

    @Resource
    private LineMetersMapper lineMetersMapper;

    @ApiOperation("查询线路")
    @GetMapping("/select")
    public List<LineMeters> selectLM(){

        return lineMetersMapper.selectList(null);
    }



}
