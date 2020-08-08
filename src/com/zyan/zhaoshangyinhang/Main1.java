package com.zyan.zhaoshangyinhang;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        if (k > n) {
            System.out.println(-1);
        }
    }
}
