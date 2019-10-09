package com.bitcoding.template.howard.entity.shiro;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Dept implements Serializable {
    private Integer id;
    private String name ;
    private Integer parentId;
    private Date createTime;
    private Date modifyTime;
    private Integer sort;
    private List<Dept> children;
}
