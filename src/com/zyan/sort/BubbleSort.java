package com.zyan.sort;

import java.util.Arrays;

public class BubbleSort {
    public static int[] bubble(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    swap(nums, i, j);
                }
            }
            System.out.println(Arrays.toString(nums));
        }
        return nums;
    }



    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {5,6,9,6,8,0,2,3};
        bubble(a);
    }
}
