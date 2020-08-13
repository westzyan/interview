package com.zyan.bilibili;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by zhangyan122 on 2020/8/13
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(GetCoinCount(200));
    }

    public static int GetCoinCount (int N) {
        int n = 1024 - N;
        int[] coins = {1, 4, 16, 64};
        int max = n + 1;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 4; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[n] > n ? -1 : dp[n];
    }
}
