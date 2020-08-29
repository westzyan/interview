package com.zyan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * @author zhangyan
 * @date 2020/8/27 下午7:05
 */
public class JD implements B{
    public static void main(String[] args) throws IOException {
        int i;
        JD j = new JD();
        i = j.k;
        System.out.println(i);
//        new ObjectInputStream(new FileInputStream("dd"));

        new BufferedWriter(new FileWriter(""));
        new GZIPOutputStream(new FileOutputStream("dsaf"));
//        new BufferedReader(new FileInputStream("dfsf"));
    }
}
interface B{
    int k = 100;
}
