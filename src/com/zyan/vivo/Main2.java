package com.zyan.vivo;

import java.util.*;
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
//        System.out.println(getAns(str));
        String reverse = new StringBuilder(str).reverse().toString();
        str = str.trim();
        if (str.equals(reverse)) {
            System.out.println(str);
        }else {
            for (int i = 0; i < str.length(); i++) {
                String newString = str.substring(0, i) + str.substring(i + 1, str.length());
                String newReverse = new StringBuilder(newString).reverse().toString();
                if (newString.equals(newReverse)) {
                    System.out.println(newString);
                    return;
                }
            }
            System.out.println("false");
        }

    }
    public static String getAns(String s) {
        for (int i = 0; i < s.length(); i++) {
            String tmp = s.substring(0, i) + s.substring(i + 1, s.length());
            if (new StringBuilder(tmp).reverse().toString().equals(tmp)) {
                return tmp;
            }
        }
        return "false";
    }
}
