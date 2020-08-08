package com.zyan.netease;


import java.util.Scanner;

/**
 * Created by zhangyan122 on 2020/8/8
 */
public class Main1 {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int[] array = new int[n];
//        for (int i = 0; i < n; i++) {
//            array[i] = scanner.nextInt();
//        }
//        int count = 0;
//        for (int i = 0; i < n; i++) {
//            if (array[i] == 2 || array[i] == 3) {
//                count++;
//            }
//            if (array[i] >= 4) {
//                count = count + array[i] / 2;
//            }
//        }
//        System.out.println(count);
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            count+= array[i] / 2;
        }
        System.out.println(count);

    }


}
