package com.zyan.leetcode;

import java.util.*;

public class LeetCode41 {


    /**
     * 第一个缺失的整数
     * 1，简单的方法是使用一个set，先把所有的都放进去，然后从1到n+1依次遍历，如果不在，则返回，但是空间复杂度不符合题意
     * 2.把数组本身当成hash表，就把 11 这个数放到下标为 00 的位置， 22 这个数放到下标为 11 的位置，按照这种思路整理一遍数组。然后我们再遍历一次数组，第 11 个遇到的它的值不等于下标的那个数，就是我们要找的缺失的第一个正数。
     * 看评论说字节跳动面试时候考到了
     * <p>
     * 复杂度分析：
     * <p>
     * 时间复杂度：O(N)O(N)，这里 NN 是数组的长度。
     * 说明：while 循环不会每一次都把数组里面的所有元素都看一遍。如果有一些元素在这一次的循环中被交换到了它们应该在的位置，那么在后续的遍历中，由于它们已经在正确的位置上了，代码再执行到它们的时候，就会被跳过。
     * <p>
     * 最极端的一种情况是，在第 1 个位置经过这个 while 就把所有的元素都看了一遍，这个所有的元素都被放置在它们应该在的位置，那么 for 循环后面的部分的 while 的循环体都不会被执行。
     * <p>
     * 平均下来，每个数只需要看一次就可以了，while 循环体被执行很多次的情况不会每次都发生。这样的复杂度分析的方法叫做均摊复杂度分析。
     * <p>
     * 空间复杂度：O(1)O(1)。
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                //满足在指定位置内，并且没有放在正确的位置上，才交换
                swap(nums, nums[i] - 1, i);
            }
        }
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return len + 1;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    /**
     * 打家劫舍问题
     * 给出一个数组，相邻两个不能同时取值，找最大和
     * 明显的dp方法，dp[i]=max(dp[i−2]+nums[i],dp[i−1])
     * 边界条件dp[0]=nums[0]
     * dp[1]=max(nums[0],nums[1])
     * ​
     * <p>
     * 只有一间房屋，则偷窃该房屋
     * 只有两间房屋，选择其中金额较高的房屋进行偷窃
     * ​
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];
    }

    public int rob1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }


    /**
     * 接雨水问题
     * 解法一，每层每层计算，如果值开始比这一层小，不更新，出现比这一层大的，开始更新，如果遇到小的，temp++，遇到大的，
     * 累加，然后temp=0,时间复杂度为O(M*N)
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int sum = 0;
        int max = getMax(height);
        for (int i = 1; i <= max; i++) {
            boolean isStart = false;
            int temp = 0;
            for (int j = 0; j < height.length; j++) {
                if (isStart && height[j] < i) {
                    temp++;
                }
                if (height[j] >= i) {
                    sum = sum + temp;
                    temp = 0;
                    isStart = true;
                }
            }
        }
        return sum;
    }

    private int getMax(int[] height) {
        int max = 0;
        for (int value : height) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    /**
     * 接雨水问题的双指针方法
     * 牛逼
     * 我们先明确几个变量的意思：
     * <p>
     * left_max：左边的最大值，它是从左往右遍历找到的
     * right_max：右边的最大值，它是从右往左遍历找到的
     * left：从左往右处理的当前下标
     * right：从右往左处理的当前下标
     * 定理一：在某个位置i处，它能存的水，取决于它左右两边的最大值中较小的一个。
     * <p>
     * 定理二：当我们从左往右处理到left下标时，左边的最大值left_max对它而言是可信的，但right_max对它而言是不可信的。（见下图，由于中间状况未知，对于left下标而言，right_max未必就是它右边最大的值）
     * <p>
     * 定理三：当我们从右往左处理到right下标时，右边的最大值right_max对它而言是可信的，但left_max对它而言是不可信的。
     * <p>
     * right_max
     * left_max                             __
     * __                                |  |
     * |  |__   __??????????????????????  |  |
     * __|     |__|                       __|  |__
     * left                      right
     * 对于位置left而言，它左边最大值一定是left_max，右边最大值“大于等于”right_max，这时候，如果left_max<right_max成立，那么它就知道自己能存多少水了。无论右边将来会不会出现更大的right_max，都不影响这个结果。 所以当left_max<right_max时，我们就希望去处理left下标，反之，我们希望去处理right下标。
     *105
     * @param height
     * @return
     */
    public int trap1(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int ans = 0;
        while (left <= right) {
            if (leftMax < rightMax) {
                ans = ans + Math.max(0, leftMax - height[left]);
                leftMax = Math.max(leftMax, height[left]);
                left++;
            } else {
                ans = ans + Math.max(0, rightMax - height[right]);
                rightMax = Math.max(rightMax, height[right]);
                right--;
            }
        }
        return ans;
    }

    /**
     * 字符串相乘
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        // 保存计算结果
        String res = "0";

        // num2 逐位与 num1 相乘
        for (int i = num2.length() - 1; i >= 0; i--) {
            int carry = 0;
            // 保存 num2 第i位数字与 num1 相乘的结果
            StringBuilder temp = new StringBuilder();
            // 补 0
            for (int j = 0; j < num2.length() - 1 - i; j++) {
                temp.append(0);
            }
            int n2 = num2.charAt(i) - '0';

            // num2 的第 i 位数字 n2 与 num1 相乘
            for (int j = num1.length() - 1; j >= 0 || carry != 0; j--) {
                int n1 = j < 0 ? 0 : num1.charAt(j) - '0';
                int product = (n1 * n2 + carry) % 10;
                temp.append(product);
                carry = (n1 * n2 + carry) / 10;
            }
            // 将当前结果与新计算的结果求和作为新的结果
            res = addStrings(res, temp.reverse().toString());
        }
        return res;
    }

    /**
     * 对两个字符串数字进行相加，返回字符串形式的和
     */
    public String addStrings(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1;
             i >= 0 || j >= 0 || carry != 0;
             i--, j--) {
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            int sum = (x + y + carry) % 10;
            builder.append(sum);
            carry = (x + y + carry) / 10;
        }
        return builder.reverse().toString();
    }

    public String addStrings1(String num1, String num2) {
        if (num1 == null || num1.equals("0")) return num2;
        if (num2 == null || num2.equals("0")) return num1;

        int l1 = num1.length() - 1;
        int l2 = num2.length() - 1;
        int carry = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (l1 >= 0 || l2 >= 0) {
            int x = l1 >= 0 ? (num1.charAt(l1) - '0') : 0;
            int y = l2 >= 0 ? (num2.charAt(l2) - '0') : 0;
            int s = x + y + carry;
            stringBuilder.append(s % 10);
            carry = s / 10;
            l1--;
            l2--;
        }
        if (carry != 0) {
            stringBuilder.append("1");
        }
        return stringBuilder.reverse().toString();
    }


    /**
     * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
     * <p>
     * '?' 可以匹配任何单个字符。
     * '*' 可以匹配任意字符串（包括空字符串）。
     * 两个字符串完全匹配才算匹配成功。
     * <p>
     * 说明:
     * <p>
     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
     * <p>
     * 动态规划
     */
    public boolean isMatch(String s, String p) {
        // dp[i][j]表示s截止到第i个位置(s[i-1])的子串与p截止到第j个位置(p[j-1])的子串是否匹配
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        // dp[0][0]表示 空s 和 空p 匹配
        dp[0][0] = true;
        // for循环的开始条件是i=1,也就是没考虑 空s 的匹配情况，dp[0][0]只考虑了 空s匹配空p
        // 但 空s 也可以匹配 *,**,而且对于 *abc*, 空s的匹配情况可以简化
        // 这里对空s的匹配情况进行初始化
        for (int j = 1; j <= p.length(); j++) {
            // s匹配*,相当于s匹配空；s匹配**相当于s匹配*；s匹配*cb*相当于s匹配*cb
            if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 1];
        }
        // 注意 i,j表示的是s和p中第几个字符，对应的索引索引是i-1,j-1
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                // 根据模式串的当前位置的字符来分类讨论
                // 当前位置是 *
                // * 可以匹配任意字符包括空字符，所以考虑 dp[i-1][j-1],dp[i-1][j],dp[i][j-1]这前面已处理过的三个结果
                // 但其实 dp[i-1][j-1]不用考虑，因为考虑dp[i-1][j]时也会执行这个if，还是会考虑到dp[i-1][j-1]
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                    // 当前位置是 ？,可以匹配任意单个字符，所以和前一个位置结果一样
                    // p当前位置字符和s当前字符一样，所以跟前一个位置匹配结果一样
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        // 返回
        return dp[s.length()][p.length()];
    }

    // 状态 dp[i][j] : 表示 s 的前 i 个字符和 p 的前 j 个字符是否匹配 (true 的话表示匹配)
    // 状态转移方程：
    //      1. 当 s[i] == p[j]，或者 p[j] == ? 那么 dp[i][j] = dp[i - 1][j - 1];
    //      2. 当 p[j] == * 那么 dp[i][j] = dp[i][j - 1] || dp[i - 1][j]    其中：
    //      dp[i][j - 1] 表示 * 代表的是空字符，例如 ab, ab*
    //      dp[i - 1][j] 表示 * 代表的是非空字符，例如 abcd, ab*
    // 初始化：
    //      1. dp[0][0] 表示什么都没有，其值为 true
    //      2. 第一行 dp[0][j]，换句话说，s 为空，与 p 匹配，所以只要 p 开始为 * 才为 true
    //      3. 第一列 dp[i][0]，当然全部为 false
    public boolean isMatch1(String s, String p) {
        int m = s.length();
        int n = p.length();

        //状态 dp[i][j]:表示s的前i个字符和 p 的前 j 个字符是否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];

        //初始化
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            dp[0][i] = dp[0][i - 1] && p.charAt(i - 1) == '*';
        }

        //状态转移
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) && p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 跳跃游戏
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     * <p>
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * <p>
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     * <p>
     * 示例:
     * <p>
     * 输入: [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
     * <p>
     * 过程解析：
     * 最开始遍历 i = 0, end = 0,因此 step 会进行 step ++，我们可以认为，这是开始起跳，因为必定会落下，因此跳跃次数 + 1
     * 而 nums[0] 这个数限制了你只能在落脚在某个范围内，假如 nums[0] = 4，那么你只能选择落脚在 [1, 4] 位置，而如果到了边界，那么肯定是一次新的起跳，因此次数需要再 + 1
     * <p>
     * 从 0 位置开始起跳，你落脚的必定是 [1, 4] 位置中能够跳得更远的，因为根据贪心思想，这样做能够尽可能的减少跳跃次数，因为更加接近最后一个位置
     * 而在这个过程遍历 [1, 4] 过程中一直记录着最远位置 k，而你落地在 [1, 4] 之间，落地的那个点也就是 [1, 4] 之间最能够跳得远的那个位置，因此当到达边界的时候，将 end 更新为 k
     * <p>
     * 注意：[1, 4] 跳得最远的位置必定不会在 [1, 4] ，因为如果在 [1, 4] ，那么表示根本就出不去 [1, 4] 这个圈
     * 当然不会是 [4,1,1,1,0,1,2] 这种的，因为如果是这种的，压根过不去这个 0，因此必定第一次起跳必定能够跳出 [1, 4] 这个范围，比如 [4,1,1,1,1,1,0]
     * <p>
     * 解法：贪婪算法，每次选择能跳的最大的 我们每次在可跳范围内选择可以使得跳的更远的位置。
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int end = 0; //代表能跳的边界
        int maxPosition = 0;
        int steps = 0;
        //2 3 1 1 4 2 1
        for (int i = 0; i < nums.length - 1; i++) {
            //找能跳最远的
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if (i == end) { //遇到边界，更新边界，步数加1
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    //旋转图像
    //给定一个 n × n 的二维矩阵表示一个图像。
    //
    //将图像顺时针旋转 90 度。
    //
    //说明：
    //
    //你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
    //
    //示例 1:
    //
    //给定 matrix =
    //[
    //  [1,2,3],
    //  [4,5,6],
    //  [7,8,9]
    //],
    //
    //原地旋转输入矩阵，使其变为:
    //[
    //  [7,4,1],
    //  [8,5,2],
    //  [9,6,3]
    //]
    public void rotate(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        System.out.println(Arrays.deepToString(matrix));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
            }
        }
        System.out.println(Arrays.deepToString(matrix));
    }

    public void rotate1(int[][] matrix) {
        int n = matrix.length;

        // transpose matrix
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
        System.out.println(Arrays.deepToString(matrix));
        // reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
            }
        }
        System.out.println(Arrays.deepToString(matrix));
    }

    /**
     * 字母异位词分组
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     * <p>
     * 示例:
     * <p>
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出:
     * [
     * ["ate","eat","tea"],
     * ["nat","tan"],
     * ["bat"]
     * ]
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            if (map.containsKey(String.valueOf(chars))) {
                map.get(String.valueOf(chars)).add(str);
            } else {
                map.put(str, new ArrayList<String>());
                map.get(String.valueOf(chars)).add(str);
            }
        }
        return new ArrayList<>(map.values());
    }


    /**
     * 螺旋矩阵
     * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * [
     * [ 1, 2, 3 ],
     * [ 4, 5, 6 ],
     * [ 7, 8, 9 ]
     * ]
     * 输出: [1,2,3,6,9,8,7,4,5]
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix.length == 0) {
            return list;
        }
        int up = 0;
        int down = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (true) {
            // 最上面一行
            for (int col = left; col <= right; col++) {
                list.add(matrix[up][col]);
            }
            // 向下逼近
            up++;
            // 判断是否越界
            if (up > down) {
                break;
            }
            // 最右边一行
            for (int row = up; row <= down; row++) {
                list.add(matrix[row][right]);
            }
            right--;
            if (right < left) {
                break;
            }
            for (int col = right; col >= left; col--) {
                list.add(matrix[down][col]);
            }
            // 向上逼近
            down--;
            // 判断是否越界
            if (up > down) {
                break;
            }
            // 最左边一行
            for (int row = down; row >= up; row--) {
                list.add(matrix[row][left]);
            }
            // 向右逼近
            left++;
            // 判断是否越界
            if (left > right) {
                break;
            }
        }
        return list;
    }


    /**
     * 跳跃游戏
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     * <p>
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * <p>
     * 判断你是否能够到达最后一个位置。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [2,3,1,1,4]
     * 输出: true
     * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
     * <p>
     * 解题思路：
     * 如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点。
     * 可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新。
     * 如果可以一直跳到最后，就成功了。
     * 方  法所依据的核心特性：如果一个位置能够到达，那么这个位置左侧所有位置都能到达。
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                return false;
            }
            k = Math.max(k, i + nums[i]);
        }
        return true;
    }

    /**
     * 区间合并问题
     * 给出一个区间的集合，请合并所有重叠的区间。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [[1,3],[2,6],[8,10],[15,18]]
     * 输出: [[1,6],[8,10],[15,18]]
     * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * 可以被合并的区间一定是有交集的区间，前提是区间按照左端点排好序，这里的交集可以是一个点（例如例 2）。
     * <p>
     * 至于为什么按照左端点升序排序，这里要靠一点直觉猜想，我没有办法说清楚是怎么想到的，有些问题的策略是按照右端点升序排序（也有可能是降序排序，具体问题具体分析）。
     * <p>
     * 接着说，直觉上，只需要对所有的区间按照左端点升序排序，然后遍历。
     * <p>
     * 如果当前遍历到的区间的左端点 > 结果集中最后一个区间的右端点，说明它们没有交集，此时把区间添加到结果集；
     * 如果当前遍历到的区间的左端点 <= 结果集中最后一个区间的右端点，说明它们有交集，此时产生合并操作，即：对结果集中最后一个区间的右端点更新（取两个区间的最大值）。
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        int len = intervals.length;
        if (len < 2) {
            return intervals;
        }

        //按照起点排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 也可以使用 Stack，因为我们只关心结果集的最后一个区间
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);

        for (int i = 1; i < len; i++) {
            int[] cur = intervals[i];
            // 每次新遍历到的列表与当前结果集中的最后一个区间的末尾端点进行比较
            int[] peek = res.get(res.size() - 1);
            if (cur[0] > peek[1]) {
                res.add(cur);
            } else {
                // 注意，这里应该取最大
                peek[1] = Math.max(cur[1], peek[1]);
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    /**
     * 最后一个单词的长度
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        int end = s.length() - 1;
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }
        if (end < 0) {
            return 0;
        }
        int start = end;
        while (start >= 0 && s.charAt(start) != ' ') {
            start--;
        }
        return end - start;
    }

    /**
     * 螺旋矩阵2
     * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
     * <p>
     * 示例:
     * <p>
     * 输入: 3
     * 输出:
     * [
     * [ 1, 2, 3 ],
     * [ 8, 9, 4 ],
     * [ 7, 6, 5 ]
     * ]
     */
    public int[][] generateMatrix(int n) {
        if (n == 0) {
            return null;
        }
        int[][] mat = new int[n][n];
        int up = 0;
        int down = n - 1;
        int left = 0;
        int right = n - 1;
        int num = 1;
        while (num <= n * n) {
            for (int i = left; i <= right; i++) {
                mat[up][i] = num;
                num++;
            }
            up++;
            for (int i = up; i <= down; i++) {
                mat[i][right] = num;
                num++;
            }
            right--;
            for (int i = right; i >= left; i--) {
                mat[down][i] = num;
                num++;
            }
            down--;
            for (int i = down; i >= up; i--) {
                mat[i][left] = num;
                num++;
            }
            left++;
        }
        return mat;
    }

    /**
     * 下一个排列
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        boolean[] used = new boolean[n + 1];
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
        List<Integer> path = new ArrayList<>();
        getPermutationDFS(0, n, k, factorial, used, path);

        StringBuilder stringBuilder = new StringBuilder();
        for (Integer integer : path) {
            stringBuilder.append(integer);
        }
        return stringBuilder.toString();

    }

    private void getPermutationDFS(int index, int n, int k, int[] factorial, boolean[] used, List<Integer> path) {
        if (index == n) {
            return;
        }
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
            getPermutationDFS(index + 1, n, k, factorial, used, path);
        }
    }

    /**
     * 旋转链表
     * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->2->3->4->5->NULL, k = 2
     * 输出: 4->5->1->2->3->NULL
     * 解释:
     * 向右旋转 1 步: 5->1->2->3->4->NULL
     * 向右旋转 2 步: 4->5->1->2->3->NULL
     * 思路，连接成环，再截断
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode oldTail = head;
        int n;
        for (n = 1; oldTail.next != null; n++) {
            oldTail = oldTail.next;
        }
        oldTail.next = head;

        ListNode newTail = head;
        for (int i = 0; i < n - k % n - 1; i++) {
            newTail = newTail.next;
        }
        ListNode newHead = newTail.next;

        newTail.next = null;
        return newHead;
    }


    //解决不同路径问题
    //一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
    //
    //机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
    //
    //问总共有多少条不同的路径？
    // 多计算方法
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 因为在这里面每个点的路径都是这个点的 上一个点的路径数 + 左边点的路径数 ，因此只要将上一行和本行的数值记录下来就能推导目标点的
     * 路径数，可以将空间复杂度由 O(m * n) 优化为 O(2n)
     *
     * @param m -
     * @param n -
     * @return -
     */
    public int uniquePaths2(int m, int n) {
        int[] preLine = new int[n];
        int[] curLine = new int[n];
        Arrays.fill(preLine, 1);
        Arrays.fill(curLine, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                curLine[j] = preLine[j] + curLine[j - 1];
            }
            preLine = curLine.clone();
        }
        return curLine[n - 1];
    }

    /**
     * 从解法二可以看出，我们求一个点需要的是它头上的那个点和它本行左边的那个点，只要有这两个点，那么就能够计算出当前点
     * 所以可以直接将两行数据优化为一行，每次循环都会提前计算它左边的点，这个左边的点就可以理解成本行左边的那个点，而因为
     * 当前点还未进行计算，这个位置上实际存储的数据是它头上的那个点（上一行）的数据，因此空间复杂度优化为O(n)
     *
     * @param m -
     * @param n -
     * @return -
     */
    public int uniquePaths3(int m, int n) {
        int[] arr = new int[n];
        Arrays.fill(arr, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                arr[j] += arr[j - 1];
            }
        }
        return arr[n - 1];
    }


    //不同路径
    //一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
    //
    //机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
    //
    //现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
    //说明：m 和 n 的值均不超过 100。
    //
    //示例 1:
    //
    //输入:
    //[
    //  [0,0,0],
    //  [0,1,0],
    //  [0,0,0]
    //]
    //输出: 2
    //解释:
    //3x3 网格的正中间有一个障碍物。
    //从左上角到右下角一共有 2 条不同的路径：
    //1. 向右 -> 向右 -> 向下 -> 向下
    //2. 向下 -> 向下 -> 向右 -> 向右


    //事实上，这道题和上一道题类似，唯一的区别在于中间可能有障碍物，那么就把有障碍物的点视为不可达，不可达的意思就是没有路径（0条路径）可以到达当前点，这里用dp[i[[j]=0表示不可达。
    //
    //初始化第一行和第一列，障碍物后面都不可达
    //填充dp数组，分三种情况：
    //上边的点和左边的点都不可达，那么当前点不可达:dp[i][j] = 0
    //当前点是障碍物，那么当前点不可达:dp[i][j] = 0
    //上边的点或者左边的点可达，那么当前点就可达，路径数等于到上边点的路径数加到左边点的路径数:dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
    //事实上第一种情况和第三种情况是可以合并的，因为 上边的点和左边的点都不可达，那么当前点还是可以表示为dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length < 1)
            return 0;
        if (obstacleGrid[0].length < 1)
            return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];

        //边界处理，注意如果遇到了障碍，后面的点的路径数就都为0，要break
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) break;
            dp[i][0] = 1;
        }
        //边界处理，注意如果遇到了障碍，后面的点的路径数就都为0，要break
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) break;
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) continue; //如果这个点是障碍，则跳过，dp[i][j] = 0
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];

    }

    //直接初始化的方法
    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        obstacleGrid[0][0] = 1;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        for (int i = 1; i < n; i++) {
            obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1 : 0;
        }
        for (int i = 1; i < m; i++) {
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    obstacleGrid[i][j] = obstacleGrid[i][j - 1] + obstacleGrid[i - 1][j];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }
        return obstacleGrid[m - 1][n - 1];
    }


    /**
     * 最小路径和
     * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * <p>
     * 说明：每次只能向下或者向右移动一步。
     * <p>
     * 示例:
     * <p>
     * 输入:
     * [
     *   [1,3,1],
     * [1,5,1],
     * [4,2,1]
     * ]
     * 输出: 7
     * 解释: 因为路径 1→3→1→1→1 的总和最小。
     * <p>
     * 解题思路
     * 1、dp定义：dp[i][j]代表从grid[0][0]-grid[i][j]的最小路径和
     * 2、初始状态：dp[0][0] = grid[0][0], dp[0][j] = sum(grid[0][0-j]), dp[i][0] = sum(grid[0-i][0])
     * 3、状态方程：dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]
     * 4、所求答案：dp[grid.length - 1][grid[0].length - 1]
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (m == 0 || n == 0) {
            return 0;
        }
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 根据题意加一，没错就是加一这很重要，因为它是只加一的所以有可能的情况就只有两种：
     * <p>
     * 除 99 之外的数字加一；
     * 数字 99。
     * 加一得十进一位个位数为 00 加法运算如不出现进位就运算结束了且进位只会是一。
     * <p>
     * 所以只需要判断有没有进位并模拟出它的进位方式，如十位数加 11 个位数置为 00，如此循环直到判断没有再进位就退出循环返回结果。
     * <p>
     * 然后还有一些特殊情况就是当出现 9999、999999 之类的数字时，循环到最后也需要进位，出现这种情况时需要手动将它进一位。
     *
     * @param digits -
     * @return -
     */
    public int[] plusOne(int[] digits) {
        if (digits[digits.length - 1] < 9) {
            digits[digits.length - 1] += 1;
            return digits;
        }
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    /**
     * 二进制求和
     * 内置函数办法
     *
     * @param a -
     * @param b -
     * @return -
     */
    public String addBinary(String a, String b) {
        return Integer.toBinaryString(Integer.parseInt(a, 2) + Integer.parseInt(b, 2));
    }

    //位运算
    public String addBinary1(String a, String b) {
        StringBuilder stringBuilder = new StringBuilder();
        int radix = 2;
        int length1 = a.length();
        int length2 = b.length();
        int carry = 0;
        while (length1 > 0 || length2 > 0) {
            int val1 = length1 > 0 ? a.charAt(length1 - 1) - '0' : 0;
            int val2 = length2 > 0 ? b.charAt(length2 - 1) - '0' : 0;
            int sum = (val1 + val2 + carry) % radix;
            stringBuilder.append(sum);
            carry = (val1 + val2 + carry) / 2;
            length1--;
            length2--;
        }
        stringBuilder.append(carry == 0 ? "" : carry);
        return stringBuilder.reverse().toString();
    }

    /**
     * 求一个数的平方根
     * 方法一：二分法
     *
     * @param x -
     * @return -
     */
    public int mySqrt(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    // 牛顿迭代法
    public int mySqrt1(int x) {
        if (x == 0) {
            return 0;
        }

        double C = x, x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int) x0;
    }

    //尝试求三次方根
    public double mySqrt3(double x) {
        if (x == 0 || x == 1 || x == -1) {
            return x;
        }
        double l = x > 0 ? 0 : x;
        double r = x > 0 ? x : 0;
        double mid = l + (r - l) / 2;

        while (r - l > 1e-7) {
            if (mid * mid * mid > x) {
                r = mid;
            } else {
                l = mid;
            }
            mid = l + (r - l) / 2;
        }
        return mid;
    }

    /**
     * 爬楼梯
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n < 3) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int climbStairs1(int n) {
        if (n < 3) {
            return n;
        }
        int pre = 1, preOfPre = 1, cur = 1;
        for (int i = 2; i <= n; i++) {
            cur = pre + preOfPre;
            preOfPre = pre;
            pre = cur;
        }
        return cur;
    }

    /**
     * 简化路径，unix路径
     *
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] str = path.split("/");
        for (String s : str) {
            if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!s.equals("") && !s.equals(".")) {
                stack.push(s);
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
        StringBuilder ans = new StringBuilder();
        for (String s : stack) {
            ans.append("/").append(s);
        }
        return ans.toString();
    }

    /**
     * 1.此题主要考察的是栈,所以定义一个辅助栈;
     * 2.先把字符串以"/"为分隔符分割成数组,此时数组有"路径"、""、"."、".."这四种情况;
     * 3.遍历数组,当s[i].equals("..")并且栈不空时pop,当!s[i].equals("") && !s[i].equals(".") && !s[i].equals(".."),即s[i]是路径入栈;
     * 4.栈空,返回"/",栈非空,用StringBuffer做一个连接返回即可;
     *
     * @param path
     * @return
     */
    public String simplifyPath1(String path) {
        String[] s = path.split("/");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length; i++) {
            if (!stack.isEmpty() && s[i].equals("..")) {
                stack.pop();
            } else if (!s[i].equals("") && !s[i].equals(".") && !s[i].equals("..")) {
                stack.push(s[i]);
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < stack.size(); i++) {
            res.append("/").append(stack.get(i));
        }
        return res.toString();
    }


    /**
     * 矩阵置为零
     * 如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
     *
     * @param matrix -
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = 1;
                    cols[j] = 1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rows[i] == 1) {
                    matrix[i][j] = 0;
                }
                if (cols[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }
//        for (int j = 0; j < n; j++) {
//            for (int i = 0; i < m; i++) {
//
//            }
//        }

        System.out.println(Arrays.deepToString(matrix));
    }

    /**
     * 空间复杂度降低了，用原始矩阵的第一行和第一列记录，原始里面如果有0则用两个Boolean变量记录
     *
     * @param matrix
     */
    public void setZeroes1(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean row0_flag = false;
        boolean col0_flag = false;
        // 第一行是否有零
        for (int j = 0; j < col; j++) {
            if (matrix[0][j] == 0) {
                row0_flag = true;
                break;
            }
        }
        // 第一列是否有零
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == 0) {
                col0_flag = true;
                break;
            }
        }
        // 把第一行第一列作为标志位
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        // 置0
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (row0_flag) {
            for (int j = 0; j < col; j++) {
                matrix[0][j] = 0;
            }
        }
        if (col0_flag) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }
    }


    public static void main(String[] args) {
//        System.out.println(new LeetCode41().multiply("555", "1199999"));
        int[][] mat = {
                {0, 2, 3},
                {0, 1, 6},
                {7, 8, 9}
        };
//        int[][] mat1 = {
//                {1,2,3},
//                {4,5,6},
//                {7,8,9}
//        };
//        new LeetCode41().setZeroes(mat);
//        new LeetCode41().rotate1(mat1);
//        System.out.println(new LeetCode41().mySqrt(5));
//        System.out.println(new LeetCode41().simplifyPath1("/mm/../kj/k///kj../hkj/"));
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        for (Integer integer : stack) {
            System.out.println(integer);
        }
    }

}
