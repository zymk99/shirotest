package com.example.shirotest;


import javafx.beans.binding.IntegerBinding;
import net.sf.json.util.JSONUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;


public class TestClass {
    public static void main(String[] a)
    {
        ArrayList aa=new ArrayList();
        aa.add(2);
        aa.add(3);
        aa.add(5);
        aa.add(1);
        aa.add(-5);
        aa.sort((x,y)->{
            return Integer.parseInt(y.toString())-Integer.parseInt(x.toString());
        });
        int aaa=numTilePossibilities("A");
        int xx=10;
    }
    public static int numTilePossibilities(String tiles) {
        char[] cs=tiles.toCharArray();
        HashMap map=new HashMap();
        for(char c:cs){
            if(map.get(c)!=null){
                map.put(c, (int)map.get(c)+1);
            }else{
                map.put(c, 1);
            }
        }
        int v=db(map)-1;
        return v;
    }
    public static int db(HashMap map){
        int num=1;
        Iterator it=map.keySet().iterator();
        while(it.hasNext()){
            char c=(char)it.next();
            int x=(int)map.get(c);
            if((int)map.get(c) >0){
                map.put(c,x-1);
                num=num+db(map);
                map.put(c,x);
            }
        }
        return num;
    }


}
