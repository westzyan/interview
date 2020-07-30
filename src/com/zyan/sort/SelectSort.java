package com.zyan.sort;

import java.util.Arrays;

public class SelectSort {

    public static int[] select(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int min = nums[i];
            int index = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (min > nums[j]) {
                    min = nums[j];
                    index = j;
                }
            }
            swap(nums, i, index);
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
        select(a);
    }
}
