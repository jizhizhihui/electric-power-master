package com.electricPower.project.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.electricPower.project.entity.PowerBureau;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.electricPower.project.service.impl.PowerBureauServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-10-04
 */
public interface PowerBureauMapper extends BaseMapper<PowerBureau> {

    String toString(QueryWrapper queryWrapper);

    List<Map<String, Object>> selectMaps(PowerBureauServiceImpl powerBureauServiceImpl);
}
