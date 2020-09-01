package com.zyan.pingduoduo;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

public class Main10 {
    int[] dr = new int[]{-1, 0, 1, 0};
    int[] dc = new int[]{0, -1, 0, 1};
    int[][] grid;
    int N;
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
        System.out.println(new Main10().largestIsland(nums));
    }

    public int largestIsland(int[][] grid) {
        this.grid = grid;
        N = grid.length;
        int index = 2;
        int[] area = new int[N * N + 2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    area[index] = dfs(i, j, index++);
                }
            }
        }
        int ans = 0;
        for (int x: area) {
            ans = Math.max(ans, x);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> set = new HashSet<>();
                    for(Integer move : neighbors(i, j)) {
                        if (grid[move / N][move % N] > 1) {
                            set.add(grid[move / N][move % N]);
                        }
                    }
                    int bns = 1;
                    for (Integer integer : set) {
                        bns = bns + area[integer];
                    }
                    ans = Math.max(ans, bns);
                }
            }
        }
        return ans;
    }

    public  int dfs(int r, int c, int index) {
        int ans = 1;
        grid[r][c] = index;
        for (Integer move : neighbors(r, c)) {
            if (grid[move / N][move % N] == 1) {
                grid[move / N][move % N] = index;
                ans += dfs(move / N, move % N, index);
            }
        }
        return ans;
    }

    public List<Integer> neighbors(int r, int c) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                ans.add(nr * N + nc);
            }
        }
        return ans;
    }
}
