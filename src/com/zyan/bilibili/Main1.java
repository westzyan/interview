package com.zyan.bilibili;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by zhangyan122 on 2020/8/13
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    }

    public boolean Game24Points (int[] arr) {
        if (arr == null || arr.length != 4) {
            return false;
        }
        List<Integer> list = new ArrayList<>();
        for (int i : arr) {
            list.add(i);
        }
        return help(list);
    }
    public boolean help(List<Integer> list) {
        if (list.size() == 1) {
            return Math.abs(list.get(0) - 24) < 1e-6;
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i != j) {
                    List<Integer> newList = new ArrayList<>();
                    for (int k = 0; k < list.size(); k++) {
                        if (i != j && i != k && j != k) {
                            newList.add(list.get(k));
                        }
                    }
                    List<Integer> list1 = new ArrayList<>(list);
                    List<Integer> list2 = new ArrayList<>(list);
                    List<Integer> list3 = new ArrayList<>(list);
                    List<Integer> list4 = new ArrayList<>(list);
                    list1.add(list.get(i) + list.get(j));
                    list2.add(list.get(i) - list.get(j));
                    list3.add(list.get(i) * list.get(j));
                    list4.add(list.get(i) / list.get(j));
                    if (help(list1)) {
                        return true;
                    }
                    if (help(list2)) {
                        return true;
                    }
                    if (help(list3)) {
                        return true;
                    }
                    if (help(list4)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
