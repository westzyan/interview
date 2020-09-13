package com.zyan.didi;

import java.util.*;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int k = scanner.nextInt();
            scanner.nextLine();
            int[][] graph = new int[n][n];
            for (int i = 0; i < m; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                int c = scanner.nextInt();
                if (c <= k) {
                    graph[a - 1][b - 1] = 1;
                    graph[b - 1][a - 1] = 1;
                }
            }
            boolean[] visited = new boolean[n];
            if (dfs(graph, visited, n)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    public static void visit(int[][] graph, boolean[] visited, int n, int x) {
        visited[x] = true;
        for (int i = 0; i < n; i++) {
            if (graph[i][x] == 1 && !visited[i]) {
                visit(graph, visited, n, i);
            }
        }
    }

    public static boolean dfs(int[][] graph, boolean[] visited, int n) {
        visit(graph, visited, n, 0);
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}
