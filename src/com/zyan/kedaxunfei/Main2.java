package com.zyan.kedaxunfei;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        quickSort(array, 0, n - 1);
        for (int i : array) {
            System.out.print(i + " ");
        }
    }


    public static void quickSort(int[] s, int left, int right){
        if (left < right){
            int index = Partition(s, left, right);
            quickSort(s, left, index - 1);
            quickSort(s, index + 1, right);
        }
    }

    public static int Partition(int[] s, int left, int right){
        int i = left, j = right;
        int x = s[left];
        while (i < j){
            //从右向左找小于x的数来填s[i]
            while (i < j && s[j] >= x){
                j--;
            }
            if (i < j){
                s[i] = s[j]; //将s[j]填到s[i]中，s[j]就形成了一个新的坑
                i++;
            }

            // 从左向右找大于或等于x的数来填s[j]
            while(i < j && s[i] < x){
                i++;
            }
            if(i < j) {
                s[j] = s[i]; //将s[i]填到s[j]中，s[i]就形成了一个新的坑
                j--;
            }
        }
        //退出时，i等于j。将x填到这个坑中。
        s[i] = x;
        return i;
    }
}
