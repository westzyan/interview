package com.zyan.meituan;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();


        int maxSingle = 0;
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                int count = 0;
                while (str.charAt(i) != str.charAt(i + count) && (i + count) < str.length()) {
                    count++;
                }
                if (count > maxSingle) {
                    maxSingle = count;
                }
            }
        }

//        for (int i = 0; i < ; i++) {
//
//        }
    }
}
