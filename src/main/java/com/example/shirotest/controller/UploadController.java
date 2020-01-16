package com.example.shirotest.controller;

import com.example.shirotest.Utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/updata")
public class UploadController {
    @Autowired
    ImageUtils imageutils;
    @RequestMapping("/headPortrait")
    public String upHeadPortrait(@RequestParam("file")MultipartFile file, HttpServletRequest request)
    {
        try {
            BufferedImage img=imageutils.imageScale(file.getInputStream(),100,100,1.0f);
            //把临时文件的信息存起来
            Map map=new HashMap();
            InputStream is= imageutils.bufferingToInputstream(img); //得到文件流
            String fileName=file.getOriginalFilename();
            if(fileName.indexOf("\\.")>0)
            {
                fileName= UUID.randomUUID().toString().replaceAll("-","")+fileName.split("\\.")[1];
            }
            if(fileName.indexOf(".")>0)
            {
                fileName= UUID.randomUUID().toString().replaceAll("-","")+fileName.split("\\.")[1];
            }
            map.put("fileName",file.getOriginalFilename()  ); //文件名
            map.put("contentType" ,file.getContentType() );  //类型
            map.put("is",is);
            HttpSession session=request.getSession();
            session.setAttribute("userTmpHeadPortrait",map);
        }catch (Exception e){

        }
        return "123";
    }
}
