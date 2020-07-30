package com.zyan;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

//    public static void main(String[] args) {
//	// write your code here
//        byte[] a = {'a','b','v'};
//        String b = new String(a);
//        System.out.println(b);
//        byte[] c = b.getBytes();
////        System.out.println();
//
//
//    }

//    public  static void main(String[] args)throws IOException {
//        FileInputStream fis=null;
//        try {
//            //创建字节输入流
//            fis=new FileInputStream("D:\\zyan\\1.txt");
//            //创建一个长度为1024的竹筒
//            byte[] b=new byte[1024];
//            //用于保存的实际字节数
//            int hasRead=0;
//            //使用循环来重复取水的过程
//            while((hasRead=fis.read(b))>0){
//                //取出竹筒中的水滴（字节），将字节数组转换成字符串进行输出
//                System.out.print(new String(b,0,hasRead));
//                System.out.println("-----------------------------------------------------"+fis.read(b));
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }finally {
//            fis.close();
//        }
//    }

//    public  static void main(String[] args)throws IOException{
//        FileReader fis=null;
//        try {
//            //创建字节输入流
//            fis=new FileReader("D:\\zyan\\1.txt");
//            //创建一个长度为1024的竹筒
//            char[] b=new char[1024];
//            //用于保存的实际字节数
//            int hasRead=0;
//            //使用循环来重复取水的过程
//            while((hasRead=fis.read(b))>0){
//                //取出竹筒中的水滴（字节），将字节数组转换成字符串进行输出
//                System.out.print(new String(b,0,hasRead));
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }finally {
//            fis.close();
//        }
//    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        System.out.println(0.6000);

        System.out.println(a);
        System.out.println(b);
    }

}
