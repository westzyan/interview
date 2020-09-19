package com.zyan.freewheel;

import java.lang.reflect.Array;
import java.util.*;

public class Main1 {

    public static void main(String[] args) {
        ArrayList<Integer> setA = new ArrayList<>();
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setA.add(3);
        setA.add(4);
        setA.add(4);
        setA.add(5);
        ArrayList<Integer> setB = new ArrayList<>();
        setB.add(1);
        setB.add(2);
        setB.add(4);
        setB.add(4);
        setB.add(7);
        setB.add(8);

        ArrayList<ArrayList<Integer>> arrayLists = sortMergeJoin(setA, setB);
        for (ArrayList<Integer> arrayList : arrayLists) {
            for (Integer integer : arrayList) {
                System.out.print(integer);
            }
            System.out.println();
        }


    }
    public ArrayList<ArrayList<Integer>> hashJoin (ArrayList<Integer> setA, ArrayList<Integer> setB) {
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (setA.size() > setB.size()) {
            for (int i = 0; i < setB.size(); i++) {
                if (map.containsKey(setB.get(i))) {
                    map.get(setB.get(i)).add(i);
                } else {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(i);
                    map.put(setB.get(i), list);
                }
            }
            for (int i = 0; i < setA.size(); i++) {
                if (map.containsKey(setA.get(i))) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    for (Integer integer : map.get(setA.get(i))) {
                        temp.add(integer);
                        temp.add(i);
                    }
                    ans.add(temp);
                }
            }
        } else {
            for (int i = 0; i < setA.size(); i++) {
                if (map.containsKey(setA.get(i))) {
                    map.get(setA.get(i)).add(i);
                } else {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(i);
                    map.put(setA.get(i), list);
                }
            }
            for (int i = 0; i < setB.size(); i++) {
                if (map.containsKey(setB.get(i))) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    for (Integer integer : map.get(setB.get(i))) {
                        temp.add(integer);
                        temp.add(i);
                    }
                    ans.add(temp);
                }
            }
        }
        return ans;
    }

    public static ArrayList<ArrayList<Integer>> sortMergeJoin (ArrayList<Integer> setA, ArrayList<Integer> setB) {
        ArrayList<Item> list = new ArrayList<>();
        for (int i = 0; i < setA.size(); i++) {
            Item item = new Item(setA.get(i), i, 'A');
            list.add(item);
        }
        for (int i = 0; i < setB.size(); i++) {
            Item item = new Item(setB.get(i), i, 'B');
            list.add(item);
        }
        list.sort((o1, o2) -> {
            if (o1.value == o2.value) {
                if (o1.origin == o2.origin) {
                    return o1.index - o2.index;
                } else {
                    return o1.origin - o2.origin;
                }
            } else {
                return o1.value - o2.value;
            }
        });
        System.out.println(list);

        int i = 0, j = 1;
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        while (i < list.size() && j < list.size()) {
            if (list.get(i).value == list.get(j).value && list.get(i).origin != list.get(j).origin) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(list.get(i).index);
                temp.add(list.get(j).index);
                ans.add(temp);
                System.out.println(temp);
                j++;
            } else {
                if (list.get(i).value == list.get(j).value && list.get(i).origin == list.get(j).origin) {
                    i++;
                    while (list.get(i).value == list.get(j).value && list.get(i).origin == list.get(j).origin) {
                        j++;
                    }
                } else {
                    i = j;
                    j++;
                }
            }
        }
        return ans;
    }

    static class Item {
        int value;
        int index;
        char origin;
        public Item(int value, int index, char origin) {
            this.value = value;
            this.index = index;
            this.origin = origin;
        }

        @Override
        public String toString() {
            return "Item{" +
                    " " + value +
                    " " + index +
                    " " + origin +
                    '}' + "\n";
        }
    }


    static Set<Integer> set = new HashSet<>();
    static int cost;
    public static int minCost(int n, int[] arr) {
        cost = 0;
        for (int i : arr) {
            set.add(i);
        }
        countCost(0, n);
        return cost;
    }

    private static void countCost(int  start, int end) {
        int mid = start + (end - start) / 2;
        int target = -1;
        int minDiff = Integer.MAX_VALUE;
        int curDiff = 0;
        for (Integer i : set) {
            if (i >= start &&  i <= end && (curDiff = Math.abs(i - mid)) < minDiff) {
                minDiff = curDiff;
                target = i;
            }
        }
        if (target != -1) {
            cost+=(end - start);
            set.remove(target);
            if (!set.isEmpty()) {
                countCost(start, mid);
                countCost(mid, end);
            }
        }
    }

}
