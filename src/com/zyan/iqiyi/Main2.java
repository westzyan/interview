package com.zyan.iqiyi;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by zhangyan122 on 2020/8/13
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        if (isValidExp(string)) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
//        System.out.println(isValidExp(string));
    }

    public static boolean isValidExp (String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar == '(') {
                stack.push(')');
            } else if (aChar == '[') {
                stack.push(']');
            } else if (aChar == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || stack.pop() != aChar){
                return false;
            }
        }
        return stack.isEmpty();
    }
}
