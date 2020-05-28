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
        if (track.size() == nums.length){
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //排除不合法的选择
            if (track.contains(nums[i])){
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
    public static List<List<Integer>> combinationRes =  new LinkedList<>();
    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的数字可以无限制重复被选取。
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
        if (target < 0){
            return;
        }
        if (target == 0){
            combinationRes.add(new LinkedList<>(track));
            return;
        }
        //遍历选择列表，这里没有去重
        //下面会设置start，每次递归的时候只在candidates中当前及以后的数字中选择
        for (int i = 0; i < candidates.length; i++) {
            //这里就是排序的好处，可以直接剪枝，否则还得遍历
            if (target < candidates[i]){
                break;
            }
            track.add(candidates[i]);
            backtrack(candidates, target - candidates[i], track);
            track.removeLast();
        }
    }

    public  List<List<Integer>> combinationSum1(int[] candidates, int target) {
        LinkedList<Integer> track = new LinkedList<>();
        Arrays.sort(candidates);
        backtrack(candidates, 0, target, track);
        return res;
    }


    private static void backtrack(int[] candidates, int start, int target, LinkedList<Integer> track) {
        if (target < 0){
            return;
        }
        if (target == 0){
            combinationRes.add(new LinkedList<>(track));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (target < candidates[i]){
                break;
            }
            track.add(candidates[i]);
            backtrack(candidates,i,target-candidates[i],track);
            track.removeLast();
        }
    }


    /**
     *  组合总和 II
     *  给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的每个数字在每个组合中只能使用一次。
     * 方法 回溯算法+剪枝
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0){
            return res;
        }

        //先将数组排序
        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>(len);
        dfs(candidates, len, 0, target, path, res);
        return res;
    }

    /**
     *
     * @param candidates 候选数组
     * @param len 长度
     * @param begin 从候选数组的begin位置开始搜索
     * @param residue 表示剩余，这一值一开始等于target
     * @param path 从根节点到叶子节点的路径
     * @param res 结果
     */
    private void dfs(int[] candidates, int len, int begin, int residue, Deque<Integer> path, List<List<Integer>> res) {
        if (residue == 0){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < len; i++) {
            //大剪枝
            if (residue - candidates[i] < 0){
                break;
            }

            //小剪枝
            if (i > begin && candidates[i] == candidates[i - 1]){
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
        MyCombinationSum(0,0,target, candidates, res, list);
        return res;
    }
    public static void MyCombinationSum(int index,//下标
                                        int sum,//累加值
                                        int target,//目标值
                                        int[] candidates,
                                        List<List<Integer>> res,
                                        List<Integer> list){
        if (target == sum){
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            // 剪枝
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            sum += candidates[i];
            if (sum <= target){
                list.add(candidates[i]); //选择了这一条路
                MyCombinationSum(i+1,sum, target, candidates, res, list);
                list.remove(list.size()-1); //后悔了，不选了
            }else {
                return;//提前结束
            }
            sum -= candidates[i];
        }
    }


    public static void main(String[] args) {
        int[] nums = {2,3,5};
        new BackTrack().combinationSum1(nums,8);
        for (List<Integer> re : combinationRes) {
            System.out.println(re);
        }
    }
}
