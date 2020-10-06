package com.electricPower.project.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.electricPower.project.entity.MeterData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-10-04
 */
public interface MeterDataMapper extends BaseMapper<MeterData> {
    Page<MeterData> page(Page<MeterData> page);

    Page<MeterData> selectPage(Page<MeterData> page);
}
