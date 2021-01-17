package com.zyan.jiaotong;
import java.util.*;
/**
 * @author zhangyan
 * @date 2020/9/27 下午4:02
 */
public class Main {
    static int N = 100;
    static int[][] graph = new int[N][N];
    static int[] color = new int[N];
    static int vNum, eNum;
    static boolean isDAG = true;
    public int graphCircleChecker(String graph_string) {
        if (!graph_string.startsWith("{") || !graph_string.endsWith("}")) {
            System.out.println(-1);
            return -1;
        }
        graph_string = graph_string.substring(1, graph_string.length() - 1);
        System.out.println(graph_string);
        String[] edges = graph_string.split(",");

        Set<Integer> set = new HashSet<>();
        for (String edge : edges) {
            if (!edge.startsWith("(") || !edge.endsWith(")") || !edge.contains("->") || edge.length() != 6) {
                return -1;
            }
            char from = edge.charAt(1);
            char to = edge.charAt(edge.length() - 2);
            set.add(from - 'A');
            set.add(to - 'A');
            graph[from - 'A' + 1][to - 'A' + 1] = 1;
        }
        vNum = set.size();
        eNum = edges.length;
        for (int[] ints : graph) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        for (int i = 1; i <= vNum ; i++) {
            if (color[i] == -1) {
                continue;
            }
            DFS(i);
            if (!isDAG) {
                return 1;
            }
        }
        return 0;
    }

    public void DFS(int i) {
        color[i] = 1;
        for (int j = 1; j <=vNum; j++) {
            if (graph[i][j] != 0) {
                if (color[j] == 1) {
                    isDAG = false;
                    break;
                } else if (color[j] == -1) {
                    continue;
                } else {
                    DFS(j);
                }

            }
        }
        color[i] = -1;
    }

    public static void main(String[] args) {
        String s = "{(A->B),(B->C),(C->A)}";
        int i = new Main().graphCircleChecker(s);
        System.out.println(i);
    }
}
