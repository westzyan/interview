package com.zyan.jd;

import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] strings = scanner.nextLine().trim().split(" ");
            List<Integer> list = new ArrayList<>();
            for (String string : strings) {
                list.add(Integer.parseInt(string));
            }
            res.add(list);
        }
        int[][] dp = new int[n + 1][2 * (n + 1) - 1];
        for (int i = n - 1; i >= 0 ; i--) {
            for (int j = 0; j < 2 * i + 1; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], Math.max(dp[i + 1][j + 1], dp[i + 1][j + 2])) + res.get(i).get(j);
            }
        }
        System.out.println(dp[0][0]);
    }
}
