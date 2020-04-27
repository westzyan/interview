package com.zyan.kuaishou;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
    /**
     * 返回无重复幂因子的 N进制完美数之和的所有幂因子
     * @param R int整型
     * @param N int整型 N进制
     * @return int整型一维数组
     */
    public static int[] GetPowerFactor (int R, int N) {
        if (R <= 0 || N <= 0){
            return new int[0];
        }
        // write code here
        int[] ans = new int[100000];
        Arrays.fill(ans,0);

        int n = 0;
        while (R > (int)Math.pow(N,n)){
            n++;
        }
        int[] temp = new int[1];
        temp[0] = 0;
        if (n == 0){
            return temp;
        }
        n--;
        System.out.println(n);


        while (R > 0){
            if (R - (int)Math.pow(N,n) >= 0 ){
                ans[n] += 1;
                R = R - (int)Math.pow(N,n);
            }else if (R - (int)Math.pow(N,n) == 0){
               break;
            } else{
                n--;
                if (n < 0){
                    break;
                }
            }
        }

        int count = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] > 1){
                return new int[0];
            }
            if (ans[i] == 1){
                list.add(i);
            }
        }

        int[] newAns = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            newAns[i] = list.get(i);
        }

        return newAns;
    }

    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        String a = scanner.nextLine();
        String[] num = a.split(",");
        int R = Integer.parseInt(num[0]);
        int N = Integer.parseInt(num[1]);

        System.out.println(Arrays.toString(GetPowerFactor(R,N)));
    }



}
