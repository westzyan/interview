package com.zyan.pingduoduo;

import java.util.Scanner;

public class Main1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int distance = in.nextInt(); // 距离终点距离
        int count = in.nextInt(); // 还能走多少步
        if (distance == 0) {
            System.out.println("paradox");
        }
        int ansCount = 0; // 折返次数
        while (count-- > 0) {
            int num = in.nextInt();
            if (num < distance) { // 未到终点
                distance -= num;
            } else if (num > distance) { // 折返
                ansCount++;
                distance = num - distance;
            } else {
                distance -= ansCount; // 最后到达终点需要返回距离0和次数, 而不是paradox
                if (count > 0) {
                    System.out.println("paradox");
                    return;
                }
            }
        }
        System.out.println(distance + " " + ansCount);
    }
}
