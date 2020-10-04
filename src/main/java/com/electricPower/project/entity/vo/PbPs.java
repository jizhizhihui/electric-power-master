package com.electricPower.project.entity.vo;

import com.electricPower.project.entity.PowerBureau;
import com.electricPower.project.entity.PowerSupply;
import lombok.Data;

import java.util.List;

@Data
public class PbPs {

    private PowerBureau powerBureau;
    private List powerSupplyList;
}
