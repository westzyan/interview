package com.zyan.kuaishou;

import java.util.Scanner;
import java.util.Stack;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        String a = scanner.nextLine();
        int pairs = 0, left = 0, right = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < a.length(); i++) {
//            stack.push(a.charAt(i));
            if (a.charAt(i) == '('){
                stack.push('(');
            }
            if (a.charAt(i) == ')'){
                if (!stack.isEmpty()){
                    stack.pop();
                    pairs++;
                }else {
                    right++;
                }
            }
        }
        while (!stack.isEmpty()) {
            stack.pop();
            left++;
        }
        System.out.println(pairs + " " + left + " " + right);
    }

    public static void swap(int a, int b){
        int temp = a;
        a = b;
        b = temp;
    }
}
