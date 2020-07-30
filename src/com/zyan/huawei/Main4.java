package com.zyan.huawei;

import java.sql.SQLOutput;
import java.util.*;

public class Main4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String names = in.nextLine();
        String[] name = names.split(",");

        if (name.length >= 100 || name.length == 0){
            System.out.println("error.0001");
            return;
        }
        for (int i = 0; i < name.length; i++) {
            if (!isValid(name[i])){
                System.out.println("error.0001");
                return;
            }
        }
        Map<String, Integer> map = new HashMap();
        for (int i = 0; i < name.length; i++) {
            if (map.containsKey(name[i])){
                map.put(name[i], map.get(name[i]) +1);

            }else {
                map.put(name[i], 0);
            }
        }
        int max = -1;
        for (Map.Entry<String, Integer> en :map.entrySet()) {
            if (en.getValue() > max){
                max = en.getValue();
            }

        }

        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<String, Integer> en :map.entrySet()) {
            if (en.getValue() == max){
                list.add(en.getKey());
            }
        }
        Collections.sort(list);

        System.out.println(list.get(0));

    }

    private static boolean isValid(String s) {
        if (s.length() == 0){
            return false;
        }
        if (s.charAt(0) > 'Z' || s.charAt(0) < 'A'){
            return false;
        }else {
            for (int i = 1; i < s.length() ; i++) {
                if (s.charAt(i) > 'z' || s.charAt(i) < 'a'){
                    return false;
                }
            }
        }
        return true;
    }
}
