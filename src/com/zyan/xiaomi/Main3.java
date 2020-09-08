package com.zyan.xiaomi;

import java.util.Scanner;

public class Main3 {
    int[][] directs = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };
    boolean[][] visited;
    String word;
    char[][] board = {
        {'A', 'B', 'C', 'E'},
        {'S', 'F', 'C', 'S'},
        {'A', 'D', 'E', 'E'}
    };
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        System.out.println(new Main3().classify(string));
    }

    public boolean classify(String word) {
        this.word = word;
        if (word.length() == 0) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int m, int n, int index) {
        if (index == word.length() - 1) {
            return word.charAt(index) == board[m][n];
        }
        if (word.charAt(index) == board[m][n]) {
            visited[m][n] = true;
            for (int i = 0; i < 4; i++) {
                int curM = m + directs[i][0];
                int curN = n + directs[i][1];
                if (inBounds(curM, curN) && !visited[curM][curN]) {
                    if (dfs(curM, curN, index + 1)) {
                        return true;
                    }
                }
            }
            visited[m][n] = false;
        }
        return false;
    }
    public boolean inBounds(int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }
}
