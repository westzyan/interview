package com.zyan.sougou;
import java.util.*;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(numberofprize(4,4,2));
    }

    public static int numberofprize(int a, int b, int c) {
        int[] nums = new int[]{a, b, c};
        Arrays.sort(nums);
        int res = nums[0];
        int[] newNums = new int[3];
        for (int i = 0; i < 3; i++) {
            newNums[i] = nums[i] - nums[0];
        }
        int ans = 1;
        int var1 = 0;
        int var2 = 0;
        while (true) {
            if (newNums[1] >= ans) {
                var1 = newNums[1] -  ans;
            } else {
                var1 = (newNums[1] - ans) * 2;
            }
            if (newNums[2] >= ans) {
                var2 = newNums[2] - ans;
            } else {
                var2 = (newNums[2] - ans) * 2;
            }
            if ((var1 + var2) / 2 < ans) {
                break;
            }
            ans++;
        }
        return res + ans - 1;
    }
}
