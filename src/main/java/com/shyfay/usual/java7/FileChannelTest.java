package com.shyfay.usual.java7;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/** C:\Users\xue.a.mu\1111\gihub\java-usual\file
 * @Notes
 * @Author muxue
 * @Since 7/29/2020
 */
public class FileChannelTest {
    private static String filePath = "C:\\Users\\xue.a.mu\\1111\\gihub\\java-usual\\file\\FileChannelCreate.txt";
    //一定要注意，这是覆盖写
    private static void openAndWrite() throws IOException {
        FileChannel channel = FileChannel.open(
                Paths.get(filePath)
                , StandardOpenOption.CREATE
                , StandardOpenOption.WRITE);
        ByteBuffer buffer = ByteBuffer.allocate(64);
        buffer.putChar('A').flip();
        channel.write(buffer);
    }

    private static void readWriteAbsolute() throws IOException {
        FileChannel fileChannel = FileChannel.open(Paths.get(filePath)
        , StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        fileChannel.read(buffer);
        buffer.flip();
        System.out.println(buffer.getChar(0));
    }

    public static void main(String[] args) throws Exception {
        openAndWrite();
        readWriteAbsolute();
    }
}
