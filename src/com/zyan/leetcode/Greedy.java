package com.zyan.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class Greedy {


    /**
     * 最长回文串
     * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
     *
     * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
     *
     * 注意:
     * 假设字符串的长度不会超过 1010。
     *
     * 示例 1:
     *
     * 输入:
     * "abccccdd"
     *
     * 输出:
     * 7
     *
     * 解释:
     * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
     *
     *
     * 思路：贪心
     * 回文串是一个正着读和反着读都一样的字符串。以回文中心为分界线，对于回文串中左侧的字符 ch，在右侧对称的位置也会出现同样的字符。例如在字符串 "abba" 中，回文中心是 "ab|ba" 中竖线的位置，而在字符串 "abcba" 中，回文中心是 "ab(c)ba" 中的字符 "c" 本身。我们可以发现，在一个回文串中，只有最多一个字符出现了奇数次，其余的字符都出现偶数次。
     *
     * 那么我们如何通过给定的字符构造一个回文串呢？我们可以将每个字符使用偶数次，使得它们根据回文中心对称。在这之后，如果有剩余的字符，我们可以再取出一个，作为回文中心。
     *
     * @param s -
     * @return -
     */
    public int longestPalindrome(String s) {
        int[] count = new int[128];
        for (char c : s.toCharArray()) {
            count[c]++;
        }
        int ans = 0;
        for (int i : count) {
            //贪心，把所有的例如5个，取4个，6个取6个，
            ans = ans + i / 2 * 2;
            //如果i为奇数，并且ans偶数，ans加上去
            if (i % 2 == 1 && ans % 2 == 0){
                ans++;
            }
        }
        return ans;
    }

    public int longestPalindrome1(String s) {
        int[] count = new int[128];
        for (char c : s.toCharArray()) {
            count[c]++;
        }
        int ans = 0;
        boolean flag = true;
        for (int i : count) {
            //贪心，把所有的例如5个，取4个，6个取6个，
            ans = ans + i / 2 * 2;
            //如果i为奇数，并且ans偶数，ans加上去
            if (i % 2 == 1 && flag){
                ans++;
                flag = !flag;
            }
        }
        return ans;
    }


    /**
     * 本文解决一个很经典的贪心算法问题 Interval Scheduling（区间调度问题）。给你很多形如 [start, end] 的闭区间，
     * 请你设计一个算法，算出这些区间中最多有几个互不相交的区间。
     * 从区间集合 intvs 中选择一个区间 x，这个 x 是在当前所有区间中结束最早的（end 最小）。
     * 把所有与 x 区间相交的区间从区间集合 intvs 中删除。
     * 重复步骤 1 和 2，直到 intvs 为空为止。之前选出的那些 x 就是最大不相交子集。
     * 把这个思路实现成算法的话，可以按每个区间的 end 数值升序排序，因为这样处理之后实现步骤 1 和步骤 2 都方便很多:
     * 现在来实现算法，对于步骤 1，由于我们预先按照 end 排了序，所以选择 x 是很容易的。关键在于，如何去除与 x 相交的区间，选择下一轮循环的 x 呢？
     * 由于我们事先排了序，不难发现所有与 x 相交的区间必然会与 x 的 end 相交；如果一个区间不想与 x 的 end 相交，它的 start 必须要大于（或等于）x 的 end：
     * @param intvs -
     * @return -
     */
    public int intervalSchedule(int[][] intvs) {
        if (intvs.length == 0){
            return 0;
        }
        Arrays.sort(intvs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        // 至少有一个区间不相交
        int count = 1;
        // 排序后，第一个区间就是x
        int x_end = intvs[0][1];
        for (int[] intv : intvs) {
            int start = intv[0];
            if (start >= x_end){
                count++;
                x_end = intv[1];
            }
        }
        return count;
    }



    /**
     *     //无重叠区间
     *     给定一个区间的集合，找到需要移除区间的最小数量，使得剩余区间互不重叠
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        return n - intervalSchedule(intervals);
    }

    /**
     * 用最少数量的箭引爆气球
     * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以y坐标并不重要，因此只要知道开始和结束的x坐标就足够了。开始坐标总是小于结束坐标。平面内最多存在104个气球。
     *
     * 一支弓箭可以沿着x轴从不同点完全垂直地射出。在坐标x处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
     *      Example:
     *
     *      输入:
     *      [[10,16], [2,8], [1,6], [7,12]]
     *
     *      输出:
     *      2
     *
     *      解释:
     *      对于该样例，我们可以在x = 6（射爆[2,8],[1,6]两个气球）和 x = 11（射爆另外两个气球）。
     *
     * 其实稍微思考一下，这个问题和区间调度算法一模一样！如果最多有 n 个不重叠的区间，那么就至少需要 n 个箭头穿透所有区间：
     * 只是有一点不一样，在 intervalSchedule 算法中，如果两个区间的边界触碰，不算重叠；
     * 而按照这道题目的描述，箭头如果碰到气球的边界气球也会爆炸，所以说相当于区间的边界触碰也算重叠：
     * @param points -
     * @return -
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0){
            return 0;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        // 至少有一个区间不相交
        int count = 1;
        // 排序后，第一个区间就是x
        int x_end = points[0][1];
        for (int[] intv : points) {
            int start = intv[0];
            if (start > x_end){
                count++;
                x_end = intv[1];
            }
        }
        return count;
    }



}
