package com.zyan.beike;



import java.util.Scanner;

/**
 * Created by zhangyan122 on 2020/8/11
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in);
        int n = scanner1.nextInt();
        scanner1.nextLine();
        char[] str = scanner1.nextLine().toCharArray();
        int count = 0;
        int i = 0, j = str.length - 1;
        while (i < j) {
            if (str[i] != str[j]) {
                count++;
            }
            i++;
            j--;
        }
        System.out.println(count);
    }
}
