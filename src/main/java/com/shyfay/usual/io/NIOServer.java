package com.shyfay.usual.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * 从JDK1.4开始提出了NIO（NEW I/O）的概念，其实距今已经很久了（并不新），因此民间很多都将其称为Non-block I/O （非阻塞I/O）
 * 就现在我们编程使用的所有IO操作其实都是指的NIO（例如java.io.*），即使你并没有显示地引用java.nio.*
 * NIO提供了与传统BIO模型中的Socket和ServerSocket相对应的SocketChannel和ServerSocketChannel两种不同的套接字通道实现。
 * 新增的这两种通道都支持阻塞和非阻塞模式。
 * 阻塞模式使用像传统BIO的支持一样，比较简单，但是性能和可靠性都不好，非阻塞模式正好相反
 * 对于低负载，低并发的应用程序，可以使用同步阻塞的模型来提升开发效率和更好的维护性，对于高负载，高并发（网络）应用，应使用NIO的非阻塞模式来实现
 * NIO中的基本概念
 * 缓冲区Buffer
 *  - Buffer是一个对象，包含一些要写入和读出的数据，
 *    在NIO库中，所有的数据都是在缓冲区里处理的。在读取数据时，它是直接读到缓冲区的，在写入数据时，也是写入到缓冲区的。缓冲区其实是一个数组，并提供
 *    了对数据结构化访问以及维护数据的读写位置等信息。具体的缓冲区实现类有：ByteBuffer、CharBuffe、ShrotBuffer、IntBuffer、LongBuffer、
 *    FloatBuffer、DoubleBuffer。它们都实现了相同的接口Buffer。
 *  - 通道Channel
 *    我们对数据的读取和写入都要通过通道Channel。它就想水管一样，是一个通道。通道与流不同的地方在与通道是双向的，可以用于读、写或者同时读写操作。
 *    底层操作系统的通道一般是全双工的，所以全双工的channel比流更好的映射底层操作系统API
 *    Channel主要分为两大类
 *    SelectableChannel：用于网络读写
 *    FileChannel：用于文件读写
 *    非阻塞模型中使用到的ServerSocketChannel和SocketChannel都是SelectableChannel的子类
 *  - 多路复用器Selector
 *    Selector是JAVA NIO编程的基础。
 *    Selector 提供选择已经就绪的任务的能力。Selector会不断的轮训注册在其上的Channel，如果某个Channel上发生读写操作事件，这个Channel就处于
 *    就绪状态，会被Selector轮训出来，然后通过SelectionKey可以获取就绪的Channel集合，进行后续的IO操作。
 *    一个Selector可以同时轮训多个Channel，因为JDK使用epoll()代替传统的select实现，所以没有最大连接句柄1024/2048的限制。只需要一个线程负责
 *    Selector的轮训，就可以介入成千上万的客户端。
 *  - NIO 服务端
 *    代码比传统的Socket变成看起来要复杂不少，
 *    创建NIO服务端的主要步骤如下：
 *    1.打开ServerSocketChannel，监听客户端连接
 *    2.绑定监听端口，设置连接为非阻塞模式
 *    3.创建Reactor，创建多路复用器并启动线程
 *    4.将ServerSocketChannel注册到Reactor的Selector上，监听Accpet事件。
 *    5.Selector轮训就绪的key
 *    6.Selector监听到新的客户端接入，处理新的接入请求，完成TCP三次握手，建立物理链路
 *    7.设置客户端链路为非阻塞模式
 *    8.将新接入的客户端连接注册到Reactor线程的Selector上，监听读写操作，读写客户端发送的网络请求。
 *    9.异步读取客户端消息到缓冲区
 *    10.对Buffer编解码，处理半包消息，将解码成功的消息封装成Task
 *    11.将应答消息编码成Buffer，调用SocketChannel的write方法将消息异步发送给客户端。
 * https://www.cnblogs.com/sally-zhou/p/6442468.html
 * @author mx
 * @since 2019/7/29
 */
public class NIOServer {
    private String ip;
    private int port;
    private Selector selector;

    public NIOServer(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public void startListen() throws IOException{
        //创建Reactor，创建多路复用器并启动线程
        selector = Selector.open();
        //打开ServerSocketChannel，监听客户端连接
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        //设置连接为非阻塞模式
        serverChannel.configureBlocking(false);
        //将ServerSocketChannel注册到Reactor的Selector上，监听Accpet事件。
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        //绑定监听端口
        serverChannel.bind(new InetSocketAddress(ip, port));


        while(true){
            //Selector轮训就绪的key
            //不能使用select方法，该方法会阻塞，如果阻塞过程中channel状态就绪，会因此而阻塞无法执行。
            //所以，如果调用阻塞方法，下面对channel状态的处理得另起一个常驻线程
            int result = selector.selectNow();
            if(result == 0){
                continue;
            }
            //如果有就绪的key，判断key处于哪个就绪状态，从而进行相应的处理
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while(it.hasNext()){
                SelectionKey key = it.next();
                if(key.isAcceptable()){
                    accept(key);
                }else if(key.isReadable()){
                    read(key);
                }else if(key.isWritable()){
                    write(key);
                }else{
                    System.out.println("Unknow selector type...");
                }
            }
            //一定要调用remove方法将已经处理的SelectionKey清理掉，否则会造成后面的请求无法接受
            it.remove();
        }

    }

    //Selector监听到新的客户端接入，处理新的接入请求，完成TCP三次握手，建立物理链路
    private void accept(SelectionKey key) throws IOException {
        System.out.println("Receive connection");
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
        SocketChannel channel = serverSocketChannel.accept();
        if(channel != null){
            //设置客户端链路为非阻塞模式
            channel.configureBlocking(false);
            //将新接入的客户端连接注册到Reactor线程的Selector上，监听读写操作，读写客户端发送的网络请求。
            channel.register(selector, SelectionKey.OP_READ);
        }
        System.out.println("Channel end");
    }

    //异步读取客户端消息到缓冲区
    private void read(SelectionKey key) throws IOException {
        System.out.println("Start read");
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(64);
        boolean hasContent = false;

        //对Buffer编解码，处理半包消息，将解码成功的消息封装成Task
        //这里的判断条件不能是不能于-1，因为channel一直都在，只有在数据都读完后里面为空，返回的长度是0，用-1会导致循环一直无法退出。
        while(channel.read(buffer) > 0){
            //切换成读模式
            buffer.flip();
            CharBuffer cb = Charset.forName("UTF-8").decode(buffer);
            System.out.println(cb.toString());
            buffer.clear();
            hasContent = true;
        }
        if(hasContent){
            //设置interestOps用于写响应
            key.interestOps(SelectionKey.OP_WRITE);
        }else{
            channel.close();
        }
        System.out.println("Read end");
    }


    //将应答消息编码成Buffer，调用SocketChannel的write方法将消息异步发送给客户端。
    private void write(SelectionKey key) throws IOException {
        System.out.println("Start write");
        SocketChannel channel = (SocketChannel)key.channel();
        String resText = getResponseText();
        ByteBuffer buffer = ByteBuffer.wrap(resText.getBytes());
        //此处不能写成channel.write(buffer) != -1，因为两端都不关闭的情况下，会要一直返回0，导致该循环无法退出。
        while(buffer.hasRemaining()){
            channel.write(buffer);
        }
        channel.close();
        System.out.println("Write end...");
    }

    private String getResponseText(){
        StringBuffer sb = new StringBuffer();
        sb.append("HTTP:/1.1 200 OK\n");
        sb.append("Content-Type: text/html; charset=UTF-8\n");
        sb.append("\n");
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>");
        sb.append("NIO HTTP Server");
        sb.append("</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<h1>We received your request...</h1>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    public static void main(String[] args) {
        NIOServer server = new NIOServer("127.0.0.1", 8080);
        try{
            server.startListen();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
