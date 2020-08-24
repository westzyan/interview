package com.zyan.S360;

import java.util.Scanner;

public class Main824 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        for (int i = 0; i < string.length(); i++) {
            if (i == 0) {
                string = string.substring(0, 1).toUpperCase() + string.substring(1);
            } else {
                if (string.charAt(i) == 'n') {
                    string = string.substring(0, i) + "\n" + 'N' + string.substring(i + 1);
                }
            }

        }
        System.out.println(string);
    }
}
