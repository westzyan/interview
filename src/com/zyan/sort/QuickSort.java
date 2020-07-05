package com.zyan.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class QuickSort {

    public void quickSort(int[] s, int left, int right){
        if (left < right){
            int index = Partition(s, left, right);
            quickSort(s, left, index - 1);
            quickSort(s, index + 1, right);
        }
    }

    public int Partition(int[] s, int left, int right){
        int i = left, j = right;
        int x = s[left];
        while (i < j){
            //从右向左找小于x的数来填s[i]
            while (i < j && s[j] >= x){
                j--;
            }
            if (i < j){
                s[i] = s[j]; //将s[j]填到s[i]中，s[j]就形成了一个新的坑
                i++;
            }

            // 从左向右找大于或等于x的数来填s[j]
            while(i < j && s[i] < x){
                i++;
            }
            if(i < j) {
                s[j] = s[i]; //将s[i]填到s[j]中，s[i]就形成了一个新的坑
                j--;
            }
        }
        //退出时，i等于j。将x填到这个坑中。
        s[i] = x;
        return i;
    }






    public static void main(String[] args) {
//        int[] a = new int[]{4,4,3,5,3,3,5,7,8,2,4,325,345,3245,2345,4};
//        new QuickSort().quickSort(a, 0, a.length -1);
//        System.out.println(Arrays.toString(a));

        new QuickSort().topK("zhang n dfas zhang yan yan yan dfas dfs fdsadf dfsf");
    }



    public void topK(String words){
        String[] word = words.split(" ");
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String s : word) {
            if (hashMap.containsKey(s)){
                hashMap.put(s, hashMap.get(s) + 1);
            } else {
                hashMap.put(s, 1);
            }
        }
        StringCount[] stringCounts = new StringCount[hashMap.size()];
        Iterator iterator = hashMap.entrySet().iterator();
        int count = 0;
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            stringCounts[count] = new StringCount((String)entry.getKey(), (int)entry.getValue());
            count++;
        }
//        for (Map.Entry<String, Integer> stringIntegerEntry : hashMap.entrySet()) {
//
//        }
        System.out.println(Arrays.toString(stringCounts));
        quickSort111(stringCounts, 0, stringCounts.length - 1);
        System.out.println(Arrays.toString(stringCounts));

    }

    public void quickSort111(StringCount[] s, int left, int right){
        if (left < right){
            int index = partition(s, left, right);
            quickSort111(s, left, index - 1);
            quickSort111(s, index + 1, right);
        }


    }

    private int partition(StringCount[] s, int left, int right) {
        int i = left, j = right;
        int x = s[left].getCount();
        String word = s[left].getString();
        while (i < j){
//            while (i < j && s[j].getCount() >= x){
            while (i < j && s[j].getCount() <= x){
                j--;

            }
            if (i < j){
                s[i] = s[j];
                i++;
            }
//            while (i < j && s[i].getCount() <= x){
            while (i < j && s[i].getCount() >= x){
                i++;
            }
            if (i < j){
                s[j] = s[i];
                j--;
            }

        }
        s[i] = new StringCount(word, x);
        return i;
    }


}

class StringCount{
    private String string;
    private int count;
    public StringCount(String string, int count){
        this.string = string;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return "StringCount{" +
                "string='" + string + '\'' +
                ", count=" + count +
                '}';
    }
}
