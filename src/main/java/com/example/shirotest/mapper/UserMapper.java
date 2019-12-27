package com.example.shirotest.mapper;

import com.example.shirotest.dao.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    TUser selectU(String id);
    TUser login(TUser user);

}
