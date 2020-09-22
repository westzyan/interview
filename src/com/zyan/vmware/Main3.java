package com.zyan.vmware;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author zhangyan
 * @date 2020/9/21 下午8:45
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nums = scanner.nextLine();
        String[] numsArray = nums.split(" ");
        BigDecimal n = new BigDecimal(numsArray[0]);
        int m = Integer.parseInt(numsArray[1]);
        BigDecimal r = new BigDecimal(numsArray[2]);
        BigDecimal perimeter = n.multiply(new BigDecimal(4));
        BigDecimal range1 = n;
        BigDecimal range2 = n.multiply(new BigDecimal(2));
        BigDecimal range3 = n.multiply(new BigDecimal(3));
        BigDecimal sum = new BigDecimal(0);
        DecimalFormat df = new DecimalFormat("0.00");
//        df.setRoundingMode(RoundingMode.HALF_EVEN);
//        df.setRoundingMode(RoundingMode.HALF_UP);
        for (int i = 0; i < m; i++) {
            sum = sum.add(r);
//            System.out.println(sum);
//            double curSum = sum % perimeter;
            BigDecimal curSum = sum.subtract(perimeter.multiply(new BigDecimal(i % 4)));
            if (curSum.compareTo(range1) < 0) {
                System.out.println(df.format(curSum.floatValue()) + " 0.00");
            } else if (curSum.compareTo(range2) < 0) {
                System.out.println(df.format(n.floatValue()) + " " + df.format(curSum.subtract(range1).floatValue()));
            } else if (curSum .compareTo(range3) < 0) {
                System.out.println(df.format(n.subtract(curSum).add(range2).floatValue()) + " " + df.format(n.floatValue()));
            } else {
                System.out.println(df.format(0) + " " + df.format(n.subtract(curSum).add(range3).floatValue()));
            }
        }
    }
}
