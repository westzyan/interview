package com.zyan.leetcode;

import java.util.*;

public class LeetCode74 {


    /**
     * 搜索二维矩阵
     * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
     * <p>
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int i = m - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                i--;
            } else {
                j++;
            }
        }
        return false;
    }


    /**
     * 荷兰国旗问题，可以两次扫描，计数
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return;
        }
        // all in [0, zero) = 0
        // all in [zero, i) = 1
        // all in [two, len - 1] = 2

        // 循环终止条件是 i == two，那么循环可以继续的条件是 i < two
        // 为了保证初始化的时候 [0, zero) 为空，设置 zero = 0，
        // 所以下面遍历到 0 的时候，先交换，再加
        int zero = 0;
        // 为了保证初始化的时候 [two, len - 1] 为空，设置 two = len
        // 所以下面遍历到 2 的时候，先减，再交换
        int two = len;
        int i = 0;
        // 当 i == two 上面的三个子区间正好覆盖了全部数组
        // 因此，循环可以继续的条件是 i < two
        while (i < two) {
            if (nums[i] == 0) {
                swap(nums, i, zero);
                zero++;
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                two--;
                swap(nums, i, two);
            }
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }


    /**
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     * <p>
     * 示例:
     * <p>
     * 输入: n = 4, k = 2
     * 输出:
     * [
     * [2,4],
     * [3,4],
     * [2,3],
     * [1,2],
     * [1,3],
     * [1,4],
     * ]
     *
     * @param n -
     * @param k -
     * @return -
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 0 || k <= 0 || n < k) {
            return result;
        }
        findCombinations(n, k, 1, new Stack<Integer>(), result);
        return result;
    }

    private void findCombinations(int n, int k, int index, Stack<Integer> stack, List<List<Integer>> result) {
        if (stack.size() == k) {
            result.add(new ArrayList<>(stack));
            return;
        }
        for (int i = index; i <= n - (k - stack.size()) + 1; i++) {
            stack.push(i);
            findCombinations(n, k, i + 1, stack, result);
            stack.pop();
        }
    }

    /**
     * 子集
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     * <p>
     * 说明：解集不能包含重复的子集。
     * <p>
     * 示例:
     * <p>
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     * [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     * 循环枚举，也就是原始里面是一个空集，然后迭代，在空集里面每次追加一个新元素，当做新集合
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int num : nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                //这里一定要new 一个，要不然会操作原来的
                List<Integer> newsub = new ArrayList<>(res.get(i));
                newsub.add(num);
                res.add(newsub);
            }
        }
        return res;
    }

    public static List<List<Integer>> enumerate(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        for (Integer n : nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newSub = new ArrayList<Integer>(res.get(i));
                newSub.add(n);
                res.add(newSub);
            }
        }
        return res;
    }

    /**
     * 单词搜索
     * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     * <p>
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     * board =
     * [
     * ['A','B','C','E'],
     * ['S','F','C','S'],
     * ['A','D','E','E']
     * ]
     * <p>
     * 给定 word = "ABCCED", 返回 true
     * 给定 word = "SEE", 返回 true
     * 给定 word = "ABCB", 返回 false
     *
     * @param board
     * @param word
     * @return
     */
    int[][] directs = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };

    public boolean exist(char[][] board, String word) {

        if (board.length == 0) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        int[][] mark = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mark[i][j] = 0;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    mark[i][j] = 1;
                    if (backtrack(i, j, mark, board, word.substring(1))) {
                        return true;
                    } else {
                        mark[i][j] = 0;
                    }
                }
            }
        }

        return false;
    }

    private boolean backtrack(int i, int j, int[][] mark, char[][] board, String word) {
        if (word.length() == 0) {
            return true;
        }
        for (int[] direct : directs) {
            int curI = i + direct[0];
            int curJ = j + direct[1];
            if (curI >= 0 && curJ >= 0 && curI < board.length && curJ < board[0].length && board[curI][curJ] == word.charAt(0)) {
                if (mark[curI][curJ] == 1) {
                    continue;
                }
                mark[curI][curJ] = 1;
                if (backtrack(curI, curJ, mark, board, word.substring(1))) {
                    return true;
                } else {
                    mark[curI][curJ] = 0;
                }
            }
        }
        return false;
    }

    /**
     * 删除排序数组中的重复项
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     * <p>
     * 示例 1:
     * <p>
     * 给定 nums = [1,1,1,2,2,3],
     * <p>
     * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
     * <p>
     * 你不需要考虑数组中超出新长度后面的元素。
     * 双指针方法
     * <p>
     * 将快指针（当前遍历的数字）和慢指针指向的数字的前一个数字比较（也就是满足条件的倒数第 2 个数）。
     * <p>
     * 如果相等，因为有序，所以倒数第 1 个数字和倒数第 2 个数字都等于当前数字，再添加就超过 2 个了。
     * 如果不相等，那么就添加。
     *
     * @param nums -
     * @return -
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        int fast = 2;
        int slow = 1;
        int max = 2;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow - max + 1]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }

//    public int removeDuplicates2(int[] nums) {
//        if (nums.length <= 2) {
//            return nums.length;
//        }
//        int fast = 2, slow = 1;
//        while (fast < nums.length) {
//            if (nums[fast] != nums[slow -max - 1]) {
//                slow++;
//                nums[slow] = nums[fast];
//            }
//            fast++;
//        }
//        return slow + 1;
//    }

    //原地删除排序数组重复项
    public int removeDuplicates1(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        int fast = 1;
        int slow = 0;
        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }


    /**
     * 搜索旋转排序数组2
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     * <p>
     * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
     * <p>
     * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [2,5,6,0,0,1,2], target = 0
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: nums = [2,5,6,0,0,1,2], target = 3
     * 输出: false
     * <p>
     * 本题是需要使用二分查找，怎么分是关键，举个例子：
     * <p>
     * 第一类
     * 10111 和 11101 这种。此种情况下 nums[start] == nums[mid]，分不清到底是前面有序还是后面有序，此时 start++ 即可。相当于去掉一个重复的干扰项。
     * 第二类
     * 2 3 4 5 6 7 1 这种，也就是 nums[start] < nums[mid]。此例子中就是 2 < 5；
     * 这种情况下，前半部分有序。因此如果 nums[start] <=target<nums[mid]，则在前半部分找，否则去后半部分找。
     * 第三类
     * 6 7 1 2 3 4 5 这种，也就是 nums[start] > nums[mid]。此例子中就是 6 > 2；
     * 这种情况下，后半部分有序。因此如果 nums[mid] <target<=nums[end]。则在后半部分找，否则去前半部分找。
     *
     * @param nums   -
     * @param target -
     * @return -
     */
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[start] == nums[mid]) {
                start++;
                continue;
            }
            //前半部分有序
            if (nums[start] < nums[mid]) {
                //target在前半部分
                //这里<=的原因是需要完全覆盖到，后面没有等于是因为前面判断过了
                if (nums[start] <= target && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                //后半部分有序
                //target在后半部分
                if (nums[mid] < target && nums[end] >= target) {
                    start = mid + 1;
                } else {  //否则，去后半部分找
                    end = mid - 1;

                }
            }
        }
        return false;
    }


    /**
     * 删除排序链表中的重复元素
     * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->2->3->3->4->4->5
     * 输出: 1->2->5
     * 示例 2:
     * <p>
     * 输入: 1->1->1->2->3
     * 输出: 2->3
     *
     * @param head -
     * @return -
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p1 = dummy;
        ListNode p2 = head;
        boolean flag = false;
        while (p1.next != null) {
            while (p2.next != null && p2.next.val == p1.next.val) {
                p2 = p2.next;
                flag = true;
            }
            if (flag) {
                p1.next = p2.next;
                p2 = p2.next;
                flag = false;
            } else {
                p1 = p2;
                if (p2.next != null) {
                    p2 = p2.next;
                }
            }
        }
        return dummy.next;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        boolean flag = false;
        while (cur != null) {
            while (cur.next != null && cur.next.val == pre.next.val) {
                cur = cur.next;
                flag = true;
            }
            if (flag) {
                pre.next = cur.next;
                cur = cur.next;
                flag = false;
            } else {
                pre = pre.next;
                cur = cur.next;
            }
        }
        return dummy.next;
    }


    public ListNode deleteDuplicates1(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }


    /**
     * 合并两个有序数组
     * 精髓在于三指针，从后开始
     * 也可以先合并，然后快排
     * 或者建立一个新数组放nums1
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] >= nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }
        while (p2 >= 0) {
            nums1[p] = nums2[p2];
            p2--;
            p--;
        }
    }


    /**
     * 将有序数组转换为二叉搜索树
     * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
     * <p>
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     * 给定有序数组: [-10,-3,0,5,9],
     * <p>
     * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
     * <p>
     * 0
     * / \
     * -3   9
     * /   /
     * -10  5
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }


    /**
     * 判断平衡二叉树
     * 给定二叉树 [3,9,20,null,null,15,7]
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     *
     * @param root -
     * @return -
     */
    public boolean isBalanced(TreeNode root) {
        return depth(root) != -1;
    }

    public boolean isBalanced1(TreeNode root) {
        if (root == null) {
            return true;
        }

        int left = depth1(root.left);
        int right = depth1(root.right);
        if (Math.abs(left - right) > 1) {
            return false;
        }
        return isBalanced1(root.right) && isBalanced1(root.right);
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = depth(root.left);
        if (left == -1) {
            return -1;
        }
        int right = depth(root.right);
        if (right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return 1 + Math.max(left, right);
    }

    public int depth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = depth1(root.left);
        int right = depth1(root.right);
        return Math.max(left, right) + 1;
    }


    /**
     * 二叉树的最近公共祖先
     *
     * @param root -
     * @param p    -
     * @param q    -
     * @return -
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    /**
     * 零钱兑换问题
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
     *
     * @param coins  -
     * @param amount -
     * @return -
     */
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        return coinChange(coins, amount, new int[amount]);
    }

    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) {
            return -1;
        }
        if (rem == 0) {
            return 0;
        }
        if (count[rem - 1] != 0) {
            return count[rem - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        if (min == Integer.MAX_VALUE) {
            count[rem - 1] = -1;
        } else {
            count[rem - 1] = min;
        }
        return count[rem - 1];
    }

    public int coinChange3(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    public int coinChange1(int[] coins, int amount) {
        return coinChange(0, coins, amount);
    }

    private int coinChange(int idxCoin, int[] coins, int amount) {
        if (amount == 0)
            return 0;
        if (idxCoin < coins.length && amount > 0) {
            int maxVal = amount / coins[idxCoin];
            int minCost = Integer.MAX_VALUE;
            for (int x = 0; x <= maxVal; x++) {
                if (amount >= x * coins[idxCoin]) {
                    int res = coinChange(idxCoin + 1, coins, amount - x * coins[idxCoin]);
                    if (res != -1)
                        minCost = Math.min(minCost, res + x);
                }
            }
            return (minCost == Integer.MAX_VALUE) ? -1 : minCost;
        }
        return -1;
    }

    public ListNode reverseList(ListNode head) {
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


    /**
     * 二叉树的层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                list.add(node.val);
            }
            res.add(list);
        }
        return res;
    }


    /**
     * 从前序和中序遍历序列构造二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, 0, preorder.length, inorder, 0, inorder.length);

    }

    private TreeNode buildTreeHelper(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
        //preorder 为空，直接返回null
        if (pStart == pEnd) {
            return null;
        }
        int rootVal = preorder[pStart];
        TreeNode root = new TreeNode(rootVal);
        //在中序遍历中找到根节点的位置
        int iRootIndex = 0;
        for (int i = iStart; i < iEnd; i++) {
            if (rootVal == inorder[i]) {
                iRootIndex = i;
                break;
            }
        }
        int leftNum = iRootIndex - iStart;
        root.left = buildTreeHelper(preorder, pStart + 1, pStart + leftNum + 1, inorder, iStart, iRootIndex);
        root.right = buildTreeHelper(preorder, pStart + leftNum + 1, pEnd, inorder, iRootIndex + 1, iEnd);
        return root;
    }


    /**
     * 从后续序和中序遍历序列构造二叉树
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0 || postorder == null || postorder.length == 0) {
            return null;
        }
        int value = postorder[postorder.length - 1];
        int index = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == value) {
                index = i;
                break;
            }
        }
        TreeNode root = new TreeNode(value);
        root.left = buildTree1(Arrays.copyOfRange(inorder, 0, index), Arrays.copyOfRange(postorder, 0, index));
        root.right = buildTree1(Arrays.copyOfRange(inorder, index + 1, inorder.length), Arrays.copyOfRange(postorder, index, postorder.length - 1));
        return root;
    }


    /**
     * 将每个元素替换为右侧最大元素
     *
     * @param arr
     * @return
     */
    public int[] replaceElements(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        ans[n - 1] = -1;
        for (int i = n - 2; i >= 0; i--) {
            ans[i] = Math.max(ans[i + 1], arr[i + 1]);
        }
        return ans;
    }


    /**
     * 链表相交
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int len1 = 0;
        int len2 = 0;
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != null) {
            p1 = p1.next;
            len1++;
        }
        while (p2 != null) {
            p2 = p2.next;
            len2++;
        }
        p1 = headA;
        p2 = headB;
        int step = 0;
        if (len1 > len2) {
            step = len1 - len2;
            while (step > 0) {
                p1 = p1.next;
                step--;
            }
            while (p1 != p2) {
                p1 = p1.next;
                p2 = p2.next;
            }
            return p1;
        } else {
            step = len2 - len1;
            while (step > 0) {
                p2 = p2.next;
                step--;
            }
            while (p1 != p2) {
                p1 = p1.next;
                p2 = p2.next;
            }
            return p1;
        }
    }

    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }


    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }


    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return 1 + Math.max(left, right);
    }


    /**
     * 二叉搜索树中第K小的结点
     *
     * @param root
     * @param k
     * @return
     */
    int num = 0;
    int res;

    public int kthSmallest(TreeNode root, int k) {
        inOrderKth(root, k);
        return res;
    }

    private void inOrderKth(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        inOrderKth(root.left, k);
        num++;
        if (num == k) {
            res = root.val;
            return;
        }
        inOrderKth(root.right, k);
    }


    int ans;

    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth3(root);
        return ans - 1;
    }

    public int depth3(TreeNode node) {
        if (node == null) return 0; // 访问到空节点了，返回0
        int L = depth(node.left); // 左儿子为根的子树的深度
        int R = depth(node.right); // 右儿子为根的子树的深度
        ans = Math.max(ans, L + R + 1); // 计算d_node即L+R+1 并更新ans
        return Math.max(L, R) + 1; // 返回该节点为根的子树的深度
    }


    /**
     * 路径总和
     * 时间复杂度：O(N)O(N)，其中 NN 是树的节点数。对每个节点访问一次。
     * <p>
     * 空间复杂度：O(H)O(H)，其中 HH 是树的高度。空间复杂度主要取决于递归时栈空间的开销，最坏情况下，树呈现链状，空间复杂度为 O(N)O(N)。平均情况下树的高度与节点数的对数正相关，空间复杂度为 O(\log N)O(logN)。
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public boolean hasPathSum1(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queNode = new LinkedList<>();
        Queue<Integer> queVal = new LinkedList<>();
        queNode.offer(root);
        queVal.offer(root.val);
        while (!queNode.isEmpty()) {
            TreeNode now = queNode.poll();
            int temp = queVal.poll();
            if (now.left == null && now.right == null) {
                if (temp == sum) {
                    return true;
                }
                continue;
            }
            if (now.left != null) {
                queNode.offer(now.left);
                queVal.offer(now.left.val + temp);
            }
            if (now.right != null) {
                queNode.offer(now.right);
                queVal.offer(now.right.val + temp);
            }
        }
        return false;
    }

    /**
     * 链表是否有环
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        if (head.next == head) {
            return true;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }


    /**
     * 在二叉树的每个行里面找到最大值
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        int max = Integer.MIN_VALUE;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (node.val > max) {
                    max = node.val;
                }
            }
            res.add(max);
            max = Integer.MIN_VALUE;
        }
        return res;
    }


    /**
     * 二叉树的序列化与反序列化
     *
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder res = serHelp(root, new StringBuilder());
        return res.toString();
    }

    private StringBuilder serHelp(TreeNode root, StringBuilder stringBuilder) {
        if (root == null) {
            stringBuilder.append("null,");
            return stringBuilder;
        }
        stringBuilder.append(root.val);
        stringBuilder.append(",");
        stringBuilder = serHelp(root.left, stringBuilder);
        stringBuilder = serHelp(root.right, stringBuilder);
        return stringBuilder;
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strings = data.split(",");
        List<String> listWord = new LinkedList<>(Arrays.asList(strings));
        return deserHelp(listWord);
    }

    private TreeNode deserHelp(List<String> listWord) {
        if (listWord.get(0).equals("null")) {
            listWord.remove(0);
            return null;
        }
        TreeNode res = new TreeNode(Integer.parseInt(listWord.get(0)));
        listWord.remove(0);
        res.left = deserHelp(listWord);
        res.right = deserHelp(listWord);
        return res;
    }

    // Encodes a tree to a single string.
    public String serialize1(TreeNode root) {
        if (root == null) return "[null]";
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        sb.append(Integer.toString(root.val));
        sb.append(',');
        while (!queue.isEmpty()) {

            TreeNode node = queue.poll();

            if (node.left != null) {
                queue.offer(node.left);
                sb.append(node.left.val);
            } else {
                sb.append("null");
            }
            sb.append(',');
            if (node.right != null) {
                queue.offer(node.right);
                sb.append(node.right.val);
            } else {
                sb.append("null");
            }
            sb.append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        // System.out.println(sb);
        return sb.toString();

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize1(String data) {
        String data1 = data.substring(1, data.length() - 1);
        String[] d = data1.split(",");
        if (d[0].equals("null")) {
            return null;
        }
        TreeNode root = new TreeNode(0);
        root.val = Integer.parseInt(d[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode rootp = root;
        int i = 1;
        while (i < d.length && !queue.isEmpty()) {
            rootp = queue.poll();
            if (d[i].equals("null")) {
                rootp.left = null;
            }
            else {
                rootp.left = new TreeNode(Integer.parseInt(d[i]));
                queue.add(rootp.left);
            }
            i++;
            if (d[i].equals("null")) {
                rootp.right = null;
            }
            else {
                rootp.right = new TreeNode(Integer.parseInt(d[i]));
                queue.add(rootp.right);
            }
            i++;
        }
        return root;
    }


    /**
     * 寻找峰值
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }


    /**
     * 最大的正方形
     *
     * 用 dp(i, j)dp(i,j) 表示以 (i, j)(i,j) 为右下角，且只包含 11 的正方形的边长最大值。如果我们能计算出所有 dp(i, j)dp(i,j) 的值，那么其中的最大值即为矩阵中只包含 11 的正方形的边长最大值，其平方即为最大正方形的面积。
     *
     * 那么如何计算 dpdp 中的每个元素值呢？对于每个位置 (i, j)(i,j)，检查在矩阵中该位置的值：
     *
     * 如果该位置的值是 00，则 dp(i, j) = 0dp(i,j)=0，因为当前位置不可能在由 11 组成的正方形中；
     *
     * 如果该位置的值是 11，则 dp(i, j)dp(i,j) 的值由其上方、左方和左上方的三个相邻位置的 dpdp 值决定。具体而言，当前位置的元素值等于三个相邻位置的元素中的最小值加 11，状态转移方程如下：
     *
     * dp(i, j)=min(dp(i−1, j), dp(i−1, j−1), dp(i, j−1))+1
     * dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1
     *
     * 如果读者对这个状态转移方程感到不解，可以参考 1277. 统计全为 1 的正方形子矩阵的官方题解，其中给出了详细的证明。
     *
     * 此外，还需要考虑边界条件。如果 ii 和 jj 中至少有一个为 00，则以位置 (i, j)(i,j) 为右下角的最大正方形的边长只能是 11，因此 dp(i, j) = 1dp(i,j)=1。
     *
     * 以下用一个例子具体说明。原始矩阵如下。
     *
     *
     * 0 1 1 1 0
     * 1 1 1 1 0
     * 0 1 1 1 1
     * 0 1 1 1 1
     * 0 0 1 1 1
     * 对应的 dpdp 值如下。
     *
     *
     * 0 1 1 1 0
     * 1 1 2 2 0
     * 0 1 2 3 1
     * 0 1 2 3 2
     * 0 0 1 2 3
     * @param matrix -
     * @return -
     */
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }


    /**
     * 和为K的连续子数组的个数
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int len = nums.length;
        for (int left = 0; left < len; left++) {
            int sum = 0;
            for (int right = left; right < len; right++) {
                sum  = sum + nums[right];
                if (sum == k) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    /**
     * 和为K的连续子数组的个数
     * 前缀和  哈希表 方法
     * @param nums -
     * @param k -
     * @return -
     */
    public int subarraySum1(int[] nums, int k) {
        // key：前缀和，value：key 对应的前缀和的个数
        Map<Integer, Integer> preSumFreq = new HashMap<>();
        // 对于下标为 0 的元素，前缀和为 0，个数为 1
        preSumFreq.put(0, 1);

        int preSum = 0;
        int count = 0;
        for (int i : nums) {
            preSum = preSum + i;

            //// 先获得前缀和为 preSum - k 的个数，加到计数变量里
            if (preSumFreq.containsKey(preSum - k)) {
                count = count + preSumFreq.get(preSum - k);
            }
            // 然后维护 preSumFreq 的定义
            preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

    public int subarraySum2(int[] nums, int k) {
        int len = nums.length;
        // 计算前缀和数组
        int[] preSum = new int[len + 1];
        preSum[0] = 0;
        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        int count = 0;
        for (int left = 0; left < len; left++) {
            for (int right = left; right < len; right++) {
                // 区间和 [left..right]，注意下标偏移
                if (preSum[right + 1] - preSum[left] == k) {
                    count++;
                }
            }
        }
        return count;
    }


    /**
     * 字符串相加
     * @param num1 -
     * @param num2 -
     * @return -
     */
    public String addStrings(String num1, String num2) {
        int carry = 0;
        StringBuilder res = new StringBuilder();
        int length1 = num1.length() - 1;
        int length2 = num2.length() - 1;
        while (length1 >= 0 || length2 >= 0) {
            int val1 = length1 >= 0 ? num1.charAt(length1) - '0' : 0;
            int val2 = length2 >= 0 ? num2.charAt(length2) - '0' : 0;
            int sum = val1 + val2 + carry;
            res.append(sum % 10);
            carry = sum / 10;
            length1--;
            length2--;
        }
        if (carry == 1) {
            res.append(1);
        }
        return res.reverse().toString();
    }

    /**
     * 奇偶链表
     * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
     *
     * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }


    /**
     * 岛屿的最大面积
     * @param grid -
     * @return -
     */
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int max = 0;
        int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = grid[i][j];
                    }
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j] + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        return max;
    }

    /**
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland1(int[][] grid) {
        int maxArea = 0;
        int area = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    area = getArea(grid, i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    private int getArea(int[][] grid, int i, int j) {
        if (i == grid.length || i < 0 || j == grid[0].length || j < 0) {
            return 0;
        }
        if (grid[i][j] == 1) {
            grid[i][j] = 0;
            return 1 + getArea(grid, i + 1, j) + getArea(grid, i - 1, j) + getArea(grid, i, j + 1) + getArea(grid, i, j - 1);
        }
        return 0;
    }

    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return max;
    }

    public int dfs(TreeNode root) {
        if (root == null ) {
            return 0;
        }
        int leftMax = Math.max(0, dfs(root.left));
        int rightMax = Math.max(0, dfs(root.right));
        max = Math.max(max, root.val + leftMax + rightMax);
        return root.val + Math.max(leftMax,rightMax);
    }

    public static void main(String[] args) {
//        int[] num = {1, 1, 2, 2, 2, 3};
//        System.out.println(new LeetCode74().removeDuplicates(num));
        char[][] arr = new char[][]{
                {'a', 'b', 'b', 'c'},
                {'r', 'd', 'b', 'c'},
                {'y', 'g', 'b', 'c'},
                {'b', 'r', 'b', 'c'}
        };
//        System.out.println(new LeetCode74().exist(arr, "adgb"));
//        System.out.println("a".substring(1).length());
//        System.out.println(new LeetCode74().removeDuplicates(new int[]{1, 1, 1, 1, 2, 2, 2, 2, 3, 6, 6, 6}));

        int[] a = {9, 3, 15, 20, 7};
        int[] b = {9, 15, 7, 20, 3};
        TreeNode node = new LeetCode74().buildTree1(a, b);
        System.out.println();
    }
}
