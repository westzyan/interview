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
     * 最接近的三数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (Math.abs(target - sum) < Math.abs(target - ans)) {
                    ans = sum;
                }
                if (sum > target) {
                    end--;
                } else if (sum < target) {
                    start++;
                } else {
                    return ans;
                }
            }
        }
        return ans;
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
     * 找零问题
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
     *
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }
        if (!Character.isDigit(str.charAt(0)) && str.charAt(0) != '-' && str.charAt(0) != '+') {
            return 0;
        }
        long ans = 0L;
        boolean neg = str.charAt(0) == '-';
        int i = !Character.isDigit(str.charAt(0)) ? 1 : 0;
        while (i < str.length() && Character.isDigit(str.charAt(i))) {
            ans = ans * 10 + (str.charAt(i) - '0');
            i++;
            if (!neg && ans > Integer.MAX_VALUE) {
                ans = Integer.MAX_VALUE;
                break;
            }
            if (neg && ans > 1L + Integer.MAX_VALUE) {
                ans = 1L + Integer.MAX_VALUE;
                break;
            }
        }
        return neg ? (int) -ans : (int) ans;
    }


    /**
     * 判断是否为回文数
     * 关键是不转换为字符串解决问题
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {

        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x = x / 10;
        }
        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }

    /**
     * 整数转为罗马数字
     * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     * <p>
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     * <p>
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 3
     * 输出: "III"
     * 示例 2:
     * <p>
     * 输入: 4
     * 输出: "IV"
     * 示例 3:
     * <p>
     * 输入: 9
     * 输出: "IX"
     * 示例 4:
     * <p>
     * 输入: 58
     * 输出: "LVIII"
     * 解释: L = 50, V = 5, III = 3.
     * 示例 5:
     * <p>
     * 输入: 1994
     * 输出: "MCMXCIV"
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     * <p>
     * <p>
     * 1.枚举法，牛逼
     * String[][] c = {
     * {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
     * {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
     * {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
     * {"", "M", "MM", "MMM"}
     * };
     * return c[3][num / 1000] +
     * c[2][num / 100 % 10] +
     * c[1][num / 10 % 10] +
     * c[0][num % 10];
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        // 把阿拉伯数字与罗马数字可能出现的所有情况和对应关系，放在两个数组中
        // 并且按照阿拉伯数字的大小降序排列，这是贪心选择思想
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        while (index < 13) {
            while (num >= nums[index]) {
                // 注意：这里是等于号，表示尽量使用大的"面值"
                stringBuilder.append(romans[index]);
                num = num - nums[index];
            }
            index++;
        }
        return stringBuilder.toString();
    }

    /**
     * 罗马数字转为数字
     * 思路 ：把一个小值放在大值的左边，就是做减法，否则为加法。
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int num = getValue(s.charAt(i));
            if (preNum < num) {
                sum = sum - preNum;
            } else {
                sum = sum + preNum;
            }
            preNum = num;
        }
        sum = sum + preNum;
        return sum;
    }

    private int getValue(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    public int romanToInt1(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);
        int ans = 0;
        for (int i = 0; i < s.length(); ) {
            if (i + 1 < s.length() && map.containsKey(s.substring(i, i + 2))) {
                ans = ans + map.get(s.substring(i, i + 2));
                i = i + 2;
            } else {
                ans = ans + map.get(s.substring(i, i + 1));
                i = i + 1;
            }
        }
        return ans;
    }


    /**
     * 最长公共前缀
     * 先找到最短的，两两比较，如果不等，就把最后一个去掉
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
//        for (String s : strs) {
//            if (s.length() > prefix.length()) {
//                prefix = s;
//            }
//        } 这里有问题
        for (int i = 1; i < strs.length; i++) {
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    /**
     * 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if (digits.length() != 0) {
            backtrack("", digits);
        }
        return output;
    }

    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    List<String> output = new ArrayList<>();

    public void backtrack(String combination, String nextDigits) {
        if (nextDigits.length() == 0) {
            output.add(combination);
        } else {
            String digit = nextDigits.substring(0, 1);
            String letters = phone.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = phone.get(digit).substring(i, i + 1);
                backtrack(combination + letter, nextDigits.substring(1));
            }
        }
    }

    public void backtrack1(String combination, String nextDigits) {
        if (nextDigits.length() == 0) {
            output.add(combination);
        } else {
            String digit = nextDigits.substring(0, 1);
            String letters = phone.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = phone.get(digit).substring(i, i + 1);
                backtrack1(combination + letter, nextDigits.substring(1));
            }
        }
    }


    /**
     * 四数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        if (nums.length < 4) {
            return lists;
        }
        int length = nums.length;
        int a, b, c, d;
        for (a = 0; a <= nums.length - 4; a++) {
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            //获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏
            int min1 = nums[a] + nums[a + 1] + nums[a + 2] + nums[a + 3];
            if (min1 > target) {
                break;
            }
            //获取当前最大值，如果最大值比目标值小，说明后面越来越小的值根本没戏，忽略
            int max1 = nums[a] + nums[length - 1] + nums[length - 2] + nums[length - 3];
            if (max1 < target) {
                continue;
            }
            for (b = a + 1; b <= nums.length - 3; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }
                c = b + 1;
                d = nums.length - 1;
                /*获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏，忽略*/
                int min = nums[a] + nums[b] + nums[c] + nums[c + 1];
                if (min > target) {
                    continue;
                }
                /*获取当前最大值，如果最大值比目标值小，说明后面越来越小的值根本没戏，忽略*/
                int max = nums[a] + nums[b] + nums[d] + nums[d - 1];
                if (max < target) {
                    continue;
                }
                while (c < d) {
                    if (nums[a] + nums[b] + nums[c] + nums[d] < target) {
                        c++;
                    } else if (nums[a] + nums[b] + nums[c] + nums[d] > target) {
                        d--;
                    } else {
                        lists.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                        while (c < d && nums[c + 1] == nums[c]) {//确保nums[c] 改变了 做一下优化
                            c++;
                        }
                        while (c < d && nums[d - 1] == nums[d]) {//确保nums[c] 改变了
                            d--;
                        }
                        c++;
                        d--;
                    }
                }
            }
        }
        return lists;
    }

    /**
     * 删除链表中倒数第n个节点，n有效
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        for (int i = 0; i < n + 1; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                char top = stack.isEmpty() ? '#' : stack.pop();
                if (top != map.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    /**
     * 合并两个有序链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(0);
        ListNode prev = preHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return preHead.next;
    }


    /**
     * 括号生成
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 输入：n = 3
     * 输出：[
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     * <p>
     * 暴力方法：生成所有可能的序列，然后逐一判断
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<>();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    private void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current)) {
                result.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }

    /**
     * 判断小括号序列是否合法
     *
     * @param current
     * @return
     */
    private boolean valid(char[] current) {
        int balance = 0;
        for (char c : current) {
            if (c == '(') {
                balance++;
            } else {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }

    /**
     * 方法一还有改进的余地：我们可以只在序列仍然保持有效时才添加 '(' or ')'，而不是像 方法一 那样每次添加。我们可以通过跟踪到目前为止放置的左括号和右括号的数目来做到这一点，
     * <p>
     * 如果左括号数量不大于 nn，我们可以放一个左括号。如果右括号数量小于左括号的数量，我们可以放一个右括号。
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis1(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }

        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    /**
     * n = 2 为例，画树形结构图。方法是 “做减法”。
     * 画图以后，可以分析出的结论：
     * <p>
     * 当前左右括号都有大于 0 个可以使用的时候，才产生分支；
     * <p>
     * 产生左分支的时候，只看当前是否还有左括号可以使用；
     * <p>
     * 产生右分支的时候，还受到左分支的限制，右边剩余可以使用的括号数量一定得在严格大于左边剩余的数量的时候，才可以产生分支；
     * <p>
     * 在左边和右边剩余的括号数都等于 0 的时候结算。
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        dfs("", n, n, res);
        return res;
    }

    /**
     * @param curStr 当前递归得到的结果
     * @param left   左括号还有几个可以使用
     * @param right  右括号还有几个可以使用
     * @param res    结果集
     */
    private void dfs(String curStr, int left, int right, List<String> res) {
        // 因为每一次尝试，都使用新的字符串变量，所以无需回溯
        // 在递归终止的时候，直接把它添加到结果集即可，注意与「力扣」第 46 题、第 39 题区分
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }
        if (left > right) {// 剪枝（如图，左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节）
            return;
        }
        if (left > 0) {
            dfs(curStr + "(", left - 1, right, res);
        }
        if (right > 0) {
            dfs(curStr + ")", left, right - 1, res);
        }
    }

    /**
     * 递归方法dp[i] = "(" + dp[可能的括号对数] + ")" + dp[剩下的括号对数]
     * dp[i] = "(" + dp[j] + ")" + dp[i- j - 1] , j = 0, 1, ..., i - 1
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis3(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        // 这里 dp 数组我们把它变成列表的样子，方便调用而已
        List<List<String>> dp = new ArrayList<>(n);

        List<String> dp0 = new ArrayList<>();
        dp0.add("");
        dp.add(dp0);

        for (int i = 1; i <= n; i++) {
            List<String> cur = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                List<String> str1 = dp.get(j);
                List<String> str2 = dp.get(i - 1 - j);
                for (String s1 : str1) {
                    for (String s2 : str2) {
                        // 枚举右括号的位置
                        cur.add("(" + s1 + ")" + s2);
                    }
                }
            }
            dp.add(cur);
        }
        return dp.get(n);
    }

    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    private double quickMul(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double y = quickMul(x, n / 2);
        return n % 2 == 0 ? y * y : y * y * x;
    }

    /**
     * 这里用了一个巧妙的方法，如果是奇数，则乘过去
     *
     * @param x
     * @param N
     * @return
     */
    private double quickMul1(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }

    public int singleNumber(int[] nums) {
        int number = nums[0];
        for (int i = 1; i < nums.length; i++) {
            number = number ^ nums[i];
        }
        return number;
    }

    /**
     * 合并k个链表
     * 简单的思路是先把所有元素取出来，放到一个数组中，快排，然后再构建链表 空间复杂度较高
     * <p>
     * 或者用一个优先队列，将k个链表的头结点放到队列中，每次取出来第一个，就是最小的，然后将后续的放进去
     * 时间复杂度：O(n*log(k))，n 是所有链表中元素的总和，k 是链表个数。
     * <p>
     * 或者就是逐一对比，循环k-1次
     * <p>
     * 或者分治法，两两合并，再两两合并，类似于归并排序
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return Integer.compare(o1.val, o2.val);
            }
        });
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        for (ListNode list : lists) {
            if (list != null) {
                queue.add(list);
            }
        }
        while (!queue.isEmpty()) {
            p.next = queue.poll();
            p = p.next;
            if (p.next != null) {
                queue.add(p.next);
            }
        }
        return dummy.next;
    }

    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int mid = (left + right) / 2;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }


    /**
     * 链表两两节点交换，不能只修改值
     * 迭代法
     * firstNode（即 A） 和 secondNode（即 B） 分别遍历偶数节点和奇数节点，即两步看作一步。
     * 交换两个节点：
     * firstNode.next = secondNode.next
     * secondNode.next = firstNode
     * 还需要更新 prevNode.next 指向交换后的头
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode preNode = dummy;
        while (head != null && head.next != null) {
            ListNode firstNode = head;
            ListNode secondNode = head.next;

            preNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            preNode = firstNode;
            head = firstNode.next;
        }
        return dummy.next;
    }

    /**
     * 递归法
     * 从链表的头节点 head 开始递归。
     * 每次递归都负责交换一对节点。由 firstNode 和 secondNode 表示要交换的两个节点。
     * 下一次递归则是传递的是下一对需要交换的节点。若链表中还有节点，则继续递归。
     * 交换了两个节点以后，返回 secondNode，因为它是交换后的新头。
     * 在所有节点交换完成以后，我们返回交换后的头，实际上是原始链表的第二个节点。
     *
     * @param head
     * @return
     */
    public ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode firstNode = head;
        ListNode secondNode = head.next;

        firstNode = swapPairs1(secondNode.next);
        secondNode.next = firstNode;

        return secondNode;
    }

    /**
     * 翻转链表，常规解法
     * 头插法，
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /**
     * 递归方法
     *
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    /**
     * k个一组反转链表
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }
        ListNode tail = head;
        // 找该组k个节点的尾节点
        for (int i = 1; i < k && tail != null; i++) {
            tail = tail.next;
        }
        // 如果为空，说明长度不够，原顺序返回
        if (tail == null) {
            return head;
        }
        // 记录一下下一组的头结点
        ListNode next = tail.next;
        reverse(head, tail);
        // 连接上下一组的头结点
        head.next = reverseKGroup(next, k);
        return tail;
    }

    private void reverse(ListNode head, ListNode tail) {
        if (head == tail) {
            return;
        }
        reverse(head.next, tail);
        head.next.next = head;
        head.next = null;
    }

    /**
     * 先计算长度的解法
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup1(ListNode head, int k) {
        ListNode dummy = new ListNode(0), prev = dummy, curr = head, next;
        dummy.next = head;
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        head = dummy.next;
        for (int i = 0; i < length / k; i++) {
            for (int j = 0; j < k - 1; j++) {
                next = curr.next;
                curr.next = next.next;
                next.next = prev.next;
                prev.next = next;
            }
            prev = curr;
            curr = prev.next;
        }
        return dummy.next;
    }

    public ListNode reverseKGroup2(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = head;
        ListNode tail = head;
        while (true) {
            int count = 0;
            while (tail != null && count != k) {
                count++;
                tail = tail.next;
            }
            if (tail == null) {
                break;
            }
            ListNode head1 = pre.next;
            while (pre.next != tail) {
                ListNode cur = pre.next;
                pre.next = cur.next;
                cur.next = tail.next;
                tail.next = cur;
            }
            pre = head1;
            tail = head1;
        }
        return dummy.next;
    }

    /**
     * 删除排序数组的重复项
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        //使用双指针
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0, j = 1;
        while (j < nums.length) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
            j++;
        }
        return i + 1;
    }

    /**
     * 移除元素，如果元素值与给定值相等，移除
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    /**
     * 两数相除，给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
     * <p>
     * 返回被除数 dividend 除以除数 divisor 得到的商。
     * <p>
     * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        boolean flag = true;
        if (dividend < 0) {
            flag = !flag;
        }
        if (divisor < 0) {
            flag = !flag;
        }
        long dividend1 = (long) dividend;
        long divisor1 = (long) divisor;
        dividend1 = dividend1 < 0 ? -dividend1 : dividend1;
        divisor1 = divisor1 < 0 ? -divisor1 : divisor1;
        if (dividend1 < divisor1) {
            return 0;
        }
        long ans = 0L;
        while (dividend1 >= divisor1) {
            dividend1 = dividend1 - divisor1;
            ans++;
        }
        if (ans > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return flag ? (int) ans : (int) (-ans);
    }

    /**
     * 两数相除，给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
     * 解题思路：这题是除法，所以先普及下除法术语
     * 商，公式是：(被除数-余数)÷除数=商，记作：被除数÷除数=商...余数，是一种数学术语。
     * 在一个除法算式里，被除数、余数、除数和商的关系为：(被除数-余数)÷除数=商，记作：被除数÷除数=商...余数，
     * 进而推导得出：商×除数+余数=被除数。
     * <p>
     * 要求商，我们首先想到的是减法，能被减多少次，那么商就为多少，但是明显减法的效率太低
     * <p>
     * 那么我们可以用位移法，因为计算机在做位移时效率特别高，向左移1相当于乘以2，向右位移1相当于除以2
     * <p>
     * 我们可以把一个dividend（被除数）先除以2^n，n最初为31，不断减小n去试探,当某个n满足dividend/2^n>=divisor时，
     * <p>
     * 表示我们找到了一个足够大的数，这个数*divisor是不大于dividend的，所以我们就可以减去2^n个divisor，以此类推
     * <p>
     * 我们可以以100/3为例
     * <p>
     * 2^n是1，2，4，8...2^31这种数，当n为31时，这个数特别大，100/2^n是一个很小的数，肯定是小于3的，所以循环下来，
     * <p>
     * 当n=5时，100/32=3, 刚好是大于等于3的，这时我们将100-32*3=4，也就是减去了32个3，接下来我们再处理4，同样手法可以再减去一个3
     * <p>
     * 所以一共是减去了33个3，所以商就是33
     * <p>
     * 这其中得处理一些特殊的数，比如divisor是不能为0的，Integer.MIN_VALUE和Integer.MAX_VALUE
     */
    public int divide1(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean negative;
        negative = (dividend ^ divisor) < 0;//用异或来计算是否符号相异
        long t = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);
        int result = 0;
        for (int i = 31; i >= 0; i--) {
            if ((t >> i) >= d) {//找出足够大的数2^n*divisor
                result += 1 << i;//将结果加上2^n
                t -= d << i;//将被除数减去2^n*divisor
            }
        }
        return negative ? -result : result;//符号相异取反
    }

    /**
     * 下一个排列
     * 首先，我们观察到对于任何给定序列的降序，没有可能的下一个更大的排列。
     *
     * 例如，以下数组不可能有下一个排列：
     *
     * [9, 5, 4, 3, 1]
     * 我们需要从右边找到第一对两个连续的数字 a[i]a[i] 和 a[i-1]a[i−1]，它们满足 a[i]>a[i-1]a[i]>a[i−1]。现在，没有对 a[i-1]a[i−1] 右侧的重新排列可以创建更大的排列，因为该子数组由数字按降序组成。因此，我们需要重新排列 a[i-1]a[i−1] 右边的数字，包括它自己。
     *
     * 现在，什么样子的重新排列将产生下一个更大的数字呢？我们想要创建比当前更大的排列。因此，我们需要将数字 a[i-1]a[i−1] 替换为位于其右侧区域的数字中比它更大的数字，例如 a[j]a[j]。
     * 我们交换数字 a[i-1]a[i−1] 和 a[j]a[j]。我们现在在索引 i-1i−1 处有正确的数字。 但目前的排列仍然不是我们正在寻找的排列。我们需要通过仅使用 a[i-1]a[i−1]右边的数字来形成最小的排列。 因此，我们需要放置那些按升序排列的数字，以获得最小的排列。
     * 但是，请记住，在从右侧扫描数字时，我们只是继续递减索引直到我们找到 a[i]a[i] 和 a[i-1]a[i−1] 这对数。其中，a[i] > a[i-1]a[i]>a[i−1]。因此，a[i-1]a[i−1] 右边的所有数字都已按降序排序。此外，交换 a[i-1]a[i−1] 和 a[j]a[j] 并未改变该顺序。因此，我们只需要反转 a[i-1]a[i−1] 之后的数字，以获得下一个最小的字典排列。
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i , j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }




    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }


    /**
     * 搜索旋转排序数组，排序数组如 5678901234
     * 先根据 nums[mid] 与 nums[lo] 的关系判断 mid 是在左段还是右段，接下来再判断 target 是在 mid 的左边还是右边，从而来调整左右边界 lo 和 hi
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target){
        int low = 0, high = nums.length - 1, mid = 0;
        while (low <= high){
            mid = (low + high) / 2;
            if (nums[mid] == target){
                return mid;
            }
            //先根据nums[mid] 与 nums[low] 的关系判断 mid 是在左段还是右段
            if (nums[mid] >= nums[low]){  //mid在左段
                //再判断 target 是在 mid 的左边还是右边，从而调整左右边界 lo 和 hi
                if (target >= nums[low] && target <= nums[mid]){  //mid在左，
                    high = mid - 1;
                }else {
                    low = mid + 1;
                }
            }else {
                if (target >= nums[mid] && target <= nums[high]){
                    low = mid + 1;
                }else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     *
     * 你可以假设数组中无重复元素。
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int mid =  left + (right - left)/2 ;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }


    // 101/4
    public static void main(String[] args) {
//        System.out.println(new Main().divide(99,8));
//        System.out.println((-1^-1));
        System.out.println(101 >> 4);
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
