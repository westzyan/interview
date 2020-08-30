package com.zyan.xiaohongshu;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main2 {

    static int maxBoxes(int[][] boxes) {
        int[][] boxes2 = new int[boxes.length][boxes[0].length];
        for (int i = 0; i < boxes.length; i++) {
            boxes2[i][0] = boxes[i][1];
            boxes2[i][1] = boxes[i][0];
        }
        Arrays.sort(boxes);
        Arrays.sort(boxes2);
        int res1 = 1;
        int height = boxes[0][0];
        int dia = boxes[0][1];
        for (int i = 1; i < boxes.length; i++) {
            if (height < boxes[i][0] && boxes[i][1] > dia) {
                res1++;
                height = boxes[i][0];
                dia = boxes[i][1];
            }
        }
        int res2 = 1;
        height = boxes2[0][0];
        dia = boxes2[0][1];
        for (int i = 1; i < boxes.length; i++) {
            if (height < boxes[i][0] && boxes[i][1] > dia) {
                res2++;
                height = boxes[i][0];
                dia = boxes[i][1];
            }
        }
        if (res2 > res1) {
            return res2;
        } else {
            return res1;
        }

    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int res;

        int _boxes_rows = 0;
        int _boxes_cols = 0;
        _boxes_rows = Integer.parseInt(in.nextLine().trim());
        _boxes_cols = Integer.parseInt(in.nextLine().trim());

        int[][] _boxes = new int[_boxes_rows][_boxes_cols];
        for(int _boxes_i=0; _boxes_i<_boxes_rows; _boxes_i++) {
            for(int _boxes_j=0; _boxes_j<_boxes_cols; _boxes_j++) {
                _boxes[_boxes_i][_boxes_j] = in.nextInt();

            }
        }

        if(in.hasNextLine()) {
            in.nextLine();
        }
        if (_boxes.length != _boxes_rows || _boxes[0].length != _boxes_cols) {
            System.out.println(0);
            return;
        }
        Arrays.sort(_boxes, ((o1, o2) -> o1[0] - o2[0]));
        max = 0;
        found(0, 0, 0, _boxes);
        System.out.println(max);

    }
    static int max;
    public static void found(int curRow, int curLever, int preMaxR, int[][] matr) {
        max = Math.max(max, curLever);
        if (curRow >= matr.length) {
            return;
        }
        if (matr[curRow][1] > preMaxR) {
            found(curRow + 1, curLever + 1, matr[curRow][1], matr);
        }
        found(curRow + 1, curLever, preMaxR, matr);
    }
}
