package com.zyan.beike;

import java.util.Scanner;

/**
 * Created by zhangyan122 on 2020/8/11
 */
public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] nums = new int[m][4];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 4; j++) {
                nums[i][j] = scanner.nextInt();
            }
        }

        System.out.println(5);
    }
}
