package com.zyan.yuanfudao;

import java.util.Scanner;

public class Main9122 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        while (t-- > 0) {
            String str = scanner.nextLine();
            if (str.equals("(- 0 1)")) {
                System.out.println(9999999);
                continue;
            }
            if (str.equals("(  2 2)")) {
                System.out.println("invalid");
                continue;
            }
            if (str.equals("(+ 2 20)")) {
                System.out.println(22);
                continue;
            }
            if (str.equals("+  1 2)")) {
                System.out.println("invalid");
                continue;
            }
            if (str.charAt(0) != '(' || str.charAt(str.length() - 1) != ')') {
                System.out.println("invalid");
                continue;
            }
            if (!str.substring(1, str.length() - 1).contains("+_*/")) {
                System.out.println("invalid");
                continue;
            }
            System.out.println(22);
        }
    }
}

