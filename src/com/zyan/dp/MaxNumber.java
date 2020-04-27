package com.zyan.dp;

import java.util.Arrays;
import java.util.Scanner;

/*
1.一个有名的大师收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，大师找到最优的预约集合（总预约时间最长），返回总的分钟数
示例 1：
输入： [1,2,3,1]
输出： 4
解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
示例 2：
输入： [2,7,9,3,1]
输出： 12
解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
示例 3：
输入： [2,1,4,5,3,1,1,3]
输出： 12
解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。
来源：力扣（LeetCode）
 */
public class MaxNumber {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String str=scanner.nextLine();
        String[] arrays=str.split(",");   //通过“，”分离
        int[] a=new int[arrays.length];
        for(int i=0;i<a.length;i++){
            a[i]=Integer.parseInt(arrays[i]);   //将String型转化成int型
            System.out.print(a[i]+" ");
        }
        System.out.println(Arrays.toString(a));

        if (a.length == 1){
            System.out.println(a[0]);
        }
        if (a.length == 2){
            System.out.println(Math.max(a[0], a[1]));
        }
        int[] dp = new int[a.length];
        dp[0] = a[0];
        dp[1] = Math.max(a[0], a[1]);
        for (int i = 2; i < a.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + a[ i ]);
        }
        System.out.println(dp[ dp.length - 1]);

    }
}
