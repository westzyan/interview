package com.zyan.meituan;
import java.util.Scanner;
public class Problem5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        int[][] mat = new int[n][m];

        int left = 0, right = m - 1, up = 0, down = n - 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mat[i][j] = in.nextInt();
            }
        }

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//
//            }
//        }
//        int i = 0, j = 0;
//        while (left < right){
//            System.out.print(mat[i][j] + " ");
//            i++;
//            if (i <= right && i >= left){
//                System.out.print(mat[i][j]);
//            }
//            i--;
//            j++;
//            if (i <= right && i >= left  )
//        }
    }



}
