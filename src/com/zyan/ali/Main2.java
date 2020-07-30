package com.zyan.ali;

import java.util.Arrays;

public class Main2 {
    public String restoreString(String s, int[] indices) {
        char[] chars = s.toCharArray();
        char[] newChars = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            newChars[indices[i]] = chars[i];
        }
        return String.valueOf(newChars);
    }


    public int minFlips(String target) {
        char[] chars = target.toCharArray();
        int count = 0;
        int i = 0;
        for (i = 0; i < chars.length; i++) {
            if (chars[i] == '1') {
                break;
            }
        }
        int count0 = 0;
        for (int j = 0; j < chars.length; j++) {
            if (chars[j] == '0') {
                count0++;
            }
        }
        for (int j = i + 1; j < chars.length; j++) {
            if (chars[j] == '0') {
                count++;
            }
        }
        if (count == 0 && count0 == chars.length) {
            return 0;
        }
        return count * 2 + 1;
    }

    public int minFlips1(String target) {
        char[] chars = target.toCharArray();
        int count = 0;
        if (target.startsWith("0") && target.endsWith("1")) {
            int i = 0;
            for (i = 0; i < chars.length; i++) {
                if (chars[i] == '1') {
                    break;
                }
            }
            target = "";
            for (int j = i; j < chars.length; j++) {
                target = target + chars[j];
            }
            char[] newchars = target.toCharArray();
            if (target.startsWith("1") && target.endsWith("1")) {
                for (int k = 0; k < newchars.length; k++) {
                    if (k < newchars.length - 1 &&newchars[k] == '0' && newchars[k + 1] == '1') {
                        count++;
                    }
                }
                return count * 2 + 1;
            } else if (target.startsWith("1")){
                return 2;
            } else if (target.endsWith("1")){
                return 1;
            } else {
                return 0;
            }

        }  else if (target.startsWith("0") && target.endsWith("0")) {
            int i = 0;
            for (i = 0; i < chars.length; i++) {
                if (chars[i] == '1') {
                    break;
                }
            }
            target = "";
            for (int j = i; j < chars.length; j++) {
                target = target + chars[j];
            }
            char[] newchars = target.toCharArray();
            if (target.startsWith("1") && target.endsWith("1")) {
                for (int k = 0; k < newchars.length; k++) {
                    if (k < newchars.length - 1 &&newchars[k] == '0' && newchars[k + 1] == '1') {
                        count++;
                    }
                }
                return count * 2 + 1;
            } else if (target.startsWith("1")){
                return 2;
            } else if (target.endsWith("1")){
                return 1;
            } else {
                return 0;
            }
        }
        if (target.startsWith("1") && target.endsWith("1")) {
            for (int i = 0; i < chars.length; i++) {
                if (i < chars.length - 1 && chars[i] == '0' && chars[i + 1] == '1') {
                    count++;
                }
            }
            return count * 2 + 1;
        } else if (target.startsWith("1")){
            return 2;
        } else if (target.endsWith("1")){
            return 1;
        } else {
            return 0;
        }
    }


    public int minFlips2(String target) {
        int sign = 1;
        int count = 0;
        for (int i = 0;i<target.length();i++){
            if (target.charAt(i) -'0'== sign){
                count++;
                if (sign == 1){
                    sign = 0;
                }else {
                    sign =1;
                }
            }
        }
        return count;
    }



    public static void main(String[] args) {
        String s = "codeleet";
        int[] a = {4,5,6,7,0,2,1,3};
        System.out.println(new Main2().minFlips1("100111"));
    }
}
