package com.shyfay.usual.apachecommons.io;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @Notes
 * @Author muxue
 * @Since 8/15/2020
 */
public class IOUtilsTest {
    public static void main(String[] args) {
        //InputStream inputStream
        InputStream inputStream = null;
        try {
            inputStream = new URL("http://www.baidu.com").openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //1.toString
            System.out.println(IOUtils.toString(inputStream, StandardCharsets.UTF_8.name()));
            //2.toByteArray 把InputStream outputStream, Reader, Writer等转换成字节数组
            IOUtils.toByteArray(inputStream);
            //3.contentEquals/contentEqualsIgnoreEOL 判断两个Stream或者Reader/Writer里面的内容是否相等
            //4.copy 拷贝流
            //5.copyLarge 拷贝比较大的流
            //6.lineiterator 行迭代器
            LineIterator lineIterator = IOUtils.lineIterator(inputStream, StandardCharsets.UTF_8.name());
            while(lineIterator.hasNext()){
                lineIterator.nextLine();
            }
            lineIterator.close();
            //7.read/readFully 把流里的内容添加到第二个参数指定的字节数组里
            //8.readLine
            //9.toBufferedInputStream InputStream -> BufferedInputStream
            //10.toCharArray
            //11.toInputStream 把字符/字符串等等直接读到流里
            //12.write/writeChunked/writeLines 把传入的字节数组写入到输出流里，可指定编码
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
