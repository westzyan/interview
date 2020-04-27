package com.zyan.leetcode;


import java.util.*;

public class Main {


    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * <p>
     * 示例:
     * <p>
     * 给定 nums = [2, 7, 11, 15], target = 9
     * <p>
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        throw new IllegalArgumentException();
    }


    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     * <p>
     * 示例：
     * <p>
     * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     * <p>
     * 满足要求的三元组集合为：
     * [
     * [-1, 0, 1],
     * [-1, -1, 2]
     * ]
     * 三数之和为0
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeNum(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return Collections.emptyList();
        }
        Set<List<Integer>> result = new LinkedHashSet<>();

        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int head = i + 1;
            int tail = nums.length - 1;
            while (head < tail) {
                int sum = -(nums[head] + nums[tail]);
                if (sum == nums[i]) {
                    List<Integer> value = Arrays.asList(nums[i], nums[head], nums[tail]);
                    result.add(value);
                }
                if (sum <= nums[i]) {
                    tail--;
                } else {
                    head++;
                }
            }
        }

        return new ArrayList<>(result);
    }


    /**
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
     * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 示例：
     * <p>
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     * 盛最多水的容器
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            } else {
                --r;
            }
        }
        return ans;
    }


    /**
     * 无重复字符的最长子串
     * 们使用 HashSet 将字符存储在当前窗口 [i, j)[i,j)（最初 j = ij=i）中。
     * 然后我们向右侧滑动索引 jj，如果它不在 HashSet 中，
     * 我们会继续滑动 jj。直到 s[j] 已经存在于 HashSet 中。此时，
     * 我们找到的没有重复字符的最长子字符串将会以索引 ii 开头。
     * 如果我们对所有的 ii 这样做，就可以得到答案。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                j++;
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i));
                i++;
            }
        }
        return ans;
    }


    /**
     * 两数相加，两个数用链表实现
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;

            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);

            cur = cur.next;
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry == 1) {
            cur.next = new ListNode(1);
        }

        return pre.next;
    }


    /**
     * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     * 二叉树的右视图
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int count = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                count++;
                if (count == size) {
                    list.add(node.val);
                }
            }
        }
        return list;
    }


    /**
     * 寻找两个有序数组的中位数
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 0;
    }

    /**
     * 最长回文子串
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s.equals("")) {
            return "";
        }
        String origin = s;
        String reverse = new StringBuilder(s).reverse().toString();
        int length = s.length();
        int[][] arr = new int[length][length];
        int maxLen = 0;
        int maxEnd = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (origin.charAt(i) == reverse.charAt(j)) {
                    if (i == 0 || j == 0) {
                        arr[i][j] = 1;
                    } else {
                        arr[i][j] = arr[i - 1][j - 1] + 1;
                    }
                }

//                if (arr[i][j] > maxLen) {
//                    maxLen = arr[i][j];
//                    maxEnd = i; //以 i 位置结尾的字符
//                }

                if (arr[i][j] > maxLen) {
                    int beforeRev = length - 1 - j;
                    if (beforeRev + arr[i][j] - 1 == i) {//判断下标是否对应
                        maxLen = arr[i][j];
                        maxEnd = i;
                    }
                }
            }
        }
        return s.substring(maxEnd - maxLen + 1, maxEnd + 1);
    }


    /**
     * z字型变换
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        int i = 0, flag = -1;

        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            if (i == 0 || i == numRows - 1) {
                flag = -flag;
            }
            i = i + flag;
        }
        boolean flag1 = true;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            if (flag1) {
                i++;
            }
            if (!flag1) {
                i--;
            }
            if (i == 0 || i == numRows - 1) {
                flag1 = !flag1;
            }
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) {
            res.append(row);
        }
        return res.toString();
    }


    /**
     * 整数翻转
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {
        if (x >= 0) {
            String str = String.valueOf(x);
            str = new StringBuilder(str).reverse().toString();
            long ans = Long.parseLong(str);
            if (ans > Integer.MAX_VALUE) {
                return 0;
            } else {
                return (int) ans;
            }
        } else {
            String str = String.valueOf(x);
            str = new StringBuilder(str.substring(1)).reverse().toString();
            long ans = Long.parseLong(str);
            if (-ans < Integer.MIN_VALUE) {
                return 0;
            } else {
                return -(int) ans;
            }
        }
    }

    /**
     * 硬币问题
     * 动态规划，每次小循环只用一种硬币。
     * <p>
     * 若在一次for循环中处理四种情况(一个for里带四个硬币的处理情况)，每次计算新一项时，由于每次取的硬币是任意的，
     * 会出现对于不同的硬币取法，情况重复的现象。 例如：n=15时，res[15] = 1(全1) + res[15 - 5] + res[15 - 10] = 7，
     * 但10 + 5和5 + 10是重复的。
     * 每次小循环只用一种硬币可以避免重复，因为每次小循环中选用的硬币是固定的，在没有到对应硬币的循环前，
     * 表内记录对应的解必然不包含该硬币。 例如：n=15时，四次：res[15]=0 -> res[15] = 0 -> res[15] = 2 -> res[15] = 6
     * 实际上coins数组升序也不会影响结果
     *
     * @param n
     * @return
     */
    private final int mod = 1000000007;
    private final int[] coins = {25, 10, 5, 1};

    public int waysToChange(int n) {
        int[] res = new int[n + 1];
        res[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= n; i++) {
                res[i] = (res[i] + res[i - coin]) % mod;
            }
        }
        return res[n];
    }

    public int waysToChange1(int n) {
        int[] dp = new int[n + 1];
        int[] tokens = {1, 5, 10, 25};
        int mod = 1000000007;
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= n; j++) {
                if (tokens[i] == j) {  //硬币刚好等于当前面额
                    dp[j] = (dp[j] + 1) % mod;
                } else if (tokens[i] < j) {         //硬币小于当前面额
                    dp[j] = (dp[j] + dp[j - tokens[i]]) % mod;
                }                               //硬币大于当前面额，dp[j] = dp[j]，省略了
            }
        }
        return dp[n];
    }

    /**
     * 字符串转为整数
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        str = str.trim();
        if (str.length() == 0){
            return 0;
        }
        if (!Character.isDigit(str.charAt(0)) && str.charAt(0) != '-' && str.charAt(0) != '+'){
            return 0;
        }
        long ans = 0L;
        boolean neg = str.charAt(0) == '-';
        int i = !Character.isDigit(str.charAt(0)) ? 1 : 0;
        while (i < str.length() && Character.isDigit(str.charAt(i))) {
            ans = ans * 10 + (str.charAt(i) - '0');
            i++;
            if (!neg && ans > Integer.MAX_VALUE){
                ans = Integer.MAX_VALUE;
                break;
            }
            if (neg && ans > 1L + Integer.MAX_VALUE){
                ans = 1L + Integer.MAX_VALUE;
                break;
            }
        }
        return neg ?  (int)-ans : (int)ans;
    }



    public static void main(String[] args) {
        System.out.println("请输入一个数：");
        Scanner sc=new Scanner(System.in);
        String str=sc.next();
        //将用户输入的字符串转换成char数组
        char[]numChar=str.toCharArray();
        String[] chinese = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        String[] unit = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };
        StringBuffer sb=new StringBuffer();
        //获取用户输入的字符串长度
        int size=numChar.length;
        //遍历char数组
        for (int i = 0; i < numChar.length; i++) {
            //将用户输入的数字拿出来
            //比如用户输入1234数字
            //numChar['1','2','3','4']
            //numChar[0]
            //因此拿到的阿拉伯数字就是num=1
            int num = Integer.parseInt(String.valueOf(numChar[i]));
            //因为咱们的汉字和数组下标一一对应，所以可以直接将拿到的阿拉伯数字作为中文数组的下标取汉字
            //因此这时chinese[1]对应的就是汉字"一"
            //再就是打印一个汉字数字就加一个单位
            //通过观察可以找出阿拉伯数字和单位的关系，关系如下
            //1234长度为4，对应到计数单位数组4索引是”万”单位
            //1是千单位，以4-1就可以得到计数单位数组的"千”单位
            //4-1=3|unit[3]拿第一个千
            //4-2=2|unit[2]拿第二个百
            //4-3=1|unit[1]拿第三个十
            if(i!=0){
                //数组长度-i
                //上述对应成代码就是 size-i
                //因为计数单位数组省略了"个”单位
                //所以需要减去1，取出所有单位
                //单位+数字|千二百三十四
                sb.append(unit[size-i-1]+chinese[num]);
            }else{
                //这里是第一个数字，第一个数字前面没有计数单位，所以单独拿出处理
                sb.append(chinese[num]);
            }
        }
        //结果就是一千二百三十四
        System.out.println(sb);
    }


}


class ListNode {
    int val;
    ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
