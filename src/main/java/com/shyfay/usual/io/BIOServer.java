package com.shyfay.usual.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 网络编程的基本模型是C/S模型，即两个进程间的通信。C/S Client/Server 即客户端和服务器
 * 服务器端提供IP和监听端口，客户端通过连接操作向服务端监听的地址发起连接请求，通过TCP协议的三次握手，如果连接成功建立，
 * 双方就可以通过套接字进行通信。
 * 本实例用于演示早期的JAVA Web服务器是怎么接受和响应Http请求的
 * 早期的JAVA Web服务器是使用BIO编程，即同步阻塞模型，ServerSocket负责绑定IP地址，启动监听端口；SOCKET负责发起连接操作。
 * 连接成功后双方通过输入输出流进行同步阻塞式通信。采用BIO通信模型的服务端，通常由一个独立的Acceptor线程负责监听客户端的连接，
 * 它收到客户端的请求之后为每个客户端请求创建一个新的线程进行业务逻辑处理，处理完成之后通过输出流返回给客户端，线程销毁。即典型
 * 的一请求一应答通宵模型。该模型最大的问题就在于缺乏弹性伸缩能力，当客户端并发量很大时，服务器端就会创建很多线程，从而导致服务
 * 器端的性能急剧下降甚至宕机。
 * @author mx
 * @since 2019/7/29
 */

public class BIOServer {
    private ServerSocket serverSocket;
    private String ip;
    private int port;

    public BIOServer(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public void startListen() throws IOException {
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(ip, port));
        while(true){
            accpet();
        }
    }

    private void accpet() throws IOException {
        //an
        Socket socket = serverSocket.accept();

        //输入流用于接收客户端传输过来的数据
        InputStream in = socket.getInputStream();
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);
        String line = null;
        while((line = br.readLine()) != null){
            System.out.println(line);
            //按照Http协议，请求头和请求体之间用一空行分割
            if("".equals(line)){
                break;
            }
        }

        //输出流用于向客户端发送响应消息，需遵从HTTP协议
        OutputStream out = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out);
        BufferedWriter bw = new BufferedWriter(writer);
        bw.write("HTTP/1.1 200 OK \n");
        bw.write("Content-Type: text/html; charset=UTF-8\n");
        bw.write("\n");
        bw.write("<html>");
        bw.write("<title>");
        bw.write("BIO Http Server");
        bw.write("</title>");
        bw.write("</head>");
        bw.write("<body>");
        bw.write("<h1>We received your request...</h1>");
        bw.write("</body>");
        bw.write("</html>");

        //必须在请求读取和响应写入处理完毕之后才可以调用close()方法，将输入流关闭也会导致输入流不可用
        bw.close();
        writer.close();
        out.close();
        br.close();
        reader.close();
        reader.close();
        in.close();
        socket.close();
    }

    public static void main(String[] args) {
        BIOServer server = new BIOServer("127.0.0.1", 8080);
        try{
            server.startListen();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
