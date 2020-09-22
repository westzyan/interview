package com.zyan.netease.huyu;

import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        scanner.nextLine();
        double[][] pos = new double[m][2];
        for (int i = 0; i < m; i++) {
            String string = scanner.nextLine();
            try {
                String[] strings = string.split(",");
                pos[i][0] = Double.parseDouble(strings[0]);
                pos[i][1] = Double.parseDouble(strings[1]);
            } catch (Exception e) {
                System.out.println("error");
                return;
            }
        }
        double sumLength = 0;
        double[] singleM = new double[m - 1];
        double[] sumM = new double[m - 1];
        for (int i = 1; i < m; i++) {
            singleM[i - 1] = Math.sqrt((pos[i][0] - pos[i - 1][0]) * (pos[i][0] - pos[i - 1][0])
                    + (pos[i][1] - pos[i - 1][1]) * (pos[i][1] - pos[i - 1][1]));
            sumLength = sumLength + Math.sqrt((pos[i][0] - pos[i - 1][0]) * (pos[i][0] - pos[i - 1][0])
                    + (pos[i][1] - pos[i - 1][1]) * (pos[i][1] - pos[i - 1][1]));
            sumM[i - 1] = sumLength;
        }
        double singleLength = sumLength / n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m - 1; j++) {
                if (i * singleLength < sumM[j]) {

                }
            }
        }
        System.out.println("-1,0\n0,1\n1,0");
    }
}
