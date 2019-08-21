package com.shyfay.usual.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * InputStreamReader 从字节流到字符流的转换
 * @author mx
 * @since 2019/7/27
 */
public class InputStreamReaderTest {
    public static void main(String[] args) {
        try{
            transReadByBuf();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    //不带高效缓冲的读取操作
    private static void transReadNoBuff() throws IOException {
        InputStream in = System.in;
        InputStreamReader isr = new InputStreamReader(in);
        char []arr = new char[1024];
        int len = isr.read(arr);
        System.out.println(new String(arr, 0, len));
        isr.close();
    }

    //带高效缓冲的读取操作
    private static void transReadByBuf() throws IOException {
        InputStream in = System.in;
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        while((line = br.readLine()) != null){
            System.out.println(line);
        }
        isr.close();
        br.close();
    }
}
