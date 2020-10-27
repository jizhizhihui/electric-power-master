package com.electricPower.project.service;

import com.electricPower.project.entity.PowerLine;
import com.baomidou.mybatisplus.extension.service.IService;
import com.electricPower.project.entity.Terminal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author
 * @since 2020-10-04
 */
public interface IPowerLineService extends IService<PowerLine> {

    Object getTsPl(String lineSn);

    Object getALLType1();

    Object queryList(Integer current, Integer size);

    Object getType2(PowerLine powerLine, Terminal terminal);
}
