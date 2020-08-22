package com.zyan.S360;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
//        int[] operator = new int[m];
//        List<Integer> list = new LinkedList<>();
//        for (int i = 0; i < n; i++) {
//            list.add(i + 1);
//        }

        int a = 1, b = 2;
        boolean now = true;
        int x;
        while (m-- > 0) {
            x = scanner.nextInt();
            if (x == 1) {
                if (now) {
                    a = a + 2;
                } else {
                    b = b + 2;
                }
            }
            now = !now;
        }
        for (int i = 1; i <= n; i++) {
            if (i != 1) {
                System.out.print(" ");
            }
            int temp = 0;
            if (now) {
                temp = 1;
            }
            if (((i + temp + 1) & 1) == 1) {
                a = a % n != 0 ? a % n : n;
                System.out.print(a);
                a = a + 2;
            } else {
                b = b % n != 0 ? b % n : n;
                System.out.print(b);
                b = b + 2;
            }
        }

    }
}
