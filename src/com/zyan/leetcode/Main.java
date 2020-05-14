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
            if (map.containsKey(c)){
                char top = stack.isEmpty()? '#' : stack.pop();
                if (top != map.get(c)){
                    return false;
                }
            }else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    /**
     * 合并两个有序链表
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(0);
        ListNode prev = preHead;
        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                prev.next = l1;
                l1 = l1.next;
            }else {
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
     *
     *  
     *
     * 示例：
     *
     * 输入：n = 3
     * 输出：[
     *        "((()))",
     *        "(()())",
     *        "(())()",
     *        "()(())",
     *        "()()()"
     *      ]
     *
     *      暴力方法：生成所有可能的序列，然后逐一判断
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<>();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    private void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length){
            if (valid(current)){
                result.add(new String(current));
            }
        }else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }

    /**
     * 判断小括号序列是否合法
     * @param current
     * @return
     */
    private boolean valid(char[] current) {
        int balance = 0;
        for (char c : current) {
            if (c == '('){
                balance++;
            }else {
                balance--;
            }
            if (balance < 0){
                return false;
            }
        }
        return balance == 0;
    }

    /**
     * 方法一还有改进的余地：我们可以只在序列仍然保持有效时才添加 '(' or ')'，而不是像 方法一 那样每次添加。我们可以通过跟踪到目前为止放置的左括号和右括号的数目来做到这一点，
     *
     * 如果左括号数量不大于 nn，我们可以放一个左括号。如果右括号数量小于左括号的数量，我们可以放一个右括号。
     * @param n
     * @return
     */
    public List<String> generateParenthesis1(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max){
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }

        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open+1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close+1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    /**
     *  n = 2 为例，画树形结构图。方法是 “做减法”。
     *  画图以后，可以分析出的结论：
     *
     * 当前左右括号都有大于 0 个可以使用的时候，才产生分支；
     *
     * 产生左分支的时候，只看当前是否还有左括号可以使用；
     *
     * 产生右分支的时候，还受到左分支的限制，右边剩余可以使用的括号数量一定得在严格大于左边剩余的数量的时候，才可以产生分支；
     *
     * 在左边和右边剩余的括号数都等于 0 的时候结算。
     * @param n
     * @return
     */
    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0){
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
        if (left == 0 && right == 0){
            res.add(curStr);
            return;
        }
        if (left > right){// 剪枝（如图，左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节）
            return;
        }
        if (left > 0){
            dfs(curStr + "(", left - 1, right, res);
        }
        if (right > 0){
            dfs(curStr + ")", left, right - 1, res);
        }
    }

    /**
     * 递归方法dp[i] = "(" + dp[可能的括号对数] + ")" + dp[剩下的括号对数]
     * dp[i] = "(" + dp[j] + ")" + dp[i- j - 1] , j = 0, 1, ..., i - 1
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

    public double myPow(double x, int n){
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    private double quickMul(double x, long n) {
        if (n == 0){
            return 1.0;
        }
        double y = quickMul(x,n / 2);
        return n % 2 == 0 ? y * y : y * y * x;
    }

    /**
     * 这里用了一个巧妙的方法，如果是奇数，则乘过去
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
        for (int i = 1; i < nums.length ; i++) {
            number = number ^ nums[i];
        }
        return number;
    }

    /**
     * 合并k个链表
     * 简单的思路是先把所有元素取出来，放到一个数组中，快排，然后再构建链表 空间复杂度较高
     *
     * 或者用一个优先队列，将k个链表的头结点放到队列中，每次取出来第一个，就是最小的，然后将后续的放进去
     * 时间复杂度：O(n*log(k))，n 是所有链表中元素的总和，k 是链表个数。
     *
     * 或者就是逐一对比，循环k-1次
     *
     * 或者分治法，两两合并，再两两合并，类似于归并排序
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0){
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
            if (list != null){
                queue.add(list);
            }
        }
        while (!queue.isEmpty()){
            p.next = queue.poll();
            p = p.next;
            if (p.next != null){
                queue.add(p.next);
            }
        }
        return dummy.next;
    }

    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0){
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }
    private ListNode merge(ListNode[] lists, int left, int right){
        if (left == right){
            return lists[left];
        }
        int mid = (left + right) / 2;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }


    /**
     * 链表两两节点交换，不能只修改值
     *  迭代法
     *  firstNode（即 A） 和 secondNode（即 B） 分别遍历偶数节点和奇数节点，即两步看作一步。
     * 交换两个节点：
     *  firstNode.next = secondNode.next
     *  secondNode.next = firstNode
     * 还需要更新 prevNode.next 指向交换后的头
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode preNode = dummy;
        while (head != null && head.next != null){
            ListNode firstNode = head;
            ListNode secondNode = head.next;

            preNode.next = secondNode;
            firstNode.next =secondNode.next;
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
     * @param head
     * @return
     */
    public ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode firstNode = head;
        ListNode secondNode = head.next;

        firstNode = swapPairs1(secondNode.next);
        secondNode.next = firstNode;

        return secondNode;
    }


    public static void main(String[] args) {
        System.out.println("请输入一个数：");
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        //将用户输入的字符串转换成char数组
        char[] numChar = str.toCharArray();
        String[] chinese = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] unit = {"十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};
        StringBuffer sb = new StringBuffer();
        //获取用户输入的字符串长度
        int size = numChar.length;
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
            if (i != 0) {
                //数组长度-i
                //上述对应成代码就是 size-i
                //因为计数单位数组省略了"个”单位
                //所以需要减去1，取出所有单位
                //单位+数字|千二百三十四
                sb.append(unit[size - i - 1] + chinese[num]);
            } else {
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
