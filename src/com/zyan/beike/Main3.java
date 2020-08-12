package com.zyan.beike;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by zhangyan122 on 2020/8/11
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String numsStr = scanner.nextLine();
        String[] str = numsStr.split(" ");
        int[] nums = new int[str.length];
        int len = nums.length;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(str[i]);
        }
        int[][] dp = new int[len][len];





        System.out.println(Arrays.toString(nums));
    }
}
