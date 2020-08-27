package com.zyan.huawei;
import java.util.Scanner;

/**
 * @author zhangyan
 * @date 2020/8/26 下午7:01
 */
public class Main8261 {
    public static void main(String[] args) {
        String zero = "00000000000000000000000000000000";
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        String[] strings = string.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            int num = Integer.parseInt(strings[i]);
            String numBin = Integer.toBinaryString(num);
            sb.append(zero, 0, 32 - numBin.length()).append(numBin);
        }
        for (int i = 0; i < sb.length() - 1; i = i + 2) {
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(i + 1));
            sb.setCharAt(i + 1, temp);
        }
        int length = sb.length();
        String res = sb.substring(length - 2, length) + sb.substring(0, length - 2);

        for (int i = 0; i < length - 31; i = i + 32) {
            long longNum = Long.parseLong(res.substring(i, i + 32), 2);
            System.out.print(longNum + " ");
        }
    }
}
