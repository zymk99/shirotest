package com.example.shirotest.controller;

import com.example.shirotest.Utils.CurrUtils;
import com.example.shirotest.bean.FilePic;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/filepic")
public class FilePicController {
    @Autowired
    FilePic FPic;
    @RequestMapping(value="/getall",produces = "text/plain;charset=utf-8")
    public String getAllCover(){
        List list=FPic.getAllIndex(1,20);
        return JSONArray.fromObject(list).toString();
    }
}
