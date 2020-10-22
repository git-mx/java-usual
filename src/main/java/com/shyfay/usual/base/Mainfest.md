一个可执行的jar包里面都包含了一个文件MAINFEST.MF jvm就是根据这个文件来执行main方法从而运行应用程序的
MAINFEST.MF文件总是被放在META-INF目录下
MAINFEST.MF最主要的两个信息有：
Main-Class: shyfay.test.MainTest
Class-Path: lib/commons-beanutils-1.8.0.jar
 lib/byte-buddy-1.7.5.jar
 lib/client-combined-3.8.1.jar
 ...
Main-Class是用于告知JVM这个应用程序的main方法所在的类
Class-Path是用于告知JVM这个应用程序需要用到的外部第三方jar包所在位置
一般使用eclipse或者ideak开发的项目IDE都会为我们自动生成MAINFEST.MF
如果需要手动编写MAINFEST.MF，那么需要遵循如下规范
1.第一行不能空，行与行之间不能有空行，每一行的最后一个字符不能是空格。
2.最后一行一定是空行。
3.每个属性的名称和值之间（冒号后面）一定要有空格。
4.文件的每一行都不能超过72个字节（一般是70个ASCII字母加上回车换行符）；
  如果72个字节不够用，则另起一行并以空格开头：
  以空格开头的行都被视为前一行的续行，不要使用TAB键，否则会报错：invalid header。
5.Class-Path属性指定的类或jar包是本地的文件，不可以是远程访问的类或者JAR包文件中的JAR包，
  即不能是jar in jar，当然也就不能是本jar包中包含的jar包。要实现对jar in jar的引用，
  需要自定义相关代码来读取它们。上面提到的RunnableJAR file之所以能够运行，
  就是因为Eclipse为我们提供了jarinjarloader，来帮助我们读取jar in jar

