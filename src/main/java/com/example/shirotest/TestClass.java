package com.example.shirotest;


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
        int[] c={1,4,4,-1,2,2,-1,18,-1,6,8,-1,-1,-1,-1,1,3};
        int aaa=leastInterval(c);
        int xx=10;
    }
    public static int leastInterval(int[] s) {
        class Poi{
            Poi left=null;
            Poi right=null;
            int value;
            int index=0;
            Poi(int x){
                value=x;
            }
            Poi next;
        }
        Poi H=new Poi(s[0]);
        Poi node=H;
        Poi R=H;
        for(int i:s){
            if(i==1)continue;
            if(node==null)break;
            if(node.index==0 ){
                if(i>=0){
                    Poi tmp=new Poi(i);
                    R.next=tmp;
                    R=tmp;
                    node.left=tmp;
                }
                node.index++;
            }else{
                if(i>=0){
                    Poi tmp=new Poi(i);
                    R.next=tmp;
                    R=tmp;
                    node.right=tmp;
                }
                node=node.next;
            }
        }
        return 1;
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
