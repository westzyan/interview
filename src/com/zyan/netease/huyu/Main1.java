package com.zyan.netease.huyu;

import java.util.*;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        scanner.nextLine();
        while (T-- > 0) {
            String str = scanner.nextLine();
            String[] pai = str.split(" ");
            List<Data> dataList = new ArrayList<>();
            for (String s : pai) {
                Data data = new Data(s.charAt(0) - '0', s.charAt(1));
                dataList.add(data);
            }
            Collections.sort(dataList, new Comparator<Data>() {
                @Override
                public int compare(Data o1, Data o2) {
                    if (o1.WBT < o2.WBT) {
                        return 1;
                    } else if (o1.WBT > o2.WBT) {
                        return -1;
                    } else if (o1.num > o2.num) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            });
            System.out.println(dataList);
        }
    }


}
class Data {
    int num;
    char WBT;

    public Data(int num, char WBT) {
        this.num = num;
        this.WBT = WBT;
    }

    @Override
    public String toString() {
        return num + "" + WBT;
    }
}