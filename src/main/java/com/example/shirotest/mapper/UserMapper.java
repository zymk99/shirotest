package com.example.shirotest.mapper;

import com.example.shirotest.dao.TUser;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface UserMapper {
    TUser selectU(String id);
    TUser login(TUser user);
    boolean updateItemById(Map map);
    boolean deleteUser(String id);
    boolean addUser(Map map);
    @MapKey("id")
    List<Map> selectUUU();
    @MapKey("id")
    List<Map> selectAllUser(Map map);
    @MapKey("id")
    List<Map> selectLeapCount();
    @MapKey("id")
    List<Map> selectLeap();
}
