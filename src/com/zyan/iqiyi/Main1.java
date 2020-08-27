package com.zyan.iqiyi;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int num = 0;
        for (int i = 5; i <= n; i++) {
            int j = i;
            while (j % 5 == 0) {
                num++;
                j = j / 5;
            }
        }
        System.out.println(num);
        System.out.println(new Integer(100) == new Integer(100));
    }
    static {
        System.out.println("ddd");
    }
    public Main1() {
        System.out.println("main");
    }
}
