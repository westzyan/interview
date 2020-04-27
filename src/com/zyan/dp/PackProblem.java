package com.zyan.dp;

public class PackProblem {
    public static int ZeroOnePack(int V, int N, int[] weight, int[] value){
        //初始化动态规划数组int
        int[][] dp = new int[N + 1][V + 1];
        //将dp[i][0] 和dp[0][j]均设置为0
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < V + 1; j++) {
                //由于weight和value数组下标都是从0开始，注意第i个物品的重量为weight[i - 1]
                if (weight[i - 1] > j){//如果第i件物品的重量大于背包容量j,则不装入背包
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-weight[i-1]]+value[i-1]);
                }
//                System.out.println(dp[i][j]);
            }
        }
        return dp[N][V];
    }

    public static void main(String[] args) {
        int[] w = new int[]{2, 1, 6, 2, 3};
        int[] v = new int[]{5, 2, 10, 4, 6};
        int a = ZeroOnePack(5, 10, w, v);
        System.out.println(a);
    }
}
