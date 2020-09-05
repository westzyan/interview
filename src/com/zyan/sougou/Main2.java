package com.zyan.sougou;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = 2;
        int[] xa = {-1, 4, 5, 2};
        System.out.println(getHouses(t, xa));
    }

    public static int getHouses (int t, int[] xa) {
        float[] newNums = new float[xa.length];
        for (int i = 0; i < xa.length - 1; i = i + 2) {
            newNums[i] = xa[i] - xa[i + 1] / 2.0f;
            newNums[i + 1] = xa[i] + xa[i + 1] / 2.0f;
        }

        int count = 2;
        for (int i = 1; i < newNums.length - 1; i = i + 2) {
            float gap = newNums[i + 1] - newNums[i];
            if (gap == t) {
                count++;
            }
            if (gap > t) {
                count = count + 2;
            }
        }
        return count;
    }
}
