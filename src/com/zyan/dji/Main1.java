package com.zyan.dji;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by zhangyan122 on 2020/8/16
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int p = scanner.nextInt();
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    graph[i][j] = 10000;
                }
            }
        }
        for (int i = 0; i < p; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            graph[a][b] = c;
        }
        int dest = scanner.nextInt();
        dijstra(graph, 0, dest);

    }
    public static void dijstra(int[][] graph, int source, int dest) {
        int[] shortest = new int[graph.length];
        int[] visited = new int[graph.length];
        for (int i = 1; i < graph.length; i++) {
            int min = Integer.MAX_VALUE;
            int index = -1;
            for (int j = 0; j < graph.length; j++) {
                if (visited[j] == 0 && graph[source][j] < min) {
                    min = graph[source][j];
                    index = j;
                }
            }
            shortest[index] = min;
            visited[index] = 1;
            for (int m = 0; m < graph.length; m++) {
                if (visited[m] == 0 && graph[source][index] + graph[index][m] < graph[source][m]) {
                    graph[source][m] = graph[source][index] + graph[index][m];
                }
            }
        }

        for (int i = 0; i < graph.length; i++) {
            if (i == dest) {
                System.out.println(shortest[i]);
            }
        }

    }

}
