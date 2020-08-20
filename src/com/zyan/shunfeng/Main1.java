package com.zyan.shunfeng;

import java.util.*;

/**
 * Created by zhangyan122 on 2020/8/20
 */
public class Main1 {
    public static int[] visited = new int[1000];
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] w = new int[n];
//        int[] visited = new int[m];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        List<Custom> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
           int a = scanner.nextInt();
           int b = scanner.nextInt();
           Custom custom = new Custom(a, b);
           list.add(custom);
        }
        Arrays.sort(w);

        list.sort((o1, o2) -> {
            if (o1.bandwidth > o2.bandwidth) {
                return 1;
            } else if (o1.bandwidth < o2.bandwidth) {
                return -1;
            } else {
                return Integer.compare(o2.money, o1.money);
            }
        });
        System.out.println(list);
        long allMoney = 0;

        for (int i = 0; i < n; i++) {
            int money = find(list, w[i]);
            allMoney += money;
        }
        System.out.println(allMoney);
    }

    public static int find(List<Custom> list, int bandwidth) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).bandwidth == bandwidth && visited[i] == 0) {
                visited[i] = 1;
                return list.get(i).money;
            }
        }
        return 0;
    }
}

class Custom {
    int bandwidth;
    int money;
    public Custom(int bandwidth, int money) {
        this.bandwidth = bandwidth;
        this.money = money;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Custom.class.getSimpleName() + "[", "]")
                .add("bandwidth=" + bandwidth)
                .add("money=" + money)
                .toString();
    }
}
