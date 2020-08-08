package com.zyan.zhaoshangyinhang;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        float s = scanner.nextInt();
        float m = scanner.nextInt();
        float sum = 0;
        int count = 0;
        PriorityQueue<Float> queue = new PriorityQueue<>((o1, o2) -> {
            if (o2 - o1 > 0) {
                return 1;
            } else if (o2 - o1 < 0) {
                return -1;
            } else {
                return 0;
            }
        });
        queue.offer(s);
        while (!queue.isEmpty() && queue.peek() >= 2) {
            float max = queue.poll();
            sum += (max / 2) * (max / 2);
            queue.offer(max / 2);
            queue.offer(max / 2);
            count++;
            if (sum >= m) {
                System.out.println(count);
                return;
            }
        }
        System.out.println(-1);
    }
}
