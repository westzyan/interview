package com.zyan.xiaomi;

import java.util.*;
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        String[] strings = string.split(" ");
        for (String s : strings) {
            if (s.length() < 8 || s.length() > 120) {
                System.out.println(1);
            } else {
                int[] flag = new int[4];
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) >= '1' && s.charAt(i) <= '9') {
                        flag[0] = 1;
                    } else if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                        flag[1] = 1;
                    } else if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                        flag[2] = 1;
                    } else {
                        flag[3] = 1;
                    }
                }
                int ans = 0;
                for (int i : flag) {
                    if (i != 1) {
                        ans = 2;
                        break;
                    }
                }
                System.out.println(ans);
            }
        }
    }
}
