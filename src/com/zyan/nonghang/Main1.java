package com.zyan.nonghang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhangyan
 * @date 2020/8/29 上午9:59
 */
public class Main1 {
    public static void main(String[] args) {
        Main1 main1 = new Main1();
    }
    public String triCoding(int num){
        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "@");
        map.put(1, "$");
        map.put(2, "&");
        List<Integer> list = new ArrayList<>();
        while(num != 0){//当输入的数不为0时循环执行求余和赋值
            int remainder = num % 3;
            num = num / 3;
            list.add(0, remainder);
        }
        System.out.println(list);
        StringBuilder res = new StringBuilder();
        for (Integer integer : list) {
            res.append(map.get(integer));
        }
        System.out.println(res.toString());
        return res.toString();
    }
}
