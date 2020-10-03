package com.electricPower.project.service;

import com.electricPower.project.entity.TruckMeters;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author com.chaFan
 * @since 2020-09-17
 */
public interface ITruckMetersService extends IService<TruckMeters> {

    Boolean updateById(String sn);
}
