package com.zyan.wanmeishijie;

import java.util.*;
public class Main2 {
    public static void main(String[] args) {
        int[][] weight = new int[6][];
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 6; i++) {
            String[] valuesStr = input.nextLine().split(" ");
            int[] values = new int[valuesStr.length];
            for (int j = 0; j < valuesStr.length; j++) {
                values[j] = Integer.parseInt(valuesStr[j]);
            }
            weight[i] = values;
        }
        input.close();
        if (weight[0].length != 6) {
            for (int i = 0; i < 6; i++) {
                System.out.println(9999);
            }
        }
        method(weight);
    }

    public static void method(int[][] weight) {
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j < weight[0].length; j++) {
                if (weight[i][j] == -1) {
                    weight[i][j] = 10000000;
                }
            }
        }
        int start = 0;
        int n = weight.length;
        int[] shortPath = new int[n];
        int[] visited = new int[n];
        shortPath[start] = 0;
        visited[start] = 1;
        for (int count = 1; count < n; count++) {
            int k = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (visited[i] == 0 && weight[start][i] < min) {
                    min = weight[start][i];
                    k = i;
                }
            }
            shortPath[k] = min;
            visited[k] = 1;
            for (int i = 0; i < n; i++) {
                if (visited[i] == 0 && weight[start][k] + weight[k][i] < weight[start][i]) {
                    weight[start][i] = weight[start][k] + weight[k][i];
                }
            }
        }
        for (int i = 1; i < n; i++) {
            if (shortPath[i] == 0) {
                System.out.println(9999);
            } else {
                System.out.println(shortPath[i]);
            }
        }
    }

}
