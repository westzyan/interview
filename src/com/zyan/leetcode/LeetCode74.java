package com.zyan.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeetCode74 {


    /**
     *
     * 搜索二维矩阵
     * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
     *
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0){
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int i = m - 1, j = 0;
        while (i >= 0 && j < n){
            if (matrix[i][j] == target){
                return true;
            }else if (matrix[i][j] > target){
                i--;
            }else {
                j++;
            }
        }
        return false;
    }

    public void sortColors(int[] nums){
        int len = nums.length;
        if (len < 2){
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
        while (i < two){
            if (nums[i] == 0){
                swap(nums, i , zero);
                zero++;
                i++;
            }else if (nums[i] == 1){
                i++;
            }else {
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
     *
     * 示例:
     *
     * 输入: n = 4, k = 2
     * 输出:
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     *
     * @param n -
     * @param k -
     * @return -
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 0 || k <= 0 || n < k){
            return result;
        }
        findCombinations(n, k, 1, new Stack<Integer>(), result);
        return result;
    }

    private void findCombinations(int n, int k, int index, Stack<Integer> stack, List<List<Integer>> result) {
        if (stack.size() == k){
            result.add(new ArrayList<>(stack));
            return;
        }
        for (int i = index; i <= n- (k - stack.size()) + 1; i++) {
            stack.push(i);
            findCombinations(n, k, i + 1, stack, result);
            stack.pop();
        }
    }

    /**
     * 子集
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     *
     * 说明：解集不能包含重复的子集。
     *
     * 示例:
     *
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int num : nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
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


    public static void main(String[] args) {
        int[] num = {1,2,3};
        enumerate(num);
    }
}
