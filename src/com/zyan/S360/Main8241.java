package com.zyan.S360;

import java.util.Scanner;

public class Main8241 {
    public static int maxVal = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] array = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = scanner.nextInt();
            }
        }

        int[] visited = new int[m];
        dfs(array, visited, 0, 0, n);
        System.out.println(maxVal);

    }
    public static void dfs(int[][] array, int[] visited, int hasWeapon, int depth, int n){
        if (hasWeapon == n) {
            return;
        }
        if (depth >= n || visited[depth] == 1) {
            return;
        }
        visited[depth] = 1;
        for (int i = hasWeapon - 1; i < n; i++) {
            maxVal = Math.max(maxVal, array[depth - 1][i]);
            dfs(array, visited, i + 1, depth, n);
        }
        visited[depth] = 0;
        dfs(array, visited, hasWeapon, depth + 1, n);
    }
}
