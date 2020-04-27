package com.zyan.Tencent;

import java.math.BigInteger;
import java.util.Scanner;

public class Main5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            BigInteger x = in.nextBigInteger();
            int K = in.nextInt();
            int depth = (int) format(x.doubleValue(), 2) + 1;
            if (depth <= K) {
                System.out.println(-1);
            } else {
                for (int a = 0; a < depth - K; a++) {
                    x = x.divide(new BigInteger("2"));
                }
                System.out.println(x);
            }
        }
        in.close();
    }
    public static double format(double value, double base) {
        return Math.log(value) / Math.log(base);
    }
}