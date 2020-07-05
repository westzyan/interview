package com.zyan.leetcode;

public class SecondTime {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        int sum = 0;
        int carry = 0;
        while (l1 != null && l2 != null) {
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            sum = num1 + num2;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry == 1) {
            cur.next = new ListNode(1);
        }
        return head.next;
    }


    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        int ans1 = 0;
        if (s.length() == 1) {
            return 1;
        }
        int i = 0;
        while (i < s.length() - 1){
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    ans = Math.max(ans, j - i);
                    break;
                }
            }
            i++;
        }

        System.out.println(ans);
        return ans;
    }

    public int leng(String s) {
        int size, i = 0, j, k, max = 0;
        size = s.length();
        for (j = 0; j < size; j++) {
            for (k = i; k < j; k++)
                if (s.charAt(k) == s.charAt(j)) {
                    i = k + 1;
                    break;
                }
            if (j - i + 1 > max)
                max = j - i + 1;
        }
        System.out.println(max);
        return max;
    }


    public static void main(String[] args) {
        System.out.println(new Main().longestPalindrome11("aba"));;
    }

}
