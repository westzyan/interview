package com.zyan.ali;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int [] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        int max = 0, sum = 0;
        for (int i = 0; i < array.length; i++) {
            max = array[i];
            sum = sum + max;
            for (int j = i + 1; j < array.length; j++) {
                if (max < array[j]){
                    max = array[j];
                }
                sum = sum + max;
            }
        }
        String e = txfloat(sum, solve(array.length));
        System.out.println(e);
    }
    public static int solve(int n){
//        int[] result = new int[n + 1];
//        if (n <= 1){
//            return 1;
//        }
//        result[0] = 1;
//        for (int i = 1 ; i < result.length ; i++) {
//            result[i] = result[i - 1] * i;
//        }
//        return result[n];
        int m = (n+1) * n /2;
        return m;
    }



    public static String txfloat(int a, int b){
        DecimalFormat decimalFormat = new DecimalFormat("0.000000");
        return decimalFormat.format((float)a/b);
    }
}
