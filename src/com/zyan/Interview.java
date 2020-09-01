package com.zyan;

/**
 * @author zhangyan
 * @date 2020/9/1 下午12:08
 */
public class Interview {
    /**
     * 计算大整数的阶乘
     *
     * @param bigNumber
     */
    public static void getBigFactorial(long bigNumber) {

        if (bigNumber < 0) {
            throw new IllegalArgumentException("参数必须为非负数");
        }
        //数组长度
        double length = 0;
        //总位数
        int count = 0;
        //记录最高位
        int maxIndex = 0;

        //计算最终数组的长度
        for (int i = 1; i <= bigNumber; i++) {
            length += Math.log10(i);
        }
        //最终结果数组，结果按照倒序存放
        long[] result = new long[(int) length + 1];

        result[0] = 1;
        for (int i = 1; i <= bigNumber; i++) {

            //获取最高位下标位置
            maxIndex = getMaxIndex(result);

            //从个位开始向后计算每一位的结果
            for (int j = 0; j <= maxIndex; j++) {
                result[j] *= i;
            }
            //a为进位数
            long a = 0;
            //将数组进位
            for (int j = 0; j <= maxIndex; j++) {
                //当前位加上进位
                result[j] += a;
                if (result[j] <= 9) {
                    //下一位不需要进位
                    a = 0;
                } else if (result[j] > 9 && j < maxIndex) {
                    //记录进位
                    a = result[j] / 10;
                    //当前位
                    result[j] %= 10;
                } else if (result[j] > 9 && j >= maxIndex) {

                    //最高位，循环进位
                    while (result[j] > 9) {
                        a = result[j] / 10;
                        result[j] = result[j] % 10;
                        //最高位保留进位
                        result[++j] = a;
                    }
                }
            }
        }

        int zeroCount = 0;
        for (int i = result.length - 1; i >= 0; i--) {

            count++;
            if (result[i] == 0) {
                zeroCount++;
            } else {
                zeroCount = 0;
            }
            System.out.print(result[i]);

            if (count % 40 == 0) {
                System.out.println();
            } else if (count % 5 == 0) {
                System.out.print(" ");
            }
        }

        System.out.println("\n计算结束，总位数：" + count + "\t 末尾0的个数：" + zeroCount);

    }

    /**
     * 获取数组最高位下标位置
     *
     * @param arr
     * @return
     */
    private static int getMaxIndex(long[] arr) {

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != 0) {
                return i;
            }
        }
        return 0;
    }


    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
//        getBigFactorial(600);
//        long end = System.currentTimeMillis();
//        System.out.println("计算用时: "+(end-start)+" ms");
        int n = 9;
        System.out.println(n >>> 1);
//        n |= n >>> 1;
//        System.out.println(n);
//        n |= n >>> 2;
//        System.out.println(n);
//        n |= n >>> 4;
//        System.out.println(n);
//        n |= n >>> 8;
//        System.out.println(n);
//        n |= n >>> 16;
//        System.out.println(n);
    }
}
