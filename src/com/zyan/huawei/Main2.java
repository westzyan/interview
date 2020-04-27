package com.zyan.huawei;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String s = in.nextLine();

        String[] temp = s.split(" ");
        if (temp.length != 2){
            System.out.println("FAIL");
        }

        int count = 0;
        String pattern = temp[0];
        String strings = temp[1];
        String[] reads = strings.split("],");

        for (int i = 0; i < reads.length - 1; i++) {
            if (reads[i].startsWith(pattern + "[")){
                reads[i] = reads[i].substring(pattern.length()+1);
//                System.out.println(reads[i]);
                String[] sout = reads[i].split(",");
                if (sout.length == 3){
//                    System.out.println("FAIL");
                    String addr = sout[0].substring(sout[0].lastIndexOf("=") + 1);
                    if (is16(addr)){
                        String mask = sout[1].substring(sout[1].lastIndexOf("=") + 1);
                        if (is16(mask)){
                            String val = sout[2].substring(sout[2].lastIndexOf("=") + 1);
                            if (is16(val)){
                                System.out.println(addr + " " + mask + " " + val);
                                count++;
                            }
                        }
                    }
                }
//                System.out.println(reads[i]);
            }
        }

        if (reads[reads.length - 1].startsWith(pattern + "[")){
            reads[reads.length - 1]= reads[reads.length - 1].substring(pattern.length()+1,reads[reads.length - 1].length() - 1);
//            System.out.println(reads[reads.length - 1]);
            String[] sout = reads[reads.length - 1].split(",");
            if (sout.length == 3){
//                    System.out.println("FAIL");
                String addr = sout[0].substring(sout[0].lastIndexOf("=") + 1);
                if (is16(addr)){
                    String mask = sout[1].substring(sout[1].lastIndexOf("=") + 1);
                    if (is16(mask)){
                        String val = sout[2].substring(sout[2].lastIndexOf("=") + 1);
                        if (is16(val)){
                            System.out.println(addr + " " + mask + " " + val);
                            count++;
                        }
                    }
                }
            }
//                System.out.println(reads[i]);
        }





        if (count == 0){
            System.out.println("FAIL");
        }

//        System.out.println(is16("0xdd"));

    }

    private static boolean is16(String s){
        if (s.startsWith("0x") || s.startsWith("0X")){
            String regex = "^[A-Fa-f0-9]+$";
            if (s.substring(2).matches(regex)){
                return true;
            }
        }
        return false;
    }
}
