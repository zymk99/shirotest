package com.example.shirotest.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class RedisUtils {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public String getStringValue(String key){
        return redisTemplate.opsForValue().get(key);
    }

    boolean setStringValue(String key,String value){
        redisTemplate.opsForValue().set(key,value);
        return true;
    }

    public Map getHash(String key){
        return redisTemplate.opsForHash().entries(key);
    }
    //模糊查询
    public List<Map> getHashVague(String key){
        Set<String> set=redisTemplate.keys("*"+key+"*");
        // 将set转成ArrayList
        List<String> list=new ArrayList<>(set);
        List<Map> result=new ArrayList<>();
        if(list.size()!=0) {
            for (String str : list) {
                result.add(redisTemplate.opsForHash().entries(str));
            }
        }
        return result;
    }

    public List<String> getStringVague(String key){
        Set<String> set=redisTemplate.keys("*"+key+"*");
        // 将set转成ArrayList
        List<String> list=new ArrayList<>(set);
        List<String> result=new ArrayList<>();
        if(list.size()!=0) {
            for (String str : list) {
                result.add(redisTemplate.opsForValue().get(str));
            }
        }
        return result;
    }
}
