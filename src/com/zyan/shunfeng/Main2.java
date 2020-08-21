package com.zyan.shunfeng;

import java.util.*;

/**
 * Created by zhangyan122 on 2020/8/20
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
//        int[] start = new int[n];
//        int[] end =  new int[n];
//        int[] profit = new int[n];
//        for (int i = 0; i < n; i++) {
//            start[i] = scanner.nextInt();
//            end[i] = scanner.nextInt();
//            profit[i] = scanner.nextInt();
//        }


        int[][] mession = new int[n][3];
        for (int i = 0; i < n; i++) {
            mession[i][0] = scanner.nextInt();
            mession[i][1] = scanner.nextInt();
            mession[i][2] = scanner.nextInt();
        }
        Arrays.sort(mession, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        System.out.println(getValue(mession, n));

    }

    public static int getValue(int[][] mession, int n) {

        int[] pre = new int[n];
        int[] option = new int[n];
        for (int i = n - 1; i >= 0 ; i--) {
            pre[i] = -1;
            for (int j = i - 1; j >= 0 ; j--) {
                if (mession[j][1] <= mession[i][0]) {
                    pre[i] = j;
                    break;
                }
            }
        }
        option[0] = mession[0][2];
        for (int i = 1; i < n; i++) {
            int val;
            if (pre[i] == -1) {
                val = mession[i][2];
            } else {
                val = option[pre[i]] + mession[i][2];
            }
            option[i] = Math.max(val, option[i - 1]);
        }

        return option[n - 1];
    }

    public static int getAns(int[] start, int[] end, int[] profit) {
        int n = start.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{start[i], end[i], profit[i]};
        }
        Arrays.sort(jobs, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b)-> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });
        for (int i = 0; i < n; i++) {
            int[] cur = jobs[i];
            Queue<int[]> tmp = new LinkedList<>();
            while (!queue.isEmpty() && queue.peek()[0] > cur[0]) {
                tmp.add(queue.poll());
            }
            if (queue.isEmpty()) {
                tmp.add(new int[]{cur[1], cur[2]});
            } else {
                tmp.add(new int[]{cur[1], cur[2] + queue.peek()[1]});
                tmp.add(queue.peek());
                queue.clear();
            }
            while (!tmp.isEmpty()) {
                queue.add(tmp.poll());
            }
        }
        return queue.peek()[1];
    }
}
