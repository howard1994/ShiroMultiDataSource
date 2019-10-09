package com.bitcoding.template.howard.entity.shiro;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Menu implements Serializable {
    private Integer id ;
    private String name;
    private Integer parentId;
    private String url;
    private String perms;
    private String icon;
    private Date createTime;
    private Date modifyTime;
    private Integer sort;
    private List<Menu> children;
}
