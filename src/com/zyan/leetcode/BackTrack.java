package com.zyan.leetcode;

import java.util.*;

public class BackTrack {

    /**
     * 1、路径：也就是已经做出的选择。
     * <p>
     * 2、选择列表：也就是你当前可以做的选择。
     * <p>
     * 3、结束条件：也就是到达决策树底层，无法再做选择的条件。
     * result = []
     * def backtrack(路径, 选择列表):
     * if 满足结束条件:
     * result.add(路径)
     * return
     * <p>
     * for 选择 in 选择列表:
     * 做选择
     * backtrack(路径, 选择列表)
     * 撤销选择
     */

    //demo 全排列问题
    static List<List<Integer>> res = new LinkedList<>();

    /* 主函数，输入一组不重复的数字，返回它们的全排列 */
    List<List<Integer>> permute(int[] nums) {
        //记录 路径
        // 这里的选择列表即包含在nums中
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return res;
    }

    // 路径：记录在 track 中
    // 选择列表：nums 中的元素
    // 结束条件：nums 中的元素全都在 track 中出现
    private void backtrack(int[] nums, LinkedList<Integer> track) {
        //触发结束条件
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //排除不合法的选择
            if (track.contains(nums[i])) {
                continue;
            }
            //做选择
            track.add(nums[i]);
            //进入下一层决策树
            backtrack(nums, track);
            //取消选择，返回上一层决策树
            track.removeLast();
        }
    }


    //组合总和
    public static List<List<Integer>> combinationRes = new LinkedList<>();

    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * <p>
     * candidates 中的数字可以无限制重复被选取。
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        LinkedList<Integer> track = new LinkedList<>();
        //排序的原因是在回溯的时候容易剪枝
        Arrays.sort(candidates);
        //套用上面的公式，candidates是选择的列表，target判断是否结束，以及用于剪枝
        //track是路径，已经做出的选择
        backtrack(candidates, target, track);
        return combinationRes;
    }

    private void backtrack(int[] candidates, int target, LinkedList<Integer> track) {
        //先判断结束条件
        if (target < 0) {
            return;
        }
        if (target == 0) {
            combinationRes.add(new LinkedList<>(track));
            return;
        }
        //遍历选择列表，这里没有去重
        //下面会设置start，每次递归的时候只在candidates中当前及以后的数字中选择
        for (int i = 0; i < candidates.length; i++) {
            //这里就是排序的好处，可以直接剪枝，否则还得遍历
            if (target < candidates[i]) {
                break;
            }
            track.add(candidates[i]);
            backtrack(candidates, target - candidates[i], track);
            track.removeLast();
        }
    }

    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        LinkedList<Integer> track = new LinkedList<>();
        Arrays.sort(candidates);
        backtrack(candidates, 0, target, track);
        return res;
    }


    private static void backtrack(int[] candidates, int start, int target, LinkedList<Integer> track) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            combinationRes.add(new LinkedList<>(track));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (target < candidates[i]) {
                break;
            }
            track.add(candidates[i]);
            backtrack(candidates, i, target - candidates[i], track);
            track.removeLast();
        }
    }


    /**
     * 组合总和 II
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * <p>
     * candidates 中的每个数字在每个组合中只能使用一次。
     * 方法 回溯算法+剪枝
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        //先将数组排序
        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>(len);
        dfs(candidates, len, 0, target, path, res);
        return res;
    }

    /**
     * @param candidates 候选数组
     * @param len        长度
     * @param begin      从候选数组的begin位置开始搜索
     * @param residue    表示剩余，这一值一开始等于target
     * @param path       从根节点到叶子节点的路径
     * @param res        结果
     */
    private void dfs(int[] candidates, int len, int begin, int residue, Deque<Integer> path, List<List<Integer>> res) {
        if (residue == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < len; i++) {
            //大剪枝
            if (residue - candidates[i] < 0) {
                break;
            }

            //小剪枝
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }

            //因为元素不可重复使用，这里递归传递下去的是i+1,而不是i
            //这里不能更改residue - candidates[i]，不能让residue = residue - candidates[i],因为循环还要用，
            //所以写成residue - candidates[i] 不改变residue的值
            dfs(candidates, len, i + 1, residue - candidates[i], path, res);
            path.removeLast();
        }

    }

    public static List<List<Integer>> combinationSum22(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        MyCombinationSum(0, 0, target, candidates, res, list);
        return res;
    }

    public static void MyCombinationSum(int index,//下标
                                        int sum,//累加值
                                        int target,//目标值
                                        int[] candidates,
                                        List<List<Integer>> res,
                                        List<Integer> list) {
        if (target == sum) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            // 剪枝
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            sum += candidates[i];
            if (sum <= target) {
                list.add(candidates[i]); //选择了这一条路
                MyCombinationSum(i + 1, sum, target, candidates, res, list);
                list.remove(list.size() - 1); //后悔了，不选了
            } else {
                return;//提前结束
            }
            sum -= candidates[i];
        }
    }


    /**
     * 回溯法：全排列问题
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute1(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        boolean[] used = new boolean[len];
        List<Integer> path = new ArrayList<>();
        permuteDFS(nums, len, 0, path, used, res);
        return res;
    }

    private void permuteDFS(int[] nums, int len, int depth, List<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;
                permuteDFS(nums, len, depth + 1, path, used, res);
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * 全排列问题
     * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
     * <p>
     * 示例:
     * <p>
     * 输入: [1,1,2]
     * 输出:
     * [
     * [1,1,2],
     * [1,2,1],
     * [2,1,1]
     * ]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Arrays.sort(nums);

        boolean[] used = new boolean[len];

        Deque<Integer> path = new ArrayDeque<>(len);
        permuteUniqueDFS(nums, len, 0, used, path, res);
        return res;
    }

    private void permuteUniqueDFS(int[] nums, int len, int depth, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }
            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            permuteUniqueDFS(nums, len, depth + 1, used, path, res);
            used[i] = false;
            path.removeLast();
        }
    }

    /**
     * n皇后问题
     *
     * @param n
     * @return
     */
    List<List<String>> queenRes = new LinkedList<>();
    public List<List<String>> solveNQueens(int n) {
        if (n < 0) {
            return null;
        }
        char[][] board = new char[n][n];
        for (char[] chars : board) {
            Arrays.fill(chars, '.');
        }
        queenBacktrack(board, 0);
        return queenRes;
    }

    /**
     * 路径：board中小于row的那些行都已经成功放置了皇后
     * 可选择列表: 第row行的所有列都是放置Q的选择
     * 结束条件: row超过board的最后一行
     *
     * @param board
     * @param row
     */
    private void queenBacktrack(char[][] board, int row) {
        if (row == board.length) {
            queenRes.add(charToString(board));
            return;
        }
        int n = board[row].length;
        for (int col = 0; col < n; col++) {
            if (!isVilid(board, row, col)) {
                continue;
            }
            board[row][col] = 'Q';
            queenBacktrack(board, row + 1);
            board[row][col] = '.';
        }
    }

    //是否可以在 board[row][col] 放置皇后？
    //因为下方的行还没有轮到，所以只需要判断该位置的上方，以及左上方，右上方是否已经放置了皇后，
    // 如果放过了，那么这个位置就不能再放了
    private boolean isVilid(char[][] board, int row, int col) {
        int rows = board.length;
        // check is valid in col
        for (char[] chars : board) {
            if (chars[col] == 'Q') {
                return false;
            }
        }
        // check is valide upright
        for (int i = row - 1, j = col + 1; i >= 0 && j < rows; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        // check is valide upleft
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    private List<String> charToString(char[][] array) {
        List<String> result = new LinkedList<>();
        for (char[] chars : array) {
            result.add(String.valueOf(chars));
        }
        return result;
    }

    //借助“回溯”方法中的“剪枝”技巧
    public String getPermutation(int n, int k) {
        boolean[] used = new boolean[n + 1];
        Arrays.fill(used, false);

        //计算阶乘数组
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        List<Integer> path = new ArrayList<>(n);

        getPermutationDFS(0, n, k, factorial, used, path);

        StringBuilder stringBuilder = new StringBuilder();
        for (Integer integer : path) {
            stringBuilder.append(integer);
        }
        return stringBuilder.toString();
    }

    private void getPermutationDFS(int index, int n, int k, int[] factorial, boolean[] used, List<Integer> path) {
        if (index == n) {//触发出口条件，开始收集结果集
            return;
        }

        //还未确定的数字的全排列的个数，第一次进入的时候是n-1
        int cnt = factorial[n - 1 - index];
        for (int i = 1; i <= n; i++) {
            if (used[i]) {
                continue;
            }
            if (cnt < k) {
                k = k - cnt;
                continue;
            }
            path.add(i);
            used[i] = true;
            getPermutationDFS(index + 1, n, k, factorial, used, path);//这里到了根节点就结束了，借助了回溯思想
        }
    }

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] < nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
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
     * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     * <p>
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     * <p>
     *  
     * <p>
     * 示例:
     * <p>
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
     * @param board -
     * @param word  -
     * @return
     */
    public boolean exist(char[][] board, String word) {
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        BackTrack solution = new BackTrack();
        List<List<Integer>> lists = solution.permute(nums);
        System.out.println(lists);
    }
}
