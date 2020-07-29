package com.zyan.singleton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public enum  Singleton {
    INSTANCE;
    private User instance;
    Singleton() {
        instance = new User();
    }
    public User getInstance() {
        return instance;
    }
}


class User{
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        queue.offer(1);
        queue.offer(8);
        queue.offer(9);
        queue.offer(6);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(new ArrayList<>(queue));
//        User instance = Singleton.INSTANCE.getInstance();
    }
}