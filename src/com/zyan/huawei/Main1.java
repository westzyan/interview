package com.zyan.huawei;

import java.util.*;

public class Main1 {
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

        HashMap<String, Integer> map = new HashMap();

        for (String s : name) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);

            } else {
                map.put(s, 0);
            }
        }
        List list = new ArrayList<>();
        for (Map.Entry<String, Integer> i: map.entrySet()) {
            list.add(new NameAndNum(i.getKey(),i.getValue()));
        }

        Collections.sort(list, new Comparator<NameAndNum>() {

            @Override
            public int compare(NameAndNum o1, NameAndNum o2) {
                if (o1.number > o2.number){
                    return 1;
                }else if (o1.number < o2.number){
                    return 0;
                }else {
                    if (o1.name.compareTo(o2.name) >= 0){
                        return 1;
                    }else {
                        return 0;
                    }
                }
            }
        });
        NameAndNum a = (NameAndNum) list.get(0);
        System.out.println(a.name);

//        String a = "Tom";
//        System.out.println(isValid(a));


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
class NameAndNum implements Comparator<NameAndNum> {
    String name;
    Integer number;
    public NameAndNum(String name, Integer number){
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public int compare(NameAndNum o1, NameAndNum o2) {
        if (o1.number > o2.number){
            return 1;
        }else if (o1.number < o2.number){
            return -1;
        }else {
            if (o1.name.compareTo(o2.name) > 0){
                return 1;
            }else {
                return -1;
            }
        }
    }
}
