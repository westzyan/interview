package com.zyan.Tencent;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        double[] ans = new double[k];

        for (int l = 0; l < k; l++) {
            int n = in.nextInt();
            int[][] A = new int[n][2];
            int[][] B = new int[n][2];
            for (int i = 0; i < n; i++) {
                A[i][0] = in.nextInt();
                A[i][1] = in.nextInt();
            }
            for (int i = 0; i < n; i++) {
                B[i][0] = in.nextInt();
                B[i][1] = in.nextInt();
            }

            double min = Double.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    double temp = Math.pow((A[i][0] - B[j][0]), 2) + Math.pow((A[i][1] - B[j][1]), 2);
                    min = Math.min(min, temp);
                }
            }
            ans[l] = Math.sqrt(min);
        }
        in.close();
        for (int i = 0; i < k; i++){
            System.out.printf("%.3f\n", ans[i]);
        }
    }
}