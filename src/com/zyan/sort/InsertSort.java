package com.zyan.sort;

import java.util.Arrays;

public class InsertSort {
    public static void insertSort(int[] a, int size){
        for (int i = 1; i < size ; i++) {
            int temp = a[i];
            int j = i;
            while (j > 0 && a[j - 1] > temp){
                a[j] = a[j - 1];
                j--;
            }
            a[j] = temp;
        }
    }


    public static void main(String[] args) {
        int[] a = {3,4,5,9,1,5,3,6,7,3,4,5};
        insert(a);
//        insertSort1(a);
//        System.out.println(Arrays.toString(a));
    }

    public static void insertSort1(int[] a){
        int size = a.length;
        for (int i = 1; i < size; i++) {
            int temp = a[i];
            int j = i;
            while (j > 0 && a[j - 1] > temp){
                a[j] = a[j - 1];
                j--;
            }
            a[j] = temp;
        }
    }

    public static void insert(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0 ; j--) {
                if (nums[j] < nums[j - 1]) {
                    swap(nums, j - 1, j);
                } else {
                    break;
                }
            }
            System.out.println(Arrays.toString(nums));
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
