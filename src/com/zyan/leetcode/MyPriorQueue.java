package com.zyan.leetcode;

public class MyPriorQueue {
    private int[] nums;
    private int maxSize;
    private int size;

    public MyPriorQueue(int maxSize) {
        nums = new int[maxSize];
        this.maxSize = maxSize;
        this.size = 0;
    }

    public void add(int num) {
        if (size == maxSize - 1) {
            nums[nums.length - 1] = num;
        } else {
            nums[size] = num;
        }
        adjustUp(nums, size);
        size++;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int poll() {
        int max = nums[0];
        swap(nums, 0, size - 1);
        adjustHeap(nums, 0, size - 1);
        size--;
        return max;
    }

    public void adjustUp(int[] nums, int index) {
        while (index != 0) {
            int parent = index / 2;
            if (nums[parent] < nums[index]) {
                swap(nums, parent, index);
            }
            index = parent;
        }
    }

    public int size() {
        return size;
    }


    public  void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//先取出当前元素i
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {//从i结点的左子结点开始，也就是2i+1处开始
            if (k + 1 < length && arr[k] < arr[k + 1]) {//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if (arr[k] > temp) {//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }

    public boolean isEmpty() {
        if (size <= 0) {
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        MyPriorQueue queue = new MyPriorQueue(10);
        queue.add(5);
        queue.add(7);
        queue.add(6);
        queue.add(8);
        queue.add(3);
        queue.add(7);
        queue.add(9);
        queue.add(1);

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}
