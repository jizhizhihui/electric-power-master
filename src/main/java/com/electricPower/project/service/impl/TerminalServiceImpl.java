package com.electricPower.project.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.electricPower.project.entity.PowerBureau;
import com.electricPower.project.entity.PowerLine;
import com.electricPower.project.entity.Terminal;
import com.electricPower.project.entity.vo.PbPs;
import com.electricPower.project.entity.vo.PlT;
import com.electricPower.project.mapper.TerminalMapper;
import com.electricPower.project.service.IPowerLineService;
import com.electricPower.project.service.ITerminalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Resource
    IPowerLineService powerLineService;

//    public PlT getALLType(String lineSn){
//        PlT plT = new PlT();
//        plT.setPowerLines( powerLineService.getBaseMapper().selectOne(new QueryWrapper<PowerLine>()
//                .select("power_supply_id","line","line_sn","sub_station_areas","capacity","principal","comm_address")
//                .eq("line_sn",lineSn)));
//
//        plT.setTerminal( getBaseMapper().selectOne(new QueryWrapper<Terminal>()
//                .select("terminal_num","comm_protocol","line_sn","put_operation_date","add_date","location","rated_voltage",
//                        "sim_number","type")
//                .eq("line_sn",lineSn)));
//
//        return  plT;
//    }
//
//    @Override
//    public Object getALLType1() {
//        return null;
//    }


}
