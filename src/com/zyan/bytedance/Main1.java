package com.zyan.bytedance;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zhangyan
 * @date 2020/9/20 下午7:06
 */
public class Main1 {
/*    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();

        int len = string.length();
        int[] next = new int[len];
        int i = 0;
        int j = 0;
        for (i = 1; i < len; i++) {
            while (j != 0 && string.charAt(i) != string.charAt(j)) {
                j = next[j - 1];
            }
            if (string.charAt(i) == string.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        int l = len - next[len - 1];
        System.out.println(Arrays.toString(next));
        if (len % l != 0) {
            System.out.println(string);
        } else {
            System.out.println(string.substring(0, l));
        }

    }*/


    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String ar="";
        StringBuilder br= new StringBuilder();
        String str=sc.nextLine();
        int i=0;
        //不断用同一个相同的字串加自身，如果最后与输入字符串相等，则输出i+1
        for (i = 0; i < str.length(); i++) {
            ar+=str.charAt(i)+"";
            br = new StringBuilder(ar);
            while (br.length()<str.length()) {//只要没有加满输入字符串长度就继续加
                br.append(ar);
            }
            if (br.toString().equals(str)) {
                break;
            }
        }
        System.out.println(i+1);
    }

}
