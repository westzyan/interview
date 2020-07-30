package com.zyan.dp;

import java.util.Arrays;
import java.util.Scanner;

public class LongIncSeq {
    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[] number = new int[n];
        int[] f = new int[n];
        for (int i = 0; i < number.length; i++) {
            Scanner sc1 = new Scanner(System.in);
            number[ i ] = sc.nextInt();
            f[i] = 1;
        }
        System.out.println(Arrays.toString(number));
        System.out.println(Arrays.toString(f));

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (number[ j ] < number[ i ]){
                    f[ i ] = Math.max(f[ i ], f[ j ] + 1);
                }
            }
            System.out.println(i+" "+f[i]);
        }

    }
}
