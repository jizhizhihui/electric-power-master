package com.electricPower.project.entity.vo;

import com.electricPower.project.entity.PowerSupply;
import lombok.Data;

import java.util.List;

@Data
public class PsPl {

    private PowerSupply powerSupply;
    private List PowerLineList;

}
