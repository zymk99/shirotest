package com.example.shirotest.dao;

import lombok.Data;

@Data
public class TUser {
    private String id;
    private String name;
    private String passwd;

    public TUser(String _name,String _pass)
    {
        this.name=_name;
        this.passwd=_pass;
    }
}
