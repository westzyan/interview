package com.zyan.dp;

import java.util.Arrays;

public class LCS {
    public static void main(String[] args) {
//        String  a = "12asdfafdssdfjalkfjl;asdfjadffsadff";
//        String b = "fdsjaklfddfafsafssdfjalkdfjalkfjl;asdfjafjadffsadf";
//        System.out.println(findLCS(a, a.length(), b, b.length()));
        System.out.println(3 << 10);
    }


    public static int findLCS(String A, int n, String B, int m){
        int dp[][] = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = 0;
            }
        }
        int[][] path = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    path[i][j] = 0;
                }else if (dp[i - 1][j] > dp[i][j - 1]){
                    dp[i][j] = dp[i - 1][j];
                    path[i][j] = 1;
                }else {
                    dp[i][j] = dp[i][j - 1];
                    path[i][j] = -1;
                }
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        PrintLCS(path, A, A.length(), B.length());
        System.out.println();
        return dp[n][m];
    }

    public static void PrintLCS(int[][]b,String x,int i,int j){
        if(i == 0 || j == 0){
            return;
        }
        if(b[i][j] == 0){
            PrintLCS(b,x,i-1,j-1);
            System.out.printf("%c",x.charAt(i-1));
        }else if(b[i][j] == 1){
            PrintLCS(b,x,i-1,j);
        }else{
            PrintLCS(b,x,i,j-1);
        }
    }
}
