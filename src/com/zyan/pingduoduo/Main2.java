package com.zyan.pingduoduo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main2 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        int n = in.nextInt();
        Integer[][] nums = new Integer[n][6];
        Map<Integer[], Integer> map = new HashMap<>();
        for (int i = 0; i < n * 6; i++) {
            nums[i / 6][i % 6] = in.nextInt();
        }

        System.out.println(Arrays.deepToString(nums));

        for (int i = 0; i < nums.length; i++) {
            nums[i] = reverse(nums[i]);
            if (nums[i][1] == 2) {
                if (!map.containsKey(nums[i])) {
                    boolean flag = false;
                    for (int j = 0; j < 3; j++) {
                        nums[i] = turnLeft(nums[i]);
                        if (map.containsKey(nums[i])) {
                            flag = true;
                            map.put(nums[i], map.get(nums[i]) + 1);
                            break;
                        }
                    }
                    if (!flag) {
                        map.put(nums[i], 1);
                    }
                } else {
                    map.put(nums[i], map.get(nums[i]) + 1);
                }
            }
        }
        System.out.println(Arrays.deepToString(nums));
        for (Map.Entry<Integer[], Integer> integerEntry : map.entrySet()) {
            System.out.println(Arrays.toString(integerEntry.getKey()) + " " + integerEntry.getValue());
        }

    }


    public static Integer[] reverse(Integer[] start) {
        char[] operator = {'L','L','L','L','A','A'};
        for (int i = 0; i < operator.length; i++) {//读取指令，操作
            if (operator[i] == 'L') {
                int temp1 = start[4];
                int temp2 = start[5];
                start[5] = start[2];
                start[4] = start[3];
                start[3] = temp1;
                start[2] = temp2;
                if (start[0] == 1) {
                    return start;
                }
            }

            if (operator[i] == 'A') {
                int temp1 = start[4];
                int temp2 = start[5];
                start[4] = start[0];
                start[5] = start[1];
                start[0] = temp2;
                start[1] = temp1;
                if (start[0] == 1) {
                    return start;
                }
            }
        }
        return start;
    }


    public static Integer[] turnLeft(Integer[] start) {
        int temp1 = start[2];
        int temp2 = start[3];
        start[2] = start[4];
        start[3] = start[5];
        start[4] = temp2;
        start[5] = temp1;
        return start;
    }
}

//3
//        1 2 3 4 5 6
//        1 2 6 5 3 4
//        1 2 3 4 6 5