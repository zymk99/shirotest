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
        int[] c={-1,3,5,1,4,2,-9};
        int aaa=maxNonOverlapping(c,6);
        int xx=10;
    }

    public static int maxNonOverlapping(int[] nums, int target) {
            int[] list=new int[nums.length+1];
            list[0]=0;
            int lastIndex=0;
            for(int i=0;i<nums.length;i++){
                int tmp=0;
                list[i+1]=list[i];
                for(int j=i;j>=lastIndex;j--){
                    tmp+=nums[j];
                    if(tmp==target){
                        list[i+1]=list[lastIndex]+1;
                        lastIndex=i+1;
                        break;
                    }
                }
            }
            return list[list.length-1];
    }
    public static int db(int[] nums, int target,int lift){
        return 1;
    }


}
