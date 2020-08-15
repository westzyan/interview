package com.zyan.meituan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main8151 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int count = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < n / 4 + 1; i++) {
            int times4 = i * 4;
            if (Integer.parseInt(new StringBuilder(String.valueOf(times4)).reverse().toString()) == i) {
                list.add(i);
                list.add(times4);
                count++;
            }
        }
        System.out.println(count);
        for (int i = 0; i < list.size(); i = i + 2) {
            System.out.print(list.get(i) + " " + list.get(i + 1));
            System.out.println();
        }
    }
}
