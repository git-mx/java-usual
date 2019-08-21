package com.shyfay.usual.io;

import java.io.*;

/**
 * 磁盘IO：
 * JAVA IO有四个基本的虚类
 *  -InputStream 用于字节流读取
 *  -OutputStream 用于字节流写入
 *  -Reader 用于字符流读取
 *  -Writer 用于字符流写入
 * 两个特殊的类
 *  -OutputStreamWriter 字符流到字节流的桥梁
 *  -InputStreamReader 字节流到字符流的桥梁
 *  其他几乎所有的JAVA IO类几乎都继承自这四个虚类
 *  只要是以InputStream或者OutputStream结尾的JAVA IO类都是字节流处理类
 *  只要是以Reader或者Writer结尾的JAVA IO类都是字符流处理类
 *  字符与字节的区别是：字符就是我们平时在文档上看到的特定格式的文本（这个本文这个JAVA代码，就是以UTF-8编码的文本），
 *  字符流的读取和写入是占用CPU时间的，也不需要将字符转换成字节
 *  字节流就是以字节为单位的二进制流，是CPU能识别的二进制串。
 *  对于一个线程来说磁盘IO操作是很耗时的操作，因为磁盘的写入与读取都是很慢的
 *  但是对于现代的LINUX系统或者其他系统来说磁盘IO操作其实并不会耗费很多CPU资源
 *  现在的操作系统都是使用DMA（Direct Memory Access，直接内存存取）来进行磁盘IO操作的
 *  CPU的执行时间片中如果遇到线程的IO请求，则会将IO的具体工作交给DMA去处理，这时线程阻塞，等到DMA执行完磁盘IO操作后
 *  告知CPU说我执行完磁盘IO操作了，这时CPU唤醒刚才被阻塞的线程继续执行后面的逻辑代码。
 *  JAVA的磁盘IO操作分为普通的IO操作和带有高效缓冲区的IO操作，其中以Buffered打头的JAVA类都是带有高效缓冲的磁盘IO处理类
 *  对于普通的写入操作而言（这里拿字节流来举例），是一个字节一个字节地往硬盘里写，这样的话就意味着有多少个字节的数据就有多少次磁盘写入操作，是很耗时的
 *  带有缓冲区的写入操作，是先将N个字节的数据一个字节一个字节地写入缓冲区，等到了一定的量时，再将缓冲区内的数据一次性写入磁盘
 *  读操作也是一样的，对于普通的读取操作而言（这里拿字节流来举例），是一个字节一个字节地从磁盘里读出数据，这也是有多少个字节的数据，都要读多少次磁盘
 *  带有缓冲区的读操作，是先将磁盘的数据写入到缓冲区，然后冲缓冲区一次性读取一定量的字节数，例如BufferedReader.readLine()，一行一个行地读
 * @author mx
 * @since 2019/6/17
 */
public class IOTest {
    public static void main(String[] args) {
        //copyFile();
        editFile();
        //readFile();
        System.out.println("OK");
    }

    private static void copyFile(){
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try{
            fileInputStream = new FileInputStream("D:\\files\\2M.jpg");
            fileOutputStream = new FileOutputStream("D:\\files\\2M-COPY.jpg");
            byte[] buf = new byte[1024];
            int n = 0;
            while((n = fileInputStream.read(buf)) != -1){
                fileOutputStream.write(buf, 0, n);
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                fileInputStream.close();
                fileOutputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private static void editFile(){
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try{
            fileWriter = new FileWriter("D:\\files\\TEST.txt", true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
            bufferedWriter.write("BBBB");
            bufferedWriter.newLine();
            bufferedWriter.write("CCCC");
            bufferedWriter.flush();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                bufferedWriter.close();
                fileWriter.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private static void readFile(){
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try{
            fileReader = new FileReader("D:\\files\\TEST.txt");
            bufferedReader = new BufferedReader(fileReader);
            while(true){
                String str;
                try{
                    str = bufferedReader.readLine();
                    if(str == null){
                        break;
                    }
                    System.out.println(str);
                }catch(Exception e){

                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                bufferedReader.close();
                fileReader.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
