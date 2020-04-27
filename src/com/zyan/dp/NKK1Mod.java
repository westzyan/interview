package com.zyan.dp;

import java.util.Scanner;

public class NKK1Mod {
    static int mod = 1000000007;

    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        int n = input.nextInt();
//        System.out.println((pow(n - 1) * n) % mod);
        System.out.println(pow(6));
    }

    public static long pow(int n) {
        if (n == 0)
            return 1;
        long half = pow(n / 2);
        if (n % 2 == 0)
            return (half * half) % mod;
        else
            return (half * half * 2) % mod;
    }
}
