package com.zyan.netease;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by zhangyan122 on 2020/8/8
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while (T > 0) {
            int n = scanner.nextInt();
            int[] a = new int[n];
            int sum = 0;
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
                sum = sum + a[i];
            }
            System.out.println(sum - helper(a) * 2);
            T--;
        }
    }

    public static int helper(int[] nums) {
        Map<Integer, Integer> dp = new HashMap<>();
        Map<Integer, Integer> cur;
        dp.put(0, 0);
        for (int x : nums) {
            cur = new HashMap<>(dp);
            for (Integer integer : cur.keySet()) {
                dp.put(integer + x, Math.max(cur.get(integer), dp.getOrDefault(x + integer, 0)));
                dp.put(Math.abs(integer - x), Math.max(cur.get(integer) + Math.min(integer, x), dp.getOrDefault(Math.abs(integer - x), 0)));
            }
        }
        return dp.get(0);
    }


}
