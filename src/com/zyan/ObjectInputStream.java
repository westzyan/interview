package com.zyan;

import java.io.*;

public class ObjectInputStream {

    public static void writeObject(){
        OutputStream outputStream=null;
        BufferedOutputStream buf=null;
        ObjectOutputStream obj=null;
        try {
            //序列化文件輸出流
            outputStream=new FileOutputStream("E:\\learnproject\\Iotest\\lib\\src\\main\\java\\com\\myfile.tmp");
            //构建缓冲流
            buf=new BufferedOutputStream(outputStream);
            //构建字符输出的对象流
            obj=new ObjectOutputStream(buf);
            //序列化数据写入
            obj.writeObject(new Person("A", 21));//Person对象
            //关闭流
            obj.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 读取对象
     */
//    public static void readObject() throws IOException {
//        try {
//            InputStream inputStream=new FileInputStream("E:\\learnproject\\Iotest\\lib\\src\\main\\java\\com\\myfile.tmp");
//            //构建缓冲流
//            BufferedInputStream buf=new BufferedInputStream(inputStream);
//            //构建字符输入的对象流
//            ObjectInputStream obj=new ObjectInputStream(buf);
//            Person tempPerson=(Person)obj.readObject();
//            System.out.println("Person对象为："+tempPerson);
//            //关闭流
//            obj.close();
//            buf.close();
//            inputStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

}
class Person{
    private String name;
    private Integer age;
    public Person(String name,Integer age){
        this.name = name;
        this.age = age;
    }
}