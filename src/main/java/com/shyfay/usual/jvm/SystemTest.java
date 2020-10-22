package com.shyfay.usual.jvm;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Properties;

/**
 * @Notes System类也是有许多实用方法的，它的几个方法，比如gc();exit()方法实际上都是调用的
 * 在IDEA中将带main函数的java项目打包成jar包的方法
 * File -> Project Structure ->
 * Artifacts(+jar From modules of Dependencies) ->
 * 选择一个带main函数的Class其他默认点击apply ok
 * Build -> Build Artifacts ->选择要重新生成的jar包
 * 在CMD命令行，通过java -jar -Dspring.profiles.active=dev xxx.jar 运行即可
 * Runtime对应的方法
 * @Author muxue
 * @Since 8/1/2020
 */
public class SystemTest {
    public static void main(String[] args) {
        //1.获取系统环境变量,也可以根据具体的环境变量名称来获取
        Map<String, String> envMap = System.getenv();
        //2.获取JVM的启动参数列表
        //java -jar -Dspring.profiles.active=dev xxx.jar
        //注意是将参数放在xxx.jar之前
        Properties properties = System.getProperties();//获取所有的参数
        System.out.println(properties.getProperty("spring.profiles.active"));
        //下面这些属性都可以通过System.getProperties("");来获得
        //java.version java.home java.class.version
        //java.class.path java类的查找路径
        //java.io.tmpdir 默认的临时目录
        //os.name os.version
        //file.separator 文件分隔符UNIX下是/
        //path.separator 路径分隔符UNIX下是":"
        //line.separator 换行符UNIX下是\n JDK7以后可以这么用System.lineSeparator()
        //user.name 当前登录操作系统的账号
        //user.home 当前登录操作系统账号的用户目录
        //user.dir 用户当前的工作目录


        //为mian方法设置启动参数，直接写参数名
        //java -jar xxx.jar param1 注意是将参数放在 xxx.jar之后


        //3.获取当前时间的微毫秒，即一毫秒=10微毫秒，并不是纳秒，1毫秒=1000纳秒
        System.nanoTime();

        //4.使用setOut()改变输出流
        try {
            PrintStream out = System.out;
            PrintStream ps = new PrintStream(System.getProperty("user.dir") + System.getProperty("file.separator") + "file" + System.getProperty("file.separtor") + "log.txt");
            System.setOut(ps);
            System.out.println("AAAA");
            System.out.println("BBBB");
            System.setOut(out);
            System.out.println("CCCC");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //5.调用identityHashCode()获取到对象的引用地址，每一个对象的identityHashCode()
        //都是不一样的
        System.out.println(System.identityHashCode(envMap));

        //6.使用arrayCopy()方法实现最高效的数组元素拷贝，它是一个native方法，效率很高
        //源数组，起始位置，目标数组，目标开始位置，拷贝的元素个数
        Integer[] arr1 = {1,2,3,4,5};
        Integer[] arr2 = new Integer[5];
        System.arraycopy(arr1, 3, arr2, 2, 3);

    }
}
