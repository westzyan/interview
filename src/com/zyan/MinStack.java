package com.zyan;

import java.util.Stack;

public class MinStack {
    Stack<Integer> data = new Stack<Integer>();
    Stack<Integer> min = new Stack<Integer>();
    public void push(int node) {
        data.push(node);
        if (min.size() == 0){
            min.push(node);
        }else {
            if (node <= min.peek()){
                min.push(node);
            }else {
                min.push(min.peek());
            }
        }
    }

    public void pop() {
        data.pop();
        min.pop();
    }

    public int top() {
        return data.peek();
    }

    public int min() {
        return min.peek();
    }

}
