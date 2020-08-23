package com.zyan.iqiyi;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        if ("".equals(string)) {
            System.out.println("False");
            return;
        }
        int x = 0;
        int y = 0;
        Set<String> set = new HashSet<>();
        set.add("0,0");
        for (int i = 0; i < string.length(); i++) {
            switch (string.charAt(i)) {
                case 'E':
                    x++;
                    break;
                case 'W':
                    x--;
                    break;
                case 'S':
                    y--;
                    break;
                case 'N':
                    y++;
                    break;
                default:
                    break;
            }
            if (!set.add(x + "," + y)){
                System.out.println("True");
                return;
            }
        }
        System.out.println("False");
    }
}
