package com.zyan.leetcode;

import java.util.*;

public class LeetCode84 {


    /**
     * 84. 柱状图中最大的矩形
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     *
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) throws Exception {

        if (heights.length == 2) {
            throw new Exception("666");
        }
        return 0;
    }


    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                list.add(temp.val);
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
            ans.add(0, list);
        }
        return ans;
    }


    /**
     * 有序链表转换为二叉搜索树
     * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
     *
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     *
     * 示例:
     *
     * 给定的有序链表： [-10, -3, 0, 5, 9],
     *
     * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
     *
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     *
     *
     *  想法
     *
     * 题目中最重要的要求是需要利用链表中的节点，构建一颗高度平衡的二叉搜索树，好消息是链表中的元素是升序的。
     *
     * 众所周知，一棵二叉搜索树是一棵有根二叉树并且对于所有节点满足特殊的性质：对于树中任意一个点，它的权值必然 \geq≥ 所有左子树节点的权值，\leq≤ 所有右子树节点的权值。因为二叉树具有递归的子结构，二叉搜索树也同理：所有子树也是二叉搜索树。
     *
     * 当前方法和下一个方法的主要思路是：
     *
     * 给定列表中的中间元素将会作为二叉搜索树的根，该点左侧的所有元素递归的去构造左子树，同理右侧的元素构造右子树。这必然能够保证最后构造出的二叉搜索树是平衡的。
     *
     * 算法
     *
     * 由于我们得到的是一个有序链表而不是数组，我们不能直接使用下标来访问元素。我们需要知道链表中的中间元素。
     * 我们可以利用两个指针来访问链表中的中间元素。假设我们有两个指针 slow_ptr 和 fast_ptr。slow_ptr 每次向后移动一个节点而 fast_ptr 每次移动两个节点。当 fast_ptr 到链表的末尾时 slow_ptr 就访问到链表的中间元素。对于一个偶数长度的数组，中间两个元素都可用来作二叉搜索树的根。
     * 当找到链表中的中间元素后，我们将链表从中间元素的左侧断开，做法是使用一个 prev_ptr 的指针记录 slow_ptr 之前的元素，也就是满足 prev_ptr.next = slow_ptr。断开左侧部分就是让 prev_ptr.next = None。
     * 我们只需要将链表的头指针传递给转换函数，进行高度平衡二叉搜索树的转换。所以递归调用的时候，左半部分我们传递原始的头指针；右半部分传递 slow_ptr.next 作为头指针。
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode mid = findMiddleElement(head);
        TreeNode node = new TreeNode(mid.val);
        if (head == mid) {
            return node;
        }
        node.left = sortedListToBST(head);
        node.right = sortedListToBST(mid.next);
        return node;
    }

    private ListNode findMiddleElement(ListNode head) {
        ListNode prevPtr = null;
        ListNode slowPtr = head;
        ListNode fastPtr = head;

        while (fastPtr != null && fastPtr.next != null) {
            prevPtr = slowPtr;
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        if (prevPtr != null) {
            prevPtr.next = null;
        }
        return slowPtr;
    }


    /**
     * 二叉树的最小深度
     * 给定一个二叉树，找出其最小深度。
     *
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例:
     *
     * 给定二叉树 [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最小深度  2.
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helpMinDepth(root);
    }

    private int helpMinDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root.left == null || root.right == null) {
            return helpMinDepth(root.left) + helpMinDepth(root.right) + 1;
        }
        return 1 + Math.min(helpMinDepth(root.left), helpMinDepth(root.right));
    }


    /**
     * 113. 路径总和 II
     * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例:
     * 给定如下二叉树，以及目标和 sum = 22，
     *
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \    / \
     *         7    2  5   1
     * 返回:
     *
     * [
     *    [5,4,11,2],
     *    [5,8,4,5]
     * ]
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        Deque<Integer> path = new LinkedList<>();
        traceback(root, sum, path, ans);
        return ans;
    }

    private void traceback(TreeNode root, int sum, Deque<Integer> path, List<List<Integer>> ans) {
        if (root == null) {
            return;
        }
        sum = sum - root.val;
        path.add(root.val);
        if (root.left == null && root.right == null) {
            if (sum == 0) {
                ans.add(new ArrayList<>(path));
                return;
            }
        }
        if (root.left != null) {
            traceback(root.left, sum, path, ans);
            path.removeLast();
        }
        if (root.right != null) {
            traceback(root.right, sum, path, ans);
            path.removeLast();
        }
    }


    /**
     * 114. 二叉树展开为链表
     * 给定一个二叉树，原地将它展开为一个单链表。
     *
     *
     *
     * 例如，给定二叉树
     *
     *     1
     *    / \
     *   2   5
     *  / \   \
     * 3   4   6
     * 将其展开为：
     *
     * 1
     *  \
     *   2
     *    \
     *     3
     *      \
     *       4
     *        \
     *         5
     *          \
     *           6
     * @param root
     */
    public void flatten(TreeNode root) {
        while (root != null) {
            //左子树为 null，直接考虑下一个节点
            if (root.left == null) {
                root = root.right;
            } else {
                //找左子树最右边的节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                //将原来的右子树接到左子树的最右边节点
                pre.right = root.left;
                root.left = null;
                //考虑下一个节点
                root = root.right;
            }
        }
    }

    /**
     * 尝试将节点先序遍历存到list，然后重新构造
     * @param root
     */
    public void flatten1(TreeNode root) {
        if(root == null){
            return ;
        }
        //将根节点的左子树变成链表
        flatten(root.left);
        //将根节点的右子树变成链表
        flatten(root.right);
        TreeNode temp = root.right;
        //把树的右边换成左边的链表
        root.right = root.left;
        //记得要将左边置空
        root.left = null;
        //找到树的最右边的节点
        while(root.right != null) {
            root = root.right;
        }
        //把右边的链表接到刚才树的最右边的节点
        root.right = temp;
    }

    private void preOrder(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        list.add(root);
        if (root.left != null) {
            preOrder(root.left, list);
        }
        if (root.right != null) {
            preOrder(root.right, list);
        }
    }

    /**
     *
     * 115. 不同的子序列
     * 给定一个字符串 S 和一个字符串 T，计算在 S 的子序列中 T 出现的个数。
     *
     * 一个字符串的一个子序列是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
     *
     * 题目数据保证答案符合 32 位带符号整数范围。
     *
     *
     *
     * 示例 1：
     *
     * 输入：S = "rabbbit", T = "rabbit"
     * 输出：3
     * 解释：
     *
     * 如下图所示, 有 3 种可以从 S 中得到 "rabbit" 的方案。
     * (上箭头符号 ^ 表示选取的字母)
     *
     * rabbbit
     * ^^^^ ^^
     * rabbbit
     * ^^ ^^^^
     * rabbbit
     * ^^^ ^^^
     * 思路:
     * 动态规划
     *
     * dp[i][j] 代表 T 前 i 字符串可以由 S j 字符串组成最多个数.
     *
     * 所以动态方程:
     *
     * 当 S[j] == T[i] , dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
     *
     * 当 S[j] != T[i] , dp[i][j] = dp[i][j-1]
     *
     * 举个例子,如示例的
     *
     *
     *
     * 对于第一行, T 为空,因为空集是所有字符串子集, 所以我们第一行都是 1
     *
     * 对于第一列, S 为空,这样组成 T 个数当然为 0` 了
     *
     * 至于下面如何进行,大家可以通过动态方程,自行模拟一下!
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int[][] dp = new int[t.length() + 1][s.length() + 1];
        for (int j = 0; j < s.length() + 1; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < t.length() + 1; i++) {
            for (int j = 1; j < s.length() + 1; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[t.length()][s.length()];
    }

    public int numDistinct1(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i < s.length() + 1; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < t.length() + 1; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[s.length()][t.length()];
    }





    public static void main(String[] args) throws Exception {
//        int[] a = {6,3};
//        System.out.println(new LeetCode84().largestRectangleArea(a));

        long a = 156164;
        long b = 156164L;

        System.out.println(true);
    }


}

