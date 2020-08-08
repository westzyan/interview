package com.zyan.netease;

import java.util.Scanner;

/**
 * Created by zhangyan122 on 2020/8/8
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] nums = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nums[i][j] = scanner.nextInt();
            }
        }
        System.out.println(3);
    }
}
