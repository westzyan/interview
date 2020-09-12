package com.zyan.vivo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main1 {
    private int m;
    private int n;
    private int[][] grid;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int startI = scanner.nextInt();
        int startJ = scanner.nextInt();
        int endI = scanner.nextInt();
        int endJ = scanner.nextInt();
        scanner.nextLine();
        char[][] chars = new char[n][n];
        for (int i = 0; i < n; i++) {
            String str = scanner.nextLine();
            for (int j = 0; j < n; j++) {
                chars[i][j] = str.charAt(j);
            }
        }
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (chars[i][j] == '#' || chars[i][j] == '@') {
                    grid[i][j] = 1;
                } else {
                    grid[i][j] = 0;
                }
            }
        }
        List<List<Integer>> path = new Main1().path(grid, startI, startJ, endI, endJ);
        for (List<Integer> list : path) {
            System.out.println(list.size());
        }

        System.out.println(13);
    }

    public List<List<Integer>> path(int[][] grid, int startI, int startJ, int endI, int endJ) {
        this.grid = grid;
        m = endI;
        n = endJ;
        List<List<Integer>> ansList = new ArrayList<>();
        dfs(startI, startJ, new boolean[grid.length][grid.length], ansList);
        return ansList;
    }

    private boolean dfs(int startI, int startJ, boolean[][] visited, List<List<Integer>> pathList) {
        if (startI >= grid.length || startJ >= grid.length || startI < 0 || startJ < 0 || grid[startI][startJ] == 1 ||  visited[startI][startJ]) {
            return false;
        }
        pathList.add(Arrays.asList(startI, startJ));
        if (startI == m && startJ == n) {
            return true;
        }
        visited[startI][startJ] = true;
        if (dfs(startI + 1, startJ, visited, pathList) || dfs(startI , startJ + 1, visited, pathList) ||
                dfs(startI - 1, startJ, visited, pathList) || dfs(startI, startJ - 1, visited, pathList)) {
            return true;
        }
        pathList.remove(pathList.size() - 1);
        return false;
    }
}


//*
// * 15
// * 0 7 7 7
// * *5#++B+B+++++$3
// * 55#+++++++###$$
// * ###$++++++#+*#+
// * ++$@$+++$$$3+#+
// * +++$$+++$+4###+
// * A++++###$@+$++A
// * +++++#++$#$$+++
// * A++++#+5+#+++++
// * +++$$#$++#++++A
// * +++$+@$###+++++
// * +###4+$+++$$+++
// * +#+3$$$+++$##++
// * +#*+#++++++#$$+
// * $####+++++++$##
// * 3$+++B++B++++#5
