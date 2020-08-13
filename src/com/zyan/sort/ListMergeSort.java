package com.zyan.sort;

public class ListMergeSort {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;  // 分割
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        if (left != null) {
            h.next = left;
        } else {
            h.next = right;
        }
        return res.next;

    }


    public static ListNode sortList1(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = dummy.next;
        int length = 0;
        while (p != null) {
            length++;
            p = p.next;
        }
        //循环切割合并
        for (int size = 1; size < length; size <<= 1) {
            ListNode cur = dummy.next;
            ListNode tail = dummy;
            while (cur != null) {
                ListNode left = cur;
                ListNode right = cut(cur, size); // 链表切掉size剩下的返还给right
                cur = cut(right, size); // 链表切掉size剩下的返还给cur;
                tail.next  = merge(left ,right);
                while ( tail.next != null) {
                    tail = tail.next;
                }
            }
        }

        return dummy.next;
    }

    private static ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                p.next = left;
                left = left.next;
            } else {
                p.next = right;
                right = right.next;
            }
            p = p.next;
        }
        if (left == null) {
            p.next = right;
        } else {
            p.next = left;
        }
        return dummy.next;
    }

    private static ListNode cut(ListNode head, int size) {
        if (size < 0) {
            return head;
        }
        ListNode p = head;
        //向前走size-1步
        size--;
        while (size > 0 && p != null) {
            size--;
            p = p.next;
        }
        if (p == null) {
            return null;
        }
        ListNode next = p.next;
        p.next = null;
        return next;
    }
}
