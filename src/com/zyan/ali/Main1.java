package com.zyan.ali;

import java.util.Arrays;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String number = scanner.nextLine();
        int n = Integer.parseInt(number.split(" ")[0]);
        int k = Integer.parseInt(number.split(" ")[1]);
        String ops = scanner.nextLine();

        long ans = 0L;
        long[][] axis = new long[100000][2];
        for (long[] axi : axis) {
            Arrays.fill(axi, 0);
        }
        int cur = 0;
        for (int j = 0; j < k; j++) {
            for (int i = 0; i < n; i++) {
                if (ops.charAt(i) == 'L'){
                    cur--;
                    if (axis[Math.abs(cur)][0] == 0){
                        ans = ans + Math.abs(cur);
                        axis[Math.abs(cur)][0] = 1;
                    }
                }else {
                    cur++;
                    if (axis[Math.abs(cur)][1] == 0){
                        ans = ans + Math.abs(cur);
                        axis[Math.abs(cur)][0] = 1;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
