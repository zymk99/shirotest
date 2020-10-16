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
        int aaa=scoreOfParentheses("(())");
        int xx=10;
    }
    public static int scoreOfParentheses(String S) {
        int xx=db(S);
        return xx;
    }
    public static int db(String s){
        if(s.equals("()")){
            return 1;
        }else if(s.isEmpty() || s.equals("")){
            return 0;
        }
        int v=0,c=0,index=0;
        char[] cs=s.toCharArray();
        for(int i=0;i<cs.length;i++){
            if(cs[i]=='('){
                c++;
            }else{
                c--;
            }
            if(c==0){
                if(i!=index+1){
                    v+= ( 2*db(s.substring(index+1,i))  );
                }else{
                    v++;
                }
                index=i+1;
            }
        }
        return v;
    }


}
