package com.zyan.yuanfudao;

import java.util.*;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = 0;
//        Clazz[] clazzes = new Clazz[n];
        List<Clazz> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Clazz clazz = new Clazz();
            clazz.setStart(scanner.nextInt());
            clazz.setEnd(scanner.nextInt());
            list.add(clazz);
        }
        Collections.sort(list, new Comparator<Clazz>() {
            @Override
            public int compare(Clazz o1, Clazz o2) {
                return o1.getStart() - o2.getStart();
            }
        });

        for (int i = 0; i < list.size(); i++) {

        }
    }
}

class Clazz {
    int start;
    int end;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }


    public void setEnd(int end) {
        this.end = end;
    }

    public Clazz(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public Clazz() {
    }
}
