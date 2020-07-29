package com.zyan.leetcode;

import java.util.*;

public class ByteDance {

    /**
     * 对一个奇数位升序，偶数位降序的链表，进行排序，例如 1->100->20->80->40->30
     *
     * @param head
     * @return
     */
    public static ListNode[] sortEvenOdd(ListNode head) {
        ListNode head1 = null;
        ListNode head2 = null;
        ListNode cur1 = null;
        ListNode cur2 = null;
        int count = 1;
        while (head != null) {
            if (count % 2 == 1) {
                if (cur1 != null) {
                    cur1.next = head;
                    cur1 = cur1.next;
                } else {
                    cur1 = head;
                    head1 = cur1;
                }
            } else {
                if (cur2 != null) {
                    cur2.next = head;
                    cur2 = cur2.next;
                } else {
                    cur2 = head;
                    head2 = cur2;
                }
            }
            head = head.next;
            count++;
        }
        cur1.next = null;
        cur2.next = null;
        return new ListNode[]{head1, head2};
    }

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public void listEvenSort() {
        Scanner scanner = new Scanner(System.in);
        ListNode head = new ListNode(1);
        ListNode cur = head;
        for (int i = 0; i < 8; i++) {
            cur.next = new ListNode(scanner.nextInt());
            cur = cur.next;
        }
        head = head.next;

        ListNode[] ans = sortEvenOdd(head);
        ListNode even = ans[0];
        ListNode odd = ans[1];

        odd = reverse(odd);

        ListNode preHead = new ListNode(0);
        ListNode prev = preHead;
        while (even != null && odd != null) {
            if (even.val < odd.val) {
                prev.next = even;
                even = even.next;
            } else {
                prev.next = odd;
                odd = odd.next;
            }
            prev = prev.next;
        }
        prev.next = (even == null ? odd : even);
        head = preHead.next;

        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

//    public int minPalindrome(int num) {
//        if (num < 0) {
//            return 0;
//        }
//        char[] nums = String.valueOf(num).toCharArray();
//        int mid = nums.length / 2;
//        if (nums.length % 2 == 1) {
//            if (mid > 0 && nums[mid - 1] < nums[mid + 1]) {
//                swap(nums, mid - 1, mid + 1);
//            }
//        }
//    }

    public boolean hasRingList(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        if (head.next == head) {
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public ListNode reverseKList(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode end = dummy;
        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }

            ListNode start = pre.next;
            ListNode next = end.next;

            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    public static float mySqrt(int n) {
        float left = 0, right = n;
        float mid = left + (right - left) / 2;
        while (right - left > 1e-7) {
            mid = left + (right - left) / 2;
            if (mid * mid < n) {
                left = mid;
            } else if (mid * mid > n) {
                right = mid;
            } else {
                return mid;
            }
        }
        return mid;
    }

    /**
     * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
     * 最大数
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
// Get input integers as strings.
        String[] asStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            asStrs[i] = String.valueOf(nums[i]);
        }

        // Sort strings according to custom comparator.
        Arrays.sort(asStrs, (o1, o2) -> {
            String order1 = o1 + o2;
            String order2 = o2 + o1;
            return order2.compareTo(order1);
        });

        // If, after being sorted, the largest number is `0`, the entire number
        // is zero.
        if (asStrs[0].equals("0")) {
            return "0";
        }

        // Build largest number from sorted array.
        String largestNumberStr = "";
        for (String numAsStr : asStrs) {
            largestNumberStr += numAsStr;
        }

        return largestNumberStr;
    }




    /**
     * 长度最小的子数组
     * 给定一个含有 n 个正整数的数组和一个正整数 s ，
     * 找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum+=nums[j];
                if (sum >= s) {
                    ans = Math.min(ans, j - i + 1);
                    break;

                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }


    /**
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen11(int s, int[] nums) {
        int n  = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= s){
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static int RectCover(int target) {
        if (target <= 2) {
            return target;
        }
        int[] dp = new int[target + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= target; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[target];
    }
    int ans = 0;
    public int sumShaizi(int n) {
        dfs(n);
        return ans;
    }

    private void dfs(int n) {
        if (n == 0) {
            ans++;
            return;
        }
        for (int i = 1; i < 7; i++) {
            if (n - i < 0) {
                break;
            }
            dfs(n - i);
        }
    }

    /**
     * 数组中两个数的最大异或值
     * 给定一个非空数组，数组中元素为 a0, a1, a2, … , an-1，其中 0 ≤ ai < 231 。
     * 找到 ai 和aj 最大的异或 (XOR) 运算结果，其中0 ≤ i,  j < n 。
     * 你能在O(n)的时间解决这个问题吗？
     * @param nums
     * @return
     */
    public int findMaximumXOR(int[] nums) {
        int res = 0;
        int mask = 0;
        for (int i = 30; i >= 0; i--) {
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num & mask);
            }

            int temp = res | (1 << i);
            for (Integer integer : set) {
                if (set.contains(integer ^ temp)) {
                    res = temp;
                    break;
                }
            }
        }
        return res;
    }



    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        ListNode head = new ListNode(1);
//        ListNode cur = head;
//        for (int i = 0; i < 8; i++) {
//            cur.next = new ListNode(scanner.nextInt());
//            cur = cur.next;
//        }
//        head = head.next;


//        System.out.println(new ByteDance().sumShaizi(5));
        Integer a = 10;

        System.out.println(3 | 2);
    }
}
