package com.example.shirotest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Spider {
    public static void main(String[] a){
        downImages("",30,".jpg");
    }
    public static void downImages(String Url,int num,String type){
        String filePath="";
        // 截取图片的名称
        //String fileName = imageUrl.substring(Url.lastIndexOf("/"));
        String fileName="";
        //创建文件的目录结构
        File files = new File(filePath);
        if(!files.exists()){// 判断文件夹是否存在，如果不存在就创建一个文件夹
            files.mkdirs();
        }
        try {
            for(int i=1;i<=num;i++){
                System.out.println(""+i);
                String imageUrl=Url+i+type;
                fileName= i<10? "00"+i : (i<100?"0"+i : ""+i);
                fileName+=type;
                URL url = new URL(imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream is = connection.getInputStream();
                // 创建文件，并设置默认文件名
                File file = new File(filePath+fileName);
                FileOutputStream out = new FileOutputStream(file);
                int j = 0;
                while((j = is.read()) != -1){
                    out.write(j);
                }
                is.close();
                out.close();
                System.out.println("OK");
                Thread.sleep( (int)(Math.random()*300) );
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
