package com.bitcoding.template.howard.mapper.shiro;

import com.bitcoding.template.howard.entity.shiro.Role;

import java.util.List;

public interface RoleMapper {
    List<Role> selectRolesByUserId(Integer userId);

    List<Role> selectAllRoles();
}
