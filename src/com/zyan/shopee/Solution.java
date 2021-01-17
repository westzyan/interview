package com.zyan.shopee;

import java.util.*;

/**
 * @author zhangyan
 * @date 2021/1/17 下午4:06
 */
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        input = input.substring(1,input.length() - 1);
        String[] data = input.split(",");
        int[][] newInput = new int[data.length][2];
        for (int i = 0; i < data.length; i++) {
            String tmp = data[i].substring(1, data[i].length() - 1);
            String[] tmp1 = tmp.split(":");
            newInput[i][0] = Integer.parseInt(tmp1[0]);
            newInput[i][1] = Integer.parseInt(tmp1[1]);
        }
        int[][] output = new Solution().merge(newInput);
        System.out.println("[[" + output[0][0] + ":" + output[0][1] + "]]");
    }
}

