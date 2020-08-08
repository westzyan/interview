package com.zyan.netease;

import java.util.*;

/**
 * Created by zhangyan122 on 2020/8/8
 */
public class Main22 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] arr = new int[m];
        int[] res = new int[n];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            arr[i] = scanner.nextInt();
            set.add(arr[i]);
        }
        if (n == m) {
            for (int i = 0; i < m; i++) {
                if (i == m - 1) {
                    System.out.print(arr[i]);
                } else {
                    System.out.print(arr[i] + " ");
                }
            }
        }
        int j = 0;
        int count = 1;
        for (int i = 0; i < n; i++) {
            while (set.contains(count)) {
                count++;
            }
            if (i < n && j < m && count > arr[j]) {
                res[i] = arr[j];
                j++;
            } else if ((i < n && j < m && count <= arr[j]) || i < n && j >= m) {
                res[i] = count++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                System.out.print(res[i]);
            } else {
                System.out.print(res[i] + " ");
            }
        }
    }
}
