package com.example.shirotest;


import javafx.beans.binding.IntegerBinding;
import net.sf.json.util.JSONUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
        char[] c={'A','A','A','B','B','B','c','c','c','D','D','E'};
        int aaa=leastInterval(c,2);
        int xx=10;
    }
    public static int leastInterval(char[] tasks, int n) {
        HashMap map=new HashMap();
        for(char c :tasks){
            map.put(c, map.get(c)==null?1 : Integer.parseInt(map.get(c).toString())+1 );
        }
        ArrayList list=new ArrayList();
        Iterator it=map.keySet().iterator();
        while(it.hasNext()){
            char c=(char)it.next();
            list.add(String.valueOf(c)+"-"+map.get(c).toString());
        }
        Collections.sort(list,(a1,a2)->{
            return Integer.valueOf(a2.toString().split("-")[1])-Integer.valueOf(a1.toString().split("-")[1]);
        });
        char[] cs=new char[1000002];
        Iterator iter=list.iterator();
        while(iter.hasNext()){
            String tmps=iter.next().toString();
            int x=Integer.valueOf(tmps.split("-")[1]);
            int index=0;
            while(cs[index++]!='\0');
            index--;
            for(;x>0;x--,index=index+n+1){
                cs[index]=tmps.split("-")[0].toCharArray()[0];
            }
        }
        int v=1000000;
        for(;v>=0;v--){
            if(cs[v]!='\0')break;
        }
        return v+1;
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
