package com.zyan.ali;

import java.util.Scanner;

public class Main8281 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();
        if (str1.length() != n || str2.length() != n) {
            System.out.println(0);
            return;
        }
        String str1Rev = new StringBuilder(str1).reverse().toString();
        int difNum1 = 0;
        int difNum2 = 0;
        int num0 = 0;
        int revNum0 = 0;

        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                difNum1++;
                if (str1.charAt(i) == '0') {
                    num0++;
                }
            }
        }
        for (int i = 0; i < str1.length(); i++) {
            if (str1Rev.charAt(i) != str2.charAt(i)) {
                difNum2++;
                if (str1Rev.charAt(i) == '0') {
                    revNum0++;
                }
            }
        }

        if (difNum1 > difNum2) {
            if (difNum2 == revNum0) {
                System.out.println(difNum2 + 1);
            } else {
                System.out.println(difNum2 - Math.min(revNum0, difNum2 - revNum0) * 2
                        + Math.min(revNum0, difNum2 - revNum0) + 1);
            }
        } else {
            if (difNum1 == num0) {
                System.out.println(difNum1);
            } else {
                System.out.println(difNum1 - Math.min(num0, difNum1 - num0) * 2
                        + Math.min(num0, difNum1 - num0));
            }
        }
    }
}
