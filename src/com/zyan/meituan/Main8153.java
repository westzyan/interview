package com.zyan.meituan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main8153 {

    private  static int[] visited;
    private  static int count = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] gra = new int[n][n];
        for (int i = 0; i < n; i++) {
            gra[i][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt() - 1;
            int b = scanner.nextInt() - 1;
            gra[a][b] = 1;
            gra[b][a] = 1;
        }
        visited = new int[gra.length];
        new Main8153().Graph_DFS(gra);
        System.out.println(count);


    }


    public void Graph_DFS(int[][] graph) {
        //初始化visited数组，表示该无向图的所有节点都没有查找过
        for (int i = 0; i < graph.length; i++) {
            visited[i] = 0;
        }
        //图的遍历
        for (int j = 0; j < graph.length; j++) {
            if (visited[j] == 0) {
                //每次执行以下2行代码，表示多出一个连通图
                count++;
                DFS_visit(graph, j);
            }
        }
    }

    public void DFS_visit(int[][] array, int n) {
        //节点n已查找
        visited[n] = 1;
        //从n出发查找与n相连的节点
        for (int i = n; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[n][j] == 1) {
                    if (visited[j] == 0) {
                        DFS_visit(array, j);
                    }
                }
            }
        }
        visited[n] = 2;
        //以上两次打印visited数组是为了显示遍历过程，当某一次打印的数组只有2或0时，表示这里有一个已经查找完毕的连通图
    }
}
