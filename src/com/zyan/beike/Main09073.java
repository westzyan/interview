package com.zyan.beike;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zhangyan
 * @date 2020/9/7 下午3:49
 */
public class Main09073 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            System.out.println(numWays2(n, k));
        }
    }
    public static int numWays2(int n, int k) {
        if (k == 0) {
            return 0;
        }
        if (n == 1) {
            return k;
        }
        int ans = k;
        for (int i = 1; i < n; i++) {
            ans = ans * (k - 1) % (1000000007);
        }
        return ans;
    }



    public static int numWays1(int n, int k) {
        int[] dp = new int[]{0,k,k*k,0};
        if(n <= 2){
            return dp[n];
        }
        for(int i = 3;i<=n;i++){
            dp[3] = (k-1) * (dp[1] + dp[2]);
            dp[1] = dp[2];
            dp[2] = dp[3];
        }
        return dp[3];
    }

    public static int numWays(int n, int k) {
        if (n == 0 || k == 0) return 0;
        if (n > 2 && k == 1) return 0;
        if (n == 1) return k;
        int[][] matrix = new int[2 * k][n];
        int pre_sum = 0;
        int curr_sum = 0;
        for (int i = 0; i < 2 * k; i++) {
            if (i % 2 == 0) {
                matrix[i][0] = 1;
                pre_sum++;
            } else {
                matrix[i][0] = 0;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 2 * k; j++) {
                if (j % 2 == 0) {
                    matrix[j][i] = pre_sum - matrix[j][i - 1] - matrix[j + 1][i - 1];
                } else {
                    matrix[j][i] = matrix[j - 1][i - 1];
                }
                curr_sum += matrix[j][i];
            }
            pre_sum = curr_sum;
            curr_sum = 0;
        }
        int res = 0;
        for (int i = 0; i < 2 * k; i++) {
            System.out.println(Arrays.toString(matrix[i]));
            res += matrix[i][n - 1];
        }
        return res;
    }
}
