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
        int[] xx={50,47,68,33,35,84,25,49,91,75};
        minTime(xx,1);
    }

    public static int minTime(int[] time, int m) {
        int xx=dep(time,m);
        return xx;
    }

    public static int dep(int[] time, int m){
        if(time.length<=m){
            return 0;
        }
        if(time.length==m+1){
            Arrays.sort(time);
            return time[0];
        }
        List list=new ArrayList<int[]>();
        for(int i=0;i<time.length-1;i++){
            String tmp=String.valueOf(time[i]+time[i+1])+"-"+String.valueOf(i);
            list.add(tmp);
        }
        Collections.sort(list,(a,b)->{
            return Integer.parseInt(a.toString().split("-")[0])-Integer.parseInt(b.toString().split("-")[0]);
        });
        int min=Integer.parseInt(list.get(0).toString().split("-")[0]);
        int minIndex=Integer.parseInt(list.get(0).toString().split("-")[1]);
        int[] tmptime=Arrays.copyOfRange(time,0,minIndex);
        List tl=new ArrayList();
        if(tmptime.length>0){
            for(int i:tmptime){
                tl.add(i);
            }
        }
        tl.add(min);
        if(minIndex+2<=time.length){
            int[] tmp=Arrays.copyOfRange(time,minIndex+2,time.length);
            if(tmp.length>0){
                for(int i:tmp){
                    tl.add(i);
                }
            }
        }
        int[] ta=(int[]) Array.newInstance(int.class, tl.toArray().length);
        int c=0;
        for(Object x:tl.toArray()){
            ta[c++]=(int)x;
        }
        return dep(ta,m);
    }

}
