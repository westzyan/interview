package com.zyan.S360;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int count = 0;
        for (int i = 0; i < n; i++) {
            String s = scanner.nextLine();
            if (s.length() > 10) {
                continue;
            }
            boolean flag = true;
            for (int j = 0; j < s.length(); j++) {
                if (!Character.isLetter(s.charAt(j))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                count++;
            }
        }
        System.out.println(count);

    }
}
