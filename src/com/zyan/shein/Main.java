package com.zyan.shein;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String string;
        while ((string = br.readLine()) != null) {
            char[] chars = string.toCharArray();
            int[] temp = new int[128];
            for (char aChar : chars) {
                temp[aChar]++;
            }
            int max = 0;
            for (int value : temp) {
                if (max < value) {
                    max = value;
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            while (max != 0) {
                for (int i = 0; i < temp.length; i++) {
                    if (temp[i] == max) {
                        stringBuilder.append((char)i);
                    }
                }
                max--;
            }
            System.out.println(stringBuilder.toString());
        }

    }
}

