package com.zyan;

import java.util.Scanner;

public class Problem {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[][] a = new char[3][3];
        for (int i = 0; i < 3; i++) {
            String s = in.next();
            for (int j = 0; j < 3; j++) {
                a[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < 3; i++) {
//            String s = in.next();
            for (int j = 0; j < 3; j++) {
                System.out.println(a[i][j]);
            }
        }


        int i;
        for (i = 0; i < 3; i++)
        {
            if (a[i][0] == a[i][1] && a[i][1] == a[i][2] )//判断列是否相同
            {
                if (a[i][1] == 'K')
                {
                    System.out.println("KiKi wins!");
                }

                if (a[i][1] == 'B')
                {
                    System.out.println("BoBo wins!");
                }
            }

        }
        for (i = 0; i < 3; i++)
        {
            if (a[0][i] == a[1][i] && a[1][i] ==a[2][i] )//判断行是否相同
            {
                if (a[1][i] == 'K')
                {
                    System.out.println("KiKi wins!");
                }

                if (a[1][i] == 'B')
                {
                    System.out.println("BoBo wins!");
                }
            }

        }
        if (a[0][0] == a[1][1] && a[1][1] == a[2][2])//判断左斜线是否相同
        {
            if (a[1][1] == 'K')
            {
                System.out.println("KiKi wins!");
            }

            if (a[1][1] == 'B')
            {
                System.out.println("BoBo wins!");
            }
        }
        if (a[2][0] == a[1][1] && a[1][1] == a[0][2])//判断右斜线是否相同
        {
            if (a[1][1] == 'K')
            {
                System.out.println("KiKi wins!");
            }

            if (a[1][1] == 'B')
            {
                System.out.println("BoBo wins!");
            }
        }
        else{
            System.out.println("没有赢家");
        }

    }
}
