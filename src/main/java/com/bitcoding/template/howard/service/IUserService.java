package com.bitcoding.template.howard.service;

import com.bitcoding.template.howard.entity.shiro.User;

public interface IUserService {
    User getUserByUserName(String userName);
}
