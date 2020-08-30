package com.zyan.shunfeng;

import java.util.Date;
import java.util.Scanner;

/**
 * @author zhangyan
 * @date 2020/8/29 下午2:56
 */
public class Main8292{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s;
        while (true) {
            if((s = scanner.nextLine()).equals("")){
                return;
            }
            int n = Integer.parseInt(s);
            String str = scanner.nextLine();
            int[] nums = new int[n];
            String[] strings = str.split(" ");
            for (int i = 0; i < strings.length; i++) {
                nums[i] = Integer.parseInt(strings[i]);
            }
            int count = 0;
            if (n == 1) {
                System.out.println(1);
                return;
            }
            for (int i = 1; i < nums.length - 1; i++) {
                if (nums[i] < nums[i - 1] && nums[i] < nums[i + 1]) {
                    count++;
                }
            }
            System.out.println(count + 1);
        }
    }

}
