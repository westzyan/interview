package com.zyan.netease;

import java.util.*;

/**
 * Created by zhangyan122 on 2020/8/8
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] nums = new int[m];
        for (int i = 0; i < m; i++) {
            nums[i] = scanner.nextInt();
        }

        int lack = n - m;
        if (lack == 0) {
            for (int i = 0; i < n; i++) {
                System.out.println(nums[i] + " ");
            }
        }
        List<Integer> ans = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int max = 0;
        for (int num : nums) {
            for (int i = 1; i < num && set.size() < n; i++) {
                if (!set.contains(i)) {
                    ans.add(i);
                    set.add(i);
                    if (ans.size() == n) {
                        break;
                    }
                    max = Math.max(max, i);
                }
            }
            if (ans.size() == n) {
                break;
            }
            ans.add(num);
            if (ans.size() == n) {
                break;
            }
            max = Math.max(max, num);
        }
        int num = max + 1;
        int now = ans.size();
        while (n - now > 0) {
            ans.add(num);
            num++;
            now++;
        }
        for (Integer i : ans) {
            System.out.print(i + " ");
        }
    }
}
