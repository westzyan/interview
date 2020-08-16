package com.zyan.dji;

import java.util.Scanner;

/**
 * Created by zhangyan122 on 2020/8/16
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();
        int[] weight = new int[n];
        int[] value = new int[n];
        for (int i = 0; i < n; i++) {
            value[i] = scanner.nextInt();
            weight[i] = scanner.nextInt();
        }
        int res = zereOnePack(x, n, weight, value);
        System.out.println(res); 
    }

    public static int zereOnePack(int x, int n, int[] weight, int[] value) {
        int[] dp = new int[x + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = x; j >= weight[i - 1]; j--) {
                dp[j] = Math.max(dp[j - weight[i - 1]] + value[i - 1], dp[j]);
            }
        }
        return dp[x];
    }
}
