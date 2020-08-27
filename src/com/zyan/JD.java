package com.zyan;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyan
 * @date 2020/8/27 下午7:05
 */
public class JD {
    public static void main(String[] args) {
//        List<Integer> list  = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//
////        for (Integer integer : list) {
////            list.remove(integer);
////        }
//        list.forEach(list::remove);
//        for (Integer integer : list) {
//            System.out.println(integer);
//        }

//        for (int i = 1; i <= 4; i++) {
//            for (int j = i; j <= 4; j++) {
//                System.out.println(1);
//            }
//        }
        int a = 2, b = 3, c = 4, d = 5;
        float k = 4.3f;
        System.out.println(--b* a + c* d--);
    }

    /**
     * Return whether the given throwable is a checked exception:
     * that is, neither a RuntimeException nor an Error.
     * @param ex the throwable to check
     * @return whether the throwable is a checked exception
     * @see java.lang.Exception
     * @see java.lang.RuntimeException
     * @see java.lang.Error
     */
    public static boolean isCheckedException(Throwable ex) {
        return !(ex instanceof RuntimeException || ex instanceof Error);
    }
}
