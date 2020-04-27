package com.zyan.ali;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[j] = a[j] + k;
            }
//            Arrays.sort(a);
            maopao(a);
            a[m - 1] = a[m - 1] / 2;
//            System.out.println(Arrays.toString(a));
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + a[i];
        }
//        int[] a = new int[]{2,3,2,5,6,4};
//        maopao(a);
        System.out.println(sum);
    }

    public static void maopao(int[] a){
        int temp = 0;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]){
                temp = a[i + 1];
                a[i + 1] = a[i];
                a[i] = temp;
            }
        }
    }
}
