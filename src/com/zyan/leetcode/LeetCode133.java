package com.zyan.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyan122 on 2020/8/4
 */
public class LeetCode133 {

    /**
     * 133. 克隆图
     * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
     *
     * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
     *
     * class Node {
     *     public int val;
     *     public List<Node> neighbors;
     * }
     *
     *
     * 测试用例格式：
     *
     * 简单起见，每个节点的值都和它的索引相同。例如，第一个节点值为 1（val = 1），第二个节点值为 2（val = 2），以此类推。该图在测试用例中使用邻接列表表示。
     *
     * 邻接列表 是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。
     *
     * 给定节点将始终是图中的第一个节点（值为 1）。你必须将 给定节点的拷贝 作为对克隆图的引用返回。
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {
        //todo
        return null;
    }

    class Node {
        public int val;
        public List<com.zyan.leetcode.Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<com.zyan.leetcode.Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<com.zyan.leetcode.Node>();
        }

        public Node(int _val, ArrayList<com.zyan.leetcode.Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }


    /**
     * 加油站问题
     * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
     *
     * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
     *
     * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
     *
     * 说明: 
     *
     * 如果题目有解，该答案即为唯一答案。
     * 输入数组均为非空数组，且长度相同。
     * 输入数组中的元素均为非负数。
     * 示例 1:
     *
     * 输入:
     * gas  = [1,2,3,4,5]
     * cost = [3,4,5,1,2]
     *
     * 输出: 3
     *
     * 解释:
     * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
     * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
     * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
     * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
     * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
     * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
     * 因此，3 可为起始索引。
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int totalTank = 0;
        int currTank = 0;
        int startStation = 0;
        for (int i = 0; i < n; i++) {
            totalTank = totalTank + gas[i] - cost[i];
            currTank = currTank + gas[i] - cost[i];
            if (currTank < 0) {
                startStation = i + 1;
                currTank = 0;
            }
        }
        return totalTank >= 0 ? startStation : -1;
    }
}
