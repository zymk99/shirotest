package com.example.shirotest.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Mapper
@Component
public interface RolePermMapper {
    @MapKey("id")
    LinkedList<Map> getRolePerByUserid(String id);
}
