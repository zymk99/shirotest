package com.example.shirotest.bean;

import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FilePic {
    String tomcat="http://localhost:8080/picture/";
    String filePath="E:\\web\\pic\\";
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
                        //m.put("height",String.valueOf((int)(Math.random()*180)+280));
                        m.put("height",370);
                        m.put("src",filePath+tmp.getName());
                        m.put("title",tmp.getName());
                        fileList.add(m);
                    }
                }
            }
        }
    }
    //图片的base64
    public String getPic(String path,File file){
        try {
//            BufferedInputStream bf=new BufferedInputStream(new FileInputStream(filePath+"1\\001.jpg"));
//            byte[] bs=new byte[bf.available()];
//            bf.read(bs);
            InputStream in=null;
            if(file!=null){
                in = new FileInputStream(file);
            } else{
                in = new FileInputStream(path);
            }
            byte[] bs= new byte[in.available()];
            in.read(bs);
            in.close();
            BASE64Encoder encoder = new BASE64Encoder();
            String base64Str = encoder.encode(bs);
            return base64Str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //参数以1为起点
    public List getAllIndex(int begin,int end){
        if(begin>end || begin>fileList.size()){
            return null;
        }
        int max=fileList.size();
        List tmp=new ArrayList();
        for(int i=begin-1;i<end && i<max;i++){
            HashMap m=(HashMap)((HashMap) fileList.get(i)).clone();
            //获取封面
            String p=m.get("src").toString();
            File[] files =(new File(p)).listFiles();
            String base=getPic(p+"\\"+files[0].getName(),null);
            m.put("src",base);
            m.put("index",i);
            tmp.add(m);
        }
        return tmp;
    }
    //获取目标下的图片
    public List getPicByIndex(int index){
        ArrayList value=new ArrayList();
        Map m=(Map)fileList.get(index);
        String src=m.get("src").toString();
        File folder =new File(src);
        if(folder.exists()){
            File[] files =folder.listFiles();
            if(files!=null){
                for(File tmp:files)
                {
                    if(!tmp.isDirectory()){
                        String base=getPic(null,tmp);
                        value.add(base);
                    }
                }
            }
        }
        return value;
    }
}
