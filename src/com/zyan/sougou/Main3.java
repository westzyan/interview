package com.zyan.sougou;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main3 {
    public static void main(String[] args) {
        System.out.println(new Main3().getPasswordCount("3"));
    }

    long ans = 0;
    public long getPasswordCount (String password) {
        for (int i = 0; i < 10; i++) {
            String str = "";
            backtrack(str + i,  1, password);
        }
        return ans;
    }

    public void backtrack(String str,  int index, String password) {
        if (index == password.length()) {
            if (!(str.equals(password))) {
                ans++;
            }
            return;
        }
        int num = Integer.parseInt(String.valueOf(password.charAt(index))) + Integer.parseInt(String.valueOf(str.charAt(str.length() - 1)));
        if (num % 2 == 0) {
            backtrack(str + (num / 2),  index + 1, password);
        } else {
            backtrack(str + (num / 2),  index + 1, password);
            backtrack(str + (num / 2 + 1)  ,  index + 1, password);
        }
    }
}
