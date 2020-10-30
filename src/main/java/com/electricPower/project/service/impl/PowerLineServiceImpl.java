package com.electricPower.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.electricPower.project.entity.HouseHoldMeters;
import com.electricPower.project.entity.PowerLine;
import com.electricPower.project.entity.Terminal;
import com.electricPower.project.entity.vo.PlHs;
import com.electricPower.project.entity.vo.PlT;
import com.electricPower.project.mapper.HouseHoldMetersMapper;
import com.electricPower.project.mapper.PowerLineMapper;
import com.electricPower.project.mapper.TerminalMapper;
import com.electricPower.project.service.IPowerLineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.alibaba.druid.sql.visitor.SQLEvalVisitorUtils.eq;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author
 * @since 2020-10-04
 */
@Service
public class PowerLineServiceImpl extends ServiceImpl<PowerLineMapper, PowerLine> implements IPowerLineService {

    @Resource
    HouseHoldMetersMapper houseHoldMetersMapper;
    @Resource
    TerminalMapper terminalMapper;
    /**
     * 供电线路关联后端表箱
     * @param lineSn
     * @return
     */


    public PlHs getTsPl(String lineSn){
        PlHs plHs = new PlHs();
        plHs.setPowerLines(getById(lineSn));
        plHs.setHouseHoldMeters(houseHoldMetersMapper.selectMaps(new QueryWrapper<HouseHoldMeters>()
                .select("line_sn")
                .eq("line_sn",plHs.getPowerLines().getLineSn())));
        return plHs;
    }


    public PlT getALLType(String lineSn){
        PlT plT = new PlT();
        plT.setPowerLines((List<java.util.Map<String, Object>>) getBaseMapper().selectMaps(new QueryWrapper<PowerLine>()
                .select("power_supply_id","line","line_sn","sub_station_areas","capacity","principal","comm_address")
                .eq("line_sn",lineSn)));

        plT.setTerminal((List<java.util.Map<String, Object>>) terminalMapper.selectMaps(new QueryWrapper<Terminal>()
                .select("terminal_num","comm_protocol","line_sn","put_operation_date","add_date","location","rated_voltage",
                        "sim_number","type")
                .eq("line_sn",lineSn)));
        return  plT;
    }

    public List getALLType1(){
        QueryWrapper<PowerLine> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("line_sn");
        List<PlT> list = new ArrayList<>();
        getBaseMapper().selectList(queryWrapper).forEach(b->{
            list.add(getALLType(b.getLineSn()));
        });
        return list;

    }

    public PlT getType2(PowerLine powerLine,Terminal terminal){
        PlT plT = new PlT();
        plT.setPowerLines((List<java.util.Map<String, Object>>) getBaseMapper().selectMaps(new QueryWrapper<PowerLine>()
                .select("power_supply_id","line","line_sn","sub_station_areas","capacity","principal","comm_address")));
                if(powerLine.getLineSn()!=null)
                    eq("line_sn",powerLine.getLineSn());
                if (powerLine.getPowerSupplyId()!=null)
                    eq("power_supply_id",powerLine.getPowerSupplyId());
                if (powerLine.getSubStationAreas()!=null)
                    eq("sub_station_areas",powerLine.getSubStationAreas());
                if (powerLine.getLine()!=null)
                    eq("line",powerLine.getLine());
        plT.setTerminal((List<java.util.Map<String, Object>>) terminalMapper.selectMaps(new QueryWrapper<Terminal>()
                .select("terminal_num","comm_protocol","line_sn","put_operation_date","add_date","location","rated_voltage", "sim_number","type")));
                if (terminal.getLineSn()!=null)
                    eq("line_sn",terminal.getLineSn());
                if (terminal.getAddDate()!=null)
                    eq("add_date",terminal.getAddDate());
                if (terminal.getTerminalNum()!=null)
                    eq("terminal_num",terminal.getTerminalNum());
                if (terminal.getType()!=null)
                    eq("type",terminal.getType());

        return  plT;
    }













    /**
     * 报错空指针异常
     * @param current
     * @param size
     * @return
     */
    public PlT queryList(Integer current, Integer size) {
                 /**
                   * 这些代码应该写在service层
                   */
                 PlT plT = new PlT();
                 IPage<PlT> page = new Page<>(current, size);
                 //getBaseMapper().selectPage(page, null);
                 plT.setCurrent(current);
                 plT.setSize(size);
                 plT.setTotal(page.getTotal());
                 plT.setPlTList(new PowerLineServiceImpl().getALLType1());
                 return plT;
             }




//    public PlHm getSupplyLine(String lineSn){
//        PlHm plHm = new PlHm();
//        plHm.setPowerLines(getBaseMapper().selectOne(new QueryWrapper<PowerLine>()
//            .select("line_sn","power_supply_id")
//            .eq("line_sn",lineSn)));
//        plHm.setHouseHoldMeters(houseHoldMetersMapper.selectMaps(new QueryWrapper<HouseHoldMeters>()
//            .select("line_sn","household_sn")
//            .eq("line_sn",lineSn)));
//
//        return plHm;
//    }


}
