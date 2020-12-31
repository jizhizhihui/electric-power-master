package com.electricPower.project.service;

import com.electricPower.project.entity.MeterData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
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

    MeterData getOneBySign(String terminalNum, String sign);
}
