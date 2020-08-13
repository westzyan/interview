package com.zyan.sort;

public class ListQuickSort {

    public static ListNode quickSortList(ListNode head){
        if (null == head || null == head.next){
            return head;
        }
        ListNode preHead = new ListNode(-1);
        quickSortList(preHead, head, null);
        return preHead.next;
    }

    private static void quickSortList(ListNode preHead, ListNode head, ListNode tail){
        if (head != tail && head.next != tail){
            ListNode mid = partition(preHead, head, tail);
            quickSortList(preHead, preHead.next, mid);
            quickSortList(mid, mid.next, tail);
        }
    }

    private static ListNode partition(ListNode lowPre, ListNode low, ListNode high){
        int key = low.val;
        ListNode head1 = new ListNode(-1);
        ListNode head2 = new ListNode(-1);
        ListNode l = head1;
        ListNode h = head2;
        for (ListNode node = low.next; node != high; node = node.next){
            if (node.val <= key){
                l.next = node;
                l = node;
            }else {
                h.next = node;
                h = node;
            }
        }
        h.next = high;
        l.next = low;
        low.next = head2.next;
        lowPre.next = head1.next;
        return low;
    }
}
