package com.shyfay.usual.datatype;

/**
 * byte 是1个字节8位，short 和char 是2个字节18位 int float 是4个字节 32位 double long是8个字节64位
 * 本例中为什么结果会是-128呢，那是因为127的二进制是01111111,而byte类型的值在进行运算时会先转换成int类型的
 * int类型的011111111加1之后变成10000000，是int类型的128，但是第一位又是1代表负数，所以是-128
 * @author mx
 * @since 2019/4/8
 */
public class ByteTest {
    public static void main(String[] agrs){
        byte a = 127;
        a+=1;
        System.out.println(a);
    }
}
