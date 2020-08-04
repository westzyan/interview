package com.zyan.leetcode;

import java.util.*;

/**
 * Created by zhangyan122 on 2020/7/30
 */
public class LeetCode116 {

    /**
     * 116. 填充每个节点的下一个右侧节点指针
     * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
     *
     * struct Node1 {
     *   int val;
     *   Node1 *left;
     *   Node1 *right;
     *   Node1 *next;
     * }
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     *
     * 初始状态下，所有 next 指针都被设置为 NULL。
     * @param root
     * @return
     */
    public Node1 connect(Node1 root) {
//        List<List<Node1>> ans = new ArrayList<>();
        Queue<Node1> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
//            List<Node1> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node1 node = queue.poll();
//                list.add(node);
                if (i < size - 1) {
                    node.next = queue.peek();
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
//            ans.add(list);
        }
//        for (List<Node1> an : ans) {
//            for (int i = 0; i < an.size() - 1; i++) {
//                an.get(i).next = an.get(i + 1);
//            }
//            an.get(an.size() - 1).next = null;
//        }
        return root;
    }

    /**
     * 思路：一层一层，如果本层，直接设置，如果下一次，用这一层的
     * @param root
     * @return
     */
    public Node1 connect1(Node1 root) {
        if (root == null) {
            return null;
        }
        Node1  leftMost = root;
        while (leftMost.left != null) {
            Node1 head = leftMost;
            while (head != null) {
                head.left.next = head.right;
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            leftMost = leftMost.left;
        }
        return root;
    }

    public Node1 connect12(Node1 root) {
        if (root != null) {
            connect(root.left, root.right);
        }
        return root;
    }

    private void connect(Node1 left, Node1 right) {
        if (left != null) {
            left.next = right;
            connect(left.left, left.right);
            connect(left.right, right.left);
            connect(right.left, right.left);
        }
    }

    /**
     * 118. 杨辉三角
     * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
     *
     *
     *
     * 在杨辉三角中，每个数是它左上方和右上方的数的和。
     *
     * 示例:
     *
     * 输入: 5
     * 输出:
     * [
     *      [1],
     *     [1,1],
     *    [1,2,1],
     *   [1,3,3,1],
     *  [1,4,6,4,1]
     * ]
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 0; i < numRows; i++) {
            ArrayList<Integer> sub = new ArrayList<Integer>();
            for (int j = 0; j <= i; j++) {
                //第一个位置和最后一个位置的元素为1
                if (j == 0 || j == i) {
                    sub.add(1);
                } else {
                    //上一行的元素进行相加
                    sub.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
                }
            }
            result.add(sub);
        }
        return result;
    }

    /**
     * 119. 杨辉三角 II
     * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
     *
     *
     *
     * 在杨辉三角中，每个数是它左上方和右上方的数的和。
     *
     * 示例:
     *
     * 输入: 3
     * 输出: [1,3,3,1]
     * 进阶：
     *
     * 你可以优化你的算法到 O(k) 空间复杂度吗？
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> pre = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            cur = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    cur.add(1);
                } else {
                    cur.add(pre.get(j - 1) + pre.get(j));
                }
            }
            pre = cur;
        }
        return cur;
    }

    /**
     * 120. 三角形最小路径和
     * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
     *
     * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
     *
     *
     *
     * 例如，给定三角形：
     *
     * [
     *      [2],
     *     [3,4],
     *    [6,5,7],
     *   [4,1,8,3]
     * ]
     * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     *
     *
     *
     * 说明：
     *
     * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        return 0;
    }

    /**
     * 125. 验证回文串
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     *
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     *
     * 示例 1:
     *
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     * 示例 2:
     *
     * 输入: "race a car"
     * 输出: false
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
        }
        return true;
    }


    /**
     * 127. 单词接龙
     * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
     *
     * 每次转换只能改变一个字母。
     * 转换过程中的中间单词必须是字典中的单词。
     * 说明:
     *
     * 如果不存在这样的转换序列，返回 0。
     * 所有单词具有相同的长度。
     * 所有单词只由小写字母组成。
     * 字典中不存在重复的单词。
     * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
     * 示例 1:
     *
     * 输入:
     * beginWord = "hit",
     * endWord = "cog",
     * wordList = ["hot","dot","dog","lot","log","cog"]
     *
     * 输出: 5
     *
     * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     *      返回它的长度 5。
     * 示例 2:
     *
     * 输入:
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log"]
     *
     * 输出: 0
     *
     * 解释: endWord "cog" 不在字典中，所以无法进行转换
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        return 0;
    }


    /**
     * 128. 最长连续序列
     * 给定一个未排序的整数数组，找出最长连续序列的长度。
     *
     * 要求算法的时间复杂度为 O(n)。
     *
     * 示例:
     *
     * 输入: [100, 4, 200, 1, 3, 2]
     * 输出: 4
     * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int res = 0;
        for (int num : nums) {
            if (!map.containsKey(num)) {
                int left = map.get(num - 1) == null ? 0 : map.get(num - 1);
                int right = map.get(num + 1) == null ? 0 : map.get(num + 1);
                int cur = 1 + left + right;
                if (cur > res) {
                    res = cur;
                }
                map.put(num, cur);
                map.put(num - left, cur);
                map.put(num + right, cur);
            }
        }
        return res;
    }

    /**
     * 242. 有效的字母异位词
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     *
     * 示例 1:
     *
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     * 示例 2:
     *
     * 输入: s = "rat", t = "car"
     * 输出: false
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        int[] cnts = new int[26];
        for (char c : s.toCharArray()) {
            cnts[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            cnts[c - 'a']--;
        }
        for (int cnt : cnts) {if (cnt != 0) {
            return false;
        }
        }
        return true;
    }


    /**
     * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
     *
     *     具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
     *
     *     示例 1:
     *
     *     输入: "abc"
     *     输出: 3
     *     解释: 三个回文子串: "a", "b", "c".
     *     示例 2:
     *
     *     输入: "aaa"
     *     输出: 6
     *     说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
     * @param s
     * @return
     */
    int cnt = 0;
    public int countSubstrings(String s) {

        for (int i = 0; i < s.length(); i++) {
            extendSubstrings(s, i, i);// 奇数长度
            extendSubstrings(s, i, i + 1); // 偶数长度
        }
        return cnt;
    }
    private void extendSubstrings(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
            cnt++;
        }
    }


    /**
     * 696. 计数二进制子串
     * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
     *
     * 重复出现的子串要计算它们出现的次数。
     *
     * 示例 1 :
     *
     * 输入: "00110011"
     * 输出: 6
     * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
     *
     * 请注意，一些重复出现的子串要计算它们出现的次数。
     *
     * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
     * 示例 2 :
     *
     * 输入: "10101"
     * 输出: 4
     * 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
     * @param s
     * @return
     */
    public int countBinarySubstrings(String s) {
        int[] groups = new int[s.length()];
        int t = 0;
        groups[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) != s.charAt(i)) {
                t++;
                groups[t] = 1;
            } else {
                groups[t]++;
            }
        }

        int ans = 0;
        for (int i = 1; i <= t; i++) {
            ans += Math.min(groups[i - 1], groups[i]);
        }
        return ans;
    }


    /**
     * 129. 求根到叶子节点数字之和
     * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
     *
     * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
     *
     * 计算从根到叶子节点生成的所有数字之和。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例 1:
     *
     * 输入: [1,2,3]
     *     1
     *    / \
     *   2   3
     * 输出: 25
     * 解释:
     * 从根到叶子节点路径 1->2 代表数字 12.
     * 从根到叶子节点路径 1->3 代表数字 13.
     * 因此，数字总和 = 12 + 13 = 25.
     * 示例 2:
     *
     * 输入: [4,9,0,5,1]
     *     4
     *    / \
     *   9   0
     *  / \
     * 5   1
     * 输出: 1026
     * 解释:
     * 从根到叶子节点路径 4->9->5 代表数字 495.
     * 从根到叶子节点路径 4->9->1 代表数字 491.
     * 从根到叶子节点路径 4->0 代表数字 40.
     * 因此，数字总和 = 495 + 491 + 40 = 1026.
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        return helper(root, 0);
    }

    private int helper(TreeNode root, int temp) {
        if (root == null) {
            return 0;
        }
        temp = temp * 10 + root.val;
        if (root.left == null && root.right == null) {
            return temp;
        }
        return helper(root.left, temp) + helper(root.right, temp);
    }


    /**
     * 130. 被围绕的区域
     * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
     *
     * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     *
     * 示例:
     *
     * X X X X
     * X O O X
     * X X O X
     * X O X X
     * 运行你的函数后，矩阵变为：
     *
     * X X X X
     * X X X X
     * X X X X
     * X O X X
     * 解释:
     *
     * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
     * 思路：
     * 这道题我们拿到基本就可以确定是图的 dfs、bfs 遍历的题目了。题目中解释说被包围的区间不会存在于边界上，所以我们会想到边界上的 OO 要特殊处理，只要把边界上的 OO 特殊处理了，那么剩下的 OO 替换成 XX 就可以了。问题转化为，如何寻找和边界联通的 OO，我们需要考虑如下情况。
     *
     *
     * X X X X
     * X O O X
     * X X O X
     * X O O X
     * 这时候的 OO 是不做替换的。因为和边界是连通的。为了记录这种状态，我们把这种情况下的 OO 换成 # 作为占位符，待搜索结束之后，遇到 OO 替换为 XX（和边界不连通的 OO）；遇到 #，替换回 $O(和边界连通的 OO)。
     *
     * 如何寻找和边界联通的OO? 从边界出发，对图进行 dfs 和 bfs 即可。这里简单总结下 dfs 和 dfs。
     *
     * bfs 递归。可以想想二叉树中如何递归的进行层序遍历。
     * bfs 非递归。一般用队列存储。
     * dfs 递归。最常用，如二叉树的先序遍历。
     * dfs 非递归。一般用 stack。
     * @param board
     */
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean isEdge = false;
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    isEdge = true;
                }
                if (isEdge && board[i][j] == 'O') {
                    dfs(board, i, j);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void dfs(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == 'X' || board[i][j] == '#') {
            return;
        }
        board[i][j] = '#';
        dfs(board, i - 1, j);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
        dfs(board, i, j + 1);

    }

    /**
     * 131. 分割回文串
     * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
     *
     * 返回 s 所有可能的分割方案。
     *
     * 示例:
     *
     * 输入: "aab"
     * 输出:
     * [
     *   ["aa","b"],
     *   ["a","a","b"]
     * ]
     *
     * 思考如何根据这棵递归树编码：
     *
     * 1、每一个结点表示剩余没有扫描到的字符串，产生分支是截取了剩余字符串的前缀；
     *
     * 2、产生前缀字符串的时候，判断前缀字符串是否是回文。
     *
     * 如果前缀字符串是回文，则可以产生分支和结点；
     * 如果前缀字符串不是回文，则不产生分支和结点，这一步是剪枝操作。
     * 3、在叶子结点是空字符串的时候结算，此时从根结点到叶子结点的路径，就是结果集里的一个结果，使用深度优先遍历，记录下所有可能的结果。
     *
     * 采用一个路径变量 path 搜索，path 全局使用一个（注意结算的时候，需要生成一个拷贝），因此在递归执行方法结束以后需要回溯，即将递归之前添加进来的元素拿出去；
     * path 的操作只在列表的末端，因此合适的数据结构是栈。
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        int len = s.length();
        List<List<String>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        Stack<String> stack = new Stack<>();
        backtracking(s, 0, len, stack, res);
        return res;
    }

    private void backtracking(String s, int start, int len, Stack<String> path, List<List<String>> res) {
        if (start == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < len; i++) {
            if (!checkPalindrome(s, start, i)) {
                continue;
            }
            path.push(s.substring(start, i + 1)); //截取start到i
            backtracking(s, i + 1, len, path, res);
            path.pop();
        }
    }

    /**
     * 这一步的时间复杂度是 O(N)，因此，可以采用动态规划先把回文子串的结果记录在一个表格里
     *
     * @param str
     * @param left  子串的左边界，可以取到
     * @param right 子串的右边界，可以取到
     * @return
     */
    private boolean checkPalindrome(String str, int left, int right) {
        // 严格小于即可
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    /**
     * 132. 分割回文串 II
     * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
     *
     * 返回符合要求的最少分割次数。
     *
     * 示例:
     *
     * 输入: "aab"
     * 输出: 1
     * 解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
     *步骤 1：思考状态
     * 状态就尝试定义成题目问的那样，看看状态转移方程是否容易得到。
     *
     * dp[i]：表示前缀子串 s[0:i] 分割成若干个回文子串所需要最小分割次数。
     *
     * 步骤 2：思考状态转移方程
     * 思考的方向是：大问题的最优解怎么由小问题的最优解得到。
     *
     * 即 dp[i] 如何与 dp[i - 1]、dp[i - 2]、...、dp[0] 建立联系。
     *
     * 比较容易想到的是：如果 s[0:i] 本身就是一个回文串，那么不用分割，即 dp[i] = 0 ，这是首先可以判断的，否则就需要去遍历；
     *
     * 接下来枚举可能分割的位置：即如果 s[0:i] 本身不是一个回文串，就尝试分割，枚举分割的边界 j。
     *
     * 如果 s[j + 1, i] 不是回文串，尝试下一个分割边界。
     *
     * 如果 s[j + 1, i] 是回文串，则 dp[i] 就是在 dp[j] 的基础上多一个分割。
     *
     * 于是枚举 j 所有可能的位置，取所有 dp[j] 中最小的再加 1 ，就是 dp[i]。
     *
     * 得到状态转移方程如下：
     *
     *
     * dp[i] = min([dp[j] + 1 for j in range(i) if s[j + 1, i] 是回文])
     *
     *
     * @param s
     * @return
     */
    public int minCut(String s) {
        int len = s.length();
        if (len < 2) {
            return 0;
        }
        int[] dp = new int[len];
        // 2 个字符最多分割 1 次；
        // 3 个字符最多分割 2 次
        // 初始化的时候，设置成为这个最多分割次数
        for (int i = 0; i < len; i++) {
            dp[i] = i;
        }
        boolean[][] checkPalindrome = new boolean[len][len];
        for (int right = 0; right < len; right++) {
            for (int left = 0; left <= right; left++) {
                if (s.charAt(left) == s.charAt(right) && (right - left <= 2 || checkPalindrome[left + 1][right - 1])) {
                    checkPalindrome[left][right] = true;
                }
            }
        }

        // 1 个字符的时候，不用判断，因此 i 从 1 开始
        for (int i = 1; i < len; i++) {
            if (checkPalindrome[0][i]) {
                dp[i] = 0;
                continue;
            }
            // 注意：这里是严格，要保证 s[j + 1:i] 至少得有一个字符串
            for (int j = 0; j < i; j++) {
                if (checkPalindrome[j + 1][i]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[len - 1];
    }





    public static void main(String[] args) {
        int[] a = {400,200,3,5,9,7,4,6,100};
        System.out.println(new LeetCode116().countBinarySubstrings("11000111000000"));;
    }


}


class Node1 {
    public int val;
    public Node1 left;
    public Node1 right;
    public Node1 next;

    public Node1() {}

    public Node1(int _val) {
        val = _val;
    }

    public Node1(int _val, Node1 _left, Node1 _right, Node1 _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

