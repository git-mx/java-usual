package com.shyfay.usual.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * OutputStreamWriter 字符流到字节流的转换
 * @author mx
 * @since 2019/7/27
 */
public class OutputStreamWriterTest {
    public static void main(String[] args) {
        try{
            transWriteByBuf();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void transWriteNoBuf() throws IOException {
        OutputStream outputStream = System.out;
        OutputStreamWriter osr = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
        String str = "你好吗？\n我很好！";
        osr.write(str);
        osr.flush();
        osr.close();
    }

    public static void transWriteByBuf() throws IOException {
        OutputStream outputStream = System.out;
        OutputStreamWriter osr = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
        BufferedWriter bf = new BufferedWriter(osr);
        String str = "你好吗？\n我很好！";
        bf.write(str);
        bf.flush();
        bf.close();
    }
}
