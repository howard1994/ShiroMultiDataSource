package com.bitcoding.template.howard.entity.shiro;

import lombok.Data;


import java.io.Serializable;
import java.util.List;

@Data
public class Role implements Serializable {
    private Integer id;
    private String name;
    private String memo;
    private List<Menu> menus;
    private List<Operator> operators;
}
