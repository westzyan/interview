package com.zyan.sort;

import java.util.Scanner;




public class ListSort {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ListNode head = new ListNode(-1);
        ListNode p = head;
        for (int i = 0; i < n; i++) {
            ListNode temp = new ListNode(in.nextInt());
            p.next = temp;
            p = temp;
        }
        head = head.next;
//        while (head != null){
//            System.out.print(head.next == null?head.val : head.val + " ");
//            head = head.next;
//        }

        head = insertSort(head);

        while (head != null){
            System.out.print(head.next == null?head.val : head.val + " ");
            head = head.next;
        }




    }

    public static ListNode insertSort(ListNode head){
        if (head.next == null || head.next.next == null){
            return head;
        }
        ListNode pre = head; //pre 指向已经有序的节点
        ListNode cur = head.next;  //cur指向待排序的节点
        ListNode aux = new ListNode(-1); //辅助节点
        aux.next = head;
        while (cur != null){
            if (cur.val < pre.val){
                //先把cur节点从当前链表中删除，然后再把cur节点插入到合适位置
                pre.next = cur.next;
                //从前往后找到l2.val>cur.val,然后把cur节点插入到l1和l2之间
                ListNode l1 = aux;
                ListNode l2 = aux.next;
                while (cur.val > l2.val){
                    l1 = l2;
                    l2 = l2.next;
                }
                //把cur节点插入到l1和l2之间
                l1.next = cur;
                cur.next = l2;//插入合适位置

                cur = pre.next;  //指向下一个待处理的节点
            }else {
                pre = cur;
                cur = cur.next;
            }
        }
        return aux.next;
    }


    public static ListNode insertSort1(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode pre = head;  //有序
        ListNode cur = head.next;  //后面无序
        ListNode aux = new ListNode(-1);
        aux.next = head;

        while (cur != null){
            if (cur.val < pre.val){
                pre.next = cur.next;
                //从前往后找到l2.val>cur.val,然后把cur节点插入到l1和l2之间
                ListNode l1 = aux;
                ListNode l2 = aux.next;
                while (cur.val > l2.val){
                    l1 = l2;
                    l2 = l2.next;
                }
                l1.next = cur;
                cur.next = l2;
                cur = pre.next;
            }else {
                pre = cur;
                cur = cur.next;
            }
        }
        return aux.next;

    }

}

class ListNode {
    int val;
    ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }
}
