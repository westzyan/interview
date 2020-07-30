package com.zyan.dp;

import java.util.*;

public class ZeroOnePackge {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {

            /* 1.读取数据 */

            int number = sc.nextInt(); // 物品的数量

            // 注意：我们声明数组的长度为"n+1",并另score[0]和time[0]等于0。
            // 从而使得 数组的下标，对应于题目的序号。即score[1]对应于第一题的分数,time[1]对应于第一题的时间
            int[] weight = new int[number + 1]; // {0,2,3,4,5} 每个物品对应的重量
            int[] value = new int[number + 1]; // {0,3,4,5,6} 每个物品对应的价值

            weight[0] = 0;
            for (int i = 1; i < number + 1; i++) {
                weight[i] = sc.nextInt();
            }

            value[0] = 0;
            for (int i = 1; i < number + 1; i++) {
                value[i] = sc.nextInt();
            }

            int capacity = sc.nextInt(); // 背包容量

            /* 2.求解01背包问题 */

            int[][] v = new int[number + 1][capacity + 1];// 声明动态规划表.其中v[i][j]对应于：当前有i个物品可选，并且当前背包的容量为j时，我们能得到的最大价值

            // 填动态规划表。当前有i个物品可选，并且当前背包的容量为j。
            for (int i = 0; i < number + 1; i++) {
                for (int j = 0; j < capacity + 1; j++) {
                    if (i == 0) {
                        v[i][j] = 0; // 边界情况：若只有0道题目可以选做，那只能得到0分。所以令V(0,j)=0
                    } else if (j == 0) {
                        v[i][j] = 0; // 边界情况：若只有0分钟的考试时间，那也只能得0分。所以令V(i,0)=0
                    } else {
                        if (j < weight[i]) {
                            v[i][j] = v[i - 1][j];// 包的容量比当前该物品体积小，装不下，此时的价值与前i-1个的价值是一样的，即V(i,j)=V(i-1,j)；
                        } else {
                            v[i][j] = Math.max(v[i - 1][j], v[i - 1][j - weight[i]] + value[i]);// 还有足够的容量可以装当前该物品，但装了当前物品也不一定达到当前最优价值，所以在装与不装之间选择最优的一个，即V(i,j)=max｛V(i-1,j)，V(i-1,j-w(i))+v(i)｝。
                        }
                    }
                }
            }

            System.out.println();
            System.out.println("动态规划表如下：");
            for (int i = 0; i < number + 1; i++) {
                for (int j = 0; j < capacity + 1; j++) {
                    System.out.print(v[i][j] + "\t");
                }
                System.out.println();
            }
            System.out.println("背包内最大的物品价值总和为：" + v[number][capacity]);// 有number个物品可选，且背包的容量为capacity的情况下，能装入背包的最大价值

            /* 3.价值最大时，包内装入了哪些物品？ */

            int[] item = new int[number + 1];// 下标i对应的物品若被选中，设置值为1
            Arrays.fill(item, 0);// 将数组item的所有元素初始化为0

            // 从最优解，倒推回去找
            int j = capacity;
            for (int i = number; i > 0; i--) {
                if (v[i][j] > v[i - 1][j]) {// 在最优解中，v[i][j]>v[i-1][j]说明选择了第i个商品
                    item[i] = 1;
                    j = j - weight[i];
                }
            }

            System.out.print("包内物品的编号为：");
            for (int i = 0; i < number + 1; i++) {
                if (item[i] == 1) {
                    System.out.print(i + " ");
                }
            }
            System.out.println("----------------------------");

        }

    }


    /**
     * //考虑物品的件数限制
     *                     int maxV = Math.min(num[i-1],j/weight[i-1]);
     *                     for(int k=0;k<maxV+1;k++){
     *                         dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-k*weight[i-1]]+k*value[i-1]);
     *                     }
     * ————————————————
     * 版权声明：本文为CSDN博主「sinat_yt」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/sinat_38650470/article/details/98958868
     */


    /**
     *
     *      * 完全背包
     *      * 思路分析：
     *      * 注意这里当考虑放入一个物品 i 时应当考虑还可能继续放入 i，
     *      * 因此这里是dp[i][j-weight[i]]+value[i], 而不是dp[i-1][j-weight[i]]+value[i]。
     *      * 放第i件物品。这里的处理和01背包有所不同，因为01背包的每个物品只能选择一个
     *      * *因此选择放第i件物品就意味着必须转移到dp[i-1][v-w[i]]这个状态；但是完全背包
     *      *问题不同，完全背包如果选择放第i件物品之后并不是转移到dp[i-1][v-w[i]]这个状态，
     *      *而是转移到dp[i][v-w[i]]，这是因为每种物品可以放任意件（注意有容量的限制，因此
     *      *还是有限的），放了第i件物品后还可以继续放第i件物品，直到第二维的v-w[i]无法保
     *      *持大于等于0为止。
     *      * @param V
     *      * @param N
     *      * @param weight
     *      * @param value
     *      * @return
     *
     *
     */
/*    public static int completePack(int V,int N,int[] weight,int[] value){
        int[][] dp = new int[N+1][V+1];
        for(int i=1;i<N+1;i++){
            for(int j=1;j<V+1;j++){
                if(weight[i-1] > j)
                    dp[i][j] = dp[i-1][j];
                else
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-weight[i-1]]+value[i-1]);
            }
        }
        returndp[N][V];
    }
————————————————
    版权声明：本文为CSDN博主「sinat_yt」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
    原文链接：https://blog.csdn.net/sinat_38650470/article/details/98958868*/

}
