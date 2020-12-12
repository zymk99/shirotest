package com.example.shirotest;


import org.apache.shiro.subject.Subject;

import java.lang.reflect.Method;
import java.util.*;


public class TestClass {
    public static void main(String[] a)
    {
        int[] A={ 3,1,1,1,5,1,2,1};
        int aa=minSumOfLengths(A,3);
        int qwer=9;
    }

    static int[] equal;
    public static int minSumOfLengths(int[] arr, int target) {
        int a[][]=new int[arr.length+1][2];
        a[0][0]=999999;
        a[0][1]=999999;
        for(int i=1;i<arr.length+1;i++){
            int c=0,num=0,j=0;
            while(i-c>0 && num<target){
                num+=arr[i-(c++)-1];
            }
            a[i][0]=a[i-1][0];
            a[i][1]=a[i-1][1];
            if(num==target){
                if(a[i-c][0]>c){
                    a[i][0]=c;
                    a[i][1]=a[i-c][0];
                }else if(a[i-c][1]>c){
                    a[i][0]=a[i-c][0];
                    a[i][1]=c;
                }
            }
        }
        int x= (a[arr.length][0]!=999999 && a[arr.length][1]!=999999)? a[arr.length][0]+a[arr.length][1] :-1 ;
        return x;
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
