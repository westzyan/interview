package com.zyan.yuanfudao;

import java.util.Arrays;
import java.util.Comparator;

class Interval {
    int start; //起点
    int end; //终点

    Interval(int a, int b) {
        start = a;
        end = b;
    }
}

class Point {
    int value; //数值
    int type; //点的类型，0为起点，1为终点

    Point(int v, int t) {
        value = v;
        type = t;
    }

    //实现compareTo函数
//    @Override
//    public int compareTo(Point p) {
//        if ()
//        if (this.value == p.value) {
//            return 0;
//        } else if (this.value > p.value) {
//            return 1;
//        } else {
//            return -1;
//        }
//    }

    //区间转换

}

public class Main3 {

    public int getOverlappingCount(Interval[] A) {

        int max = 0, count = 1;

        if (A == null || A.length == 0)
            return max;

        Point[] points = new Point[A.length * 2];

        for (int i = 0; i < A.length; i++) {

            points[2 * i] = new Point(A[i].start, 0);

            points[2 * i + 1] = new Point(A[i].end, 1);

        }

        //Collection.sort(points);

        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return 0;
            }
        });

        for (int i = 0; i < points.length; i++) {

            if (points[i].type == 0) {

                count++;

                max = Math.max(max, count);

            } else {

                count--;

            }

        }

        return max;

    }

    public static void main(String[] args) {

        Interval[] testInterval = new Interval[4];
        testInterval[0] = new Interval(1, 4);
        testInterval[1] = new Interval(1, 2);
        testInterval[2] = new Interval(2, 3);
        testInterval[3] = new Interval(3, 4);

        Main3 demo = new Main3();
        int max = demo.getOverlappingCount(testInterval);

        System.out.println(max);

    }
}