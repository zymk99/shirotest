package com.example.shirotest;


import org.apache.shiro.subject.Subject;

import java.lang.reflect.Method;
import java.util.*;


public class TestClass {
    public static void main(String[] a)
    {
        int aa=compareVersion("1.0.1","1.0.0001.0.0.0");
        int qwer=9;
    }

    static int[] equal;
    public static int compareVersion(String version1, String version2) {
        String[] s1=version1.split("\\.");
        String[] s2=version2.split("\\.");
        int size=s1.length>s2.length? s2.length:s1.length;
        int flag=s1.length>s2.length? 1:-1;
        String[] s=s1.length>s2.length? s1:s2;
        int l=0;
        for(;l<size;l++){
            int dlt=Integer.valueOf(s1[l])-Integer.valueOf(s2[l]);
            if(dlt!=0){
                return (dlt>0?1:-1);
            }
        }
        for(;l<s.length;l++){
            if(Integer.valueOf(s[l])>0){
                return flag;
            }
        }
        return 0;
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
