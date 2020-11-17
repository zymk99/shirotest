package com.example.shirotest;


import org.apache.shiro.subject.Subject;

import java.lang.reflect.Method;
import java.util.*;


public class TestClass {
    public static void main(String[] a)
    {
        int  A[][]={{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        int[][] xx=reconstructQueue(A);
        int qwer=9;
    }

    static int[] equal;
    public static int[][] reconstructQueue(int[][] people) {
        ArrayList<int[]> list=new ArrayList<>();
        for(int i=0;i<people.length;i++){
            int[] t={people[i][0],people[i][1]};
            list.add(t);
        }
        list.sort((item1,item2)->{
            int[] t1=(int[])item1;
            int[] t2=(int[])item2;
            if(t1[0]==t2[0]){
                return t1[1]-t2[1];
            }
            return t2[0]-t1[0];
        });
        ArrayList tmp=new ArrayList();
        list.stream().forEach(item->{
            int[] t=(int[])item;
            tmp.add(t[1],t);
        });
        int[][] v=new int[people.length][2];
        return null;
    }
    public static Set db(int begin, String[] list,ArrayList AL){
        if(list==null){
            list=new String[AL.size()];
            int i=0;
            Iterator it=AL.iterator();
            while (it.hasNext()){
                list[i++]=(String)it.next();
            }
        }
        if(list.length==0){
            return null;
        }
        Set value=new HashSet();
        ArrayList<List> array=new ArrayList();
        ArrayList tmp=new ArrayList();
        int lastJ=-1;
        char[][] ch=new char[list.length][list[0].length()];
        for(int i=0;i<list.length;i++){
            ch[i]=list[i].toCharArray();
        }
        boolean flag=true;
        int i=begin;
        for(;i<list[0].length();i++){
            if(i==5){
                int aaa=10;
            }
            array=new ArrayList();
            tmp=new ArrayList();
            for(int j=1;j<list.length;j++){
                flag=true;
                if(ch[j][i]-ch[j-1][i]<0){
                    equal[i]=1;//有逆序；
                    value.add(i);
                    flag=false;
                    break;
                }else if(ch[j][i]-ch[j-1][i]==0){
                    if(lastJ+1!=j){
                        if(tmp.size()>0){
                            array.add((ArrayList)tmp.clone());
                            tmp=new ArrayList();
                        }
                        tmp.add(list[j-1]);
                        tmp.add(list[j]);
                    }else{
                        tmp.add(list[j]);
                    }
                    lastJ=j;
                }
            }
            if(tmp.size()>0){
                array.add((ArrayList)tmp.clone());
            }
            if(!flag){
                continue;
            }else if(array.size()>0){
                equal[i]=2; //有相等
                break;
            }
        }
        if(array.size()>0){
            Iterator iter=array.iterator();
            while(iter.hasNext()){
                Set s=db(i+1,null,(ArrayList) iter.next());
                if(s!=null && s.size()!=0){
                    value.addAll(s);
                }
            }
        }
        return value;
    }


}
