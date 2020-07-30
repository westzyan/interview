package com.zyan.guanglianda;

import java.util.*;

public class Main5 {
    public static void main(String[] args) {
        new Main5().printList();
    }
    public void printList() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(scanner.nextInt());
        }
        scanner.close();
        while (!dum(list)) {
            int minIndex = indexOfMin(list);
            int minValue = list.get(minIndex);
            list.remove(minIndex);
            list.set(minIndex, minValue * 2);
        }
        System.out.println(list);
    }

    private int indexOfMin(List<Integer> list) {
        int index = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < min && i + 1 < list.size() && list.get(i).equals(list.get(i + 1))) {
                min = list.get(i);
                index = i;
            }
        }
        return index;
    }

    private boolean dum(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).equals(list.get(i + 1))) {
                return false;
            }
        }
        return true;
    }
}