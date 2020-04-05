package com.example.shirotest.controller;

import com.example.shirotest.Utils.CurrUtils;
import com.example.shirotest.mapper.UtilsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@RestController
@RequestMapping("/table")
public class TableController {
    @Autowired
    UtilsMapper utils;
    //查询
    @PostMapping("/selectTab")
    public LinkedList<Map> selectTab(@RequestBody Map<String,String> par) //tablename pagenum pagesize order
    {
        if(par.get("tablename")==null)
        {
            return null;
        }
        String tablename=par.get("tablename").toString();
        int pagenum=par.get("pagenum")==null?1:Integer.parseInt(par.get("pagenum").toString());
        int pagesize=par.get("pagesize")==null?10:Integer.parseInt(par.get("pagesize").toString());
        String order = par.get("order")==null?" order by id ":par.get("order").toString() ;
        pagenum=(pagenum-1)*pagesize;
        Map pm=new HashMap();
        pm.put("tablename",tablename);
        pm.put("index",String.valueOf(pagenum));
        pm.put("num",String.valueOf(pagesize));
        pm.put("order",order);
        return utils.getTableData(pm);

    }

}
