package com.zyan.pingduoduo;

import java.util.*;

public class Main3 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        int n = in.nextInt();
        Integer[][] nums = new Integer[n][6];
        Map<Integer[], Integer> map = new HashMap<>();
        for (int i = 0; i < n * 6; i++) {
            nums[i / 6][i % 6] = in.nextInt();
        }

        System.out.println(Arrays.deepToString(nums));


//        List<HashSet<Integer[]>> st = new ArrayList<>();
//
//        int idx = 0;
//        int[] cnt = new int[1000];
//        for (int i = 0; i < n; i++) {
//            boolean flag = false;
//            for (int j = 0; j < ; j++) {
//
//            }
//        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = reverse(nums[i]);

            if (nums[i][1] == 2) {

                Integer[] integers2 = nums[i];

                if (!map.containsKey(nums[i])) {
                    map.put(nums[i], 1);
                } else {

                }

            }
            for (int j = 2; j < 6; j++) {
                if (nums[i][2] == j) {

                }
            }
            if (map.getOrDefault(nums[i], 0) == 0) {

            }
        }
        System.out.println(Arrays.deepToString(nums));




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
}

//3
//        1 2 3 4 5 6
//        1 2 6 5 3 4
//        1 2 3 4 6 5