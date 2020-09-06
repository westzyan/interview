package com.zyan.meituan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author zhangyan
 * @date 2020/9/6 上午10:05
 */
public class Main09063 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int[] arrays = new int[n];
            for (int i = 0; i < n; i++) {
                arrays[i] = scanner.nextInt();
            }
            if (n % 2 == 0) {
                System.out.println("NO");
                continue;
            }
            int[] nums = new int[n];

            int leafNum = n / 2 + 1;
            for (int i = n - 1; i > leafNum - 2; i--) {
                nums[i] = 1;
            }
            for (int i = leafNum - 2; i >= 0 ; i--) {
                nums[i] = nums[i * 2 + 1] + nums[i * 2 + 2] + 1;
            }
            Arrays.sort(arrays);
            Arrays.sort(nums);
            System.out.println(Arrays.toString(nums));
            System.out.println(Arrays.toString(arrays));
            boolean flag = false;
            for (int i = 0; i < nums.length; i++) {
                if (arrays[i] != nums[i]) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
    }
}
