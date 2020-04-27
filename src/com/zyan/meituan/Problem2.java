package com.zyan.meituan;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        int count = 0;
        int pos = -1;
        int tag = 0;
        while (true){
            pos++;
            if (pos == n){
                pos = 0;

            }
            if (m >= a[pos]){
                m = m - a[pos];
                count++;
                tag = 0;
            } else {
                tag++;
            }
            if (m == 0){
                break;
            }
            if (tag == n){
                break;
            }
        }
        System.out.println(count);
    }
}
