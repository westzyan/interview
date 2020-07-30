package com.zyan.Tencent;

import java.util.Scanner;

public class Main3 {
    static int res = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] array1 = new int[n];
        int[] array2 = new int[n];
        for (int i = 0; i < n; i++){
            array1[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++){
            array2[i] = in.nextInt();
        }
        in.close();
        dfs(array1, array2, 0, -1, 0);
        if (res == Integer.MAX_VALUE) {
            System.out.println(-1);
        }
        System.out.println(res);
    }

    public static void dfs(int[] nums1, int[] nums2, int start, int pre, int tempCount) {
        if (start == nums1.length - 1) {
            if (nums1[start] >= pre) {
                res = Math.min(res, tempCount);
                return;
            }
            return;
        }
        if (nums1[start] >= pre) {
            dfs(nums1, nums2, start + 1, nums1[start], tempCount);
            if (nums2[start + 1] >= pre) {
                int tmp1 = nums1[start];
                int tmp2 = nums1[start + 1];
                nums1[start] = nums2[start + 1];
                nums1[start + 1] = nums2[start];
                nums2[start] = tmp2;
                nums2[start + 1] = tmp1;
                dfs(nums1, nums2, start + 1, nums2[start + 1], tempCount + 1);
                tmp1 = nums1[start];
                tmp2 = nums1[start + 1];
                nums1[start] = nums2[start + 1];
                nums1[start + 1] = nums2[start];
                nums2[start] = tmp2;
                nums2[start + 1] = tmp1;
            }
        } else {
            if (nums2[start + 1] >= pre) {
                int tmp1 = nums1[start];
                int tmp2 = nums1[start + 1];
                nums1[start] = nums2[start + 1];
                nums1[start + 1] = nums2[start];
                nums2[start] = tmp2;
                nums2[start + 1] = tmp1;
                dfs(nums1, nums2, start + 1, nums2[start + 1], tempCount + 1);
                tmp1 = nums1[start];
                tmp2 = nums1[start + 1];
                nums1[start] = nums2[start + 1];
                nums1[start + 1] = nums2[start];
                nums2[start] = tmp2;
                nums2[start + 1] = tmp1;
            }
        }
    }
}