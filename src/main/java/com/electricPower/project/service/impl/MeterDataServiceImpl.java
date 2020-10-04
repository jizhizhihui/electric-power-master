package com.electricPower.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.electricPower.common.result.CommonResult;
import com.electricPower.project.entity.MeterData;
import com.electricPower.project.mapper.MeterDataMapper;
import com.electricPower.project.service.IMeterDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2020-10-04
 */
@Service
public class MeterDataServiceImpl extends ServiceImpl<MeterDataMapper, MeterData> implements IMeterDataService {


        public List getVoltage(String lineSn ) {
            QueryWrapper<MeterData> queryWrapper = new QueryWrapper<>();    // select用于筛选需要的字段
            queryWrapper.in("line_sn", Collections.singletonList(lineSn)).select("line_sn","voltage_a","voltage_b","voltage_c","save_time");
            return getBaseMapper().selectList(queryWrapper);
        }

        public List getCurrent(String lineSn){
            QueryWrapper<MeterData> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("line_sn",lineSn).select("line_sn","current_a","current_b","current_c","save_time");
            return getBaseMapper().selectList(queryWrapper);
        }

        public List getLineSn(String line_sn){
            QueryWrapper<MeterData> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("line_sn",line_sn);    //List<MeterData>meterDataList = meterDataService.list(queryWrapper);
            return getBaseMapper().selectList(queryWrapper);
            }

        @Override
        public Object getPaging(Integer current, Integer size) {
            return null;
        }

        //        public Page<MeterData> getPaging(Integer current, Integer size){
        //                Page<MeterData> page = new Page<>(current,size);  //current 是当前页数，size 是每页有多少条数据
        //                return getBaseMapper().page(page);
        //            }


}
