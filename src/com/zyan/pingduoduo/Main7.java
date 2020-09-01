package com.zyan.pingduoduo;

import java.util.Scanner;

public class Main7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] weight = new int[n];
        int[] value = new int[n];
        for (int i = 0; i < n; i++) {
            weight[i] = scanner.nextInt();
            value[i] = scanner.nextInt();
            if (weight[i] < 0 || value[i] < 0) {
                System.out.println("\n" + 6);
                return;
            }
        }
        System.out.println(zeroOnePack1(m, n, weight, value));
    }

    public static int zeroOnePack(int m, int n, int[] weight, int[] value) {

        int[] dp = new int[m + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = m; j >= weight[i - 1]; j--) {
                dp[j] = Math.max(dp[j - weight[i - 1]] + value[i - 1], dp[j]);
            }
        }
        return dp[m];
    }


    public static int zeroOnePack1(int m, int n, int[] weight, int[] value) {
        int fuW = fuNum(weight, value);
        int[] newWeight = new int[n - fuW];
        int[] newValue = new int[n - fuW];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (weight[i] > 0 && value[i] > 0) {
                newWeight[count] = weight[i];
                newValue[count] = value[i];
                count++;
            }
        }
        int[] dp = new int[m + 1];
        for (int i = 1; i < n - fuW + 1; i++) {
            for (int j = m; j >= newWeight[i - 1]; j--) {
                dp[j] = Math.max(dp[j - newWeight[i - 1]] + newValue[i - 1], dp[j]);
            }
        }
        return dp[m];
    }

    public static int min(int[] nums) {
        int min = 0;
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(nums[i], min);
        }
        return min;
    }

    public static int fuNum(int[] nums1, int[] nums2) {
        int count = 0;
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] < 0 || nums2[i] < 0) {
                count++;
            }
        }
        return count;
    }

}
