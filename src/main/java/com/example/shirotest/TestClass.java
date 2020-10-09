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
        List<String> list=new ArrayList<String>();
        list.add("ac");
        list.add("ab");
        list.add("aaa");
        String aaa=replaceWords(list,"it is abnormal that this solution is accepted");
        int xx=10;
    }
    public static String replaceWords(List<String> dictionary, String sentence) {
        if(dictionary.size()==0){
            return sentence;
        }else if(sentence.length()==0){
            return sentence;
        }
        Collections.sort(dictionary,(item1,item2)->{
            return item1.length()-item2.length();
        });
        String[] ss=sentence.split(" ");
        for(int i=0;i<ss.length;i++){
            if(ss[i].length()>0){
                Iterator it=dictionary.iterator();
                while (it.hasNext()){
                    String tmp=(String)it.next();
                    if(ss[i].length()<tmp.length())continue;
                    if(ss[i].startsWith(tmp)){
                        ss[i]=tmp;
                        break;
                    }
                }
            }
        }
        String v="";
        for(String t:ss){
            v += t+" ";
        }
        v=v.substring(0,v.length()-1);
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
