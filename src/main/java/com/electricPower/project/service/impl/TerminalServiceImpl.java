package com.electricPower.project.service.impl;

import com.electricPower.project.entity.Terminal;
import com.electricPower.project.mapper.TerminalMapper;
import com.electricPower.project.service.ITerminalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author
 * @since 2020-10-04
 */
@Service
public class TerminalServiceImpl extends ServiceImpl<TerminalMapper, Terminal> implements ITerminalService {

//    @Resource
//    IPowerLineService powerLineService;

    @Override
    public void setTerminalStatus(Terminal terminal) {
        Terminal newTerminal = new Terminal();
        newTerminal.setTerminalNum(terminal.getTerminalNum());
        newTerminal.setIsAlive(terminal.getIsAlive());
        updateById(newTerminal);
    }


    @Override
    public Boolean isId(String terminalId){
        return getById(terminalId) != null;
    }
}
