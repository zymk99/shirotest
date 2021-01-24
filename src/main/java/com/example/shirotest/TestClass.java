package com.example.shirotest;


import org.apache.shiro.subject.Subject;

import java.lang.reflect.Method;
import java.util.*;


public class TestClass {
    public static void main(String[] a)
    {
        int[] A={ 1,3};
        int[] B={ 2};
        double aa=findMedianSortedArrays(A,B);
        int qwer=9;
    }

    static int[] equal;
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int s1=nums1.length,s2=nums2.length,x1=0,x2=0;
        if(s1+s2<2){
            if(s1>0){
                return nums1[0];
            }else if(s2>0){
                return nums2[0];
            }else return 0;
        }
        int size=(s1+s2+1)/2,i=1,v1=0,v2=0;
        while(i<size){
                if( x2<s2 && x1<s1 &&nums1[x1]>nums2[x2]){
                    x2++;
                }else if(x1<s1){
                    x1++;
                }else {
                    x2++;
                }
                i++;
        }
        if( x2<s2 && x1<s1 &&nums1[x1]>nums2[x2]){
            v1=nums2[x2];
            x2++;
        }else if(x1<s1){
            v1=nums1[x1];
            x1++;
        }else {
            v1=nums2[x2];
            x2++;
        }
        if( x2<s2 && x1<s1 &&nums1[x1]>nums2[x2]){
            v2=nums2[x2];
            x2++;
        }else if(x1<s1){
            v2=nums1[x1];
            x1++;
        }else {
            v2=nums2[x2];
            x2++;
        }
        if( (s1+s2)%2==0){
            return (v1+v2)/2.0;
        }else {
            return v1;
        }
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
