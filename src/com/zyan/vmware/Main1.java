package com.zyan.vmware;

import java.util.Scanner;

/**
 * @author zhangyan
 * @date 2020/9/21 下午10:10
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sss = sc.nextLine();
        String[] split = sss.split(" ");
        double n = Double.parseDouble(split[0]);
        double m = Double.parseDouble(split[1]);
        double r = Double.parseDouble(split[2]);
        double d = 0, x = 0, y = 0;
        for (int i = 0; i < m; i++) {
            d = (d + r) % (4 * n);
            System.out.println(d);
            if (0 <= d && d < n) {
                x = d;
                y = 0;
            } else if (n <= d && d < 2 * n) {
                x = n;
                y = d - n;
            } else if (2 * n <= d && d < 3 * n) {
                x = n - (d - 2 * n);
                y = n;
            } else {
                x = 0;
                y = n - (d - 3 * n);
            }
            System.out.println(String.format("%.2f", x) + " " + String.format("%.2f", y));
        }
    }
}
