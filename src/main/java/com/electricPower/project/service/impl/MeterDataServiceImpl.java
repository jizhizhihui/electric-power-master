package com.electricPower.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.electricPower.project.entity.MeterData;
import com.electricPower.project.mapper.HouseHoldMetersMapper;
import com.electricPower.project.mapper.MeterDataMapper;
import com.electricPower.project.service.IMeterDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author com.chaFan
 * @since 2020-09-17
 */
@Service
public class MeterDataServiceImpl extends ServiceImpl<MeterDataMapper, MeterData> implements IMeterDataService {


}
