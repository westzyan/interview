package com.zyan.guanglianda;
import java.util.*;
/**
 * Created by zhangyan122 on 2020/7/29
 */
public class Test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int bit = in.nextInt();
        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1]-o1[1]);
        for (int i = 0; i < n; i++) {
            int tmp = in.nextInt();
            pq.add(new int[]{tmp,count(tmp,bit)});
        }

        Queue<int[]> nextPq = new LinkedList<>();
        nextPq.add(new int[]{0,0});
        int count = 0;
        while (!nextPq.isEmpty()) {
            pq.addAll(nextPq);
            nextPq.clear();
            int[] base = pq.poll();
            count++;
            while (!pq.isEmpty()) {
                int[] next = pq.poll();
                if ((base[0] & next[0]) != next[0]) {
                    nextPq.add(next);
                }
            }
        }
        System.out.println(count - 1);
    }

    private static int count(int tmp,int bit) {
        int ret = 0;
        while (bit-->0){
            ret+=tmp&1;
            tmp = tmp>>>1;
        }
        return ret;
    }
}
