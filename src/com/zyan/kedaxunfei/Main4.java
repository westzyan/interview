package com.zyan.kedaxunfei;

import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        int n = scanner.nextInt();
        if (string == null || string.length() == 0) {
            System.out.println("");
        }
        int len = string.length();
        n = n % len;
        string = string + string;
        System.out.println( string.substring(n ,n + len));;
    }
}
