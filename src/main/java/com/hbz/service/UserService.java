package com.hbz.service;

import com.hbz.pojo.User;

public interface UserService{

    //查询用户
    User checkUser(String username, String password);
}
