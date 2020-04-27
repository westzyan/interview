package com.zyan;

public class NowCoder40 {
    /**
     * 计算有多少对符合条件的数对
     * @param a int整型一维数组 数组a
     * @param b int整型一维数组 数组b
     * @return int整型
     */
    public int countLR (int[] a, int[] b) {
        // write code here
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            int sum = 0;
            for (int j = i; j < a.length; j++) {
                sum = sum + a[j];
                if (sum == b[i] + b[j]){
                    count++;
                }
            }
        }
        return count;
    }

    double calc(int a, int b, int c, int d){
        if (a >= b + c + d){
            return 0;
        }
        if (b >= a + c + d){
            return 0;
        }
        if (c >= a + b + d){
            return 0;
        }
        if (d >= a + b + c){
            return 0;
        }
        double p = (a + b + c + d) / 2.0;
        return Math.sqrt((p - a) * (p - b) * (p - c) * (p - d));
    }

    double solve(int[] a){
        double ans = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < j; k++) {
                    for (int l = 0; l < k; l++) {
                        ans = Math.max(ans, calc(a[i], a[j], a[k], a[l]));
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,4};
        int[] b = new int[]{2,1,4,5};
        System.out.println(new NowCoder40().countLR(a, b));
    }
}
