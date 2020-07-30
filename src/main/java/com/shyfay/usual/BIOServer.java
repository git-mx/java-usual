package com.shyfay.usual;


import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**通过
 * @Notes
 * 网络编程的基本模型是C/S模型，即两个进程间的通信。Client/Server
 * B/S Browser/Server
 * 服务器端提供IP并开启端口监听，客户端通过连接操作向服务器监听的地址发起连接请求，通过TCP
 * 的三次握手，如果连接建立成功，双方就可以通过套接字进行通信了
 * 早期的JAVA web服务采用的BIO同步阻塞通信方式，ServerSocket负责监听和端口和IP地址，并启动监听
 * SOCKET负责建立连接和数据传输。当连接建立好以后，双方通过Socket的输入输出流进行数据的读写。
 * 采用BIO的通信模型的客户端，通常是由一个独立的Acceptor线程负责监听客户端的连接，
 * 它收到一个客户端的连接请求后，将会为每一个连接创建一个新的线程进行数据读取和写入操作，完后将线程销毁
 * 。即典型的一请求一应答通宵模型。该模型最大的问题在于缺乏弹性的伸缩能力，当客户端并发量很大时，
 * 服务器就会创建很多线程，从而导致服务器的性能急剧下降，甚至宕机
 * @Author muxue
 * @Since 7/24/2020
 */
public class BIOServer {
    private ServerSocket serverSocket;
    private String ip;
    private Integer port;

    public BIOServer(String ip, Integer port){
        this.ip = ip;
        this.port = port;
    }

    public void startListen() throws IOException {
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(this.ip, this.port));
        while(true){
            accept();
        }
    }

    private void accept() throws IOException {
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String context;
        while((context = bufferedReader.readLine()) != null){
            System.out.println(context);
            if("".equals(context)){
                break;
            }
        }

        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        bufferedWriter.write("HTTP/1.1 200 OK\n");
        bufferedWriter.write("Content-Type: text/html; charset=UTF-8\n");
        bufferedWriter.write("\n");
        bufferedWriter.write("<html><head><title>BIO SERVER</title></head>");
        bufferedWriter.write("<body><h1>We received you request ...</h1></body></html>");
        bufferedWriter.close();
        outputStreamWriter.close();
        outputStream.close();
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        BIOServer bioServer = new BIOServer("127.0.0.1", 8080);
        bioServer.startListen();
    }
}
