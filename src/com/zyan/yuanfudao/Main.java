package com.zyan.yuanfudao;

import com.zyan.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhangyan
 * @date 2020/9/17 下午11:22
 */
public class Main {

    /**
     * 判断是否是二叉搜索树
     * @param root
     * @return
     */
    private TreeNode pre;
    public boolean isBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isBST(root.left)) {
            return false;
        }
        if (pre != null && pre.val > root.val) {
            return false;
        }
        pre = root;
        if (!isBST(root.right)) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否是完全二叉树
     * @param root
     * @return
     */
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode cur;
        boolean notComplete = false;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur == null) {
                notComplete = true;
                continue;
            }
            if (notComplete) {
                return false;
            }
            queue.offer(cur.left);
            queue.offer(cur.right);
        }
        return true;
    }
}
