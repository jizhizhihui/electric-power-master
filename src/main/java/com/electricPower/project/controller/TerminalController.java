package com.electricPower.project.controller;


import com.electricPower.common.result.CommonResult;
import com.electricPower.project.entity.PowerBureau;
import com.electricPower.project.entity.Terminal;
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
@Api(tags = "采集终端表")
@RestController
@RequestMapping("//terminal")
@Slf4j
public class TerminalController {

    @Autowired
    private ITerminalService terminalService;

    @ApiOperation("查询所有的巡检机器人")
    @GetMapping("/getALLType")
    public CommonResult getList() {
        return CommonResult.success(terminalService.list());
    }

    @ApiOperation("根据Id查询巡检机器人")
    @GetMapping("/getPowerBureauId")
    public CommonResult get(String terminalNum) {
        return CommonResult.success(terminalService.getById(terminalNum));
    }

    @ApiOperation("添加巡检机器人")
    @PostMapping("/add")
    public CommonResult add(@RequestBody Terminal terminal) {
        return CommonResult.success(terminalService.save(terminal));
    }

    @ApiOperation("更新巡检机器人")
    @PutMapping("/update")
    public CommonResult update(  @RequestBody Terminal terminal) {

        return CommonResult.success(terminalService.updateById(terminal));
    }

    @ApiOperation("通过id删除巡检机器人")
    @DeleteMapping("/delete")
    public CommonResult delete(String terminalNum) {

        return CommonResult.success(terminalService.removeById(terminalNum));
    }











}
