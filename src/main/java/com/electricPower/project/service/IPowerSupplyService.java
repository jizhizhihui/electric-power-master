package com.electricPower.project.service;

import com.electricPower.project.entity.PowerSupply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.electricPower.project.entity.vo.PsPl;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author
 * @since 2020-10-04
 */
public interface IPowerSupplyService extends IService<PowerSupply> {

   PsPl getSupplyAndLine(String powerSupplyName);
}
