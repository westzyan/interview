package com.zyan.yuanfudao;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (n == 0) {
            System.out.println(0);
        }
        Interval[] intervals = new Interval[n * 2];
        int i = 0;
        while (i < n) {
            int start = in.nextInt();
            int end = in.nextInt();
            intervals[i * 2] = new Interval(start, 0);
            intervals[i * 2 + 1] = new Interval(end, 1);
            i++;
        }
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.val < o2.val) {
                    return -1;
                } else if (o1.val > o2.val) {
                    return 1;
                } else {
                    if (o1.type == o2.type) {
                        return 0;
                    } else {
                        // 由于类似[3, 5), [5, 6)这种不算是重合区间, 所以在排序时要保证在两个val相同时, type=1要在type=0前
                        if (o1.type > o2.type) {
                            return -1;
                        } else {
                            return 1;
                        }
                    }
                }
            }
        });
        // 以下代码用于查看排序好的intervals数组中的所有元素
        // 核心代码
        int max = 1;
        int count = 0;
        for (int j = 0; j < 2 * n; j++) {
            if (intervals[j].type == 0) {
                count++;
                max = Math.max(max, count);
            } else {
                // 说明某个区间已经闭合, 所以总计数应该减少1
                count--;
            }
        }
        System.out.println(max);
    }

    static class Interval {
        public int type;
        public int val;

        public Interval(int val, int type) {
            this.type = type;
            this.val = val;
        }
    }
}
