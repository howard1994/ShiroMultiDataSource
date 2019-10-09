package com.bitcoding.template.howard.service.ServiceImpl;

import com.bitcoding.template.howard.config.dataSource.DataSource;
import com.bitcoding.template.howard.config.dataSource.DataSourceConf;
import com.bitcoding.template.howard.entity.shiro.Role;
import com.bitcoding.template.howard.entity.shiro.User;
import com.bitcoding.template.howard.mapper.shiro.RoleMapper;
import com.bitcoding.template.howard.mapper.shiro.UserMapper;
import com.bitcoding.template.howard.service.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Value("shiro.admin")
    private String admin;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Override
    @Transactional
    @DataSource(DataSourceConf.SHIRO_MYSQL)
    public User getUserByUserName(String userName) {
        User user = userMapper.selectUserByUserName(userName);
        List<Role> roles;
        if (admin.equals(userName)){
            //超级管理员
            roles=  roleMapper.selectAllRoles();
        }
        else {
            //普通用户
            roles =roleMapper.selectRolesByUserId(user.getId());
        }
        user.setRoles(roles);
        return user;
    }
}
