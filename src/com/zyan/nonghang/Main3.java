package com.zyan.nonghang;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zhangyan
 * @date 2020/8/29 上午10:15
 */
public class Main3 {
    public  static String[] getPokerOrder(String[] cards) {
        String sort = "kshpq";
//        Arrays.sort(cards, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                int index1 = sort.indexOf(o1.charAt(0));
//                int index2 = sort.indexOf(o2.charAt(0));
//                if (index1 != index2) {
//                    return index1 - index2;
//                } else {
//                    return o1.charAt(1) - o2.charAt(1);
//                }
//            }
//        });


        int n = cards.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (sort(cards[i], cards[j]) > 0) {
                    String temp = cards[j];
                    cards[j] = cards[i];
                    cards[i] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(cards));

        return cards;
    }

    public static int sort(String o1, String o2) {
        String sort = "kshpq";
        int index1 = sort.indexOf(o1.charAt(0));
        int index2 = sort.indexOf(o2.charAt(0));
        if (index1 != index2) {
            return index1 - index2;
        } else {
            return o1.charAt(1) - o2.charAt(1);
        }
    }

    public static void main(String[] args) {
        String[] s = {"s1", "s3", "s9", "s4", "h1","p3","p2","q5","q4","q9","k2","k1"};
        getPokerOrder(s);
    }
}
