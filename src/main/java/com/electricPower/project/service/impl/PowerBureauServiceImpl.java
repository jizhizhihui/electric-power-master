package com.electricPower.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.electricPower.project.entity.PowerBureau;
import com.electricPower.project.entity.PowerSupply;
import com.electricPower.project.entity.vo.PbPs;
import com.electricPower.project.mapper.PowerBureauMapper;
import com.electricPower.project.mapper.PowerSupplyMapper;
import com.electricPower.project.service.IPowerBureauService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class PowerBureauServiceImpl extends ServiceImpl<PowerBureauMapper, PowerBureau> implements IPowerBureauService {

    @Resource
    PowerSupplyMapper powerSupplyMapper;

    public PbPs getBureauAndSupply(String powerBureauName){
        PbPs pbPs = new PbPs();
        pbPs.setPowerBureau(getBaseMapper().selectOne(new QueryWrapper<PowerBureau>()
                .select("power_bureau_id","p_b_name")
                .eq("p_b_name",powerBureauName)));      //eq是等于的意思

        pbPs.setPowerSupplyList(powerSupplyMapper.selectMaps(new QueryWrapper<PowerSupply>()
                .select("power_supply_id","p_s_name")
                .eq("power_bureau_id",pbPs.getPowerBureau().getPowerBureauId())));

        return pbPs;
    }

    @Override
    public PbPs getBureauAndSupply(Integer powerSupplyToId) {
        return null;
    }

    public List getBureau(){
        QueryWrapper<PowerBureau> queryWrapper = new QueryWrapper();
        queryWrapper.select("p_b_name");
        List<PbPs> list = new ArrayList<>();  //将PbPs放入集合中
        getBaseMapper().selectList(queryWrapper).forEach(b->{  //foreach 遍历lamda表达语法
            list.add(getBureauAndSupply(b.getPBName()));
        });
        return list;
    }



    public List getBureauAndSupply(){
        PowerBureau powerBureau = new PowerBureau();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("power_bureau_id","p_b_name");
        List list = getBaseMapper().selectList(queryWrapper);
        list.add(powerBureau.getPowerBureauId());
        list.add(powerBureau.getPBName());
        System.out.println(list);
        return list;
    }


}
