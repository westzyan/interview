package com.zyan.didi;

import java.util.*;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- >0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int k = scanner.nextInt();
            List<List<Integer>> tu = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                tu.add(i, new ArrayList<>());
            }
            while (m-- > 0) {
                int start = scanner.nextInt();
                int end = scanner.nextInt();
                int val = scanner.nextInt();
                if (val > k) {
                    continue;
                }
                List<Integer> node = tu.get(start);
                node.add(end);
            }
            if (check(tu, n)) {
                System.out.println("Yes");
            } else {
                System.out.println("N0");
            }
        }
    }

    public static boolean check(List<List<Integer>> tu, int n) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < tu.size(); i++) {
            List<Integer> tmp = tu.get(i);
            if (tmp.size() != 0) {
                set.add(i);
                set.addAll(tmp);
            }
        }
        return set.size() == n;
    }
}
