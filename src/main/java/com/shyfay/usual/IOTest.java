package com.shyfay.usual;

import java.io.*;

/**
 * @author mx
 * @since 2019/6/17
 */
public class IOTest {
    public static void main(String[] args) {
        //copyFile();
        editFile();
        //readFile();
        System.out.println("OK");
    }

    private static void copyFile(){
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try{
            fileInputStream = new FileInputStream("D:\\files\\2M.jpg");
            fileOutputStream = new FileOutputStream("D:\\files\\2M-COPY.jpg");
            byte[] buf = new byte[1024];
            int n = 0;
            while((n = fileInputStream.read(buf)) != -1){
                fileOutputStream.write(buf, 0, n);
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                fileInputStream.close();
                fileOutputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private static void editFile(){
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try{
            fileWriter = new FileWriter("D:\\files\\TEST.txt", true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
            bufferedWriter.write("BBBB");
            bufferedWriter.newLine();
            bufferedWriter.write("CCCC");
            bufferedWriter.flush();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                bufferedWriter.close();
                fileWriter.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private static void readFile(){
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try{
            fileReader = new FileReader("D:\\files\\TEST.txt");
            bufferedReader = new BufferedReader(fileReader);
            while(true){
                String str;
                try{
                    str = bufferedReader.readLine();
                    if(str == null){
                        break;
                    }
                    System.out.println(str);
                }catch(Exception e){

                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                bufferedReader.close();
                fileReader.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
