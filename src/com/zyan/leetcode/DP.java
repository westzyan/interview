package com.zyan.leetcode;

import java.util.Arrays;


/**
 * 动态规划标准套路
 * 第一步要明确两点，「状态」和「选择」。
 * 先说状态，如何才能描述一个问题局面？只要给几个物品和一个背包的容量限制，就形成了一个背包问题呀。所以状态有两个，就是「背包的容量」和「可选择的物品」。
 * 再说选择，也很容易想到啊，对于每件物品，你能选择什么？选择就是「装进背包」或者「不装进背包」嘛。
 * 明白了状态和选择，动态规划问题基本上就解决了，只要往这个框架套就完事儿了：
 * for 状态1 in 状态1的所有取值：
 *     for 状态2 in 状态2的所有取值：
 *         for ...
 *             dp[状态1][状态2][...] = 择优(选择1，选择2...)
 *
 * 第二步要明确 dp 数组的定义。
 * 首先看看刚才找到的「状态」，有两个，也就是说我们需要一个二维 dp 数组。
 * dp[i][w] 的定义如下：对于前 i 个物品，当前背包的容量为 w，这种情况下可以装的最大价值是 dp[i][w]。
 * 比如说，如果 dp[3][5] = 6，其含义为：对于给定的一系列物品中，若只对前 3 个物品进行选择，当背包容量为 5 时，最多可以装下的价值为 6。
 * 根据这个定义，我们想求的最终答案就是 dp[N][W]。base case 就是 dp[0][..] = dp[..][0] = 0，因为没有物品或者背包没有空间的时候，能装的最大价值就是 0。
 *
 * int dp[N+1][W+1]
 * dp[0][..] = 0
 * dp[..][0] = 0
 *
 * for i in [1..N]:
 *     for w in [1..W]:
 *         dp[i][w] = max(
 *             把物品 i 装进背包,
 *             不把物品 i 装进背包
 *         )
 * return dp[N][W]
 *
 *
 * 第三步，根据「选择」，思考状态转移的逻辑。
 * 简单说就是，上面伪码中「把物品 i 装进背包」和「不把物品 i 装进背包」怎么用代码体现出来呢？
 * 这就要结合对 dp 数组的定义和我们的算法逻辑来分析了：
 * 先重申一下刚才我们的 dp 数组的定义：
 * dp[i][w] 表示：对于前 i 个物品，当前背包的容量为 w 时，这种情况下可以装下的最大价值是 dp[i][w]。
 * 如果你没有把这第 i 个物品装入背包，那么很显然，最大价值 dp[i][w] 应该等于 dp[i-1][w]，继承之前的结果。
 * 如果你把这第 i 个物品装入了背包，那么 dp[i][w] 应该等于 dp[i-1][w - wt[i-1]] + val[i-1]。
 * 首先，由于 i 是从 1 开始的，所以 val 和 wt 的索引是 i-1 时表示第 i 个物品的价值和重量。
 * 而 dp[i-1][w - wt[i-1]] 也很好理解：你如果装了第 i 个物品，就要寻求剩余重量 w - wt[i-1] 限制下的最大价值，加上第 i 个物品的价值 val[i-1]。
 * 综上就是两种选择，我们都已经分析完毕，也就是写出来了状态转移方程，可以进一步细化代码：
 * for i in [1..N]:
 *     for w in [1..W]:
 *         dp[i][w] = max(
 *             dp[i-1][w],
 *             dp[i-1][w - wt[i-1]] + val[i-1]
 *         )
 * return dp[N][W]
 */

public class DP {

    //最长上升子序列问题
    //给定一个无序的整数数组，找到其中最长上升子序列的长度。
    //
    //示例:
    //
    //输入: [10,9,2,5,3,7,101,18]
    //输出: 4
    //解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
    //思路就是dp[i] 表示以 nums[i] 这个数结尾的最长递增子序列的长度，那么后面的一个就可以和前面对比，
    //nums[5] = 3，既然是递增子序列，我们只要找到前面那些结尾比 3 小的子序列，然后把 3 接到最后，就可以形成一个新的递增子序列，而且这个新的子序列长度加一。
    //显然，可能形成很多种新的子序列，但是我们只选择最长的那一个，把最长子序列的长度作为 dp[5] 的值即可。
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        // base case：dp 数组全都初始化为 1
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int lengthOfLIS1(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length + 1];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int res = 0;
        for (int value : dp) {
            res = Math.max(res, value);
        }
        return res;
    }

    /**
     * 最大子序列和
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 示例:
     *
     * 输入: [-2,1,-3,4,-1,2,1,-5,4],
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length ; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        int res = dp[0];
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    //优化版
    public int maxSubArray1(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0){
                sum = sum + num;
            }else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    /**
     *  0 1背包问题
     *  dp[i][j]代表的是选择前i个物品是容量为j时候，最大价值
     * @param w
     * @param n
     * @param wt
     * @param val
     * @return
     */
    public int packet01(int w, int n, int[] wt, int[] val){
        int[][] dp = new int[n + 1][w + 1];
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= w ; j++) {
                if (j - wt[i - 1] < 0){
                    dp[i][j] = dp[i - 1][j];
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j - wt[i - 1]] + val[i - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[n][w];
    }

    /**
     * 子集背包问题
     * 输入一个集合，返回是否能够分割成和相等的两个子集
     * 给一个可装载重量为 sum / 2 的背包和 N 个物品，每个物品的重量为 nums[i]。现在让你装物品，是否存在一种装法，能够恰好将背包装满？
     *
     * 第一步要明确两点，「状态」和「选择」。
     * 这个前文 经典动态规划：背包问题 已经详细解释过了，状态就是「背包的容量」和「可选择的物品」，选择就是「装进背包」或者「不装进背包」。
     * 第二步要明确 dp 数组的定义。
     * 按照背包问题的套路，可以给出如下定义：
     * dp[i][j] = x 表示，对于前 i 个物品，当前背包的容量为 j 时，若 x 为 true，则说明可以恰好将背包装满，若 x 为 false，则说明不能恰好将背包装满。
     * 比如说，如果 dp[4][9] = true，其含义为：对于容量为 9 的背包，若只是用钱 4 个物品，可以有一种方法把背包恰好装满。
     * 或者说对于本题，含义是对于给定的集合中，若只对前 4 个数字进行选择，存在一个子集的和可以恰好凑出 9。
     * 根据这个定义，我们想求的最终答案就是 dp[N][sum/2]，base case 就是 dp[..][0] = true 和 dp[0][..] = false，因为背包没有空间的时候，就相当于装满了，而当没有物品可选择的时候，肯定没办法装满背包。
     *
     * 第三步，根据「选择」，思考状态转移的逻辑。
     * 回想刚才的 dp 数组含义，可以根据「选择」对 dp[i][j] 得到以下状态转移：
     * 如果不把 nums[i] 算入子集，或者说你不把这第 i 个物品装入背包，那么是否能够恰好装满背包，取决于上一个状态 dp[i-1][j]，继承之前的结果。
     * 如果把 nums[i] 算入子集，或者说你把这第 i 个物品装入了背包，那么是否能够恰好装满背包，取决于状态 dp[i - 1][j-nums[i-1]]。
     * 首先，由于 i 是从 1 开始的，而数组索引是从 0 开始的，所以第 i 个物品的重量应该是 nums[i-1]，这一点不要搞混。
     * dp[i - 1][j-nums[i-1]] 也很好理解：你如果装了第 i 个物品，就要看背包的剩余重量 j - nums[i-1] 限制下是否能够被恰好装满。
     * 换句话说，如果 j - nums[i-1] 的重量可以被恰好装满，那么只要把第 i 个物品装进去，也可恰好装满 j 的重量；否则的话，重量 j 肯定是装不满的。
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums){
        int sum = 0;
        for (int num : nums) {
            sum = sum + num;
        }
        if (sum % 2 != 0){
            return false;
        }
        int n = nums.length;
        sum = sum / 2;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= sum ; j++) {
                if (j - nums[i - 1] < 0){
                    // 背包容量不足，不能装入第 i 个物品
                    dp[i][j] = dp[i - 1][j];
                }else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][sum];
    }

    /**
     * 再进一步，是否可以优化这个代码呢？注意到 dp[i][j] 都是通过上一行 dp[i-1][..] 转移过来的，之前的数据都不会再使用了。
     * 所以，我们可以进行状态压缩，将二维 dp 数组压缩为一维，节约空间复杂度：
     * @param nums
     * @return
     */
    public boolean canPartition1(int[] nums){
        int sum = 0;
        for (int num : nums) {
            sum = sum + num;
        }
        if (sum % 2 != 0){
            return false;
        }
        int n = nums.length;
        sum = sum / 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 0; i < n ; i++) {
            for (int j = sum; j >= 0 ; j--) {
                if (j - nums[i] >= 0){
                    // 背包容量不足，不能装入第 i 个物品
                    dp[j] = dp[j] || dp[j - nums[i]];
                }
            }
        }
        return dp[sum];
    }

    /**
     * 硬币找零问题
     *如果你不把这第 i 个物品装入背包，也就是说你不使用 coins[i] 这个面值的硬币，那么凑出面额 j 的方法数 dp[i][j] 应该等于 dp[i-1][j]，继承之前的结果。
     * 如果你把这第 i 个物品装入了背包，也就是说你使用 coins[i] 这个面值的硬币，那么 dp[i][j] 应该等于 dp[i][j-coins[i-1]]。
     * 首先由于 i 是从 1 开始的，所以 coins 的索引是 i-1 时表示第 i 个硬币的面值。
     * dp[i][j-coins[i-1]] 也不难理解，如果你决定使用这个面值的硬币，那么就应该关注如何凑出金额 j - coins[i-1]。
     * 比如说，你想用面值为 2 的硬币凑出金额 5，那么如果你知道了凑出金额 3 的方法，再加上一枚面额为 2 的硬币，不就可以凑出 5 了嘛。
     * 综上就是两种选择，而我们想求的 dp[i][j] 是「共有多少种凑法」，所以 dp[i][j] 的值应该是以上两种选择的结果之和：
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins){
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= amount ; j++) {
                if (j - coins[i - 1] < 0){
                    dp[i][j] = dp[i - 1][j];
                }else { //
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                }
            }
        }
        return dp[n][amount];
    }

    /**
     * 优化版
     * 由于dp[i][j] 只与dp[i-1]有关系，所以可以优化
     * @param amount
     * @param coins
     * @return
     */
    public int change1(int amount, int[] coins){
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= amount ; j++) {
                if (j - coins[i - 1] >= 0){
                    dp[j] =  dp[j] + dp[j - coins[i - 1]];
                }
            }
        }
        return dp[amount];
    }

    /**
     * 最小编辑距离
     * @param s1
     * @param s2
     * @return
     */
    public int minDistance(String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= n ; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= m ; i++) {
            for (int j = 1; j <= n ; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 鸡蛋掉落问题
     * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
     *
     * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
     *
     * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
     *
     * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
     *
     * 你的目标是确切地知道 F 的值是多少。
     *
     * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
     * 动态规划，第一种方法，超时，但是思路正确
     * @param k
     * @param n
     * @return
     */
    public int superEggDrop(int k, int n){
        // dp[i][j]：一共有 i 层楼梯的情况下，使用 j 个鸡蛋的最少实验的次数
        // 注意：
        // 1、i 表示的是楼层的大小，不是第几层的意思，例如楼层区间 [8, 9, 10] 的大小为 3，这一点是在状态转移的过程中调整的定义
        // 2、j 表示可以使用的鸡蛋的个数，它是约束条件，我个人习惯放在后面的维度，表示消除后效性的意思

        // 0 个楼层和 0 个鸡蛋的情况都需要算上去，虽然没有实际的意义，但是作为递推的起点，被其它状态值所参考
        int[][] dp = new int[n + 1][k + 1];
        // 由于求的是最小值，因此初始化的时候赋值为一个较大的数，9999 或者 i 都可以
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], i);
        }
        // 初始化：填写下标为 0、1 的行和下标为 0、1 的列
        // 第 0 行：楼层为 0 的时候，不管鸡蛋个数多少，都测试不出鸡蛋的 F 值，故全为 0
        for (int j = 0; j <= k; j++) {
            dp[0][j] = 0;
        }
        // 第 1 行：楼层为 1 的时候，0 个鸡蛋的时候，扔 0 次，1 个以及 1 个鸡蛋以上只需要扔 1 次
        dp[1][0] = 0;
        for (int j = 1; j <= k ; j++) {
            dp[1][j] = 1;
        }
        // 第 0 列：鸡蛋个数为 0 的时候，不管楼层为多少，也测试不出鸡蛋的 F 值，故全为 0
        // 第 1 列：鸡蛋个数为 1 的时候，这是一种极端情况，要试出 F 值，最少次数就等于楼层高度（想想复杂度的定义）
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
            dp[i][1] = i;
        }
        for (int i = 2; i <= n ; i++) {
            for (int j = 2; j <= k ; j++) {
                for (int l = 1; l <= i ; l++) {
                    // 碎了，就需要往低层继续扔：层数少 1 ，鸡蛋也少 1
                    // 不碎，就需要往高层继续扔：层数是当前层到最高层的距离差，鸡蛋数量不少
                    // 两种情况都做了一次尝试，所以加 1
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[l - 1][j - 1], dp[i - l][j]) + 1);
                }
            }
        }
        return dp[n][k];
    }

    public int superEggDrop1(int k, int n){
        // dp[i][j]：一共有 i 层楼梯的情况下，使用 j 个鸡蛋的最少实验的次数
        // 注意：
        // 1、i 表示的是楼层的大小，不是第几层的意思，例如楼层区间 [8, 9, 10] 的大小为 3，这一点是在状态转移的过程中调整的定义
        // 2、j 表示可以使用的鸡蛋的个数，它是约束条件，我个人习惯放在后面的维度，表示消除后效性的意思

        // 0 个楼层和 0 个鸡蛋的情况都需要算上去，虽然没有实际的意义，但是作为递推的起点，被其它状态值所参考
        int[][] dp = new int[n + 1][k + 1];
        // 由于求的是最小值，因此初始化的时候赋值为一个较大的数，9999 或者 i 都可以
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], i);
        }
        // 初始化：填写下标为 0、1 的行和下标为 0、1 的列
        // 第 0 行：楼层为 0 的时候，不管鸡蛋个数多少，都测试不出鸡蛋的 F 值，故全为 0
        for (int j = 0; j <= k; j++) {
            dp[0][j] = 0;
        }
        // 第 1 行：楼层为 1 的时候，0 个鸡蛋的时候，扔 0 次，1 个以及 1 个鸡蛋以上只需要扔 1 次
        dp[1][0] = 0;
        for (int j = 1; j <= k ; j++) {
            dp[1][j] = 1;
        }
        // 第 0 列：鸡蛋个数为 0 的时候，不管楼层为多少，也测试不出鸡蛋的 F 值，故全为 0
        // 第 1 列：鸡蛋个数为 1 的时候，这是一种极端情况，要试出 F 值，最少次数就等于楼层高度（想想复杂度的定义）
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
            dp[i][1] = i;
        }
        for (int i = 2; i <= n ; i++) {
            for (int j = 2; j <= k ; j++) {
                int left = 1;
                int right = i;
                while (left < right){
                    int mid = left + (right - left + 1) / 2;
                    int breakCount = dp[mid - 1][j - 1];
                    int notBreakCount = dp[i - mid][j];
                    if (breakCount > notBreakCount){
                        // 排除法（减治思想）写对二分见第 35 题，先想什么时候不是解
                        // 严格大于的时候一定不是解，此时 mid 一定不是解
                        // 下一轮搜索区间是 [left, mid - 1]
                        right = mid - 1;
                    }else {
                        // 这个区间一定是上一个区间的反面，即 [mid, right]
                        // 注意这个时候取中间数要上取整，int mid = left + (right - left + 1) / 2;
                        left = mid;
                    }
                }
                // left 这个下标就是最优的 k 值，把它代入转移方程 Math.max(dp[k - 1][j - 1], dp[i - k][j]) + 1) 即可
                dp[i][j] = Math.max(dp[left - 1][j - 1], dp[i - left][j]) + 1;
            }
        }
        return dp[n][k];
    }

    //戳气球问题
    //原因在于，这个问题中我们每戳破一个气球 nums[i]，得到的分数和该气球相邻的气球 nums[i-1] 和 nums[i+1] 是有相关性的。
    //我们前文动态规划套路框架详解 说过运用动态规划算法的一个重要条件：子问题必须独立。所以对于这个戳气球问题，如果想用动态规划，必须巧妙地定义 dp 数组的含义，避免子问题产生相关性，才能推出合理的状态转移方程。
    //如何定义 dp 数组呢，这里需要对问题进行一个简单地转化。题目说可以认为 nums[-1] = nums[n] = 1，那么我们先直接把这两个边界加进去，形成一个新的数组 points：
    //int maxCoins(int[] nums) {
    //    int n = nums.length;
    //    // 两端加入两个虚拟气球
    //    int[] points = new int[n + 2];
    //    points[0] = points[n + 1] = 1;
    //    for (int i = 1; i <= n; i++) {
    //        points[i] = nums[i - 1];
    //    }
    //    // ...
    //}
    //现在气球的索引变成了从 1 到 n，points[0] 和 points[n+1] 可以认为是两个「虚拟气球」。
    //那么我们可以改变问题：在一排气球 points 中，请你戳破气球 0 和气球 n+1 之间的所有气球（不包括 0 和 n+1），使得最终只剩下气球 0 和气球 n+1 两个气球，最多能够得到多少分？
    //现在可以定义 dp 数组的含义：
    //dp[i][j] = x 表示，戳破气球 i 和气球 j 之间（开区间，不包括 i 和 j）的所有气球，可以获得的最高分数为 x。
    //那么根据这个定义，题目要求的结果就是 dp[0][n+1] 的值，而 base case 就是 dp[i][j] = 0，其中 0 <= i <= n+1, j <= i+1，因为这种情况下，开区间 (i, j) 中间根本没有气球可以戳。
    //// base case 已经都被初始化为 0
    //int[][] dp = new int[n + 2][n + 2];
    //现在我们要根据这个 dp 数组来推导状态转移方程了，根据我们前文的套路，所谓的推导「状态转移方程」，实际上就是在思考怎么「做选择」，也就是这道题目最有技巧的部分：
    //不就是想求戳破气球 i 和气球 j 之间的最高分数吗，如果「正向思考」，就只能写出前文的回溯算法；我们需要「反向思考」，想一想气球 i 和气球 j 之间最后一个被戳破的气球可能是哪一个？
    //其实气球 i 和气球 j 之间的所有气球都可能是最后被戳破的那一个，不防假设为 k。回顾动态规划的套路，这里其实已经找到了「状态」和「选择」：i 和 j 就是两个「状态」，最后戳破的那个气球 k 就是「选择」。
    //根据刚才对 dp 数组的定义，如果最后一个戳破气球 k，dp[i][j] 的值应该为：
    //dp[i][j] = dp[i][k] + dp[k][j]
    //         + points[i]*points[k]*points[j]
    //你不是要最后戳破气球 k 吗？那得先把开区间 (i, k) 的气球都戳破，再把开区间 (k, j) 的气球都戳破；最后剩下的气球 k，相邻的就是气球 i 和气球 j，这时候戳破 k 的话得到的分数就是 points[i]*points[k]*points[j]。
    //那么戳破开区间 (i, k) 和开区间 (k, j) 的气球最多能得到的分数是多少呢？嘿嘿，就是 dp[i][k] 和 dp[k][j]，这恰好就是我们对 dp 数组的定义嘛！
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] points = new int[n + 2];
        points[0] = points[n + 1] = 1;
        for (int i = 1; i <= n ; i++) {
            points[i] = nums[i - 1];
        }
        // base case 已经都被初始化为 0
        int[][] dp = new int[n + 2][n + 2];
        // 开始状态转移
        // i 应该从下往上
        for (int i = n; i >=0 ; i--) {
            // j 应该从左往右
            for (int j = i + 1; j < n + 2 ; j++) {
                // 最后戳破的气球是哪个？
                //那么，对于一组给定的 i 和 j，我们只要穷举 i < k < j 的所有气球 k，选择得分最高的作为 dp[i][j] 的值即可，这也就是状态转移方程：
                for (int k = i + 1; k < j ; k++) {
                    //择优做选择
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + points[i]*points[j]*points[k]);
                }
            }
        }
        return dp[0][n + 1];
    }

    //最长公共子序列问题
    //如果 S[i] == T[j] 则dp[i][j] = dp[i-1][j-1] + 1
    //
    //否则dp[i][j] = max(dp[i][j-1],dp[i-1][j])
    //
    public int longestCommonSubsequence(String text1, String text2) {
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        int[][] dp = new int[s1.length + 1][s2.length + 1];
        for (int i = 1; i <= s1.length ; i++) {
            for (int j = 1; j <= s2.length ; j++) {
                //如果末端相同
                if (s1[i - 1] == s2[j - 1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[s1.length][s2.length];
    }




    public static void main(String[] args) {
        int w = 4, n = 3;
        int[] wt = {2,1,3};
        int[] val = {4, 2, 3};
//        System.out.println(new DP().packet01(w,n,wt,val));
        //System.out.println(new DP().minDistance("werdffasdjfkl","fdsafjflsadk"));
        System.out.println(new DP().superEggDrop(3, 9));
    }




}
