package com.zyan.kedaxunfei;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        int count = 0;
//        while (n != 0) {
//            count++;
//            n = n & (n - 1);
//        }
        if (n <= 0) {
            System.out.println(0);
            return;
        }
        String s = Long.toBinaryString(n);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                count++;
            }
        }
        System.out.println(count);
    }
}
