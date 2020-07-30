package com.zyan.guanglianda;

import java.util.*;

/**
 * Created by zhangyan122 on 2020/7/29
 */
public class Main7 {
    public static Map<Integer,Integer> fun(int n, List<Integer> list) {
//        String[] schar = s.split(" ");
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> mapAdd = new HashMap<>();
        //记录重复数的个数
        int count = 0;
//        for (String c : schar) {
//            int tmp = c.charAt(0) - '0';
//            if (map.containsKey(tmp)) {
//                conut++;
//            }
//            map.put(tmp, map.getOrDefault(tmp, 0) + 1);
//        }
        for (Integer integer : list) {
            if (map.containsKey(integer)) {
                count++;
            }
            map.put(integer, map.getOrDefault(integer, 0) + 1);
        }

        while (count>0) {
            count = 0;
            for (Integer i : map.keySet()) {
                int val = map.get(i);
                if (val > 1) {
                    //新生成的数
                    int tmpkey = i<<1;
                    int tmpval = val>>1;
                    //将新生成的数加入到map
                    if (map.containsKey(tmpkey)){
                        tmpval+=map.get(tmpkey);
                        map.put(tmpkey,tmpval);
                    }else {
                        //原map中没有该key
                        mapAdd.put(tmpkey,tmpval);
                        //记录重复的数的个数
                        if (tmpval > 1) {
                            count++;
                        }
                    }
                    //处理剩下的余数
                    int yvshu = val%2;
                    map.put(i,yvshu);
                }
            }
            map.putAll(mapAdd);
            mapAdd.clear();
        }
        return map;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
//        in.nextLine();
//        String s = in.nextLine();
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            integers.add(in.nextInt());
        }
        Map<Integer,Integer> ans = fun(n,integers);
        List<Integer> list = new ArrayList<>();
        for(Integer i : ans.keySet()){
            if (ans.get(i) == 1) {
                list.add(0, ans.get(i));
            }
        }
        for (Integer integer : list) {
            System.out.println(integer + " ");
        }

    }

}
