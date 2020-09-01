package com.zyan.pingduoduo;

import java.util.Scanner;

public class Main5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] nums = new int[n][n];
        if (n % 2 == 1) {
            getEvenNum(nums, n);
        } else {
            getOddNum(nums, n);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(nums[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void getEvenNum(int[][] nums, int n) {
        int mid = n / 2;
        int[] startDest1 = new int[]{mid - 2, mid + 1};
        int[] startDest3 = new int[]{mid - 1, mid - 2};
        int temp = 0;
        for (int i = startDest1[0]; i >= 0; i--) {
            temp++;
            for (int j = startDest1[1]; j < startDest1[1] + temp ; j++) {
                nums[i][j] = 1;
                nums[i][mid * 2 - j] = 2;
                nums[(mid * 2) - i][(mid * 2) - j] = 5;
                nums[(mid * 2) - i][j] = 6;
            }
        }
        temp = 0;
        for (int c = startDest3[1]; c >= 0 ; c--) {
            temp++;
            for (int r = startDest3[0]; r > startDest3[0] - temp ; r--) {
                nums[r][c] = 3;
                nums[(mid * 2) - r][c] = 4;
                nums[(mid * 2) - r][(mid * 2) - c] = 7;
                nums[r][(mid * 2) - c] = 8;
            }
        }
    }
    public static void getOddNum(int[][] nums, int n) {
        double mid = (double) (n - 1) / 2;
        double[] startDest1 = new double[]{mid - 1.5, mid + 0.5};
        double[] startDest3 = new double[]{mid - 0.5, mid - 1.5};
        int temp = 0;
        for (int i = (int)startDest1[0]; i >= 0; i--) {
            temp++;
            for (int j = (int)startDest1[1]; j < startDest1[1] + temp ; j++) {
                nums[i][j] = 1;
                nums[i][(int)(mid * 2) - j] = 2;
                nums[(int)(mid * 2) - i][(int)(mid * 2) - j] = 5;
                nums[(int)(mid * 2) - i][j] = 6;
            }
        }
        temp = 0;
        for (int c = (int)startDest3[1]; c >= 0 ; c--) {
            temp++;
            for (int r = (int)startDest3[0]; r > startDest3[0] - temp ; r--) {
                nums[r][c] = 3;
                nums[(int)(mid * 2) - r][c] = 4;
                nums[(int)(mid * 2) - r][(int)(mid * 2) - c] = 7;
                nums[r][(int)(mid * 2) - c] = 8;
            }
        }
    }

}
