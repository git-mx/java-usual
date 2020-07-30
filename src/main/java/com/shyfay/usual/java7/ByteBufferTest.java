package com.shyfay.usual.java7;

import java.nio.ByteBuffer;

/**
 * @Notes ByteBuffer是JAVA7提供的全新的NIO2.0(AIO)
 * ByteBuffer是有一个容器，它的容量单位是字节，它是对字节数组的一种封装
 * 它有三个比较重要的成员属性：
 * 1.capacity 容量 该ByteBuffer的容量（单位是字节）
 * 2.position 游标 记录最后一次读写操作之后的位置
 * 3.limit 结束标记下标，表示进行下一次读写操作时的最大结束位置，一般情况下等于capacity
 * @Author muxue
 * @Since 7/29/2020
 */
public class ByteBufferTest {
    private static void useByteBuffer(){
        ByteBuffer buffer = ByteBuffer.allocate(32);
        //放入1字节，position变成1
        buffer.put((byte)1);
        //放入3字节，position变成4
        buffer.put(new byte[3]);
        //放入一个字节
        buffer.putChar('A');
        //放入四个字节
        buffer.putFloat(0.0f);
        //放入八个字节
        buffer.putLong(10, 100L);
        System.out.println(buffer.getChar(4));
        System.out.println(buffer.remaining());
        System.out.println(buffer.getFloat(5));
    }

    public static void main(String[] args) {
        //useByteBuffer();
        ByteBuffer buffer = ByteBuffer.allocate(64);
        //position默认的-1，放入4个字节之后变成4
        buffer.putFloat(1.0f);
        //又写入4个字节，position变成8
        buffer.putInt(1);
        //又写入8个字节，position变成16
        buffer.putLong(100L);
        //默认的get() 从当前buffer的position未知开始读取一个字节的内容，即读取position所在的那个
        //字节的内容
        //System.out.println(buffer.get());
        //getFloat(index)，从指定的position开始，读取4个字节的内容并将读取到的内容转换成一个float
        System.out.println(buffer.getFloat(0));
        //getInt(index)，从指定的position开始，读取4个字节的内容并将读取到的内容转换成一个int
        System.out.println(buffer.getInt(4));
        //getLong(index)， 从指定的position开始，读取4个字节的内容并将读取到的内容转换成一个Long
        System.out.println(buffer.getLong(8));
        //获取当前ByteBuffer还有多少字节的剩余空间
        System.out.println(buffer.remaining());
        //rewind()方法将ByteBuffer的position设置为0
        buffer.rewind();
        System.out.println(buffer);
        System.out.println(buffer.getInt());
        //flip()方法，该方法不仅将position复位为0， 同时也将limit标记在了上一个position标记位
        //这样position和limit之间即位新读取到的有效数据
    }
}
