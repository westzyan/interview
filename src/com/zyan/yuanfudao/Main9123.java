package com.zyan.yuanfudao;

import java.util.Scanner;
import java.util.Stack;

public class Main9123 {
    public static void main(String[] args) {
        Stack<Object> stack = new Stack<>();
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] sarray = s.split(" ");
        String regex = "[0-9]*";
        boolean temp = false;
        int fuhao = 0;
        int number = 0;
        for (int i = sarray.length - 1; i >= 0 ; i--) {
            try {
                if (sarray[i].matches(regex)) {
                    stack.push(Double.parseDouble(sarray[i]));
                    number++;
                } else {
                    fuhao++;
                    double a = (double) stack.pop();
                    double b = (double) stack.pop();
                    double result = jisuan(a, b, sarray[i]);
                    stack.push(result);
                }
            } catch (Exception e) {
                temp = true;
            }
        }
        if (temp || !(number - 1 == fuhao) || stack.empty()) {
            System.out.println("invalid");
        } else {
            double ans = (double)stack.pop();
            System.out.println(ans);
        }
    }

    private static double jisuan(double a, double b, String s) {
        switch (s) {
            case "*" :
                return a * b;
            case "/" :
                return a / b;
            case "+" :
                return a + b;
            case "-" :
                return a - b;
        }
        return 0;
    }

}
