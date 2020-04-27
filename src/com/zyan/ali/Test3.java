package com.zyan.ali;

import java.util.*;

public class Test3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] inputs = input.split(",");
        int n = inputs.length;
        int[] number = new int[n];
        for (int i = 0; i < n; i++) {
            number[i] = Integer.parseInt(inputs[i]);
        }

        int[] ans = new int[2];
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            if (set.contains(number[i])){
                ans[0] = number[i];
            }else {
                set.add(number[i]);
            }
        }
//        LinkedHashMap<Integer, Integer> a = new LinkedHashMap<>();
//        Iterator<Map.Entry<Integer, Integer>> b = a.entrySet().iterator();

        List<Integer> list = new ArrayList<Integer>(set); //

        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i + 1) - list.get(i) > 1){
                ans[1] = list.get(i) +1;
            }

        }

        System.out.println(Arrays.toString(ans));

    }
}
