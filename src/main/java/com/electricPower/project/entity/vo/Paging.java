package com.electricPower.project.entity.vo;

import com.electricPower.project.entity.Terminal;
import lombok.Data;

import java.util.List;

@Data
public class Paging {

    private Integer current;
    private Integer size;
    private Long total;
    private List plTList;

    public void setPlTList(List<Paging> records) {
    }
}
