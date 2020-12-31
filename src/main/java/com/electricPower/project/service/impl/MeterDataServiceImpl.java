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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 * @since 2020-10-04
 */
@Service
public class MeterDataServiceImpl extends ServiceImpl<MeterDataMapper, MeterData> implements IMeterDataService {

    @Override
    public MeterData getOneBySign(String terminalNum, String sign) {
        return getBaseMapper().selectOne(new QueryWrapper<MeterData>()
                .eq("terminal_num", terminalNum)
                .eq("frame_type", sign)
                .gt("acquisition_time", LocalDate.now())
                .last("limit 1")
                .orderByDesc("acquisition_time"));
    }
}