package com.electricPower.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.electricPower.project.entity.HouseHoldMeters;
import com.electricPower.project.entity.PowerLine;
import com.electricPower.project.mapper.HouseHoldMetersMapper;
import com.electricPower.project.service.IHouseHoldMetersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class HouseHoldMetersServiceImpl extends ServiceImpl<HouseHoldMetersMapper, HouseHoldMeters> implements IHouseHoldMetersService {

}
