package cn.bcsp.mall.service;

import cn.bcsp.mall.mapper.UserMapper;
import cn.bcsp.mall.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: 用户业务层
 * @author: 王跃
 * @date: 2021年08月27日 14:44
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        return userMapper.selectOne(username,password);
    }
}
