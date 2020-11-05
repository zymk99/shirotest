package com.example.shirotest;


import org.apache.shiro.subject.Subject;

import java.lang.reflect.Method;
import java.util.*;


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
        int[][] c={ {1,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
                    {0,1,0,0,0,1,0,0,0,0,0,0,0,0,0},
                    {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
                    {1,0,0,0,1,0,0,0,0,0,0,0,1,0,0},
                    {0,1,0,0,0,1,0,0,0,0,0,0,1,0,0},
                    {0,0,0,0,0,0,1,0,0,0,0,1,0,0,0},
                    {0,0,0,0,0,0,0,1,0,0,0,1,1,0,0},
                    {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,1,1,0,0,0},
                    {0,0,0,0,0,0,1,1,0,0,1,1,0,0,1},
                    {0,0,0,0,1,1,0,1,0,0,0,0,1,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
                    {0,0,0,0,0,0,0,0,0,0,0,1,0,0,1}};
        int q=findCircleNum(c);
        int xx=9;
    }

    public static int findCircleNum(int[][] M)  {
        ArrayList list=new ArrayList();
        for(int i=0;i<M.length;i++){
            for(int j=i;j<M.length;j++){
                if(M[i][j]==1){
                    Set map=new HashSet();
                    Iterator it=list.iterator();
                    while(it.hasNext()){
                        Set m=(HashSet)it.next();
                        if(m.contains(i)){
                            map=m;
                            list.remove(m);
                            break;
                        }
                    }
                    map.add(j);
                    list.add(map);
                }
            }
        }
        int max=list.size();
        for(int i=0;i<list.size()&& list.size()>0;i++){
            Set m=(HashSet)list.remove(0);
            ArrayList tmpList=new ArrayList();
            list.stream().forEach(item->{
                Set t=(HashSet)item;
                Set tmp=new HashSet();
                tmp.addAll(m);
                tmp.retainAll(t);
                if(tmp.size()>0){
                    tmpList.add(t);
                }
            });
            list.removeAll(tmpList);
            tmpList.stream().forEach(item->{
                m.addAll((HashSet)item);
            });
            list.add(m);
        }
        return list.size();
    }
    public static int db(int[] nums, int target,int lift){
        return 1;
    }


}
