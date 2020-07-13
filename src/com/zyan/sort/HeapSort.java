package com.zyan.sort;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapSort {

    public void heapSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int len = arr.length;
        buildMaxHeap(arr, len);

        for (int i = len - 1; i > 0; i--) {
            swap(arr, 0, i);
            len--;
            heapify(arr, 0, len);
        }
    }

    private void heapify(int[] arr, int i, int len) {
        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
        if (l < len && arr[l] > arr[largest]) {
            largest = l;
        }
        if (l < len && arr[r] > arr[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, largest, len);
        }
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

}
