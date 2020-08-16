package com.zyan.dji;

import java.util.Scanner;

/**
 * Created by zhangyan122 on 2020/8/16
 */
public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int p = scanner.nextInt();
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = -1;
            }
        }
        for (int i = 0; i < p; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            graph[a][b] = c;
        }
        int dest = scanner.nextInt();
        int[] result = getShortest(graph);
        for (int i = 0; i < result.length; i++) {
            if (i == dest) {
                System.out.println(result[i]);
            }
        }
    }

    public static int[] getShortest(int[][] adjMatrix) {
        int[] result = new int[adjMatrix.length];
        boolean[] used = new boolean[adjMatrix.length];
        used[0] = true;
        for (int i = 1; i < adjMatrix.length; i++) {
            result[i] = adjMatrix[0][i];
            used[i] = false;
        }
        for (int i = 1; i < adjMatrix.length; i++) {
            int min = Integer.MAX_VALUE;
            int k = 0;
            for (int j = 1; j < adjMatrix.length; j++) {
                if (!used[j] && result[j] != -1 && min > result[j]) {
                    min = result[j];
                    k = j;
                }
            }
            used[k] = true;
            for (int j = 1; j < adjMatrix.length; j++) {
                if (!used[j]) {
                    if (adjMatrix[k][j] != -1 && (result[j] > min + adjMatrix[k][j] || result[j] == -1)) {
                        result[j] = min+ adjMatrix[k][j];
                    }
                }
            }
        }
        return result;
    }

}
