package com.zyan.Tencent;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Main4 {
    Stack<Object>stack1 = new Stack<Object>();
    Stack<Object>stack2 = new Stack<Object>();
    int peek;
    public void add(Object object) {
        stack1.push(object);
    }
    public Object poll() {
        Object object = null;
        if(stack2.size()==0) {
            while(stack1.size()>1) {
                stack2.push(stack1.pop());
            }
            if(stack1.size()==1) {
                object = stack1.pop();
            }
        }else {
            object = stack2.pop();
        }
        return object;
    }
    public int length() {
        return stack1.size()+stack2.size();
    }
    public Object peek() {
        if(stack2.size()==0) {
            while(stack1.size()>=1) {
                stack2.push(stack1.pop());
            }
            return stack2.peek();
        }else {
            return stack2.peek();
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();
        in.nextLine();
        LinkedList<String> doQueue = new LinkedList<>();
        for (int i = 0;i< times;i++){
            doQueue.add(in.nextLine());
        }
        in.close();
        Main4 stack2Queue = new Main4();
        while (!doQueue.isEmpty()){
            String tmp = doQueue.pollFirst();
            if (tmp.charAt(0) == 'a'){
                String[] makeIt = tmp.split(" ");
                stack2Queue.add(makeIt[1]);
            }else if (tmp.equals("poll")){
                stack2Queue.poll();
            }else if (tmp.equals("peek")){
                System.out.println(stack2Queue.peek());
            }

        }
    }
}
