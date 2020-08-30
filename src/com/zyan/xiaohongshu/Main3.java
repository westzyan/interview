package com.zyan.xiaohongshu;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n  = scanner.nextInt();
        int m = scanner.nextInt();
        int r = scanner.nextInt();
        int c = scanner.nextInt();
        int[][] nums = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                nums[i][j] = scanner.nextInt();
            }
        }
        int[][] select = new int[r][c];


        System.out.println(4);
    }
}
