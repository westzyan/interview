package com.zyan.beike;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author zhangyan
 * @date 2020/9/7 下午3:08
 */
public class Main09071 {
    public static HashMap<String, Integer> map = new HashMap<>();
    public static void main(String[] args) {
        map.put("S", 0);
        map.put("J", 1);
        map.put("B", 2);
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        while (t-- > 0) {
            String operator = scanner.nextLine();
            String[] strings = operator.split(" ");
            String result = "";
            int count1 = 0;
            for (int i = 2; i < strings.length; i++) {
                if (map.get(strings[0]) - map.get(strings[i]) == -1) {
                    count1++;
                }
                if (map.get(strings[0]) - map.get(strings[i]) == 0 || map.get(strings[0]) - map.get(strings[i]) == -2) {
                    count1--;
                }
            }
            int count2 = 0;
            for (int i = 2; i < strings.length; i++) {
                if (map.get(strings[1]) - map.get(strings[i]) == -1 ) {
                    count2++;
                }
                if (map.get(strings[1]) - map.get(strings[i]) == 0 || map.get(strings[1]) - map.get(strings[i]) == -2) {
                    count2--;
                }
            }
            if (count1 > count2) {
                System.out.println("left");
            } else if (count1 < count2) {
                System.out.println("right");
            } else {
                System.out.println("same");
            }
        }
    }
}
