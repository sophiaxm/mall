package cn.bcsp.mall.service;

import cn.bcsp.mall.pojo.User;

public interface UserService {
    User login(String username,String password);
}