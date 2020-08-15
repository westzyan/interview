package com.zyan.xiecheng;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main1 {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static int[] divingBoard(int a, int b, int k) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        if (k == 0) {
            return new int[]{};
        }

        int[] res = new int[k + 1];
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = k; i >= 0; i--) {
            res[i] = i * a + (k - i) * b;
            set.add(res[i]);

        }
        for (int i = 0; i < set.size(); i++) {
            res[i] = set.pollFirst();
        }
        return res;
    }
    /******************************结束写代码******************************/


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int[] res;

        int _a;
        _a = Integer.parseInt(in.nextLine().trim());

        int _b;
        _b = Integer.parseInt(in.nextLine().trim());

        int _k;
        _k = Integer.parseInt(in.nextLine().trim());

        res = divingBoard(_a, _b, _k);
        String value = "[]";
        if (res != null && res.length > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < res.length; i++) {
                if (i == 0) {
                    stringBuilder.append("[");
                }
                stringBuilder.append(res[i]);
                if (i == res.length - 1) {
                    stringBuilder.append("]");
                } else {
                    stringBuilder.append(",");
                }
            }
            value = stringBuilder.toString();
        }
        System.out.println(value);
    }
}