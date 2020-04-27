package com.zyan.huawei;

import java.util.ArrayList;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] has = new int[n + 1];
        int[] sign = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            has[i] = in.nextInt();

        }
        ArrayList<String[]> conss = new ArrayList<>();
        in.nextInt();
        for (int i = 0; i < n; i++) {
            conss.add(in.nextLine().split(" "));
        }
//        int[] big =
    }
}
