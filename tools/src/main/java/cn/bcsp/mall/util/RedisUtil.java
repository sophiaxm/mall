package cn.bcsp.mall.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description: Redis的工具类,封装了我们自己的一些操作redis的方法
 * @author: wangyue
 * @date: 2021年08月13日 16:18
 */
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    public boolean  set(String key,Object value){
        redisTemplate.opsForValue().set(key,value);
        return true;
    }

    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }


}
