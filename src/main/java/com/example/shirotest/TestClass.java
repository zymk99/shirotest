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
        List<String> list=new ArrayList<>();
        list.add("cats");
        list.add("dog");
        list.add("sand");
        list.add("and");
        boolean aaa=wordBreak("catsandog",list);
        int xx=10;
    }
    public static boolean wordBreak(String s, List<String> wordDict) {
        if(wordDict.size()==0){
            return false;
        }
        boolean val=db(s,wordDict);
        return val;
    }
    public static boolean db(String s,List<String> list){
        HashMap map=new HashMap<String,Integer>();
        list.stream().forEach(item->{
            map.put(item,item.length());
        });
        Boolean[] bs=new Boolean[s.length()+1];
        bs[0]=true;
        int i=1;
        for(i=1;i<bs.length;i++){
            boolean bol=false;
            Iterator it=map.keySet().iterator();
            while(it.hasNext()){
                String v=it.next().toString();
                if(i-v.length()>=0 && s.substring(i-v.length(),i).equals(v) && bs[i-v.length()]==true){
                    bol=true;
                    break;
                }
            }
            bs[i]=bol;
        }
        return bs[bs.length-1];
    }


}
