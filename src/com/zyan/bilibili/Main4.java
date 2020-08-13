package com.zyan.bilibili;

import com.zyan.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhangyan122 on 2020/8/13
 */
public class Main4 {
    private int[] nums;
    private int len;
    private List<List<Double>> permutationList;

    private void getPermutationList(List<Double> tmpList, boolean[] visited) {
        if (tmpList.size() == len) {
            permutationList.add(new ArrayList<>(tmpList));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (visited[i] || i > 0 && !visited[i - 1] && nums[i] == nums[i - 1]) {
                continue;
            }
            visited[i] = true;
            tmpList.add((double)nums[i]);
            getPermutationList(tmpList, visited);
            tmpList.remove(tmpList.size() - 1);
            visited[i] = false;
        }
    }

    private boolean rec(List<Double> numList) {
        int size = numList.size();
        if (size == 1) {
            return Math.abs(numList.get(0) - 24) <= 1e-6;
        }
        for (int i = 0; i < size - 1; i++) {
            List<Double> newNumList = new ArrayList<>();
            numList.add(numList.get(i) + numList.get(i + 1));
            numList.add(numList.get(i) - numList.get(i + 1));
            numList.add(numList.get(i) * numList.get(i + 1));
            numList.add(numList.get(i) / numList.get(i + 1));

            for (Double newNum : newNumList) {
                List<Double> nextList = new ArrayList<>(numList);
                nextList.set(i, newNum);
                nextList.remove(i + 1);
                if (rec(nextList)) {
                    return true;
                }
            }
        }
        return false;

    }

    public boolean Game24Points(int[] nums) {
        this.nums = nums;
        this.len = nums.length;
        permutationList = new ArrayList<>();
        Arrays.sort(nums);
        getPermutationList(new ArrayList<>(), new boolean[len]);
        for (List<Double> numList : permutationList) {
            if (rec(numList)) {
                return true;
            }
        }
        return false;
    }
}
