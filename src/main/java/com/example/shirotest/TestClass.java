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
        int aaa=longestSubarray(c);
        int xx=10;
    }

    public static int longestSubarray(int[] nums) {
        int zeroN=0,oneN=0;
        for(int i=0;i<nums.length;i++){
            int tmp=nums[i]==0 ? zeroN++ : oneN++;
        }
        if(zeroN==nums.length)return 0;
        if(oneN==nums.length)return nums.length-1;

        int len=nums.length;
        int[] tmp=new int[len+5];
        tmp[0]=-2;
        int n=0,j=1,max=0;
        for(int x:nums){
            if(x==0){
                if(max<n){
                    max=n;
                }
                tmp[j++]=n;
                if(n!=0){
                    tmp[j++]=0;
                    n=0;
                }
            }else{
                n++;
            }
        }
        if(max<n){
            max=n;
        }
        tmp[j++]=n;
        tmp[j]=-1;
        for(int i=0;i<tmp.length ;i++){
            if(tmp[i]==-1){
                break;
            }
            if(tmp[i]==0 && tmp[i-1]+tmp[i+1]>max){
                max=tmp[i-1]+tmp[i+1];
            }
        }
        return max;
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
