package com.electricPower.project.entity.vo;

import com.electricPower.project.entity.PowerLine;
import com.electricPower.project.entity.Terminal;
import lombok.Data;

import java.util.List;

@Data
public class PlT{
    private Integer current;
    private Integer size;
    private Long total;
    private List plTList;
    private List powerLines;
    private List terminal;


}
