package com.example.shirotest;


import org.apache.shiro.subject.Subject;

import java.lang.reflect.Method;
import java.util.*;


public class TestClass {
    public static void main(String[] a)
    {
        int[][] A={ {0,0,0,0,2,0,8,0,3},
                {0,0,1,0,0,0,0,0,0},
                {0,0,7,6,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,0},
                {0,8,0,0,3,0,2,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,5,7,0,0,0,0,0},
                {0,0,0,1,0,9,6,0,0},
                {0,2,0,0,0,0,0,0,8}};
//        int[][] A={ {0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0}};
        Sudoku(A);
        int qwer=9;
    }

    public static void findMedianSortedArrays(int[] nums1, int[] nums2) { }



    //解数独
    static int[][] map,row,col;
    static int[][][] cell;
    static boolean flag=true;
    public static int[][] Sudoku(int[][] A){
        flag=true;
        map=new int[9][9];
        row=new int[9][9];
        col=new int[9][9];
        cell=new int[3][3][9];
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                int t=A[i][j];
                map[i][j]=t;
                if(t!=0){
                    row[i][t-1]=1;
                    col[j][t-1]=1;
                    cell[i/3][j/3][t-1]=1;
                }
            }
        }
        SudokuDB(0,0);
        return map;
    }
    public static void SudokuDB(int I,int J){
        int aaa;
        if(map[0][0]==5){
            aaa=10;
        }
        if(map[0][1]==6){
            aaa=10;
        }
        if(map[0][2]==4){
            aaa=10;
        }
        if(map[0][3]==9){
            aaa=10;
        }
        if(map[0][4]==2){
            aaa=10;
        }
        if(map[0][5]==1){
            aaa=10;
        }
        if(map[0][6]==8){
            aaa=10;
        }
        if(map[0][7]== 7){
            aaa=10;
        }
        if(map[I][J]!=0){
            judge(I,J);
        } else{
            for(int i=0;i<9&&flag;i++){
                if(I==0 && J==0){
                    int aaaa=10;
                }
                if( row[I][i]==0 && col[J][i]==0 && cell[I/3][J/3][i]==0){
                    row[I][i]=1;
                    col[J][i]=1;
                    cell[I/3][J/3][i]=1;
                    map[I][J]=i+1;
                    judge(I,J);
                    if(I==0 && J==4){
                        aaa=10;
                    }
                    row[I][i]=0;
                    col[J][i]=0;
                    cell[I/3][J/3][i]=0;
                    map[I][J]=0;
                }
            }
        }
    }
    public static void judge(int I,int J){            //递归还是输出

        if(I==8 && J==8){
            for(int x=0;x<9;x++){
                for(int y=0;y<9;y++){
                    System.out.print(map[x][y]+" ");
                }
                System.out.println();
            }
            flag=false;
        }else{
            if(J==8){
                SudokuDB(I+1,0);
            }else{
                SudokuDB(I,J+1);
            }
        }
    }

}
