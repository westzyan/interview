package com.zyan.kuaishou;

import java.util.Arrays;

public class Main3 {
    /**
     * 根据顾客属性计算出顾客排队顺序
     * @param a int整型一维数组 顾客a属性
     * @param b int整型一维数组 顾客b属性
     * @return int整型一维数组
     */
    public static int[] WaitInLine (int[] a, int[] b) {
        // write code here
        if (a.length == 0 || a == null || b.length == 0 || b == null || a.length != b.length){
            return new int[0];
        }
        int len = a.length;
        int[] diff = new int[len + 1];
        diff[0] = 0;
        for (int i = 0; i < len; i++) {
            diff[i + 1] = a[i] - b[i];
        }
        int[] ans = new int[len];

        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        int i, j;

        for (int k = 0; k < len; k++) {
            for (i = 1; i < len + 1; i++) {
                if (diff[i] > max){
                    max = diff[i];
                    maxIndex = i;
                }
            }
            ans[k] = maxIndex;
            diff[maxIndex] = Integer.MIN_VALUE;
            max = Integer.MIN_VALUE;

        }
//        System.out.println(ans[0]);
        return ans;
    }

    public static void main(String[] args) {
        int[] a = {8,9,7};
        int[] b = {5,8,3};

//        System.out.println(Arrays.toString(WaitInLine(a,b)));
    }
}
