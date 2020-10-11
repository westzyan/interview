package com.zyan.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhangyan
 * @date 2020/9/19 上午11:47
 */
public class LeetCode950 {

    public static int[] deckRevealedIncreasing(int[] deck) {
        if (deck == null || deck.length < 1) {
            return deck;
        }

        Arrays.sort(deck);// 得到升序排列的数组

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0;i < deck.length;i++) {// 倒着遍历，便是按降序访问
            queue.add(deck[i]);// 选最大值插入队列
            if (i == 0) {// 数组中所有元素均在队列中，退出过程
                break;
            }

            queue.add(queue.poll());// 将队头元素插入到队尾中
        }



        for (int i = deck.length - 1;i >= 0;i--) {
            deck[i] = queue.poll();// 倒回去，得到answer
        }

        return deck;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,5,1,7};
        System.out.println(Arrays.toString(deckRevealedIncreasing(nums)));
    }
}
