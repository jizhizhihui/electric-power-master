package com.electricPower.project.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.electricPower.project.entity.HouseHoldMeters;
import com.electricPower.project.entity.PowerLine;
import com.electricPower.project.entity.PowerSupply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-10-04
 */
public interface PowerSupplyMapper extends BaseMapper<PowerSupply> {

    PowerLine selectMaps(QueryWrapper<PowerLine> eq);
}
