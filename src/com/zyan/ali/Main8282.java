package com.zyan.ali;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main8282 {

    public static void swap(char[] chars, int num1, int num2) {
        char temp = chars[num1];
        chars[num1] = chars[num2];
        chars[num2] = temp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        String n = input[0];
        String m = input[1];
        long parseLong = Long.parseLong(m);
        char[] chars = n.toCharArray();
        int len = chars.length;
        List<String> resultList = new ArrayList<>();
        backTrack(chars, 0, len, resultList);
        long count = 0L;
        for (String result : resultList) {
            long resultLong = Long.parseLong(result);
            if (resultLong % parseLong == 0) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static void backTrack(char[] chars, int begin, int len, List<String> list) {
        if (begin == len) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < len; i++) {
                stringBuilder.append(chars[i]);
            }
            if (stringBuilder.charAt(0) != '0') {
                list.add(stringBuilder.toString());
            }
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for (int i = begin; i < len; i++) {
            if (set.contains(chars[i])) {
                continue;
            }
            set.add(chars[i]);
            swap(chars, begin, i);
            backTrack(chars, begin + 1, len, list);
            swap(chars, begin, i);
        }
    }

}
