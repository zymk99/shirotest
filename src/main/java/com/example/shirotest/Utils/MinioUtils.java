package com.example.shirotest.Utils;

import io.minio.MinioClient;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.http.entity.ContentType;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class MinioUtils {
    private static String url = "http://203.195.251.136:9000";
    private static String accessKey = "minioadmin";
    private static String secretKey = "minioadmin";
    //上传
    public String upload(String bucketName,MultipartFile file,String type )  {
        try {
            MinioClient minioClient = new MinioClient(url, accessKey, secretKey);
            InputStream is= file.getInputStream(); //得到文件流
            String fileName = file.getOriginalFilename(); //文件名
            String contentType = type==null?file.getContentType():type;  //类型
            minioClient.putObject(bucketName,fileName,is,contentType); //把文件放置Minio桶(文件夹)
            return  "上传成功";
        }catch (Exception e){
            return "上传失败";
        }
    }

    //获取文件路径       例 ："icosource","QQ图片20190906103747.jpg"
    public  String getUrl(String bucketName,String name){
        try {
            MinioClient minioClient = new MinioClient(url, accessKey, secretKey);
            String url = minioClient.presignedGetObject(bucketName, name);
            return url;
        }catch (Exception e){
            return "获取失败";
        }
    }


    //下载
    public String download(HttpServletResponse response){
        try {
            MinioClient minioClient = new MinioClient(url, accessKey, secretKey);
            InputStream fileInputStream = minioClient.getObject("file", "test.jpg");
            response.setHeader("Content-Disposition", "attachment;filename=" + "test.jpg");
            response.setContentType("application/force-download");
            response.setCharacterEncoding("UTF-8");
            IOUtils.copy(fileInputStream,response.getOutputStream());
            return "下载完成";
        }catch (Exception e){
            return "下载失败";
        }
    }

    //URL转MultipartFile对象
    public MultipartFile getFileByUrl(String url, String fileName) throws Exception{
        FileItem item = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(30000);
            conn.setConnectTimeout(30000);
            //设置应用程序要从网络连接读取数据
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();

                FileItemFactory factory = new DiskFileItemFactory(16, null);
                String textFieldName = "uploadfile";
                item = factory.createItem(textFieldName, ContentType.APPLICATION_OCTET_STREAM.toString(), false, fileName);
                OutputStream os = item.getOutputStream();

                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.close();
                is.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("文件下载失败", e);
        }

        return new CommonsMultipartFile(item);
    }


}
