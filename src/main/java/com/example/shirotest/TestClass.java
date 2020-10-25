package com.example.shirotest;


import org.apache.shiro.subject.Subject;
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
        int[] c={1,0,0,0,0};
        int aaa=nthUglyNumber(1000000000,2,217983653,336916467);
        int xx=10;
    }

    public static int nthUglyNumber(int n, int a, int b, int c) {
        byte[] list=new byte[1+2*(int)Math.pow(10,9)];
        boolean[] Flag={false,false,false};
        for(int i=1;i<n+1;i++){
            if(Flag[0] && Flag[1] && Flag[2]){
                break;
            }
            for(int j=0;j<3;j++){
                if(!Flag[j]){
                    try{
                        int t=i*a;
                        if(t< (1+2*(int)Math.pow(10,9)) ){
                            list[t]=1;
                        }
                    }catch (Exception e){
                        Flag[j]=true;
                    }
                }
            }
        }
        int tmp=0;
        for(int i=0;i<list.length;i++){
            if(list[i]==1){
                tmp++;
            }
            if(tmp==n){
                tmp=i;
                break;
            }
        }
        return tmp;
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
