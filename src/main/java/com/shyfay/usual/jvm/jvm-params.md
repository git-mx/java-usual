JVM的参数主要分为三类
1.- 标准参数 这类参数所指定的功能要求所有的JVM都必须实现，而且向后兼容
    例如：-verbose:class -verbose:gc -verbose:jni
2.-X 非标准参数 JVM默认实现这些参数指定的功能，但是不保证所有的JVM都实现，且不保证向后兼容
    例如：-Xmx20m -Xms20m -Xmn20m -Xss128k
3.-XX 不稳定参数 此类参数各个JVM实现都会不同，将来可能会随时取消，需要慎重使用
    例如：-XX:+PrintGCDetails -XX:-UseParallelGC -XX:PrintGCTimestamp
比较重要的几个JVM参数
-Xmx20m：设置JVM的运行时内存大小为20M
-Xms20m：设置JVM的堆内存大小为20M 一般设置于JVM运行时内存大小一致避免垃圾回收完成后JVM重新分配内存
-Xmn10m：设置堆内存的新生代大小为10m
-Xss128k：设置JAVA的线程栈内存大小为128K
-verbose：gc 可以输出每次GC的一些信息
-XX:-UseConcMarkSweepGC：使用CMS垃圾收集器，CMS垃圾回收策略会使得老年代也使用标记清除算法，这样做有好处也有缺点
    好处就是它不会轻易阻断用户线程（因为不用内存拷贝和复制），缺点就是会增加FullGC的次数
    （因为老年带都是内存碎片，当有大对象需要移动到老年代时倒是没有足够大的连续内存空间从而触发FullGC）
-XX:+UseSerialGC：使用串行回收器进行回收，这个参数同时指定了新生代了老年代都使用串行回收器，新生代使用复制算法，来年代使用标记清除算法
-XX:+UseParallelGC：指定垃圾收集器为并行收集器，此设置仅对年轻代有效，设置了该参数后可以同时并行多个垃圾收集器线程，但此时用户线程必须停止
-XX:+UseParNewGC：类似于-XX:+UseParallelGC，于CMS同时使用，相较于-XX:+UseParallelGC有更好的性能
-XX:CMSInitiatingOccupancyFraction=80 CMS gc 表示老年代达到80%使用率的时候马上进行FullGC
-XX:SurvivorRatio=8：表示新生代的Eden区域和Survivor区域（From幸存区和To幸存区）的比例，默认为8，也就是说Eden区占新生代的8/10
                     From区和To区各占1/10
-XX:PretenureSizeThreshold 设置多大的对象直接进入老年代
-XX:MaxTenuringThreshold=15 设置新生代中经过多少次MinorGC后任然存活的对象会被放入老年代，默认是15
-XX:+HandlePromotionFailure 是否进行MinorGC担保，在发生MinorGC之前，JVM会判断之前每次移动到老年代的对象的平均大小是否大于大于
                            老年代剩余的空闲内存，如果大于则直接进行FullGC，若小于，则去检查HandlePromotionFailure参数是否开启
                            ，如果开启则进行MinorGC，如果没没有开启还是老老实实的进行FullGC。为什么要这么做呢？因为JVM并不知道
                            本次即将进行的MinorGC到底会有多大的对象会被移动到老年区，它也不知道到底老年代剩下的空间是否足够放下本次
                            MinorGC移动到老年代的所有对象，所以它就用以往MinorGC时移动到老年代的平均大小来和老年代剩余的可用空间进行
                            比较，如果大于则直接FullGC，如果小于则。。。
在运行JAR包的时候指定jvm参数使用： java -Xmx20m -Xms20m -jar xxx.jar 
      
