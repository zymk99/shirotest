package com.example.shirotest.dao;

import lombok.Data;

import java.io.Serializable;

@Data
public class TUser implements Serializable {
    private String id;
    private String name;
    private String passwd;
    private String icon;
    private String emails;
    private int index;

    public TUser(String _name,String _pass)
    {
        this.name=_name;
        this.passwd=_pass;
    }
    public TUser()
    {
    }
    @Override
    public String toString() {
        return "UserInfo [id=" + id + ", name=" + name + ",passwd="+passwd+",icon="+icon+",emails="+emails+",index="+index+"]";
    }

}
