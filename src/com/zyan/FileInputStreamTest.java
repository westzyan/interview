package com.zyan;

import com.sun.org.apache.xpath.internal.operations.String;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileInputStreamTest {
    public  static void main(String[] args)throws IOException {
        FileInputStream fis=null;
        FileOutputStream fos=null;
        try {
            //创建字节输入流
            fis=new FileInputStream("D:\\zyan\\1.txt");
            //创建字节输出流
            fos=new FileOutputStream("D:\\zyan\\2.txt");

            byte[] b=new byte[1024];
            int hasRead=0;

            //循环从输入流中取出数据
            while((hasRead=fis.read(b))>0){
                //每读取一次，即写入文件输入流，读了多少，就写多少。

                fos.write(b,0,hasRead);
                System.out.println();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            fis.close();
            fos.close();
        }
    }
}
