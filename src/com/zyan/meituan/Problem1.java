package com.zyan.meituan;

import java.util.Arrays;
import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();
//        if (n < 1 || x < 1){
//            System.out.println(0);
//        }
//        int ans = 0;
        int[] a = new int[n];

        for (int i = 0; i < a.length; i++) {
            a[i] = scanner.nextInt();
        }
        Arrays.sort(a);
//        int i = 0, j = a.length - 1;
//        while (i < j) {
//            if (a[j] - a[i] <= x){
//                break;
//            }else if ((a[j] - a[i + 1]) > (a[j - 1] - a[i])){
//                j--;
//                ans++;
//            }else {
//                i++;
//                ans++;
//            }
//        }

        int l = 0, r = 0;
        int ans = 0;
        for (int i = n - 1; i >= 0 ; i--) {
            for (int j = 0; j < i; j++){
                if (a[i] - a[j] > x){
                    continue;
                }
                ans = Math.max(ans, i - j + 1);
                break;
            }
        }
        System.out.println(n - ans);
    }
}
