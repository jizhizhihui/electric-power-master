package com.electricPower.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.electricPower.project.entity.PowerBureau;
import com.electricPower.project.entity.PowerLine;
import com.electricPower.project.entity.PowerSupply;
import com.electricPower.project.entity.vo.PbPs;
import com.electricPower.project.entity.vo.PsPl;
import com.electricPower.project.entity.vo.PsPlHs;
import com.electricPower.project.mapper.PowerLineMapper;
import com.electricPower.project.mapper.PowerSupplyMapper;
import com.electricPower.project.service.IPowerLineService;
import com.electricPower.project.service.IPowerSupplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PowerSupplyServiceImpl extends ServiceImpl<PowerSupplyMapper, PowerSupply> implements IPowerSupplyService {

    @Resource
    PowerLineMapper powerLineMapper;
    @Autowired
    IPowerLineService powerLineService;


    public PsPl getSupplyAndLine(String powerSupplyName){
        PsPl psPl = new PsPl();
        psPl.setPowerSupply(getBaseMapper().selectOne(new QueryWrapper<PowerSupply>()
                .select("power_supply_id","p_s_name")
                .eq("p_s_name",powerSupplyName)));//传入的参数和供电所名字相同
        psPl.setPowerLineList(powerLineMapper.selectMaps(new QueryWrapper<PowerLine>()
                .select("power_supply_id","line_sn")
                .eq("power_supply_id",psPl.getPowerSupply().getPowerSupplyId())));
        return psPl;

    }

    /**
     * 供电所关联供电线路
     * 再调用
     * @param
     * @return
     */

    public PsPlHs get(int powerSupplyId){
        PsPlHs psPlHs = new PsPlHs();
        psPlHs.setPowerSupply(getById(powerSupplyId));
        List l = new ArrayList();
        powerLineService.getBaseMapper().selectList(new QueryWrapper<PowerLine>()
                .eq("power_supply_id",psPlHs.getPowerSupply().getPowerSupplyId())).forEach(s->{
                l.add(powerLineService.getTsPl(s.getLineSn()));
        });
        psPlHs.setPlHss(l);
        return psPlHs;
    }

//    public PsPl getSupplyLine(String powerSupplyId){
//        PsPl psPl = new PsPl();
//        psPl.setPowerSupply(getBaseMapper().selectOne(new QueryWrapper<PowerSupply>()
//                .select("power_supply_id","p_s_name")
//                .eq("power_supply_id",powerSupplyId)));//传入的参数和供电所名字相同
//        psPl.setPowerLineList(powerLineMapper.selectMaps(new QueryWrapper<PowerLine>()
//                .select("power_supply_id","line_sn")
//                .eq("power_supply_id",psPl.getPowerSupply().getPowerSupplyId())));
//        return psPl;
//
//    }


}
