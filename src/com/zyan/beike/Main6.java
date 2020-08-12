package com.zyan.beike;



import java.util.*;

/**
 * Created by zhangyan122 on 2020/8/11
 */
public class Main6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while (T > 0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            if (m == 1 ) {
                System.out.println(minZhiYinzi(n));
            } else if (n == 1) {
                System.out.println(minZhiYinzi(m));
            } else {
                System.out.println(Math.min(minZhiYinzi(n), minZhiYinzi(m)));
            }
            T--;
        }
//        System.out.println(minZhiYinzi(4545));
    }

    public static int minZhiYinzi(int num) {
        float temp = (float)Math.pow(num, 0.5);
        for (int i = 2; i <= temp; i++) {
            if (num % i == 0) {
                return i;
            }
        }
        return num;
    }
}
