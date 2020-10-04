package com.electricPower.project.service;

import com.electricPower.project.entity.MeterData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-10-04
 */
public interface IMeterDataService extends IService<MeterData> {
    /**
     * object list 都可以
     * @param meterSn
     * @return
     */
    List getVoltage(String meterSn);

    Object getCurrent(String lineSn);

    List getLineSn(String lineSn);

    Object getPaging(Integer current, Integer size);
}
