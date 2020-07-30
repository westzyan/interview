package com.zyan.huawei;

import java.util.Arrays;

public class Main {
    /**
     * 计算a+b，需要消耗a+b个时间
     * n 个数，求n个数的和需要消耗最短时间
     * @param args
     */
    public static void main(String[] args) {
        int[] a = {1,1,1,1};
        sumTime(a);
    }

    public static int sumTime(int[] array){
        if (array.length <= 0){
            return 0;
        }
        int sum = 0;
        Arrays.sort(array);
        int[] temp = new int[array.length];
        temp[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            temp[i] = temp[i - 1] + array[i];
        }
        for (int i = 1; i < array.length; i++) {
            sum = sum + temp[i];
        }
        System.out.println(sum);
        return 0;

    }
}
