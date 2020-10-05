package com.electricPower.project.service;

import com.electricPower.project.entity.PowerBureau;
import com.baomidou.mybatisplus.extension.service.IService;
import com.electricPower.project.entity.vo.PbPs;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-10-04
 */
public interface IPowerBureauService extends IService<PowerBureau> {

    PbPs getBureauAndSupply(Integer powerSupplyToId);

    Object getBureau();
}
