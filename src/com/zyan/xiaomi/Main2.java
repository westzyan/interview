package com.zyan.xiaomi;

import java.util.Scanner;

public class Main2 {
    int[][] directs = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };
    public boolean exit(char[][] board, String word) {
        if (board.length == 0) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        int[][] visited = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    visited[i][j] = 1;
                    if (backtrack(i, j, visited, board, word.substring(1))) {
                        return true;
                    } else {
                        visited[i][j] = 0;
                    }
                }
            }
        }
        return false;
    }

    private boolean backtrack(int i, int j, int[][] visited, char[][] board, String word) {
        if (word.length() == 0) {
            return true;
        }
        for (int[] direct : directs) {
            int curI = i + direct[0];
            int curJ = j + direct[1];
            if (curI >= 0 && curJ >= 0 && curI < board.length && curJ < board[0].length && board[curI][curJ] == word.charAt(0)) {
                if (visited[curI][curJ] == 1) {
                    continue;
                }
                visited[curI][curJ] = 1;
                if (backtrack(curI, curJ, visited, board, word.substring(1))) {
                    return true;
                } else {
                    visited[i][j] = 0;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println(new Main2().exit(board, string));
    }
}
