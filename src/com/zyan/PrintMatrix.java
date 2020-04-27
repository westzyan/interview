package com.zyan;

import java.util.ArrayList;
import java.util.Arrays;

public class PrintMatrix {

    /**
     * 之字形
     * S型
     * 打印矩阵，[[1,2,3],[4,5,6],[7,8,9],[10,11,12]],4,3
     * 返回：[1,2,3,6,5,4,7,8,9,12,11,10]
     * @param mat
     * @param n
     * @param m
     * @return
     */
    public static int[] printMatrixZhiZiXing(int[][] mat, int n, int m){
        int[] arr = new int[m * n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i % 2 == 0){
                    arr[count] = mat[i][j];
                }else {
                    arr[count] = mat[i][m - j - 1];
                }
                count++;
            }
        }
        return arr;
    }

    public static ArrayList<Integer> printMatrixRing(int[][] mat, int n, int m){
        ArrayList<Integer> list = new ArrayList<>();
        int up = 0, down = n - 1, left = 0, right = m - 1;
        while (true){
            for (int col = left; col <= right ; col++) {
                list.add(mat[up][col]);
            }
            up++;
            if (up > down){ //TODO 一会换成等于试一下
                break;
            }
            for (int row = up; row <= down ; row++) {
                list.add(mat[row][right]);
            }
            right--;
            if (right < left){
                break;
            }
            for (int col = right; col >= left ; col--) {
                list.add(mat[down][col]);
            }
            down--;
            if (down < up){
                break;
            }
            for (int row = down; row >= up ; row--) {
                list.add(mat[row][left]);
            }
            left++;
            if (left > right){
                break;
            }
        }

        for (Integer i: list
             ) {
            System.out.print(i + " ");
        }

        return list;
    }


    /**
     * 思路就是先确定两个点的坐标，第一个是左下的坐标，第二个是右上的坐标，然后打印这两个坐标点中间的值
     *  打印完之后，两个坐标点移动，注意边界，不要越界
     *  停止条件就是
     * @param matrix
     */
    public static void printMatrixZigZag(int[][] matrix){
        // 都是从0,0 位置开始
        int row1 =0;  //1代表右上边的点的坐标
        int col1 = 0;
        int row2 = 0; //2代表左下边的点的坐标
        int col2 = 0;
        // 记录行边界
        int down = matrix.length-1;
        // 记录列边界
        int right = matrix[0].length-1;
        // 判断打印方向
        boolean fromUp = false;
        // row1越界停止，或者col2越界停止
        
        
        while (row1 <= down) {
//            printLevel(matrix, row1, col1, row2, col2, fromUp);
            // row1 开始不变，col1走的最后 开始增加 row1开始不变
            row1 = col1 == right ? row1 + 1: row1;  //如果列已经到最右边了，行坐标就该向下了
            col1 = col1 == right ? col1 : col1+ 1;  //如果列已经到最右边了，列坐标不变
            // col2开始不变， row2开始增加， row2走到最后开始不变， col2开始增加
            // 原代码 会出错 出错的原因是先更新row2的话，行数变了，下边的列变不变还有根据row2是否走到最下边
//            row2 = row2 == down ? row2 : row2 + 1 ;
//            col2 = row2 == down ? col2 + 1 : col2;

            col2 = row2 == down ? col2 + 1 : col2;
            row2 = row2 == down ? row2 : row2 + 1 ;
            fromUp = !fromUp;
//            System.out.println(row1);
//            System.out.println(col1);
//            System.out.println(row2);
//            System.out.println(col2);
        }
        System.out.println();
    }

    /**
     * 斜线打印
     * @param matrix 矩阵数组
     * @param row1 左上左行数
     * @param col1 左上左列数
     * @param row2 左下行数
     * @param col2 左下列数
     * @param fromUp 判断打印方向
     */
    public static void printLevel(int[][] matrix, int row1, int col1, int row2,int col2, boolean fromUp){
        if(fromUp) {
            // 右上往坐下打印 一条斜线
            while (row1 <= row2){
                System.out.print(matrix[row1++][col1--] + " ");
            }
        } else {
            // 左下往右上打印 一条斜线
            while (row2 >= row1) {
                System.out.print(matrix[row2--][col2++] + " ");
            }
        }
    }


    public static void printMatrixZ(int[][] matrix){
        int rows = matrix.length;
        int cols = matrix[0].length;

        int row1 = 0, col1 = 0, row2 = 0, col2 = 0;

        boolean flag = false;

        while (row1 <= rows){
            printLevel(matrix, row1, col1, row2, col2, flag);
//            if (col1 == cols){
//                row1 = row1 + 1;
//            }else {
//                col1 = col1 + 1;
//            }
//
//            if (row2 == rows){
//                col2 = col2 + 1;
//            }else {
//                row2 = row2 +1;
//            }
//
//            flag = !flag;
//            System.out.println(row1);
//            System.out.println(col1);
//            System.out.println(row2);
//            System.out.println(col2);

        }
    }



    public static void main(String[] args) {
        int[][] arr = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};

//        System.out.println(Arrays.toString(printMatrixZhiZiXing(arr,4,3)));
//        printMatrixRing(arr,4,3);


        /**
         * 1 2 4 7 5 3 6 8 10 11 9 12
         */
        printMatrixZigZag(arr);
    }
}
