package com.zyan;

import java.util.Scanner;

public class TheNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String origin = sc.nextLine();
        String target = sc.nextLine();

        if (origin.length() == 0 || target.length() == 0 || origin.length() != target.length()) {
            System.out.println(0);
        }
        int originNumberOfA = 0;
        int targetNumberOfA = 0;
        int diffNumber = 0;
        for (int i = 0; i < origin.length(); i++) {
            if (origin.charAt(i) != target.charAt(i)) {
                diffNumber++;
            }
            if (origin.charAt(i) == 'A') {
                originNumberOfA++;
            }
            if (target.charAt(i) == 'A') {
                targetNumberOfA++;
            }
        }

        int diffOriginAndTarget = Math.abs(originNumberOfA - targetNumberOfA);
        int result = diffOriginAndTarget;
        result = result + (diffNumber - diffOriginAndTarget) / 2;
        System.out.println(result);
    }
}
