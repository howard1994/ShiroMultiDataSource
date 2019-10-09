package com.bitcoding.template.howard.mapper.shiro;

import com.bitcoding.template.howard.entity.shiro.User;

public interface UserMapper {
    User selectUserByUserName(String userName);
}
