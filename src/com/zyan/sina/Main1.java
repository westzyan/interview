package com.zyan.sina;


import java.util.*;
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] strings = str.split(",");
        String numStr;
        TreeNode root = new TreeNode(Integer.parseInt(strings[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if (index == strings.length) {
                break;
            }
            numStr = strings[index];
            index++;
            if (!numStr.equals("null")) {
                int leftNum = Integer.parseInt(numStr);
                node.left = new TreeNode(leftNum);
                queue.add(node.left);
            }
            if (index == strings.length) {
                break;
            }
            numStr = strings[index];
            index++;
            if (!numStr.equals("null")) {
                int rightNum = Integer.parseInt(numStr);
                node.right = new TreeNode(rightNum);
                queue.add(node.right);
            }
        }
        root = upsideDown(root);
        List<Integer> list = new ArrayList<>();
        preOrder(root, list);
        for (int i = 0; i < list.size() - 1; i++) {
            System.out.print(list.get(i) + ",");
        }
        System.out.println(list.get(list.size() - 1));
    }

    public static TreeNode upsideDown(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        TreeNode res = upsideDown(left);
        left.left = right;
        left.right = root;
        root.left = null;
        root.right = null;
        return res;
    }

    public static void preOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}