package com.example.shirotest.controller;

import com.example.shirotest.Utils.CurrUtils;
import com.example.shirotest.bean.FilePic;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/filepic")
public class FilePicController {
    @Autowired
    FilePic FPic;
    @RequestMapping(value="/getall",produces = "text/plain;charset=utf-8")
    public String getAllCover(@RequestBody Map<String,String> map){
        int pageSize=Integer.valueOf(map.get("pageSize").toString());
        int pageNum=Integer.valueOf(map.get("pageNum").toString());
        List list=FPic.getAllIndex((pageNum-1)*pageSize+1,pageNum*pageSize);
        return JSONArray.fromObject(list).toString();
    }
    @RequestMapping(value="/getpic",produces = "text/plain;charset=utf-8")
    public String test(){
       return null;
    }
    @RequestMapping(value = "/getPicByIndex",produces = "text/plain;charset=utf-8")
    public String getPicByIndex(@RequestBody Map m){
        int index=Integer.valueOf(m.get("index").toString());
        List list=FPic.getPicByIndex(index);
        return JSONArray.fromObject(list).toString();
    }
}
