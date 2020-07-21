package com.zyan.sort;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapSort1 {

    public void heapSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int len = arr.length;
        buildMaxHeap(arr, len);

        for (int i = len - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, 0, i);
        }
    }

    private void heapify(int[] arr, int i, int len) {
//        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
//        if (l < len && arr[l] > arr[largest]) {
//            largest = l;
//        }
//        if (l < len && arr[r] > arr[largest]) {
//            largest = r;
//        }
//        if (largest != i) {
//            swap(arr, i, largest);
//            heapify(arr, largest, len);
//        }
        int temp = arr[i];//先取出当前元素i
        for (int k = i * 2 + 1; k < len; k = k * 2 + 1) {//从i结点的左子结点开始，也就是2i+1处开始
            if (k + 1 < len && arr[k] < arr[k + 1]) {//如果左子结点小于右子结点，k指向右子结点
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

    public void buildMaxHeap(int[] arr, int len) {
        for (int i = len / 2 - 1; i >= 0; i++) {
            heapify(arr, i, len);
        }
    }

    public void swap(int[] arr, int a, int b) {
        int temp = arr[b];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int num : nums) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }


    public static void main(String[] args) {
        int[] a = {3, 4, 5, 9, 1, 5, 3, 6, 7, 3, 4, 5};
        new HeapSort1().heapSort(a);
        System.out.println(a);
    }

}
