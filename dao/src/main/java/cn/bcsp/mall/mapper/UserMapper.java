package cn.bcsp.mall.mapper;

import cn.bcsp.mall.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User selectOne(@Param("username") String username,@Param("password") String password);
}