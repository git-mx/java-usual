package com.shyfay.usual.io;

import javax.annotation.processing.FilerException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Notes
 * @Author muxue
 * @Since 7/13/2020
 */
public class FileToBytesTest {
    public static void main(String[] args) throws IOException {
        File file = new File("");
        byte[] resultBytes;
        FileInputStream fis;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        fis = new FileInputStream(file);
        byte[] b = new byte[1024];
        int n;
        while ((n = fis.read(b)) != -1) {
            byteArrayOutputStream.write(b, 0, n);
        }
        fis.close();
        byteArrayOutputStream.close();
        resultBytes = byteArrayOutputStream.toByteArray();
        System.out.println(resultBytes);
    }
}
