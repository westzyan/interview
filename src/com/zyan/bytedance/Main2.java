package com.zyan.bytedance;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @author zhangyan
 * @date 2020/9/20 下午7:24
 */
public class Main2 {
    static int mod = 1000000007;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        while (n-- > 0) {
            String string = scanner.nextLine();
            String[] strings = string.split(" ");
            int a = Integer.parseInt(strings[0]);
            int b = Integer.parseInt(strings[1]);
            String op = strings[2];
            BigDecimal bigA = new BigDecimal(a);
            BigDecimal bigB = new BigDecimal(b);
            switch (op) {
                case "+" :
                    System.out.println((a + b) % mod);
                    break;
                case "-" :
                    System.out.println((a - b) % mod);
                    break;
                case "*" :
                    System.out.println(bigA.multiply(bigB).divideAndRemainder(new BigDecimal(mod))[1]);
                    break;
                case "^" :
                    System.out.println(pow(a, b));
                    break;
            }
        }
    }

    public static long pow(int a, int b) {
        if (b == 0) {
            return 1;
        }
        if (b % 2 == 0) {
            return pow(a % mod , b / 2) * pow(a % mod, b / 2) % mod;
        } else {
            return pow(a % mod, b / 2) * pow(a % mod, b / 2) * a % mod;
        }
    }
}
