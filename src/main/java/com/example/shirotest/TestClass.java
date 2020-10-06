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
        int aaa=minimumOperations("yry");
        int xx=10;
        int[][] i={{1,2}};
    }
    public static int minimumOperations(String leaves) {
        int x=0;
        if(!leaves.startsWith("r")){
            x++;
        }
        if(!leaves.endsWith("r")){
            x++;
        }
        x+=db(leaves.substring(1,leaves.length()-1),x);
        return x;
    }

    public static int db(String s,int num){
        if(s.length()==0)return 0;
        if(s.length()==1 || s.length()==2){
            if(s.indexOf("y")>=0){
                return 0;
            }else
            {
                return 1;
            }
        }
        int all=0;
        for(char c : s.toCharArray()){
            if(c=='r')
                all++;
        }
        int x1=0,x2=0,x3=0;
        if(!s.startsWith("r")){
            x1++;
        }
        if(!s.endsWith("r")){
            x2++;
        }
        x3=x1+x2;
        x1+=db(s.substring(1,s.length()),num+x1);
        x2+=db(s.substring(0,s.length()-1),num+x2);
        x3+=db(s.substring(1,s.length()-1),num+x1+x2);
        int val=Math.min(all,Math.min(x1,Math.min(x2,x3)));
        return val;
    }


}
