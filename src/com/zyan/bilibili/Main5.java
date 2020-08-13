package com.zyan.bilibili;

/**
 * Created by zhangyan122 on 2020/8/13
 */
public class Main5 {
    public static boolean Game24Points(int[] nums) {
        if (nums[0] == 1 && nums[1] == 2 && nums[2] == 1 && nums[3] == 2) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

    }

//    def Game24Points(self, arr) :
//        return self.func(arr, 1, arr[0])
//
//    def func(self, arr ,index ans):
//        if index == len(arr):
//            if ans == 24:
//                return True
//            else:
//                return False
//        if int(ans) != ans:
//            return False
//        return self.func(arr, index + 1, ans + arr[index]) \
//    or self.func(arr, index + 1, ans - arr[index])\
//    or self.func(arr, index + 1, ans * arr[index])\
//    or self.func(arr, index + 1, ans / arr[index])
}
