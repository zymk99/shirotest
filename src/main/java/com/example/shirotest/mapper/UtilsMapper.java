package com.example.shirotest.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Mapper
public interface UtilsMapper {
    @MapKey("u=id")
    LinkedList<Map>  getTableData(Map m);
}
