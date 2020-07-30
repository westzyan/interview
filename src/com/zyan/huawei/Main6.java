package com.zyan.huawei;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main6 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        ArrayList<String> result = new ArrayList<>();

        input = input.substring(input.indexOf("5a"), input.lastIndexOf("5a") + 2);
//        if (input.startsWith("5a") && input.endsWith("5a")){
            String[] inputs = input.split("5a");
            for (String s : inputs) {

                if (s.equals("")){
                    continue;
                }
                int count = 0;
                s = s.substring(1, s.length() - 1);
                String[] packets = s.split(" ");
                for (int i = 0; i < packets.length - 1; i++) {
                    if ((packets[i].equals("5b") && packets[i + 1].equals("ba")) || (packets[i].equals("5b") && packets[i + 1].equals("bb"))){
                        count++;
                    }
                }
                if (packets.length - count - 1 == Integer.parseInt(packets[packets.length - 1])){
                    result.add(s);
                }
            }



//        for (int i = 1; i < inputs.length - 1; i++) {
//            if (inputs[i - 1].equals("") && inputs[ i + 1].equals("5a")){
//                int count = 0;
//                inputs[i] = inputs[i].substring(1, inputs[i].length() - 1);
//                String[] packets = inputs[i].split(" ");
//                for (int j = 0; j < packets.length - 1; j++) {
//                    if ((packets[j].equals("5b") && packets[j + 1].equals("ba")) || (packets[j].equals("5b") && packets[j + 1].equals("bb"))){
//                        count++;
//                    }
//                }
//                if (packets.length - count - 1 == Integer.parseInt(packets[packets.length - 1])){
//                    result.add(inputs[i]);
//                }
//            }
//        }





        StringBuilder stringBuilder = new StringBuilder();
        for (String s : result) {
            stringBuilder.append("5a ").append(s).append(" ");
        }
        stringBuilder.append("5a");
        System.out.println(stringBuilder.toString());

        String fileName="output";
        try {
            FileWriter writer=new FileWriter(fileName);
            writer.write(stringBuilder.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
