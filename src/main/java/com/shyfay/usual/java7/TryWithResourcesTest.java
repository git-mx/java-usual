package com.shyfay.usual.java7;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Notes java7提供的try with resources 功能，可以将实现了AutoCloseable的资源自动关闭
 * 并且不需要写Catch模块，
 * @Author muxue
 * @Since 8/15/2020
 */
public class TryWithResourcesTest {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\xue.a.mu\\1111\\gihub\\java-usual\\file\\log.txt";
        try (FileInputStream in = new FileInputStream(filePath)) {
            in.read();
        } catch (Exception e){

        }
    }
}
