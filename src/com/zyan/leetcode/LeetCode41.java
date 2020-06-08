package com.zyan.leetcode;

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
     *
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
     *
     * '?' 可以匹配任何单个字符。
     * '*' 可以匹配任意字符串（包括空字符串）。
     * 两个字符串完全匹配才算匹配成功。
     *
     * 说明:
     *
     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
     *
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
            if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j-1];
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
        for (int i = 1; i <= n ; i++) {
            dp[0][i] = dp[0][i - 1] && p.charAt(i - 1) == '*';
        }

        //状态转移
        for (int i = 1; i <= m ; i++) {
            for (int j = 1; j <= n ; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) && p.charAt(j - 1) == '?'){
                    dp[i][j] = dp[i - 1][j - 1];
                }else if (p.charAt(j - 1) == '*'){
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 跳跃游戏
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     *
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     *
     * 示例:
     *
     * 输入: [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
     *
     * 过程解析：
     *         最开始遍历 i = 0, end = 0,因此 step 会进行 step ++，我们可以认为，这是开始起跳，因为必定会落下，因此跳跃次数 + 1
     *         而 nums[0] 这个数限制了你只能在落脚在某个范围内，假如 nums[0] = 4，那么你只能选择落脚在 [1, 4] 位置，而如果到了边界，那么肯定是一次新的起跳，因此次数需要再 + 1
     *
     *         从 0 位置开始起跳，你落脚的必定是 [1, 4] 位置中能够跳得更远的，因为根据贪心思想，这样做能够尽可能的减少跳跃次数，因为更加接近最后一个位置
     *         而在这个过程遍历 [1, 4] 过程中一直记录着最远位置 k，而你落地在 [1, 4] 之间，落地的那个点也就是 [1, 4] 之间最能够跳得远的那个位置，因此当到达边界的时候，将 end 更新为 k
     *
     *         注意：[1, 4] 跳得最远的位置必定不会在 [1, 4] ，因为如果在 [1, 4] ，那么表示根本就出不去 [1, 4] 这个圈
     *         当然不会是 [4,1,1,1,0,1,2] 这种的，因为如果是这种的，压根过不去这个 0，因此必定第一次起跳必定能够跳出 [1, 4] 这个范围，比如 [4,1,1,1,1,1,0]
     *
     * 解法：贪婪算法，每次选择能跳的最大的 我们每次在可跳范围内选择可以使得跳的更远的位置。
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
            if (i == end){ //遇到边界，更新边界，步数加1
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }


    public static void main(String[] args) {
        System.out.println(new LeetCode41().multiply("555", "1199999"));
    }

}
