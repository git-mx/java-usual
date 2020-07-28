package com.shyfay.usual.java8;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @Notes Base64是一种字符串编码格式，采用了A-Z,a-z,0-9,'+'和"/"这64个字符来编码原始字符
 * （还有用于对齐的字符=），为什么需要Base64编码的？这是因为有些网络传送渠道并不支持所有的字节，
 * 例如传统的邮件只支持可见字符的传送，像ASCII码的控制字符就不能通过邮件传送。这样用途就收到了很大
 * 的限制，比如图片二进制流的每个字节不可能全部都是可见字符，所以就无法传输。最好的方法就是在不改变
 * 传统协议的情况下，做一种扩展的方案来传送二进制文件流。
 * 一个字符本身是一个字节，也就是8位，而Base64一个字符只能表示6位的信息。也就是说原始字符串中三个
 * 字符（3个字节）如果用Base64表示需要使用4个字节来表示。如果有一个字符串经过base64编码后的长度
 * （字节数）并不是3的整数倍，最后会剩下一个或者二个字节时Base64是使用'='来进行补齐的，这就是为什么
 * 我们看到的很多图片的Base64编码最后都是以\==结尾的原因
 * 使用Base传输文件流是非常流行的做法
 * Java8中内置了Base64编码器和解码器
 * @Author muxue
 * @Since 7/27/2020
 */
public class Base64Test {
    public static void main(String[] args) {
        String str = "Hello World";
        //编码
        String encodeStr = Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
        System.out.println("加密后的字符串：" + encodeStr);
        //解码
        String decodeStr = new String(Base64.getDecoder().decode(encodeStr), StandardCharsets.UTF_8);
        System.out.println("解密后的字符串：" + decodeStr);
    }

    private static String fileToBase64(String path){
        File file = new File(path);
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            inputStream.read(bytes);
            inputStream.close();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean base64ToFile(String base64Str, String filePath){
        byte[] buffer;
        buffer = Base64.getDecoder().decode(base64Str);
        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            outputStream.write(buffer);
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
