package com.shyfay.usual.java7;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/** C:\Users\xue.a.mu\1111\gihub\java-usual\file
 * @Notes
 * @Author muxue
 * @Since 7/29/2020
 */
public class FileChannelTest {
    private static final String filePath = System.getProperty("user.dir")
            + System.getProperty("file.separator")
            + "file"
            + System.getProperty("file.separator")
            + "log.txt";

    private static void openAndWirte(final String context){
        try {
            FileChannel fileChannel = FileChannel.open(Paths.get(filePath), StandardOpenOption.WRITE);
//            ByteBuffer byteBuffer = ByteBuffer.allocate(context.length());
//            byteBuffer.put(context.getBytes(StandardCharsets.UTF_8));
//            byteBuffer.flip();
            //下面的方法不需要调用flip()方法，推荐使用
            ByteBuffer byteBuffer = ByteBuffer.wrap(context.getBytes(StandardCharsets.UTF_8));
            fileChannel.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String openAndRead(){
        try {
            FileChannel fileChannel = FileChannel.open(Paths.get(filePath), StandardOpenOption.READ);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
//            int limit = byteBuffer.limit();
//            byte[] context = new byte[limit];
//            byteBuffer.get(context, 0, limit);
//            return new String(context);
            //推荐使用下面这种方式
            return Charset.forName("UTF-8").decode(byteBuffer).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        openAndWirte("AAAA\nBBBB");
        System.out.println(openAndRead());
    }
}
