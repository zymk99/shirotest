package com.example.shirotest.Utils;

import javax.imageio.ImageIO;
import javax.net.ssl.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


public class downImp {
    public static void main(String[] args){
        downImp.downloadPicture("https://www.ccavbox.top/static/upload/book/2394/9479/205520.jpg","C:\\other\\555");
    }
    private static void downloadPicture(String urll,String path) {
        try {
            BufferedImage img = ImageIO.read(new URL("https://www.ccavbox.top/static/upload/book/2394/9479/205520.jpg").openStream());
            ImageIO.write(img, "jpg", new File("C:\\other\\image.jpg"));
        }catch (Exception e){
            System.out.println(e);
        }
    }

    static TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // TODO Auto-generated method stub
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // TODO Auto-generated method stub
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            // TODO Auto-generated method stub
            return null;
        }
    } };

    public class NullHostNameVerifier implements HostnameVerifier {
        /*
         * (non-Javadoc)
         *
         * @see javax.net.ssl.HostnameVerifier#verify(java.lang.String,
         * javax.net.ssl.SSLSession)
         */
        @Override
        public boolean verify(String arg0, SSLSession arg1) {
            // TODO Auto-generated method stub
            return true;
        }
    }
}
