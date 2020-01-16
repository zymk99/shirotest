package com.example.shirotest.Utils;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;

@Component
public class ImageUtils {
    //MultipartFile 转 File
    public File transToFile(MultipartFile cfile){
        String filename = cfile.getOriginalFilename();
        File file = new File(filename);
        try {
            cfile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    //图片转base64
    public String imageToBase64(BufferedImage img)
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, "JPEG", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        String v= encoder.encode(out.toByteArray());
        return v;
    }

    //BufferedImage 转换成 InputStream
    public InputStream bufferingToInputstream(BufferedImage img)
    {
        InputStream inputStream=null;
        try{
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
            ImageIO.write(img, "jpg", imOut);
            inputStream = new ByteArrayInputStream(bs.toByteArray());
        }catch (Exception e){
            return null;
        }
        return inputStream;
    }

    //图片缩放
    public BufferedImage imageScale(MultipartFile mFile, int x, int y, float Quality){
        BufferedImage build=null;
        try{
            File file=transToFile(mFile);
            build=Thumbnails.of(file).size(x,y).outputQuality(Quality).asBufferedImage();
        }catch (Exception e) {
            return null;
        }
        return build;
    }
    public BufferedImage imageScale(InputStream input, int x, int y, float Quality){
        BufferedImage build=null;
        try{
            build=Thumbnails.of(input).size(x,y).outputQuality(Quality).asBufferedImage();
        }catch (Exception e) {
            return null;
        }
        return build;
    }

}
