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
    private static String filePath = "C:\\Users\\xue.a.mu\\1111\\gihub\\java-usual\\file\\3333.txt";
    private static String filePath1 = "C:\\Users\\xue.a.mu\\1111\\gihub\\java-usual\\file\\FileChannelCreate.txt";
    //一定要注意，这是覆盖写
    private static void openAndWrite() throws IOException {
        FileChannel channel = FileChannel.open(
                Paths.get(filePath1)
                , StandardOpenOption.CREATE
                , StandardOpenOption.WRITE);
        ByteBuffer buffer = ByteBuffer.allocate(3651);
        channel.write(buffer);
    }


    private static String readWriteAbsolute() throws IOException {
        FileChannel fileChannel = FileChannel.open(Paths.get(filePath)
        , StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(1561);
        fileChannel.read(buffer);
        buffer.flip();
        System.out.println(buffer);
        return new String(buffer.array());
    }

//    public static void main(String[] args) throws Exception {
//        //openAndWrite();
//        String str = readWriteAbsolute();
//        String[] strs = str.split("\r\n");
//        String temp = "";
//        for(String s : strs){
//            s = s.substring(0, s.length() - 1);
//            temp += "SELECT " + s + " FROM S_CONTACT WHERE ROW_ID = '1-1GAHX3'\n";
//        }
//        System.out.println(temp);
//    }
    public static void main(String[] args) throws Exception {
        String str = readWriteAbsolute();
        String[] strs = str.split("\r\n");
        String temp = "";
        for(int i=0; i<strs.length; i++){
            if(0 == i%6){
                temp += "\n" + strs[i] + ", ";
            }else{
                temp += strs[i] + ", ";
            }
        }
        System.out.println(temp);
    }
}
