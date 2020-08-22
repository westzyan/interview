package com.zyan.S360;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
//        int[] operator = new int[n];

        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i + 1);
        }


        for (int i = 0; i < m; i++) {
            int operator = scanner.nextInt();
            if (operator == 1) {
                move(list);
            } else if (operator == 2) {
                swap(list);
            }
        }
//        int[] nums = new int[m];
//        for (int i = 0; i < m; i++) {
//            nums[i] = i + 1;
//        }
//        System.out.println(list);
        for (Integer integer : list) {
            System.out.print(integer + " ");
        }


    }

    public static void move(List<Integer> list) {
        int num = list.get(0);
        list.remove(0);
        list.add(num);
    }

    public static void swap(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i = i + 2) {
            int temp = list.get(i + 1);
            list.set(i + 1, list.get(i));
            list.set(i, temp);
        }
    }
}
