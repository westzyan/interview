package com.zyan.leetcode;

/**
 * @author zhangyan
 * @date 2020/9/13 上午10:46
 */
public class LeetCode392 {
    public boolean isSubSequence(String s, String t) {
        int n = s.length();
        int m = t.length();
        int i = 0;
        int j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }


    /**
     * 思路及算法
     *
     * 考虑前面的双指针的做法，我们注意到我们有大量的时间用于在 tt 中找到下一个匹配字符。
     *
     * 这样我们可以预处理出对于 tt 的每一个位置，从该位置开始往后每一个字符第一次出现的位置。
     *
     * 我们可以使用动态规划的方法实现预处理，令 f[i][j]f[i][j] 表示字符串 tt 中从位置 ii 开始往后字符 jj 第一次出现的位置。在进行状态转移时，如果 tt 中位置 ii 的字符就是 jj，那么 f[i][j]=if[i][j]=i，否则 jj 出现在位置 i+1i+1 开始往后，即 f[i][j]=f[i+1][j]f[i][j]=f[i+1][j]，因此我们要倒过来进行动态规划，从后往前枚举 ii。
     *
     * 这样我们可以写出状态转移方程：
     * f[i][j] = i t[i] = j
     * f[i][j] = f[i+1][j] t[i] != j
     *
     * 假定下标从 00 开始，那么 f[i][j]f[i][j] 中有 0 \leq i \leq m-10≤i≤m−1 ，对于边界状态 f[m-1][..]f[m−1][..]，我们置 f[m][..]f[m][..] 为 mm，让 f[m-1][..]f[m−1][..] 正常进行转移。这样如果 f[i][j]=mf[i][j]=m，则表示从位置 ii 开始往后不存在字符 jj。
     *
     * 这样，我们可以利用 ff 数组，每次 O(1)O(1) 地跳转到下一个位置，直到位置变为 mm 或 ss 中的每一个字符都匹配成功。
     *
     * 同时我们注意到，该解法中对 tt 的处理与 ss 无关，且预处理完成后，可以利用预处理数组的信息，线性地算出任意一个字符串 ss 是否为 tt 的子串。这样我们就可以解决「后续挑战」啦。
     * @param s
     * @param t
     * @return
     */
    public boolean isSubSequence1(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] f = new int[m + 1][26];
        for (int i = 0; i < 26; i++) {
            f[m][i] = m;
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (t.charAt(i) == j + 'a') {
                    f[i][j] = i;
                }else {
                    f[i][j] = f[i + 1][j];
                }
            }
        }
        int add = 0;
        for (int i = 0; i < n; i++) {
            if (f[add][s.charAt(i) - 'a'] == m) {
                return false;
            }
            add = f[add][s.charAt(i) - 'a'] + 1;
        }
        return true;
    }
}
