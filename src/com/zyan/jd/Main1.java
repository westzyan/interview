package com.zyan.jd;

import java.util.*;
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] dp = new int[n + 1];
        dp[1] = 2;
        dp[2] = 3;
        dp[3] = 5;
        int depth = 1;
        int start = 1;
        int i = 4;
        while (i <= n) {
            depth++;
            int end = (int)Math.pow(3, depth - 1);
            int startP = start;
            start = i;
            int depthNum = (int)Math.pow(10, depth - 1);
            int cur = startP;
            for (int j = 0; j < end && i <= n; j++, i++) {
                dp[i] = dp[cur++] + 2 * depthNum;
            }
            cur = startP;
            for (int j = 0; j < end && i <= n; j++, i++) {
                dp[i] = dp[cur++] + 3 * depthNum;
            }
            cur = startP;
            for (int j = 0; j < end && i <= n; j++, i++) {
                dp[i] = dp[cur++] + 5 * depthNum;
            }
        }
        System.out.println(dp[n]);
    }
}
