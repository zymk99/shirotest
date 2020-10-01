package com.example.shirotest;


import javafx.beans.binding.IntegerBinding;
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
        List<Integer> aaa=splitIntoFibonacci("214748364721474836422147483641");
        int xx=10;

    }
    public static List<Integer> splitIntoFibonacci(String S) {
        List<Integer> list=new ArrayList<>();
        List<Integer> objlist=new ArrayList<>();
        int l= S.length();
        if(l>=3){
            for(int i=1;i<l-1&&i<=9;i++){
                String s1=S.substring(0,i);
                if(s1.length()>1 && Integer.parseInt(s1)!=0 && s1.indexOf('0')==0){
                    continue;
                }
                for(int j=i+1;j<l&&j<=i+9;j++){
                    String s2=S.substring(i,j);
                    if(s2.length()>1 && Integer.parseInt(s2)!=0 &&s2.indexOf('0')==0){
                        continue;
                    }
                    list=new ArrayList<>();
                    list.add(Integer.parseInt(s1));
                    list.add(Integer.parseInt(s2));
                    objlist=db(Integer.parseInt(s1),Integer.parseInt(s2),S.substring(j,l),list,objlist);
                    if(objlist.size()>0){
                        return objlist;
                    }
                }
            }
        }
        return objlist;
    }

    public static List<Integer> db(int a,int b,String s,List<Integer> list,List<Integer> objlist){
        if(s.length()==0){
            objlist= (ArrayList)((ArrayList)list).clone();
            return objlist;
        }
        for(int i=1;i<s.length()+1 && i<=9;i++){
            if(i>1 && s.substring(0,i).indexOf('0')==0){
                continue;
            }
            if(a+b==Integer.parseInt(s.substring(0,i))){
                list.add(Integer.parseInt(s.substring(0,i)));
                objlist=db(b,Integer.parseInt(s.substring(0,i)),s.substring(i,s.length()),list,objlist);
                list.remove(list.size()-1);
            }
        }
        return objlist;
    }

}
