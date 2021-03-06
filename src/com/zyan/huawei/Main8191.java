package com.zyan.huawei;

import java.io.IOException;
import java.util.ArrayList;

public class Main8191  {

    public static void main(String[] args) throws IOException {
        A obj = new D();
        System.out.println(obj instanceof A);
        System.out.println(obj instanceof B);
        System.out.println(obj instanceof C);
        System.out.println(obj instanceof D);
    }
    public static ArrayList<ArrayList<Integer>> printMatrixRing(int[][] mat, int m, int n){
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int up = 0, down = m - 1, left = 0, right = n - 1;
        int count = 1;
        while (true){
            for (int col = left; col <= right ; col++) {

                if (count % 10 == 7 && (count /10) % 2 == 1){
                    ArrayList<Integer> list1 = new ArrayList<>();
                    list1.add(up);
                    list1.add(col);
                    res.add(list1);
                }
                count++;
            }
            up++;
            if (up > down){
                break;
            }
            for (int row = up; row <= down ; row++) {

                if (count % 10 == 7 && (count /10) % 2 == 1){
                    ArrayList<Integer> list1 = new ArrayList<>();
                    list1.add(row);
                    list1.add(right);
                    res.add(list1);
                }
                count++;
            }
            right--;
            if (right < left){
                break;
            }
            for (int col = right; col >= left ; col--) {

                if (count % 10 == 7 && (count / 10) % 2 == 1){
                    ArrayList<Integer> list1 = new ArrayList<>();
                    list1.add(down);
                    list1.add(col);
                    res.add(list1);
                }
                count++;
            }
            down--;
            if (down < up){
                break;
            }
            for (int row = down; row >= up ; row--) {

                if (count % 10 == 7 && (count /10) % 2 == 1){
                    ArrayList<Integer> list1 = new ArrayList<>();
                    list1.add(row);
                    list1.add(left);
                    res.add(list1);
                }
                count++;
            }
            left++;
            if (left > right){
                break;
            }
        }
        System.out.println(count);
        return res;
    }

    public static int method(int num) {
        num++;
        try {
            return ++num;
        } finally {
            num++;
            System.out.println(num);
        }
    }
}

class A{}
class B extends A{}
class C extends A{}
class D extends B{}
