package com.zyan.dji;

import java.util.Scanner;

/**
 * Created by zhangyan122 on 2020/8/16
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        int k = scanner.nextInt();
        int right = 0;
        int n = string.length();
        char[] res = new char[n];
        for (char c : string.toCharArray()) {
            while (right > 0 && k > 0 && res[right - 1] > c) {
                k--;
                right--;
            }
            res[right++] = c;
        }
        while (k-- > 0 && right > 0) {
            right--;
        } 
        int left = 0;
        while (left < right && res[left] == '0') {
            left++;
        }
        if (left == right) {
            System.out.println(0);
            return;
        }
        System.out.println(new String(res, left, right));
    }
}
