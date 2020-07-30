package com.zyan.guanglianda;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int x = scanner.nextInt();

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(scanner.nextInt());
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        queue.addAll(list);
        while (m > 0) {
            int min = queue.poll();
            queue.offer(min + x);
            m--;
        }
        System.out.println(queue.peek());

//        List<Integer> list1 = new ArrayList<>();
//        for(Integer i : ans.keySet()){
//            list1.add(0, i);
//        }
//        for (Integer integer : list1) {
//            System.out.println(integer + " ");
//        }
    }
}
