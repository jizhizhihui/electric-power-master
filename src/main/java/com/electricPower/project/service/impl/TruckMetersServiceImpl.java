package com.electricPower.project.service.impl;

import com.electricPower.project.entity.TruckMeters;
import com.electricPower.project.mapper.TruckMetersMapper;
import com.electricPower.project.service.ITruckMetersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author com.chaFan
 * @since 2020-09-17
 */
@Service
public class TruckMetersServiceImpl extends ServiceImpl<TruckMetersMapper, TruckMeters> implements ITruckMetersService {

    @Override
    public Boolean updateById(String sn) {
        return null;
    }
}
