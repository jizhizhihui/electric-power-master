package com.electricPower.project.service;

import com.electricPower.project.entity.Terminal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.electricPower.project.entity.vo.PlT;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author
 * @since 2020-10-04
 */
public interface ITerminalService extends IService<Terminal> {

    Boolean isId(String terminalId);
}
