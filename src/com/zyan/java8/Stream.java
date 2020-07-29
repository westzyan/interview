package com.zyan.java8;

import java.util.*;
import java.util.stream.Collectors;

public class Stream {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,9,9);

        Map<Integer, String> map = new HashMap<>();
        list.stream().filter(integer -> integer  >= 5).distinct().forEach(integer -> map.put(integer,integer + ""));
        for (Map.Entry<Integer, String> integerStringEntry : map.entrySet()) {
            System.out.println(integerStringEntry.getKey() + integerStringEntry.getValue());

        }
        Set<Integer> collect = list.stream().filter(integer -> integer >= 5).collect(Collectors.toSet());


    }
}
