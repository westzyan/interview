package com.zyan.Tencent;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = Integer.parseInt(in.nextLine());

        ArrayList<String[]> list = new ArrayList<>();
        while (t > 0) {
            int q = Integer.parseInt(in.nextLine());
            String[] operators = new String[q];
            for (int i = 0; i < q; i++) {
                operators[i] = in.nextLine();
            }
            list.add(operators);
            t--;
        }
        in.close();
//        for (String[] strings : list) {
//            System.out.println(strings);
//        }

        for (String[] strings : list) {
//            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (String operator : strings) {
                if (operator.startsWith("PUSH")) {
                    String[] inputs = operator.split(" ");
                    queue.offer(Integer.parseInt(inputs[1]));
                }
                if (operator.equals("POP")) {
                    if (queue.isEmpty()) {
                        System.out.println(-1);
                    } else {
                        queue.poll();
                    }
                }
                if (operator.equals("TOP")) {
                    if (queue.isEmpty()) {
                        System.out.println(-1);
                    } else {
                        System.out.println(queue.peek());
                    }
                }
                if (operator.equals("SIZE")) {
                    System.out.println(queue.size());
                }
                if (operator.equals("CLEAR")) {
                    queue.clear();
                }
            }
        }

    }
}

//2
//7
//PUSH 1
//PUSH 2
//TOP
//POP
//TOP
//POP
//POP
//5
//PUSH 1
//PUSH 2
//SIZE
//POP
//SIZE

//3
//2
//POP
//PUSH 2
//TOP
//1
//POP
//2
//PUSH 1
//POP