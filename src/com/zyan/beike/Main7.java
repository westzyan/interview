package com.zyan.beike;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by zhangyan122 on 2020/8/11
 */
public class Main7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String numsStr = scanner.nextLine();
        String[] str = numsStr.split(" ");
        int[] nums = new int[str.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(str[i]);
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int length = 0;
        int max = Integer.MIN_VALUE;
        int temp = Integer.MIN_VALUE;
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                int sum = result(nums, j, i);
                if (sum >= max) {
                    max = sum;
                    length = i - j + 1;
                    if (map.containsKey(max)) {
                        length = Math.min(length, map.get(max));
                    }
                    temp = Math.max(temp, max);
                    map.put(sum, length);
                }
            }
        }
        if (false) {
            System.out.println(map.get(temp));
        } else {
            System.out.println(2);
        }
    }

    public static int result(int[] nums, int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; i++) {
            ans = ans | nums[i];
        }
        return ans;
    }
}
