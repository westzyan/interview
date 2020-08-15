package com.zyan.meituan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main8152 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String s = scanner.nextLine();
            String city1 = s.split(" ")[0];
            String city2 = s.split(" ")[1];
            if (list.size() == 0) {
                list.add(city1);
                list.add(city2);
            } else if (list.get(list.size() - 1).equals(city1)) {
                list.add(city2);
            } else {
                list.add(city1);
                list.add(city2);
            }
        }
        int count = 0;
        String start = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (start.equals(list.get(i))){
                count++;
                if (i < list.size() - 1) {
                    start = list.get(i + 1);
                    i++;
                }
            }
        }
        System.out.println(count);
    }
}
