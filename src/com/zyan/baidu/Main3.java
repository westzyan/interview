package com.zyan.baidu;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] nums = new int[m + 1];
        for (int i = 0; i < m; i++) {
            nums[i + 1] = scanner.nextInt();
        }
        double[][][] dp = new double[51][51][51];
        double[][] tmp = new double[51][51];
        tmp[0][0] = 1.0;
        for (int i = 1; i <= 50; i++) {
            tmp[i][0] = 1.0;
            for (int j = 1; j <= i; j++) {
                tmp[i][j] = tmp[i - 1][j - 1] + tmp[i - 1][j];
            }
        }
        for (int i = 0; i <= n; i++) {
            dp[0][0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= n; k++) {
                    for (int l = 0; l <= j; l++) {
                        int max = Math.max(k, (l + nums[i] - 1) / nums[i]);
                        dp[i][j][k] += dp[i - 1][j - l][max] * Math.pow(i - 1, j - l) / Math.pow(i, j) * tmp[j][l];
                    }
                }
            }
        }
        System.out.printf("%.10f", dp[m][n][0]);
    }
}
