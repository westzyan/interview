package com.zyan;

/**
 * @author zhangyan
 * @date 2020/9/6 下午2:23
 */
public class  ZHAO{
    public static void main(String[] args) {
        ZHAO zhao = new ZHAO();
        System.out.println(zhao.minStr("ABCDEFGHID", "DH"));
    }
    public static String minStr(String str1, String str2) {
        int[] str1Char = new int[128];
        int[] str2Char = new int[128];

        for (int i = 0; i < str2.length(); i++) {
            str2Char[str2.charAt(i)]++;
        }
        int count = 0;
        int left = 0;
        int right = 0;
        String result = "";
        int minLength = str1.length();
        while (right < str1.length()) {
            char ch = str1.charAt(right);
            str1Char[ch]++;
            if (str2Char[ch] > 0 && str2Char[ch] >= str1Char[ch]) {
                count++;
            }
            while (count == str2.length()) {
                ch = str1.charAt(left);
                if (str2Char[ch] > 0 && str2Char[ch] >= str1Char[ch]) {
                    count--;
                }
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    result = str1.substring(left + 1, left + minLength + 1);
                }
                right--;
                left++;
            }
            right++;
        }
        return result;
    }
}
