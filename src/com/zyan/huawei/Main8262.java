package com.zyan.huawei;

import java.util.*;

/**
 * @author zhangyan
 * @date 2020/8/26 下午7:53
 */
public class Main8262 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        string = string.substring(1, string.length() - 1);
        String[] strs = string.split("],\\[");
        String width = strs[0];
        String height = strs[1];
        String[] widths = width.split(",");
        String[] heights = height.split(",");
        List<Integer> heightList = new ArrayList<>();
        for (int i = 0; i < widths.length; i++) {
            int widthInt = Integer.parseInt(widths[i]);
            for (int j = 0; j < widthInt; j++) {
                heightList.add(Integer.parseInt(heights[i]));
            }
        }
        int[] newHeights = new int[heightList.size()];
        for (int i = 0; i < heightList.size(); i++) {
            newHeights[i] = heightList.get(i);
        }
        System.out.println(largestRectangleArea(newHeights));
    }

    public static int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }
        int res = 0;
        int[] newHeights = new int[len + 2];
        newHeights[0] = 0;
        System.arraycopy(heights, 0, newHeights, 1, len);
        newHeights[len + 1] = 0;
        len  = len + 2;
        heights = newHeights;
        Deque<Integer> stack = new ArrayDeque<>(len);
        stack.addLast(0);
        for (int i = 1; i < len; i++) {
            while (heights[i] < heights[stack.peekLast()]) {
                int curHeight = heights[stack.pollLast()];
                int curWidth = i - stack.peekLast() - 1;
                res = Math.max(res, curHeight * curWidth);
            }
            stack.addLast(i);
        }
        return res;
    }
}
