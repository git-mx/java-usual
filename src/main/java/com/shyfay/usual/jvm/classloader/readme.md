JVM的类加载
01.java.lang.ClassLoader类的基本职责就是根据一个指定的类的名称，找到或者生成对应的字节代码，然后利用这些字节代码定义出一个JAVA类
  即java.lang.Class类的一个实例。
02.java程序启动时，并不是一次把所有的类全部加载后再运行，它总是先把保证程序运行的基础类首先一次性加载到JVM中，其他的类等待JVM需要用到
  的时候再进行加载，这么做的目的是节省内存开销。
03.类加载器的三个特性
    委托：在程序中调用子加载器加载类的时候，子加载器并不会直接加载传入的类，而是先委托给父类进行加载，如果父类加载不了，再由自己加载
    可见性：子加载器可以看到父加载器加载过的所有类，而父加载器并不能看到子加载器加载的类
    一次性：一个类只会被加载一次，父加载器加载完成后会为这个类打上标记，表示这个类已经被我加载过了，后续的子加载器不需要再次加载
04.类加载器的种类
    BootstrapClassLoader：由C++语言编写（其余的均为JAVA语言编写），它是最顶层的加载器，它主要负责加载%JAVA_HOME%/jre/lib/rt.jar
                     中的类
    ExtensionClassLoader：它是Bootstrap类加载器的子加载器，主要负责加载%JAVA_HOME%/jre/lib/ext路径下所有的class目录中的类和
                     java.ext.dirs（System.getProperty("java.ext.dirs")）系统变量指定的路径中的类库所承载的类
    ApplicationClassLoader：应用程序类加载器主要负责加载由程序员定义在classpath所指定的位置的类或者是jar文档，它是应用程序默认的
                       类加载器（也就是说当JVM启动之后，如果程序员没有为一个类或者路径指定类加载器，那么就会被该加载器加载）
    ConsumerClassLoader：
    04-1.为什么需要自定义类加载器？   
         因为JAVA中默认的ApplicationClassLoader只会加载指定目录下的jar和class，如果我们想加载其他位置的jar时，比如我们想要
         将一个网络上的类加载到应用程序中使用，这时就无法使用默认的ApplicationClassLoader了，而需要我们自定义类加载器
    04-2.自定义类加载器的操作步骤如下：
         继承java.lang.ClassLoader
         重写父类的findClass方法。（如果不想打破双亲委派模型，那么只需要重写findClass方法即可，如果需要打破双亲委派模型，那么就需要重写loadClass方法）
05.类加载器的步骤
    加载：查找.class文件并以字符流的形式读取.class文件的内容到内存
    验证：验证.class文件是否符合JVM规范，有没有安全问题
    准备：为类的静态变量分配内存，并将其初始化为初始值
    解析：把虚拟机常量池中的符号引用转换为直接引用
         在JAVA中，JVM会为每一个类维护一个常量池，这个常量池不同于字符串常量池，字符串常量池是整个JVM共享的，该常量池只属于该类
         里面保存着该类的字面值（例如类的修饰符，类名，类里面所有的方法名，返回值，参数等等）和符号引用的有序集合。
         这些符号引用（例如int a = 5;中的a）就是符号引用，而解析过程就是把它转换成指向堆中对象内存起始地址的相对地址。
         一个类里面的一切元素在编译过后都是符号引用，在解析时全部都会转换成指向存储“引用对象”的内存起始地址的指针
         比如一个类有如下信息：
         public class Person implements Runnabel {
            private String name;
            private Integer age;
            @Override
            public void run(){
                System.out.println(name + ":" + age);
            }
         }
         这个类通过反编译后得到的Constant pool里面就可以看到各个符号引用（javap -verbose Person.class）
         包含如下符号引用：
         Methodref java/lang/Object."<init>":()V 即Object类的init方法引用，所有的类就继承自Object类
         Fieldref java/lang/System.out:Ljava/io/PrintStream; System类的out变量引用
         Class  java/lang/StringBuilder StringBuilder类引用（因为拼接字符串实际上是新建了一个StringBuilder类并且调用StringBuilder的append方法）
         Methodref // java/lang/StringBuilder."<init>":()V StringBuilder的初始化方法
         Fieldref shyfay/test/MainTest.name:Ljava/lang/String; 类本身定义的name成员变量
         Methodref java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder; StringBuilder.append方法引用
         String : 字符串常量(":")引用
         Fieldref shyfay/test/MainTest.age:Ljava/lang/Integer 类本身定义的age成员变量
         Methodref java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder; StringBuilder.append方法引用
                   （注意：是方法被调用了几次就会有几个引用）
         ...
    初始化：类中静态变量的赋值动作，以及执行类中的静态代码块内的逻辑，编译器在编译阶段就会将静态代码块内的逻辑准备好，
          父类的初始化执行动作优先于当前类的初始化执行动作
06.为什么需要双亲委派模型
    双亲委派模型提供了具有层次机构的类加载模型，即JVM需要使用到的核心类由JVM自身提供的BootstrapClassLoader和
    ExtensionClassLoader加载，而其他的则由应用程序和用户自定义类加载器加载。这样做的好处是保证了核心的模块不会被污染
    保证的JVM能够顺利启动并稳定运行不受程序员的干扰的同时，提供给应用程序自定义类加载器的方式用于实现一些应用程序本身需要的功能
    在JAVA体系中以java.*开头的类，JVM的实现中已经保证了必须由BootstrapClassLoader来进行加载
    所以如果一个黑客自定义了一个java.lang.String想替换原有的String类这是完全不可能的
07.SPI（Service Provider Interface）服务提供者接口
    JAVA定义了一组应用级别的组件接口，各个应用程序可以为这些接口提供不同的实现以满足自身的需求，比如JDBC，SLF4J等。
    不同的数据库厂商提供不同的JDBC数据库驱动，比如MYSQL，ORACLE，H2等
    在面向对象程序设计里，一般推荐模块之间基于接口编程，模块之间不对实现类进行硬编码。一旦代码里涉及具体的实现类，就违反了
    可插拔原则，如果需要替换一种实现，那么就需要修改代码，这样的设计显然是很不方便的。
    SPI的具体约定为：当服务的提供者提供了服务接口的一种实现之后，在jar包的META-INF/services/目录里同时创建一个以服务
    接口命名的文件。该文件就是实现该服务接口的具体实现类。而当外部程序装配这个模块的时候，就能通过该jar包的/META-INF/services/
    里的配置文件找到具体的实现类名，并装载实例化，完成模块的注入。JDK提供服务实现查找的一个工具类ServiceLoader
08.ThreadContextClassLoader 线程上线文类加载器
    基于07里提供的SPI，接口的类加载器是BootstrapClassLoader，而实现类则是由各个厂商提供的，因此它肯定不能被JVM的BootstrapClassLoader
    和ExtensionClassLoader加载的。JVM是利用ThreadContextClassLoader来加载这些模块接口的实现类的，具体的做法是：
    JDK提供了一种帮第三饭实现者加载服务（实现类）的便捷方式，只要第三方实现则遵循约定（把类名写到/META-INF里面），那么当JVM启动时
    会去扫描所有的jar包符合约定的类名，但是JVM的ClassLoader是没办法加载的，那就把他们放到当前线程的ClassLoader里，然后再调用
    Class.forName进行加载（具体代码位置在ServicesLoader.load里有这么一句Thread.currentThread().getContextClassLoader()）。
         