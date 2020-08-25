package com.zyan.wanmeishijie;

import java.util.*;

public class Main1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int knapsackCapacity = Integer.parseInt(input.nextLine().trim());

        String[] volumesStr = input.nextLine().split(" ");
        int[] volumes = new int[volumesStr.length];
        for (int i = 0; i < volumesStr.length; i++) {
            volumes[i] = Integer.parseInt(volumesStr[i].trim());
        }

        String[] valuesStr = input.nextLine().split(" ");
        int[] values = new int[valuesStr.length];
        for (int i = 0; i < valuesStr.length; i++) {
            values[i] = Integer.parseInt(valuesStr[i].trim());
        }

        if (volumes.length == values.length) {
            System.out.println(method(knapsackCapacity, volumes, values));
        }else {
            System.out.println("道具数量不一致。");
        }
        input.close();
    }

    private static int method(int knapsackCapacity, int[] volumes, int[] values) {
        int n = volumes.length;
        int[] dp = new int[knapsackCapacity + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = knapsackCapacity; j >= volumes[i - 1] ; j--) {
                dp[j] = Math.max(dp[j - volumes[i - 1]] + values[i - 1], dp[j]);
            }
        }
        return dp[knapsackCapacity];
    }


}
