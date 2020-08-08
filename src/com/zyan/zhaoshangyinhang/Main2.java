package com.zyan.zhaoshangyinhang;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int s = scanner.nextInt();
        int m = scanner.nextInt();
        int sum = 0;
        int count = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        queue.offer(s);
        while (!queue.isEmpty() && queue.peek() >= 2) {
            int max = queue.poll();
            if (max % 2 == 0) {
                sum += (max / 2) * (max / 2);
                queue.offer(max / 2);
                queue.offer(max / 2);
            } else {
                sum += (max / 2) * (max / 2 + 1);
                queue.offer(max / 2);
                queue.offer(max / 2 + 1);
            }
            count++;
            if (sum >= m) {
                System.out.println(count);
                return;
            }
        }
        System.out.println(-1);
    }
}
