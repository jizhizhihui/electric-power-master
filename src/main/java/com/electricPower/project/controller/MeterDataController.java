package com.electricPower;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.electricPower.common.result.CommonResult;
import com.electricPower.project.entity.MeterData;
import com.electricPower.project.service.IMeterDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author com.chaFan
 * @since 2020-09-17
 */
@Api(tags = "电表数据")
@RestController
@RequestMapping("/meter-data")
@Log4j2
public class MeterDataController {

    @Autowired
    private IMeterDataService meterDataService;

    @ApiOperation("查询所有数据")
    @GetMapping("/list")
    public CommonResult getList() {

      // QueryWrapper queryWrapper = new QueryWrapper<>();
      // queryWrapper.likeLeft("meter_sn","%k");
       // queryWrapper.between("voltageA",220,222.2);
       return CommonResult.success(meterDataService.list());
       // return CommonResult.success(queryWrapper);
    }

    @ApiOperation("通过id查询电力数据")
    @GetMapping("/get")
    public CommonResult get() {
        return CommonResult.success(meterDataService.list());
    }


    @ApiOperation("添加所有数据")
    @PostMapping("/add")
    public CommonResult add( @RequestBody MeterData meterData) {

        return CommonResult.success(meterDataService.save(meterData));
    }

    @ApiOperation("更新数据数据")
    @PutMapping("/update")
    public CommonResult update( @RequestBody MeterData meterData) {
        return CommonResult.success(meterDataService.updateById(meterData));
    }

    @ApiOperation("通过id删除电力数据")
    @DeleteMapping("/delete")
    public CommonResult delete(String id) {
        return CommonResult.success(meterDataService.removeById(id));
    }
}
