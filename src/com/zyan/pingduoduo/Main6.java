package com.zyan.pingduoduo;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();
        int[][] nums = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                nums[i][j] = scanner.nextInt();
            }
        }
        System.out.println(largestIsland(nums));

    }

    public static int largestIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int index = 2;
        int max = 0;
        int[] area = new int[400];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int a = dfs(grid, i, j, index);
                    area[index++] = a;
                    max = Math.max(max, a);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    max = Math.max(max, change(grid, area, i, j));
                }
            }
        }
        return max;
    }


    public static int dfs(int[][] nums, int x, int y, int index) {
        if (x < 0 || x >= nums.length || y < 0 || y >= nums[0].length) {
            return 0;
        }
        if (nums[x][y] != 1) {
            return 0;
        }
        nums[x][y] = index;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int res = 0;
        for (int i = 0; i < 4; i++) {
            res = res + dfs(nums, x + dx[i], y + dy[i], index);
        }
        return res + 1;
    }

    public static int change(int[][] nums, int[] area, int x, int y) {
        int sum = 1;
        Set<Integer> set = new HashSet<>();
        if (x - 1 >= 0) {
            set.add(nums[x - 1][y]);
        }
        if (x + 1 < nums.length) {
            set.add(nums[x + 1][y]);
        }
        if (y - 1 >= 0) {
            set.add(nums[x][y - 1]);
        }
        if (y + 1 < nums[0].length) {
            set.add(nums[x][y + 1]);
        }
        for (Integer integer : set) {
            sum = sum + area[integer];
        }
        return sum;
    }
}
