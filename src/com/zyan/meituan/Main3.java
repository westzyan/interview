package com.zyan.meituan;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        if(scanner.hasNextInt()) {
            int n = scanner.nextInt();
            double[] w = new double[n];
            int[] A = new int[n];
            double[] p =new double[n];
            for (int i = 0; i < n; i++) {
                p[i] = scanner.nextDouble();
            }
            for (int i = 0; i < n; i++) {
                w[i] = p[i] * scanner.nextDouble();
            }
            for (int i = 0; i < n; i++) {
                A[i] = i+1;
            }
            double[][] f = new double[n+1][n+1];
            f[0][0] = 0;
            for(int i = 1;i <= n;i++)
                f[0][i] = -1;

            double pro;
            for(int i = 1;i <= n;i++){
                for(int j = 0;j <= n;j++){
                    f[i][j] = f[i-1][j];
                    if(j- A[i-1] >=0 && f[i][j-A[i-1]] != -1) {
                        pro = p[i-1];
                        if(pro * f[i][j - A[i - 1]] + w[i - 1]>f[i][j]){
                            f[i][j] = pro * f[i][j - A[i - 1]] + w[i - 1];
                            pro = pro*p[i-1];
                        }
                    }
                }
            }
            double ans = 0;
            for(int i = 0;i <= n;i++){
                //   System.out.println(f[n][i]);
                ans = Math.max(ans,f[n][i]);
            }
            System.out.println(String.format("%.2f",ans));
        }
    }
}
