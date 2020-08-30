package com.zyan.shunfeng;

import java.util.*;


/**
 * @author zhangyan
 * @date 2020/8/29 下午2:56
 */
public class Main8291 {
    public static int maxValue;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();
        String string = scanner.nextLine();
        String[] strings = string.split(" ");
        int[] nums = new int[n];
        for (int i = 0; i < strings.length; i++) {
            nums[i] = Integer.parseInt(strings[i]);
        }
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, res, new ArrayList<>(), nums, m);

    }
    public static void dfs(int start, List<List<Integer>> res, List<Integer> list, int[] nums, int m) {
        if (res.size() == 3) {
            System.out.println(res);
            return;
        }
        for (int i = start; i < nums.length - (m - res.size()) && i < nums.length; i++) {
            list.add(nums[i]);
            dfs(i + 1, res, new ArrayList<>(), nums, m);
            res.add(new ArrayList<>(list));
        }
    }
}
