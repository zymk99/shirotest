package com.example.shirotest.controller;

import com.example.shirotest.Utils.CurrUtils;
import net.sf.json.JSONArray;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/filepic")
public class FilePicController {
    String tomcat="http://localhost:8080/picture";
    String filePath="D:\\Tomcat\\apache-tomcat-7.0.106\\webapps\\ROOT\\picture";

    @RequestMapping(value="/getall",produces = "text/plain;charset=utf-8")
    public String getAllCover(){
        File folder =new File(filePath);
        List fs=new ArrayList();
        if(folder.exists()){
            File[] files =folder.listFiles();
            if(files!=null){
                for(File tmp:files)
                {
                    if(tmp.isDirectory()){
                        fs.add(tmp.getName());
                    }
                }
            }
        }
        return JSONArray.fromObject(fs).toString();
    }
}
