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
        ArrayList list=new ArrayList();
        //先替换最大值执行一遍   再替换最小值执行一遍
        for(int flag=0;flag<2;flag++){
            Map next=new HashMap();
            Map last=new HashMap();
            if(flag==0){    //替换最大化
                next.put(1,7);
                next.put(9,8);
                next.put(5,9);
                last.put(9,5);
            }
            if(flag==1){   //替换最小化
                next.put(1,7);
                next.put(9,8);
                next.put(5,6);
                last.put(9,3);
            }
            String s=String.valueOf(1995);
            for(int i=0;i<s.length();i++){
                for(int j=0;j<s.length();j++){
                    if(i==j){
                        continue;
                    }
                    if( next.get((int)s.toCharArray()[i]-48) !=null && last.get((int)s.toCharArray()[j]-48)!=null){
                        int x=(int)next.get((int)s.toCharArray()[i]-48);
                        int y=(int)last.get((int)s.toCharArray()[j]-48);
                        int a=i,b=j;
                        if(a>b){
                            int tmp=a;a=b;b=tmp;
                            tmp=x;x=y;y=tmp;
                        }
                        list.add( s.substring(0,a)+x+s.substring(a+1,b)+y+s.substring(b+1,s.length()) );
                    }
                }
            }
        }
        //自减自加的情况
        list.add("1695");
        list.add("1965");
        list.add("1095");
        list.add("1905");
        Collections.sort(list);
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
