package com.zyan.guanglianda;

import java.util.*;

public class Main4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        int count = 1;
        while (count > 0) {
            count = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i] > 0 && map.get(arr[i]) > 1) {
                    for (int j = i + 1; j < n; j++) {
                        if (arr[i] == arr[j]) {
                            arr[i] = 0;
                            arr[j] *= 2;
                            int val = map.getOrDefault(arr[j], 0) + 1;
                            if (val > 1) {
                                count++;
                            }
                            map.put(arr[j], val);
                            break;
                        }
                    }
                }
            }
        }
        for (int i : arr) {
            if (i > 0) {
                System.out.print(i + " ");
            }
        }
    }
}