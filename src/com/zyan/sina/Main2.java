package com.zyan.sina;

import java.util.*;
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        char[] chars = new char[str.length()];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = str.charAt(i);
        }
        quickSort(chars, 0, chars.length - 1);
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                System.out.println(false);
                return;
            }
        }
        System.out.println(true);
    }

    public static void quickSort(char[] s, int left, int right) {
        if (left < right) {
            int index = partition(s, left, right);
            quickSort(s, left, index - 1);
            quickSort(s, index + 1, right);
        }
    }
    public static int partition(char[] s, int left, int right) {
        char x = s[left];
        while (left < right) {
            while (left < right && s[right] >= x) {
                right--;
            }
            if (left < right) {
                s[left] = s[right];
                left++;
            }
            while (left < right && s[left] < x) {
                left++;
            }
            if (left < right) {
                s[right] = s[left];
                right--;
            }
        }
        s[left] = x;
        return left;
    }
}
