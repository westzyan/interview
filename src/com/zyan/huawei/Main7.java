package com.zyan.huawei;

public class Main7 {
    public static void main(String[] args) {
        String input = "5a 12 5b ba 34 5b bb 88 05 5a 75 cd bb 62 5a 34 cd 78 cc da fb 06 5a";
        input = input.substring(input.indexOf("5a"), input.lastIndexOf("5a") + 2);
        System.out.println(input);
    }
}
