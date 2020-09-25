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
        List<List<Integer>> aaa=combinationSum3(3,9);

        int xx=10;

    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> lists=new ArrayList<List<Integer>>();
        List<Integer> list=new ArrayList<>();
        for(int i=1;i<10;i++){
            combinationSum4(k,n,list,i,lists);
        }
        return lists;
    }

    public static List<List<Integer>> combinationSum4(int k, int n,List<Integer> list,int index,List<List<Integer>> lists) {
        if(k>=1){
            list.add(index);
            if(n==index && k==1){
                lists.add( (ArrayList)((ArrayList)list).clone() );
                list.remove(list.size()-1);
                return lists;
            }else if(n>index){
                for(int i=index+1;i<10;i++){
                    lists=combinationSum4(k-1,n-index,list,i,lists);
                    if(n<i)break;
                }
            }
            list.remove(list.size()-1);
        }
        return lists;
    }

}
