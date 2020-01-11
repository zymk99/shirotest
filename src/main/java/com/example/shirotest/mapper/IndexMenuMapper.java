package com.example.shirotest.mapper;

import com.example.shirotest.dao.IndexMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface IndexMenuMapper {
    public IndexMenu[] getMenuByAdmin();
    boolean insertMenu(IndexMenu im);
    boolean deleItemById(String id);
    boolean updateItemById(IndexMenu im);
}
