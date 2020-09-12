package com.zyan.yuanfudao;

import java.util.*;
public class Main9121 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        int[] newNums = new int[n];
        for (int i = 0; i < m; i++) {
            int count = 0;
            for (int j = 0; j < n - 1; j = j + 2) {
                newNums[j] = nums[count + n / 2];
                newNums[j + 1] = nums[count];
                count++;
            }
            if (n % 2 == 1) {
                newNums[n - 1] = nums[n - 1];
            }
            nums = Arrays.copyOf(newNums, n);
        }
        for (int newNum : newNums) {
            System.out.print(newNum + " ");
        }
    }
}
