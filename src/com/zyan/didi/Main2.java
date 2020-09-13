package com.zyan.didi;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String string = scanner.nextLine();
        int index = 0;
        StringBuilder res = new StringBuilder();
        while (index < string.length()) {
            int count = 0;
            StringBuilder stringBuilder = new StringBuilder();
            while (count < n && index < string.length()) {
                stringBuilder.append(string.charAt(index));
                index++;
                count++;
            }
            res.append(stringBuilder.reverse());
        }
        System.out.println(res.toString());
    }
}
