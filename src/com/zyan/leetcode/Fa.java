package com.zyan.leetcode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Fa {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        if (n <= 0){
//            System.out.println(0);
//            return;
//        }
//        if (n == 1){
//            System.out.println(1);
//            return;
//        }
//        int[] array = new int[n + 1];
//        array[0] = 0;
//        array[1] = 1;
//
//        int sum = 1;
//        for (int i = 2; i <= n; i++) {
//            array[i] = array[i - 1] + array[i - 2];
//            sum = sum + numberOf1(array[i]);
//        }
//
//        Date date = new Date();
//        long ctime = date.getTime();
//
//        System.out.println(ctime);
//        Set<List<Integer>> set = new HashSet<>();
//        set.add(Arrays.asList(1,2,3));
//        set.add(Arrays.asList(1,2,3));
//        set.add(Arrays.asList(1,2,3,4));
//        System.out.println(set);
//        BigDecimal bigDecimal1 = new BigDecimal("98889.3333");
//        BigDecimal bigDecimal2 = new BigDecimal("222222.22222");
//
//        System.out.println(new Formatter().format("%.2f",5.325).toString());
//        System.out.println();
//        System.out.println(bigDecimal1.multiply(bigDecimal2).setScale(3, RoundingMode.HALF_UP).toString());
//        System.out.println(new Fa().multiply("123", "988"));

        System.out.println(new BackTrack().permuteUnique(new int[]{1,1,2}));
    }
    private static int numberOf1(int n){
        int count = 0;
        char[] chars = String.valueOf(n).toCharArray();
        for (char aChar : chars) {
            if (aChar == '1'){
                count++;
            }
        }
        return count;
    }



    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        // 保存计算结果
        String res = "0";

        // num2 逐位与 num1 相乘
        for (int i = num2.length() - 1; i >= 0; i--) {
            int carry = 0;
            // 保存 num2 第i位数字与 num1 相乘的结果
            StringBuilder temp = new StringBuilder();
            // 补 0
            for (int j = 0; j < num2.length() - 1 - i; j++) {
                temp.append(0);
            }
            int n2 = num2.charAt(i) - '0';

            // num2 的第 i 位数字 n2 与 num1 相乘
            for (int j = num1.length() - 1; j >= 0; j--) {
                int n1 = num1.charAt(j) - '0';
                int product = (n1 * n2 + carry) % 10;
                temp.append(product);
                carry = (n1 * n2 + carry) / 10;
            }
            if (carry > 0){
                temp.append(carry);
            }
            // 将当前结果与新计算的结果求和作为新的结果
            res = addStrings(res, temp.reverse().toString());
        }
        return res;
    }

    /**
     * 对两个字符串数字进行相加，返回字符串形式的和
     */
    public String addStrings(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1;
             i >= 0 || j >= 0 || carry != 0;
             i--, j--) {
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            int sum = (x + y + carry) % 10;
            builder.append(sum);
            carry = (x + y + carry) / 10;
        }
        return builder.reverse().toString();
    }

    public String multiply1(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        // 保存计算结果
        String res = "0";

        // num2 逐位与 num1 相乘
        for (int i = num2.length() - 1; i >= 0; i--) {
            int carry = 0;
            // 保存 num2 第i位数字与 num1 相乘的结果
            StringBuilder temp = new StringBuilder();
            // 补 0
            for (int j = 0; j < num2.length() - 1 - i; j++) {
                temp.append(0);
            }
            int n2 = num2.charAt(i) - '0';

            // num2 的第 i 位数字 n2 与 num1 相乘
            for (int j = num1.length() - 1; j >= 0 || carry != 0; j--) {
                int n1 = j < 0 ? 0 : num1.charAt(j) - '0';
                int product = (n1 * n2 + carry) % 10;
                temp.append(product);
                carry = (n1 * n2 + carry) / 10;
            }
            // 将当前结果与新计算的结果求和作为新的结果
            res = addStrings(res, temp.reverse().toString());
        }
        return res;
    }




}
