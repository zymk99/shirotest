package com.example.shirotest.bean;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FilePic {
    String tomcat="http://localhost:8080/picture/";
    String filePath="D:\\Tomcat\\apache-tomcat-7.0.106\\webapps\\ROOT\\picture";
    List fileList=new ArrayList();
    public FilePic(){
        File folder =new File(filePath);
        if(folder.exists()){
            File[] files =folder.listFiles();
            if(files!=null){
                for(File tmp:files)
                {
                    if(tmp.isDirectory()){
                        Map m=new HashMap();
                        m.put("height",String.valueOf((int)(Math.random()*180)+300));
                        m.put("src",tomcat+tmp.getName()+"/001.jpg");
                        m.put("title",tmp.getName());
                        fileList.add(m);
                    }
                }
            }
        }
    }
    //参数以1为起点
    public List getAllIndex(int begin,int end){
        if(begin>end || begin>fileList.size()){
            return null;
        }
        int max=fileList.size();
        List tmp=new ArrayList();
        for(int i=begin-1;i<end && i<max;i++){
            tmp.add(fileList.get(i));
        }
        return tmp;
    }
}
