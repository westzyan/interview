package com.zyan.Tencent;

import java.util.*;

public class Main44 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = Integer.parseInt(in.nextLine());

        String[] operators = new String[N];
        for (int i = 0; i < N; i++) {
            operators[i] = in.nextLine();
        }
        Queue<Integer> queue = new LinkedList<>();

        for (String operator : operators) {
            if (operator.startsWith("add")) {
                String[] inputs = operator.split(" ");
                queue.offer(Integer.parseInt(inputs[1]));
            }
            if (operator.equals("poll")) {
                if (queue.isEmpty()) {
                    System.out.println(-1);
                } else {
                    queue.poll();
                }
            }
            if (operator.equals("peek")) {
                if (queue.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(queue.peek());
                }
            }
        }
    }
}
//            if (operator.equals("SIZE")){
//                System.out.println(queue.size());
//            }
//            if (operator.equals("CLEAR")){
//                queue.clear();
//            }
//        }


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