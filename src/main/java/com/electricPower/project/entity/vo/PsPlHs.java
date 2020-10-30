package com.electricPower.project.entity.vo;

import com.electricPower.project.entity.PowerLine;
import com.electricPower.project.entity.PowerSupply;
import lombok.Data;

import java.util.List;

@Data
public class PsPlHs {
    private PowerSupply powerSupply;
    private List plHss;

}
