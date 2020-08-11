package com.zyan.shopee;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String num = scanner.nextLine();
        char[] nums = num.toCharArray();
        int[] ints = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ints[i] = nums[i] - '0';
        }
        int[] res = nextPermutation(ints);
        if (res == null) {
            System.out.println(0);
            return;
        }
        for (int re : res) {
            System.out.print(re);
        }
    }
    public static int[] nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] < nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            swap(nums, i, i + 1);
            return nums;
        } else {
            return null;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
