package com.zyan;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class Test {


    /**
     * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * * 思路为从左下角向右上角比较
     *
     * @param target
     * @param array
     * @return
     */
    public boolean Find(int target, int[][] array) {
        int rows = array.length;
        if (rows == 0) {
            return false;
        }
        int cols = array[0].length;
        if (cols == 0) {
            return false;
        }
        // 左下
        int row = rows - 1;
        int col = 0;
        while (row >= 0 && col < cols) {
            if (array[row][col] < target) {
                col++;
            } else if (array[row][col] > target) {
                row--;
            } else {
                return true;
            }
        }
        return false;
    }


    /**
     * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
     * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     * 替换空格
     *
     * @param str
     * @return
     */
    public String replaceSpace(StringBuffer str) {

        int numberOfBlank = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                numberOfBlank++;
            }
        }
        int newLength = str.length() + numberOfBlank * 2;
        int indexOld = str.length() - 1;
        int indexNew = newLength - 1;
        str.setLength(newLength);
        while (indexOld >= 0 && indexOld < newLength) {
            if (str.charAt(indexOld) == ' ') {
                str.setCharAt(indexNew, '0');
                indexNew--;
                str.setCharAt(indexNew, '2');
                indexNew--;
                str.setCharAt(indexNew, '%');
                indexNew--;
            } else {
                str.setCharAt(indexNew, str.charAt(indexOld));
                indexNew--;
            }
            indexOld--;
        }

        return str.toString();

    }


    /**
     * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
     *
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList list = new ArrayList();
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.empty()) {
            list.add(stack.pop());
        }
        return list;
    }


    /**
     * 重建二叉树
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
     *
     * @param pre
     * @param in
     * @return
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < in.length; i++) {
            if (in[i] == pre[0]) {
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
                break;
            }
        }
        return root;
    }


    /**
     * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.size() <= 0) {
            while (stack1.size() != 0) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    /**
     * 翻转链表
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
//        ListNode pre = null;
        ListNode pre = head;
        ListNode cur = head.next;
        pre.next = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    /**
     * 旋转数组的最小数字
     * 二分法
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     *
     * @param array
     * @return
     */
    public int minNumberInRotateArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int firstFlag = 0;
        int secondFlag = array.length - 1;
        int mid = 0;

        while (secondFlag - firstFlag != 1) {
            mid = (secondFlag + firstFlag) / 2;
            if (array[mid] >= array[firstFlag]) {
                firstFlag = mid;
            } else {
                secondFlag = mid;
            }

        }
        return array[secondFlag];
    }


    /**
     * 斐波那契数列
     */
    public int Fibonacci(int n) {
        int[] result = {0, 1};
        if (n < 2) {
            return result[n];
        }
        int fibNMinusOne = 1;
        int fibNMinusTwo = 0;
        int fibN = 0;
        for (int i = 2; i <= n; i++) {
            fibN = fibNMinusOne + fibNMinusTwo;
            fibNMinusTwo = fibNMinusOne;
            fibNMinusOne = fibN;
        }
        return fibN;
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
     * 青蛙跳台阶
     *
     * @param target
     * @return
     */
    public int JumpFloor(int target) {
        if (target <= 2) {
            return target;
        }
        int pre2 = 1, pre1 = 2;
        for (int i = 3; i <= target; i++) {
            int cur = pre2 + pre1;
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }

    /**
     * 变态跳台阶
     *
     * @param target
     * @return
     */
    public int JumpFloorII(int target) {
        if (target <= 2) {
            return target;
        }
        int[] dp = new int[target + 1];
        Arrays.fill(dp, 1); //初始化每一种都可以直接从 0 跳到 n
        dp[0] = 0; //从 0 跳到 0 为 0 种，因为 n = 0，没法跳
        for (int i = 2; i <= target; i++) {
            for (int j = i - 1; j >= 1; j--) {
                dp[i] += dp[j]; //第 n 个状态是由前 n - 1 种状态推导出来，就是累加！
            }
        }
        return dp[target];
    }


    /**
     * 矩形覆盖
     *
     * @param target
     * @return
     */
    public int RectCover(int target) {
        if (target <= 2) {
            return target;
        }
        int fibNMinusOne = 2;
        int fibNMinusTwo = 1;
        int fibN = 0;
        for (int i = 3; i <= target; i++) {
            fibN = fibNMinusOne + fibNMinusTwo;
            fibNMinusTwo = fibNMinusOne;
            fibNMinusOne = fibN;
        }
        return fibN;
    }

    /**
     * 众所周知，牛妹非常喜欢吃蛋糕。
     * 第一天牛妹吃掉蛋糕总数三分之一多一个，第二天又将剩下的蛋糕吃掉三分之一多一个，
     * 以后每天吃掉前一天剩下的三分之一多一个，到第n天准备吃的时候只剩下一个蛋糕。
     * 牛妹想知道第一天开始吃的时候蛋糕一共有多少呢？
     *
     * @param n
     * @return
     */
    public int cakeNumber(int n) {
        // write code here
        int cnt = 1;
        for (int i = 1; i <= n - 1; i++) {//第n天还没吃，所以一共只吃了n-1天
            cnt = ((cnt + 1) * 3) / 2;
        }
        return cnt;
    }

    /**
     * 1的个数
     * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
     *
     * @param n
     * @return
     */
    public int NumberOf1(int n) {
        String s = Integer.toBinaryString(n);
        String[] split = s.split("");
        int a = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                a++;
            }
        }
        return a;

//        public int NumberOf1(int n) {
//            int count=0;
//            while(n!=0){
//                count++;
//                n=n&(n-1);
//            }
//            return count;
//        }
    }

    /**
     * 计算幂函数
     * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
     * 保证base和exponent不同时为0
     *
     * @param base
     * @param exponent
     * @return
     */
    public double Power(double base, int exponent) {
        if (base == 0.0) {
            return 0;
        }
        if (exponent == 0) {
            return 1;
        }
        double result = 1.0d;
        for (int i = 0; i < Math.abs(exponent); i++) {
            result *= base;
        }
        return exponent > 0 ? result : 1 / result;
    }


    public void reOrderArray(int[] array) {
        //所以此时，这两个array长度都==原array长度,所以需要oddLength，evenLength记录其有意义值的真实长度
        //也可先遍历原数组，确定奇偶数的个数再建立奇偶数组，但要多遍历一次。
        int[] arrayOdd = new int[array.length];
        int[] arrayEven = new int[array.length];
        int counter = 0, counterOdd = 0, counterEven = 0;
        int oddLength = 0, evenLength = 0;
        while (counter < array.length) {
            if (array[counter] % 2 == 0) {//even
                arrayEven[counterEven] = array[counter];
                counterEven++;
                evenLength++;
            } else {//odd
                arrayOdd[counterOdd] = array[counter];
                counterOdd++;
                oddLength++;
            }
            counter++;
        }
        for (int i = 0; i < oddLength; i++) {
            array[i] = arrayOdd[i];
        }
        for (int i = 0; i < evenLength; i++) {
            array[i + oddLength] = arrayEven[i];
        }

    }

    /**输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
     * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
     * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     * m记录上次的奇数填充的位置，采用插入法将偶数向后平移，然后将奇数插到前面
     * @param array
     */
//    public void reOrderArray(int [] array) {
//        if(array==null || array.length ==0){
//            return ;
//        }
//        int m = 0;
//        for(int i=0;i<array.length;i++){
//            if((Math.abs(array[i]))%2!=0){
//                int tmp = array[i];
//                int j=i;
//                while(j>m){
//                    array[j] = array[j-1];
//                    j--;
//                }
//                m=j+1;
//                array[j] = tmp;
//            }
//        }
//    }


    /**
     * 输入一个链表，输出该链表中倒数第k个结点。
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode p, q;
        p = q = head;
        int i = 0;
        while (p != null) {
            if (i >= k) {
                q = q.next;
            }
            p = p.next;
            i++;
        }
        return i < k ? null : q;
    }

    /**
     * 翻转链表
     * 以head结点为例步骤如下：
     * 1.反转后head是指向null，所以未反转的时候其前一个结点应该是null，初始化pre指针为null；
     * 2.用p指针记录head的下一个结点head.next；
     * 3.从链表上摘下head，即让head.next指向pre；
     * 4.此时已完成head结点的摘取及与前一个节点的连接，则我们需要操作下一个结点：故需移动pre和head，让pre指向head，head指向下一个节点。
     * 重复这四个操作直到head走完原链表，指向null时，循环结束，返回pre。
     *
     * @param head
     * @return
     */
    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 输入两个单调递增的链表，输出两个链表合成后的链表，
     * 当然我们需要合成后的链表满足单调不减规则。
     * 合并链表
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode Merge(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(0);
        ListNode cursor = head;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cursor.next = list1;
                list1 = list1.next;
            } else {
                cursor.next = list2;
                list2 = list2.next;
            }
            cursor = cursor.next;
        }
        if (list1 != null) {
            cursor.next = list1;
        }
        if (list2 != null) {
            cursor.next = list2;
        }
        return head.next;
    }

    //遍历大树
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        //如果找到和子树相同根的值，走判断方法
        if (root1.val == root2.val) {
            if (judge(root1, root2)) {
                return true;
            }
        }
        //遍历左孩子，右孩子
        return HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    /**
     * 树的子结构
     *
     * @param root
     * @param subtree
     * @return
     */
    //判断是否是子结构
    private boolean judge(TreeNode root, TreeNode subtree) {
        //如果子结构循环完毕，代表全部匹配
        if (subtree == null) {
            return true;
        }
        //大树循环完毕，并未成功匹配
        if (root == null) {
            return false;
        }
        //相等后判断左右孩子
        if (root.val == subtree.val) {
            return judge(root.left, subtree.left) && judge(root.right, subtree.right);
        }
        return false;
    }

    /**
     * 二叉树的镜像
     *
     * @param root
     */
    public void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        if (root.left != null) {
            Mirror(root.left);
        }
        if (root.right != null) {
            Mirror(root.right);
        }
    }
    //有问题
//    ArrayList result = new ArrayList<Integer>();
//    public ArrayList<Integer> printMatrix(int [][] matrix) {
//
//        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
//            return result;
//        }
//        int start = 0;
//        int columns = matrix.length;
//        int rows = matrix[0].length;
//        while (columns > start * 2 && rows > start * 2){
//            printMatrixIncircle(matrix, columns, rows, start);
//            start++;
//        }
//        return result;
//    }
//
//    private void printMatrixIncircle(int[][] matrix, int columns, int rows, int start) {
//        int endX = columns - 1 - start;
//        int endY = rows - 1 - start;
//        //从左到右
//        for (int i = start; i <= endX; i++) {
//            result.add(matrix[ start ][ i ]);
//        }
//        //从上到下
//        if (start < endY){
//            for (int i = start + 1; i <= endY; i++) {
//                result.add(matrix[ i ][ endX ]);
//            }
//        }
//        if (start < endX && start < endY){
//            //从右到左
//            for (int i = endY - 1; i >= start; i--) {
//                result.add(matrix[ endY ][ i ]);
//            }
//        }
//        //从下到上
//        if (start < endX && start < endY -1){
//            for (int i = endY - 1; i >= start + 1; i--) {
//                result.add(matrix[ i ][ start ]);
//            }
//        }
//    }

    /**
     * 顺时针打印矩阵
     * <p>
     * 定义四个变量代表范围，up、down、left、right
     * <p>
     * 向右走存入整行的值，当存入后，该行再也不会被遍历，代表上边界的 up 加一，同时判断是否和代表下边界的 down 交错
     * 向下走存入整列的值，当存入后，该列再也不会被遍历，代表右边界的 right 减一，同时判断是否和代表左边界的 left 交错
     * 向左走存入整行的值，当存入后，该行再也不会被遍历，代表下边界的 down 减一，同时判断是否和代表上边界的 up 交错
     * 向上走存入整列的值，当存入后，该列再也不会被遍历，代表左边界的 left 加一，同时判断是否和代表右边界的 right 交错
     */

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
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
            // 向左逼近
            right--;
            // 判断是否越界
            if (left > right) {
                break;
            }
            // 最下面一行
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
     * 判断是否为栈的压入弹出序列
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
     * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
     * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
     * （注意：这两个序列的长度是相等的）
     *
     * @param pushA
     * @param popA
     * @return
     */
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        boolean isPossible = false;
        Stack<Integer> stack = new Stack<>();
        if (pushA.length == 0 || popA.length == 0 || popA.length != pushA.length) {
            return false;
        }
        int j = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (!stack.isEmpty() && stack.peek() == popA[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }


    /**
     * 从上往下打印出二叉树的每个节点，同层节点从左至右打印
     *
     * @param root
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            list.add(temp.val);
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
        return list;
    }

    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
     * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
     *
     * @param sequence
     * @return
     */
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        return helpVerify(sequence, 0, sequence.length - 1);
    }

    public boolean helpVerify(int[] sequence, int start, int root) {
        if (start >= root) {
            return true;
        }
        int key = sequence[root];
        int i;
        for (i = start; i < root; i++) {
            if (sequence[i] > key) {
                break;
            }
        }
        for (int j = i; j < root; j++) {
            if (sequence[j] < key) {
                return false;
            }
        }
        return helpVerify(sequence, start, i - 1) && helpVerify(sequence, i, root - 1);
    }

    /**
     * 输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
     * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)
     * 二叉树和为某一值得路径
     *
     * @param root
     * @param target
     * @return
     */
    private ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> list = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) {
            return result;
        }
        list.add(root.val);
        target = target - root.val;
        if (target == 0 && root.right == null && root.left == null) {
            result.add(new ArrayList<Integer>(list)); //这里list是累加进去的，因为原来的list还要继续使用
        }
        FindPath(root.left, target);
        FindPath(root.right, target);
        list.remove(list.size() - 1);
        return result;
    }

    /**
     * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
     * 返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
     * 复杂链表的复制
     *
     * @param pHead
     * @return
     */
//    public RandomListNode Clone(RandomListNode pHead) {
//        if (pHead == null){
//            return pHead;
//        }
//        RandomListNode p1 = pHead;
//        RandomListNode p2 = pHead;
//        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
//        while (p1 != null){
//            map.put(p1, new RandomListNode(p1.label));
//            p1 = p1.next;
//        }
//        while (p2 != null){
//            if (p2.next != null){
//                map.get(p2).next = map.get(p2.next);
//            }else {
//                map.get(p2).next = null;
//            }
//            map.get(p2).random = map.get(p2.random);
//            p2 = p2.next;
//        }
//        return map.get(pHead);
//    }
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        RandomListNode currentNode = pHead;
        //1、复制每个结点，如复制结点A得到A1，将结点A1插到结点A后面；
        while (currentNode != null) {
            RandomListNode cloneNode = new RandomListNode(currentNode.label);
            RandomListNode nextNode = currentNode.next;
            currentNode.next = cloneNode.next;
            cloneNode.next = nextNode;
            currentNode = nextNode;
        }

        currentNode = pHead;
        //2、重新遍历链表，复制老结点的随机指针给新结点，如A1.random = A.random.next;
        while (currentNode != null) {
            currentNode.next.random = currentNode.random == null ? null : currentNode.random.next;
            currentNode = currentNode.next.next;
        }

        //3、拆分链表，将链表拆分为原链表和复制后的链表
        currentNode = pHead;
        RandomListNode pCloneHead = pHead.next;
        while (currentNode != null) {
            RandomListNode cloneNode = currentNode.next;
            currentNode.next = cloneNode.next;
            cloneNode.next = cloneNode.next == null ? null : cloneNode.next.next;
            currentNode = currentNode.next.next;
        }
        return pCloneHead;
    }

    /**
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
     * 二叉搜索树与双向链表
     *
     * @param pRootOfTree
     * @return
     */
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return pRootOfTree;
        }
        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        InOrderAndList(pRootOfTree, treeNodes);

        for (int i = 0; i < treeNodes.size() - 1; i++) {
            treeNodes.get(i).right = treeNodes.get(i + 1);
            treeNodes.get(i + 1).left = treeNodes.get(i);
        }
        return treeNodes.get(0);
    }

    private void InOrderAndList(TreeNode root, ArrayList<TreeNode> list) {
        if (root.left != null) {
            InOrderAndList(root.left, list);
        }
        list.add(root);
        if (root.right != null) {
            InOrderAndList(root.right, list);
        }
    }

    /**
     * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,
     * 则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
     *
     * @param str
     * @return
     */
    public ArrayList<String> Permutation(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        ArrayList<String> result = PermutationHelp(stringBuilder);
        return result;
    }

    private ArrayList<String> PermutationHelp(StringBuilder stringBuilder) {
        ArrayList<String> result = new ArrayList<String>();
        if (stringBuilder.length() == 1) {
            result.add(stringBuilder.toString());
            return result;
        }
        for (int i = 0; i < stringBuilder.length(); i++) {
            if (i == 0 || stringBuilder.charAt(i) != stringBuilder.charAt(0)) {
                char temp = stringBuilder.charAt(i);
                stringBuilder.setCharAt(i, stringBuilder.charAt(0));
                stringBuilder.setCharAt(0, temp);
                ArrayList<String> newResult = PermutationHelp(new StringBuilder(stringBuilder.substring(1)));
                for (int j = 0; j < newResult.size(); j++) {
                    result.add(stringBuilder.charAt(0) + newResult.get(j));
                }
                temp = stringBuilder.charAt(0);
                stringBuilder.setCharAt(0, stringBuilder.charAt(i));
                stringBuilder.setCharAt(i, temp);
            }
        }
        Collections.sort(result);
        return result;
    }


    /**
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
     * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
     *
     * @param array
     * @return
     */
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array == null || array.length == 0) return 0;
        int preValue = array[0];//用来记录上一次的记录
        int count = 1;//preValue出现的次数（相减之后）
        for (int i = 1; i < array.length; i++) {
            if (array[i] == preValue)
                count++;
            else {
                count--;
                if (count == 0) {
                    preValue = array[i];
                    count = 1;
                }
            }
        }
        int num = 0;//需要判断是否真的是大于1半数
        for (int i = 0; i < array.length; i++)
            if (array[i] == preValue)
                num++;
        return (num > array.length / 2) ? preValue : 0;
    }


    /**
     * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
     * 最小的k个数
     *
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        if (input == null || input.length == 0 || input.length < k || k == 0) {
            return new ArrayList<>();
        }
        //在数组中寻找位置为k-1的pivot
        int start = 0, end = input.length - 1;
        int index = partition(input, start, end);
        while (index != k - 1) {
            if (index < k - 1) {
                start = index + 1;
            } else {
                end = index - 1;
            }
            index = partition(input, start, end);
        }
        // 收集这k个数
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i <= index; i++) {
            res.add(input[i]);
        }
        return res;
    }

    public int partition(int[] input, int i, int j) {
        int x = input[i];
        while (i < j) {
            while (i < j && input[j] >= x) {
                j--;
            }
            if (i < j) {
                input[i] = input[j];
                i++;
            }
            while (i < j && input[i] < x) {
                i++;
            }
            if (i < j) {
                input[j] = input[i];
                j--;
            }
        }
        input[i] = x;
        return i;
    }


    /*

public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input == null || input.length == 0 || k > input.length || k == 0)
            return list;
        int[] arr = new int[k + 1];//数组下标0的位置作为哨兵，不存储数据
        //初始化数组
        for (int i = 1; i < k + 1; i++)
            arr[i] = input[i - 1];
        buildMaxHeap(arr, k + 1);//构造大根堆
        for (int i = k; i < input.length; i++) {
            if (input[i] < arr[1]) {
                arr[1] = input[i];
                adjustDown(arr, 1, k + 1);//将改变了根节点的二叉树继续调整为大根堆
            }
        }
        for (int i = 1; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }
     /**
     * @Author: ZwZ
     * @Description: 构造大根堆
     * @Param: [arr, length]  length:数组长度 作为是否跳出循环的条件
     * @return: void
     * @Date: 2020/1/30-22:06

    public void buildMaxHeap(int[] arr, int length) {
        if (arr == null || arr.length == 0 || arr.length == 1)
            return;
        for (int i = (length - 1) / 2; i > 0; i--) {
            adjustDown(arr, i, arr.length);
        }
    }
    /**
     * @Author: ZwZ
     * @Description: 堆排序中对一个子二叉树进行堆排序
     * @Param: [arr, k, length]
     * @return:
     * @Date: 2020/1/30-21:55

    public void adjustDown(int[] arr, int k, int length) {
        arr[0] = arr[k];//哨兵
        for (int i = 2 * k; i <= length; i *= 2) {
            if (i < length - 1 && arr[i] < arr[i + 1])
                i++;//取k较大的子结点的下标
            if (i > length - 1 || arr[0] >= arr[i])
                break;
            else {
                arr[k] = arr[i];
                k = i; //向下筛选
            }
        }
        arr[k] = arr[0];
    }
     */

    /**
     * 连续子数组的最大和
     *
     * @param array
     * @return
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        int[] dp = new int[array.length];
        int max = array[0];
        dp[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            int newMax = dp[i - 1] + array[i];
            if (newMax > array[i]) {
                dp[i] = dp[i - 1] + array[i];
            } else {
                dp[i] = array[i];
            }
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        return max;
    }

    /**
     * 存在疑问
     * 整数中1出现的次数
     *
     * @param n
     * @return
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        int cnt = 0;
        for (int m = 1; m <= n; m *= 10) {
            int a = n / m, b = n % m;
            cnt += (a + 8) / 10 * m + (a % 10 == 1 ? b + 1 : 0);
        }
        return cnt;
    }


    /**
     * 把数组排成最小的数
     * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
     * 思路是两两连在一块对比，例如3 和 32 结果为332 和323 那就取323，跟冒泡排序一样
     *
     * @param numbers
     * @return
     */
    public String PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                String sum1 = String.valueOf(numbers[i]) + String.valueOf(numbers[j]);
                String sum2 = String.valueOf(numbers[j]) + String.valueOf(numbers[i]);
                if (sum1.compareTo(sum2) > 0) {
                    int temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            stringBuilder.append(String.valueOf(numbers[i]));
        }
        return stringBuilder.toString();
    }


    /**
     * 23
     * 丑数，把只包含质因子2、3和5的数称作丑数（Ugly Number）。
     * 例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
     *
     * @param index
     * @return
     */
    public int GetUglyNumber_Solution(int index) {
        if (index <= 0) return 0;
        int p2 = 0, p3 = 0, p5 = 0;//初始化三个指向三个潜在成为最小丑数的位置
        int[] result = new int[index];
        result[0] = 1;//
        for (int i = 1; i < index; i++) {
            result[i] = Math.min(result[p2] * 2, Math.min(result[p3] * 3, result[p5] * 5));
            if (result[i] == result[p2] * 2) p2++;//为了防止重复需要三个if都能够走到
            if (result[i] == result[p3] * 3) p3++;//为了防止重复需要三个if都能够走到
            if (result[i] == result[p5] * 5) p5++;//为了防止重复需要三个if都能够走到
        }
        return result[index - 1];
    }

    /**
     * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
     *
     * @param str
     * @return
     */
    public int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0) return -1;
        int[] count = new int[123];
        //用一个类似hash的东西来存储字符出现的次数，很方便
        for (int i = 0; i < str.length(); i++)
            count[str.charAt(i)]++;
        //其实这个第二步应该也是ka我的地方，没有在第一时间想到只要在遍历一遍数组并访问hash记录就可以了
        for (int i = 0; i < str.length(); i++)
            if (count[str.charAt(i)] == 1)
                return i;
        return -1;
    }

    /**
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
     * 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
     *
     * @param array
     * @return
     */
    int cnt = 0;

    public int InversePairs(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        mergeSort(array, 0, array.length - 1);
        return cnt;
    }

    private void mergeSort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + ((hi - lo) >> 1);
        mergeSort(nums, lo, mid);
        mergeSort(nums, mid + 1, hi);
        merge(nums, lo, mid, hi);
    }

    private void merge(int[] nums, int lo, int mid, int hi) {
        int[] temp = new int[hi - lo + 1];
        int index = 0;
        int p1 = lo, p2 = mid + 1;
        while (p1 <= mid && p2 <= hi) {
            if (nums[p1] <= nums[p2]) {
                temp[index++] = nums[p1++];
            } else {
                // when left part current element p1 is bigger than right part one p2,
                // we can get mid - p1 + 1 inverse pairs in one time.
                // because left part is in order,
                // it means elements which index is bigger than p1 in left part,
                // it's value is also bigger than p1.
                // so all of elements from p1 to mid are bigger than p2,
                // we got mid - p1 + 1 inverse pairs.
                cnt = (cnt + mid - p1 + 1) % 1000000007;
                temp[index++] = nums[p2++];
            }
        }
        while (p1 <= mid) {
            temp[index++] = nums[p1++];
        }
        while (p2 <= hi) {
            temp[index++] = nums[p2++];
        }
        for (int i = 0; i < temp.length; i++) {
            nums[lo++] = temp[i];
        }
    }


    /**
     * 第一个公共子节点
     *
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        int length1 = 0;
        int length2 = 0;
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while (p1 != null) {
            length1++;
            p1 = p1.next;
        }
        while (p2 != null) {
            length2++;
            p2 = p2.next;
        }
        p1 = pHead1;
        p2 = pHead2;
        if (length2 > length1) {
            for (int i = 0; i < length2 - length1; i++) {
                p2 = p2.next;
            }
        } else {
            for (int i = 0; i < length1 - length2; i++) {
                p1 = p1.next;
            }
        }
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

//    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
//        if (pHead1 == null || pHead2 == null) return null;
//        ListNode p1 = pHead1;
//        ListNode p2 = pHead2;
//        while (p1 != p2) {
//            p1 = p1.next;
//            p2 = p2.next;
//            if (p1 != p2) {
//                if (p1 == null) p1 = pHead2;
//                if (p2 == null) p2 = pHead1;
//            }
//        }
//        return p1;
//    }

    /**
     * 统计一个数字在排序数组中出现的次数。
     * 在排序数组中查找元素的第一个和最后一个位置
     *
     * @param array
     * @param k
     * @return
     */
    public int GetNumberOfK(int[] array, int k) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int firstK = GetFirstK(array, k, 0, array.length - 1);
        System.out.println(firstK);
        int lastK = GetLastK(array, k, 0, array.length - 1);
        System.out.println(lastK);
        if (firstK >= 0 && lastK >= 0) {
            return lastK - firstK + 1;
        }
        return 0;
    }

    public int GetFirstK(int[] array, int k, int start, int end) {
        if (start > end) {
            return -1;
        }
        int middleIndex = (start + end) / 2;
        int middleData = array[middleIndex];
        if (middleData == k) {
            if (middleIndex > 0 && array[middleIndex - 1] != k || middleIndex == 0) {
                return middleIndex;
            } else {
                end = middleIndex - 1;
            }
        } else if (middleData > k) {
            end = middleIndex - 1;
        } else {
            start = middleIndex + 1;
        }
        return GetFirstK(array, k, start, end);
    }

    public int GetLastK(int[] array, int k, int start, int end) {
        if (start > end) {
            return -1;
        }
        int middleIndex = (start + end) / 2;
        int middleData = array[middleIndex];
        if (middleData == k) {
            if (middleIndex < array.length - 1 && array[middleIndex + 1] != k || middleIndex == array.length - 1) {
                return middleIndex;
            } else {
                start = middleIndex + 1;
            }
        } else if (middleData < k) {
            start = middleIndex + 1;
        } else {
            end = middleIndex - 1;
        }
        return GetLastK(array, k, start, end);
    }

    public int GetMissingNumber(int[] arr, int length) {
        if (arr == null || length == 0) {
            return -1;
        }
        int left = 0;
        int right = length - 1;
        while (left <= right) {
            int middle = (left + right) >> 1;
            if (arr[middle] == middle) {
                left = left + 1;
            } else {
                if (middle == 0 || arr[middle - 1] == middle - 1) {
                    return middle;
                } else {
                    right = middle - 1;
                }
            }
        }
        if (left == length) {
            return length;
        }
        return -1;
    }


    /**
     * 给 定一棵二叉搜索树，请找出其中的第k小的结点
     *
     * @param pRoot
     * @param k
     * @return
     */
    ArrayList<TreeNode> treeNodeArrayList = new ArrayList<>();

    public TreeNode KthNode(TreeNode pRoot, int k) {
        inOrderAddList(pRoot);
        if (k >= 1 && treeNodeArrayList.size() >= k) {
            return treeNodeArrayList.get(k - 1);
        }
        return null;
    }

    private void inOrderAddList(TreeNode root) {
        if (root != null) {
            inOrderAddList(root.left);
            treeNodeArrayList.add(root);
            inOrderAddList(root.right);
        }
    }


    /**
     * 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
     *
     * @param root -
     * @return -
     */
    public int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int nLeft = TreeDepth(root.left);
        int nRight = TreeDepth(root.right);
        return nLeft > nRight ? nLeft + 1 : nRight + 1;
    }

    /**
     * 判断平衡二叉树
     * 思路是不重复计算深度，
     * 通过后续遍历，后续先判断左子树，然后判断右子树
     * 然后判断根节点
     *
     * @param root -
     */
    public boolean IsBalanced_Solution(TreeNode root) {
        return depth(root) != -1;
    }

    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = depth(root.left);
        if (left == -1) {
            return -1;
        }
        int right = depth(root.right);
        if (right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        } else {
            return 1 + (left > right ? left : right);
        }
    }

    public int depth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftLength = depth1(root.left);
        if (leftLength == -1) {
            return -1;
        }
        int rightLength = depth1(root.right);
        if (rightLength == -1) {
            return -1;
        }

        if (Math.abs(leftLength - rightLength) > 1) {
            return -1;
        } else {
            return 1 + (leftLength > rightLength ? leftLength : rightLength);
        }

    }


    /**
     * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
     * 先进行亦或，剩下的数就是两个不同的数亦或的结果，
     *
     * @param array
     * @param num1
     * @param num2
     */
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {

        int xor1 = 0;
        for (int i = 0; i < array.length; i++)
            xor1 = xor1 ^ array[i];
        //在xor1中找到第一个不同的位对数据进行分类，分类为两个队列对数据进行异或求和找到我们想要的结果
        int index = 1;
        while ((index & xor1) == 0)
            index = index << 1;//因为可能有多个位为1所以需要求一下位置
        int result1 = 0;
        int result2 = 0;
        for (int i = 0; i < array.length; i++) {
            if ((index & array[i]) == 0)
                result1 = result1 ^ array[i];
            else
                result2 = result2 ^ array[i];
        }
        num1[0] = result1;
        num2[0] = result2;
    }


    /**
     * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
     *
     * @param array
     * @param sum
     * @return
     */
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {

        ArrayList<Integer> list = new ArrayList<>();
        if (array == null || array.length <= 1) {
            return list;
        }
        int left = 0, right = array.length - 1;
        while (left < right) {
            if (array[left] + array[right] == sum) {
                list.add(array[left]);
                list.add(array[right]);
                break;
            } else if (array[left] + array[right] < sum) {
                left++;
            } else {
                right--;
            }
        }
        return list;
    }

    /**
     * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
     * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
     * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。
     * 现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
     *
     * @param sum
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> resultList = new ArrayList<>();
        if (sum <= 0) {
            return resultList;
        }
        int left = 1;
        int right = 2;
        int sumSingle = left + right;
        while (left < (sum + 1) / 2) {
            if (sumSingle < sum) {
                right++;
                sumSingle = sumSingle + right;
            } else if (sumSingle > sum) {
                sumSingle = sumSingle - left;
                left++;
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = left; i <= right; i++) {
                    list.add(i);
                    System.out.print(i);
                }
                resultList.add(list);
                right++;
                sumSingle = sumSingle + right;
            }
        }
        return resultList;
    }


    /**
     * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，
     * 有一天他向Fish借来翻看，但却读不懂它的意思。例如，“student. a am I”。后来才意识到，
     * 这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
     *
     * @param str
     * @return
     */
//    public String ReverseSentence(String str) {
//        if (str.length() <= 0){
//            return "";
//        }
//        if (str == null || str.trim().length() == 0) return str;
//        String[] strings = str.split(" ");
//        StringBuilder result = new StringBuilder();
//        if (strings.length == 1){
//            return str;
//        }
//        for (int i = 0; i < strings.length; i++) {
//            result.append(new StringBuilder(strings[i]).reverse()).append(" ");
//        }
//        result.reverse();
//        return result.substring(1).toString();
//    }
    public String ReverseSentence1(String str) {
        if (str.length() <= 0) {
            return "";
        }
        //反转整个句子
        StringBuffer st1 = new StringBuffer(str);
        st1.reverse();
        //存放结果
        StringBuffer result = new StringBuffer();
        int j = 0;
        //标记空格数
        int blankNum = 0;
        for (int i = 0; i < st1.length(); i++) {
            //1、当有空格，且没有到达最后一个单词时
            if (st1.charAt(i) == ' ' && (i != st1.length() - 1)) {
                blankNum++;
                StringBuffer st2 = new StringBuffer(st1.substring(j, i));
                result.append(st2.reverse().toString()).append(" ");
                j = i + 1;
            }
            //2、当有空格，且到达最后一个单词时
            if (blankNum != 0 && i == (st1.length() - 1)) {
                StringBuffer st3 = new StringBuffer(st1.substring(j, i + 1));
                result.append(st3.reverse());
            }
        }
        //空格数为0时，直接返回原字符串
        if (blankNum == 0) {
            return str;
        }
        return result.toString();
    }


    public String ReverseSentence(String str) {
        if (str == null || str.trim().length() == 0) return str;
        char[] chars = str.toCharArray();
        reverseChars(chars, 0, str.length() - 1);
        // 利用滑动窗口
        // 遇到' '执行翻转
        int l = 0;
        int r = 0;
        while (l < str.length()) {
            if (chars[r] == ' ') {
                reverseChars(chars, l, r - 1);
                // 交换完之后,一起跳过' '
                r++;
                l = r;
            }
            if (r == str.length() - 1) {
                reverseChars(chars, l, r);
                // 到了最后交换玩就break，否则r会出现越界，可以在while中加对r的判断
                break;
            }
            r++;
        }
        return String.valueOf(chars);
    }

    private void reverseChars(char[] chars, int l, int r) {
        while (l < r) {
            char temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;
            l++;
            r--;
        }
    }

    /**
     * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，
     * 就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，
     * 请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。
     * 是不是很简单？OK，搞定它！
     *
     * @param str
     * @param n
     * @return
     */
    public String LeftRotateString(String str, int n) {
        if (str == null || str.length() <= 1) {
            return str;
        }
        char[] chars = str.toCharArray();
        if (n > str.length()) {
            n = n % str.length();
        }
        reverseChars(chars, 0, n - 1);
        reverseChars(chars, n, str.length() - 1);
        reverseChars(chars, 0, str.length() - 1);
        return String.valueOf(chars);
    }

    /**
     * 扑克牌顺子
     * 他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,
     * 嘿嘿！！“红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,
     * 他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
     * 上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。LL决定去买体育彩票啦。
     * 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何，
     * 如果牌能组成顺子就输出true，否则就输出false。为了方便起见,你可以认为大小王是0。
     *
     * @param numbers
     * @return
     */
    public boolean isContinuous(int[] numbers) {
        if (numbers == null || numbers.length < 1) {
            return false;
        }
        Arrays.sort(numbers);
        int numberOf0 = 0;
        int numberofSpcae = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] == 0) {
                numberOf0++;
            }
        }
        for (int i = numberOf0; i < numbers.length - 1; i++) {
            if (numbers[i] == numbers[i + 1]) {
                return false;
            }
            if (numbers[i + 1] - numbers[i] > 1) {
                numberofSpcae = numberofSpcae + numbers[i + 1] - numbers[i] - 1;
            }
        }
        if (numbers[numbers.length - 1] == 0) {
            numberOf0++;
        }
//        System.out.println(numberOf0+" "+numberofSpcae);
        if (numberOf0 >= numberofSpcae) {
            return true;
        }
        return false;
    }

    /**
     * 最后剩下来的数字
     *
     * @param n
     * @param m
     * @return
     */
    public int LastRemaining_Solution(int n, int m) {
        if (n <= 0 || m <= 0) {
            return -1;
        }
        if (n == 1) {
            return n;
        }
        ListNode head = new ListNode(0);
        ListNode node = head;
        for (int i = 1; i < n; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }
        node.next = head;
        int count = 0;
        while (node.next != node) {
            count++;
            if (count == m) {
                node.next = node.next.next;
                count = 0;
            } else {
                node = node.next;
            }
        }
        return node.val;
    }


    /**
     * 机器人能够走的路径
     * 牛逼 robot
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return dfs(0, 0, m, n, k, visited);
    }

    private int dfs(int i, int j, int m, int n, int k, boolean[][] visited) {
//        if (i < 0 || i >= m || j < 0 || j >= n || (i / 10 + i % 10 + j / 10 + j % 10) > k || visited[i][j]) {
//            visited[i][j] = true;
//            return 0;
//        }
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j]) {
            return 0;
        }
        if ((i / 10 + i % 10 + j / 10 + j % 10) > k) {
            visited[i][j] = true;
            return 0;
        }
        visited[i][j] = true;
        return dfs(i + 1, j, m, n, k, visited) + dfs(i - 1, j, m, n, k, visited) + dfs(i, j + 1, m, n, k, visited) +
                dfs(i, j - 1, m, n, k, visited) + 1;
    }


//    public int LastRemaining_Solution(int n, int m) {
//        if (n<=0 || m<=0) return -1;
//
//        List<Integer> list=new LinkedList<>();
//        for (int i=0;i<n;i++) list.add(i);
//
//        int p=0;
//        while (list.size()>1){
//            p=(p+m-1)%list.size();
//            list.remove(p);
//        }
//
//        return list.get(0);
//    }

    /**
     * 短路求值
     *
     * @param n
     * @return
     */
    public int Sum(int n) {
        int sum = n;
        boolean ans = (n > 0) && ((sum += Sum(n - 1)) > 0);
        // boolean ans = (n>0)&&((sum+=Sum(n-1))!=0);
        return sum;
    }

    /**
     * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
     *
     * @param num1
     * @param num2
     * @return
     */
    public int Add(int num1, int num2) {
        int result = 0;
        int carry = 0;
        do {
            result = num1 ^ num2;       //不带进位的加法
            carry = (num1 & num2) << 1; //进位
            num1 = result;
            num2 = carry;
        } while (carry != 0); // 进位不为0则继续执行加法处理进位
        return result;
    }

    /**
     * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。
     * 数值为0或者字符串不是一个合法的数值则返回0
     *
     * @param str
     * @return
     */
//    public int StrToInt(String str) {
//        Integer res=0;
//        try {
//            res = new Integer(str);
//        }catch (NumberFormatException e){
//
//        }finally {
//            return res;
//        }
//    }
    public int StrToInt(String str) {
        if (str.length() == 0 || "".equals(str)) {
            return 0;
        }
        boolean isNeg = false;
        if (str.charAt(0) == '+') {
            str = str.substring(1);
        } else if (str.charAt(0) == '-') {
            isNeg = true;
            str = str.substring(1);
        }
        char[] s = str.toCharArray();
        double res = 0;
        for (int i = 0; i < s.length; i++) {
            if (!Character.isDigit(s[i])) {
                return 0;
            } else {
                res += Math.pow(10, s.length - i - 1) * (s[i] - 48);
            }
        }
        if (isNeg == false) {
            if (res > Integer.MAX_VALUE) {
                return 0;
            } else {
                return (int) res;
            }
        } else {
            if ((0 - res) < Integer.MIN_VALUE) {
                return 0;
            } else {
                return (int) (0 - res);
            }
        }
    }

    /**
     * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。
     * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
     * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
     *
     * @param nums
     * @param length
     * @param duplication
     * @return
     */
    public boolean duplicate(int nums[], int length, int[] duplication) {
        if (nums == null || length == 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    duplication[0] = nums[i];
                    return true;
                }
                // swap
                int tmp = nums[i];
                nums[i] = nums[tmp];
                nums[tmp] = tmp;
            }
        }
        return false;
    }

    /**
     * 构建乘积数组
     *
     * @param A
     * @return
     */
    public int[] multiply(int[] A) {
        int length = A.length;
        int[] B = new int[length];
        int res = 1;
        for (int i = 0; i < length; i++) {
            B[i] = res;
            res = res * A[i];
        }
        res = 1;
        for (int i = length - 1; i >= 0; i--) {
            B[i] = B[i] * res;
            res = res * A[i];
        }
        return B;
    }

    /**
     * 判断二叉树是否对称
     *
     * @param pRoot 根节点
     * @return 是否对称
     */
    public boolean isSymmetrical(TreeNode pRoot) {
        return pRoot == null || jude(pRoot.left, pRoot.right);
    }

    public boolean jude(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        } else if (node1 == null || node2 == null) {
            return false;
        }
        if (node1.val != node2.val) {
            return false;
        } else {
            return jude(node1.left, node2.right) && jude(node1.right, node2.left);
        }
    }

    /**
     * 表示数值的字符串
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是
     *
     * @param str
     * @return
     */
    public boolean isNumeric(char[] str) {

        String pattern = "^[-+]?\\d*(?:\\.\\d*)?(?:[eE][+\\-]?\\d+)?$";
        String s = new String(str);
        return Pattern.matches(pattern, s);
    }


    int[] charCnt = new int[128];
    Queue<Character> queue = new LinkedList<Character>();

    /**
     * 字符流中第一个不重复的字符
     * 这个做法大致相同，利用 Hash 思想采用128大小的计数数组进行计数也好，或者是使用 Map 键值对映射也好，都差不多，使用数组会更简单。
     * <p>
     * 字符出现顺序的判断（第一个字符）：
     * 这里就是改进的关键之处了，容易发现，字符流中不重复的字符可能同时存在多个，我们只要把这些 “不重复字符” 保存起来就可以，而无需保存那些重复出现的字符，
     * 而为了维护字符出现的顺序，我们使用队列（先进先出）这一结构，先出现的不重复字符先输出：
     * <p>
     * 入队：获取字符流中的一个字符时，当我们判断它是不重复时，将它加入队列；
     * 输出/出队：注意，因为队列中存储的 “不重复字符” 在一系列的流读取操作后，
     * 随时有可能改变状态（变重复），所以，队列中的字符不能直接输出，要先进行一次重复判断，如果发现队头字符已经重复了，就将它移出队列并判断新的队头，否则，输出队头的值；
     *
     * @param ch
     */
    public void Insert(char ch) {
        if (charCnt[ch] == 0) {
            queue.add(ch);
            charCnt[ch]++;
        }
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        Character CHAR = null;
        char c = 0;
        while ((CHAR = queue.peek()) != null) {
            c = CHAR.charValue();
            if (charCnt[c] == 1) { //判断是否脱单了，没脱单则输出
                return c;
            } else {
                queue.remove();//脱单了就移出队列，它不会再回来了
            }
        }
        return '#';//队空，返回#
    }


//    LinkedHashMap<Character,Integer> map = new LinkedHashMap<>();
//    public void Insert(char ch)
//    {
//        if(map.containsKey(ch)) {
//            map.put(ch,-1);
//        } else {
//            map.put(ch, 1);
//        }
//    }
//    public char FirstAppearingOnce()
//    {
//        Iterator<Character> iterator = map.keySet().iterator();
//        while (iterator.hasNext()) {
//            char cur = iterator.next();
//            if(map.get(cur) == 1) {
//                return cur;
//            }
//        }
//        return '#';
//    }


    /**
     * 链表中环的入口结点
     *
     * @param pHead
     * @return
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        // 1.判断链表中有环
        ListNode fast = pHead;
        ListNode slow = pHead;
        boolean flag = false;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            return null;
        } else {
            //2.得到环中节点的数目
            int count = 1;
            fast = fast.next;
            while (fast != slow) {
                fast = fast.next;
                count++;
            }

            // 3.找到环中的入口节点
            fast = slow = pHead;
            for (int i = 0; i < count; i++) {
                fast = fast.next;
            }
            while (fast != slow) {
                fast = fast.next;
                slow = slow.next;
            }
            return fast;
        }
    }


    /**
     * 删除链表中重复的节点
     * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
     * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
     *
     * @param pHead
     * @return
     */
    public ListNode deleteDuplication(ListNode pHead) {
        HashMap<Integer, Integer> map = new HashMap<>();
        if (pHead == null) {
            return null;
        }
        ListNode cur = pHead;
        while (cur != null) {
            if (map.containsKey(cur.val)) {
                map.put(cur.val, map.get(cur.val) + 1);
            } else {
                map.put(cur.val, 1);
            }
            cur = cur.next;
        }
        for (Map.Entry<Integer, Integer> i : map.entrySet()) {
            System.out.println(i.getKey() + " " + i.getValue());
        }
        ListNode head = new ListNode(0);
        head.next = pHead;
        ListNode pre = head;

        while (pHead != null) {
            if (map.get(pHead.val) > 1) {
                System.out.println(pHead.val);
                System.out.println(pre.val);
                pre.next = pHead.next;
            } else {
                pre = pre.next;
            }
            pHead = pHead.next;
        }
        return head.next;
    }

    public ListNode deleteDuplication1(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        ListNode Head = new ListNode(0);
        Head.next = pHead;
        ListNode pre = Head;
        ListNode last = Head.next;
        while (last != null) {
            if (last.next != null && last.val == last.next.val) {
                // 找到最后的一个相同节点
                while (last.next != null && last.val == last.next.val) {
                    last = last.next;
                }
                pre.next = last.next;
                last = last.next;
            } else {
                pre = pre.next;
                last = last.next;
            }
        }
        return Head.next;
    }

    /**
     * 获取二叉树的下一个节点
     * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
     * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
     *
     * @param pNode
     * @return
     */
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode.right != null) { //先判断有没有右孩子，如果有，则右孩子的最左下节点即为下一个
            TreeLinkNode node = pNode.right;
            while (node.left != null)
                node = node.left;
            return node;
        } else {   //否则就是判断这个结点是否为其父亲节点的左孩子，如果是，直接返回父节点，否则继续向上找父亲节点
            while (pNode.next != null) {
                TreeLinkNode parent = pNode.next;
                if (parent.left == pNode)
                    return parent;
                pNode = pNode.next;
            }
        }
        return null;
    }

    /**
     * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
     * 第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推
     *
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot == null) {
            return result;
        }
        boolean rev = true;
//        Deque<TreeNode> queue1 = new LinkedList<>();
//        queue1.
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (rev) {
                    list.add(node.val);
                } else {
                    list.add(0, node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (list.size() != 0) {
                result.add(list);
            }
            rev = !rev;
        }
        return result;
    }

    /**
     * 递归遍历二叉树，前序
     */
    ArrayList<Integer> res1 = new ArrayList<>();

    public ArrayList<Integer> preOrderByRecursion(TreeNode root) {
        if (root == null) {
            return res1;
        }
        res1.add(root.val);
        preOrderByRecursion(root.left);
        preOrderByRecursion(root.right);
        return res1;
    }

    /**
     * 非递归前序遍历二叉树
     * 思路就是栈  利用一个栈来实现先序遍历
     * 1根节点入栈
     * 2判断栈是否为空，如果不为空
     * 弹出栈顶元素
     * 打印栈顶元素
     * 如果有右子树压栈
     * 如果有左子树压栈
     * 3如果栈为空，程序结束
     */
    ArrayList<Integer> res2 = new ArrayList<>();

    public ArrayList<Integer> preOrderByNonRecursion(TreeNode root) {
        if (root == null) {
            return res2;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res2.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return res2;
    }

    /**
     * 1.如果栈不为空或节点不指向空数据，则执行以下步骤
     * a如果节点不为空，压栈，沿着左子树走一步
     * b如果节点为空，则出栈，打印，沿着右子树走一步
     * 2.如果栈为空且当前节点为空，则结束
     * 这里在找二叉排序树第k小的节点时候，可以设置一个全局遍历，然后直接返回，不用全部遍历
     *
     * @param root
     */

    public void inOrderByNon(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> s = new Stack<TreeNode>();
        while (!s.isEmpty() || root != null) {
            if (root != null) {
                s.push(root);
                root = root.left;
            } else {
                root = s.pop();
                System.out.println(root.val);
                root = root.right;
            }
        }
    }

    /**
     * 1申请两个栈s1，s2，头节点入栈s1
     * 2如果栈s1不为空，执行以下操作：弹出一个元素，入栈s2，如果该节点左孩子不空入栈s1，如果该节点右孩子不空入栈s1.
     * 3.将栈s2中的节点一次出栈，打印。
     *
     * @param head
     */
    public void postOrder(TreeNode head) {
//        ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap();
//        HashMap<Integer, Integer> hashMap = new HashMap<>();
//        Hashtable
        if (head == null) {
            return;
        }
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();
        s1.push(head);
        while (!s1.isEmpty()) {
            TreeNode cur = s1.pop();
            s2.push(cur);
            if (cur.left != null) {
                s1.push(cur.left);
            }
            if (cur.right != null) {
                s1.push(cur.right);
            }
        }
        while (!s2.isEmpty()) {
            TreeNode cur = s2.pop();
            System.out.println(cur.val);
        }
    }


    /**
     * 二叉树打印多行
     * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
     *
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer>> Print1(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(list);
        }

        return result;
    }

    /**
     * 序列化二叉树
     * 请实现两个函数，分别用来序列化和反序列化二叉树
     * <p>
     * 二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#），以 ！ 表示一个结点值的结束（value!）。
     * <p>
     * 二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
     *
     * @param root
     * @return
     */
    String Serialize(TreeNode root) {
        if (root == null) {
            return "#";
        } else {
            return root.val + "," + Serialize(root.left) + "," + Serialize(root.right);
        }
    }

    int index = -1;

    TreeNode Deserialize(String str) {
        String[] s = str.split(",");//将序列化之后的序列用，分隔符转化为数组
        index++;//索引每次加一
        int len = s.length;
        if (index > len) {
            return null;
        }
        TreeNode treeNode = null;
        if (!s[index].equals("#")) {//不是叶子节点 继续走 是叶子节点出递归
            treeNode = new TreeNode(Integer.parseInt(s[index]));
            treeNode.left = Deserialize(str);
            treeNode.right = Deserialize(str);
        }
        return treeNode;
    }

    /**
     * 滑动窗口最大值
     * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，
     * 如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，
     * 他们的最大值分别为{4,4,6,6,6,5}；
     * 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
     * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}，
     * {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
     * 如果当前下标的值大于队列中最右边的值就循环将最右边的值poll掉，
     * 保证最左边的值是当前窗口的最大值的下标，当然还要判断是否过期，过期就将该值弹出
     *
     * @param num
     * @param size
     * @return
     */
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (size < 1 || num.length == 0) {
            return res;
        }
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {
            while (!list.isEmpty() && num[list.peekLast()] < num[i]) {
                list.pollLast();
            }
            list.add(i);
            //判断左边是否失效
            if (list.peekFirst() <= i - size) {
                list.pollFirst();
            }
            if (i >= size - 1) {
                res.add(num[list.peekFirst()]);
            }
        }
        return res;
    }


    /**
     * 剪绳子
     * 给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为k[0],k[1],...,k[m]。
     * 请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？
     * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     * 思路就是贪婪，尽可能凑够3
     *
     * @param target
     * @return
     */
    public int cutRope(int target) {
        if (target <= 0) return 0;
        if (target == 1 || target == 2) return 1;
        if (target == 3) return 2;
        int m = target % 3;
        switch (m) {
            case 0:
                return (int) Math.pow(3, target / 3);
            case 1:
                return (int) Math.pow(3, target / 3 - 1) * 4;
            case 2:
                return (int) Math.pow(3, target / 3) * 2;
        }
        return 0;
    }


    /**
     * 矩阵中的路径
     */
    boolean[] visited = null;

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        visited = new boolean[matrix.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (subHasPath(matrix, rows, cols, str, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean subHasPath(char[] matrix, int rows, int cols, char[] str, int row, int col, int len) {
        if (matrix[row * cols + col] != str[len] || visited[row * cols + col] == true) {
            return false;
        }
        if (len == str.length - 1) {
            return true;
        }
        visited[row * cols + col] = true;
        if (row > 0 && subHasPath(matrix, rows, cols, str, row - 1, col, len + 1)) {
            return true;
        }
        if (row < rows - 1 && subHasPath(matrix, rows, cols, str, row + 1, col, len + 1)) {
            return true;
        }
        if (col > 0 && subHasPath(matrix, rows, cols, str, row, col - 1, len + 1)) {
            return true;
        }
        if (col < cols - 1 && subHasPath(matrix, rows, cols, str, row, col + 1, len + 1)) {
            return true;
        }
        visited[row * cols + col] = false;
        return false;
    }


    public static void main(String[] args) {
        int[] a = new int[]{0, 2, 3, 4, 7, 9, 0, 0};
        int[] b = {0, 1, 2, 3, 5, 6, 7, 8, 9};

        /**
         * 尾插法构造链表
         */
        ListNode head = new ListNode(0);
        ListNode pre = head;
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 7; i++) {
            ListNode cur = new ListNode(in.nextInt());
            pre.next = cur;
            pre = cur;
        }


        /**
         * 头插法构造链表
         */
//        ListNode pre = null;
//        for (int i = 0; i < 7; i++) {
//            ListNode cur = new ListNode(in.nextInt());
//            cur.next = pre;
//            pre = cur;
//        }
//        while (pre != null){
//            System.out.println(pre.val);
//            pre = pre.next;
//        }

        in.close();
//        while (head.next != null){
//            System.out.println(head.next.val);
//            head = head.next;
//        }
        ListNode test = reverseList(head.next);
//        ListNode test = head.next;
        while (test != null) {
            System.out.println(test.val);
            test = test.next;
        }

////        System.out.println(Arrays.binarySearch(a,3));
//        int index = 100;
//        while(index != 0){
//            index = index <<1;//因为可能有多个位为1所以需要求一下位置
//            System.out.println(index);
//        }
//        System.out.println(10<<1);
//        System.out.println(5^5^2);
//        ArrayList<Integer> list = new Test().FindNumbersWithSum(a, 11);
//        for (Integer i :
//                list) {
//            System.out.println(i);
//        }
//        System.out.println(new Test().GetMissingNumber(b,9));

//        System.out.println(0 ^ 4);

    }


}

class Singleton {
    private volatile static Singleton instance;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
    }
}



