package com.zyan.mianshi;


public class Main {
    public static void main(String[] args) {
        System.out.println('a'&'c');
//        System.out.printf();
    }
    public ListNode reverseList(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode pre, cur, end;
        pre = null;
        cur = head;
        end = head.next;
        while (cur != null){
            cur.next = pre;
            pre = cur;
            cur = end;
            if (end != null){
                end = end.next;
            }
        }

        return pre;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
