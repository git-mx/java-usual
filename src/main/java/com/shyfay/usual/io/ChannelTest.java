package com.shyfay.usual.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author mx
 * @since 2019/7/29
 */
public class ChannelTest {
    private static class Handler {
        private int bufferSize = 1024;
        private String localCharset = "UTF-8";

        public Handler(){

        }
        public Handler(int bufferSize){
            this.bufferSize = bufferSize;
        }

        public Handler(String localCharset){
            this.localCharset = localCharset;
        }

        public Handler(int bufferSize, String localCharset){
            this.bufferSize = bufferSize;
            this.localCharset = localCharset;
        }

        public void handleAccept(SelectionKey key){
            try{
                SocketChannel channel = ((ServerSocketChannel)key.channel()).accept();
                channel.configureBlocking(false);
                channel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        public void handleRead(SelectionKey key) throws IOException {
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer buffer = (ByteBuffer) key.attachment();
            buffer.clear();
            if(channel.read(buffer) == -1){
                channel.close();
            }else{
                buffer.flip();
                String receivedString  = Charset.forName(localCharset).newDecoder().decode(buffer).toString();
                System.out.println("从客户端收到的数据：" + receivedString);
                String sendString = "接受数据:" + receivedString;
                buffer = ByteBuffer.wrap(sendString.getBytes(localCharset));
                channel.write(buffer);
                channel.close();
            }
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocketChannel channel = ServerSocketChannel.open();
            channel.socket().bind(new InetSocketAddress(8080));
            channel.configureBlocking(false);
            Selector selector = Selector.open();
            channel.register(selector, SelectionKey.OP_ACCEPT);
            Handler handler = new Handler(1024);
            while(true){
                if(selector.select(5000) == 0){
                    continue;
                }
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                while(keyIterator.hasNext()){
                    SelectionKey key = keyIterator.next();
                    if(key.isAcceptable()){
                        handler.handleAccept(key);
                    }
                    try{
                        if(key.isReadable()){
                            handler.handleRead(key);
                        }
                    }catch(IOException e){
                        keyIterator.remove();
                        e.printStackTrace();
                    }
                    keyIterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
