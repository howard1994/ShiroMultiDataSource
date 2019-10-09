package com.bitcoding.template.howard.entity.shiro;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class User implements Serializable {
    private Integer id;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private Date lastLoginTime;
    private Date createTime;
    private Date modifyTime;
    private Integer status;
    private String salt;
    private Integer deptId;
    private String deptName;
    private List<Role> roles;
}
