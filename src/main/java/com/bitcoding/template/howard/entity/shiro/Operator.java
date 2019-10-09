package com.bitcoding.template.howard.entity.shiro;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Operator implements Serializable {
    private Integer id;
    private String name;
    private Integer menuId;
    private String perms;
    private Date createTime;
    private Date modifyTime;
    private List<Operator> children;
}
