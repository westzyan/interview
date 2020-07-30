package com.zyan.guanglianda;
import java.util.*;
/**
 * Created by zhangyan122 on 2020/7/29
 */
public class Main6 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int x = scanner.nextInt();

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(scanner.nextInt());
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o1 - o2);
        queue.addAll(list);
        while (m > 0) {
            Integer min = queue.poll();
            queue.offer(min + x);
            m--;
        }
        System.out.println(queue.peek());
    }

}
