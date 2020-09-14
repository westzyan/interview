package com.zyan.baidu;

import java.util.Arrays;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[] expect = new int[n];
            int[] residue = new int[m];
            for (int i = 0; i < n; i++) {
                expect[i] = scanner.nextInt();
            }
            for (int i = 0; i < m; i++) {
                residue[i] = scanner.nextInt();
            }
            Arrays.sort(residue);
            for (int i = 0; i < n; i++) {
                int count = 0;
//                for (int j = m - 1; j >= 0 ; j--) {
//                    if (residue[j] >= expect[i]) {
//                        count++;
//                    } else {
//                        break;
//                    }
//                }
                int left = 0, right = m - 1;
                while (left < right) {
                    int mid = left + (right - left) / 2;
                    if (expect[i] > residue[mid] && mid + 1 < m && expect[i] <= residue[mid + 1]) {
                        count = m - mid - 1;
                        break;
                    } else if (expect[i] <= residue[mid] && mid - 1 >= 0 && expect[i] > residue[mid - 1]) {
                        count = m - mid;
                        break;
                    }else if (expect[i] > residue[mid]){
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                System.out.print(count + " ");
            }
            System.out.println();
        }
    }
}
