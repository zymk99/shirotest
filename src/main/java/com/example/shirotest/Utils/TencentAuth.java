package com.example.shirotest.Utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Component
public class TencentAuth {
    //图片转文字鉴权
    public Map<String,String> getAuthentication(String base64){
        UUID uuid = UUID.randomUUID();
        String r=uuid.toString().replaceAll("-","");
        String date=String.valueOf((new Date()).getTime());
        date=date.substring(0,date.length()-3);
        Map<String,String> mp=new HashMap();
        mp.put("app_id","2124377230");
        mp.put("scene","doc");
        mp.put("source","en");
        mp.put("target","zh");
        mp.put("session_id","13027168502");
        mp.put("time_stamp",date);
        mp.put("nonce_str",r);
        mp.put("image",base64);   //图片base64

        try {
            Set tmps=mp.keySet();
            Object[] tmpl=tmps.toArray();
            Arrays.sort(tmpl);
            String url="";
            for(Object s : tmpl)
            {
                String v=mp.get(s.toString());

                url=url + s.toString()+"="+ URLEncoder.encode(v, "UTF-8") +"&";

            }
            url=url+"app_key=p6XfC6CpDn3HvRNQ";
            mp.put("sign",DigestUtils.md5Hex(url).toUpperCase());
            //System.out.println(url+"\n");
            //System.out.println(date+"\n"+r+"\n"+ DigestUtils.md5Hex(url).toUpperCase());
            return mp;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
