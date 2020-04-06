package com.example.shirotest.mapper;

import com.example.shirotest.dao.IndexMenu;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
@Mapper
public interface IndexMenuMapper {
    public IndexMenu[] getMenuByAdmin();
    boolean insertMenu(IndexMenu im);
    boolean deleItemById(String id);
    boolean updateItemById(IndexMenu im);
    @MapKey("id")
    LinkedList<Map> getRoleByMenuid(String id);
    boolean setMenuRoleRela(Map map);
    boolean deleMenuRoleRela(String id);
    boolean deleMenuRoleRelaByMenuid(String menuid);
}
