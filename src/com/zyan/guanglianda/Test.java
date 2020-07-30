package com.zyan.guanglianda;

import java.util.*;

public  class Test  extends Parent{

    public static void main(String[] args) throws InterruptedException{

//        TreeMap<Integer, String> map = new TreeMap<>();
//        map.put(1,"4");
//        map.put(6,"4");
//        map.put(5,"4");
//        map.put(3,"4");
//        map.put(8,"4");
//        map.put(9,"4");
//        for (Integer integer : map.keySet()) {
//            System.out.println(integer);
//        }
//        Integer[] arr = {1, 2, 3};
//        List list = Arrays.asList(arr);
//        for (Map.Entry<Integer, String> integerStringEntry : map.entrySet()) {
//            System.out.println(integerStringEntry.getKey());
//        }
//        new Test().recu(0);
        System.out.println(1 | 2);
    }

    public void recu(int i) {
        System.out.println(i);
        recu(i + 1);
    }

//    public static int func(int i){
//        try {
////            int b = 5/0;
//            return i++;
//        }catch (Exception e){
//
//        }finally {
//            return i++;
//        }
//
//
//    }




}

//class Child extends Parent{
//    public int num = 1;
//}

//class T extends  Thread{
//    public void run(){
//        try {
//            sleep(5000);
//        } catch (InterruptedException e){
//            e.printStackTrace();
//        }
//    }
//}

class Parent{
    int num = 10;
}