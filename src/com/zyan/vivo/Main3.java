package com.zyan.vivo;

import java.util.*;

public class Main3 {
    public String compileSeq (String input) {
        String[] strings = input.split(",");
        int n = strings.length;
        int[][] adj = new int[n][n];
        int[] rely = new int[n];
        for (int i = 0; i < strings.length; i++) {
            int num = Integer.parseInt(strings[i]);
            if (num != -1) {
                adj[num][i] = 1;
                rely[i]++;
            }
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (rely[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int x = queue.poll();
            result.append(",").append(x);
            for (int j = 0; j < n; j++) {
                if (adj[x][j] == 1) {
                    rely[j]--;
                    if (rely[j] == 0) {
                        queue.offer(j);
                    }
                }
            }
        }
        return result.substring(1);
    }

    public static void main(String[] args) {
        String s = "1,2,-1,1";
        System.out.println(new Main3().compileSeq(s));
    }
}
