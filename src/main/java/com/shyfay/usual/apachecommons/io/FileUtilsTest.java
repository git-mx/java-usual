package com.shyfay.usual.apachecommons.io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.CRC32;

/**
 * @Notes FileUtils
 * @Author muxue
 * @Since 8/15/2020
 */
public class FileUtilsTest {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\xue.a.mu\\1111\\gihub\\java-usual\\file\\log.txt";
        File file = new File(filePath);
        InputStream inputStream = null;
        try {
            //1.readLines
            List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8.name());
            System.out.println(lines);
            inputStream = new URL("http://www.baidu.com").openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //2.byteCountToDisplaySize 将字节数组的长度，转化成EB/GB/MB/KB/bytes等
            String s = FileUtils.byteCountToDisplaySize(IOUtils.toByteArray(inputStream).length);
            System.out.println(s);
            //3.checksum/checksumCRC32 类似于校验MD5合，一般用于校验下载的文件是否安全等
            FileUtils.checksum(file, new CRC32()).getValue();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //还有很多方法比如文件拷贝，清空目录，目录拷贝等等
        //cleanDirectory
        //contentEquals
        //convertFileCollectionToFileArray
        //copyDirectory 拷贝或剪切
        //copyFile
        //copyFileToDirectory
        //copyFileToInputStream
        //copyURLToFile
        //deleteDirectory
        //deleteQuietly 删除文件和递归删除文件夹
        //forceDelete 强制删除一个文件
        //forceMkdir 强制创建一个目录
        //getTempDirectoryPath 拿到IO临时文件夹路径
        //isFileNewer/isFileOlder
        //listFiles/listFilesAndDirs
        //moveDirectory/moveFile
        //openInputStream 效果同 new FileInputStream
        //openOutputStream
        //readFileToByteArray/readFileToString/readLines
        //sizeOf/sizeOfAsBigInteger
        //sizeOfDirectory/sizeOfDirectoryAsBigInteger
        //toFile(URL url)
        //write/writeByteArrayToFile/writeLines/WriteStringToFile
    }
}
