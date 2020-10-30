package com.electricPower.project.entity.vo;

import com.electricPower.project.entity.PowerLine;
import lombok.Data;

import java.util.List;

@Data
public class PlHs {

    private PowerLine powerLines;
    private List houseHoldMeters;


}
