package com.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * client发送啥，server回复啥
 * 操作: client端 nc 127.0.0.1 5000
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-07-05 16:13
 */
public class NioTest12 {

    /**
     * 服务端
     *
     * <p>
     *
     * 样板1：
     * 样板2：
     * 样板3：
     *
     * 注意：
     * 1 两次register，一次serverSocketChannel.register()，一次socketChannel.register(); 对应selectionKey.channel()强转类型不同。
     * 2
     * 3
     * <p/>
     *
     * @param args
     */
    public static void main(String[] args) throws Exception{
        int[] ports = new int[5];

        ports[0] = 5000;
        ports[1] = 5001;
        ports[2] = 5002;
        ports[3] = 5003;
        ports[4] = 5004;

        Selector selector = Selector.open();

        // todo 样板1 含义是？
        for (int i = 0; i < ports.length; i++) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            ServerSocket socket  = serverSocketChannel.socket();
            InetSocketAddress address = new InetSocketAddress(ports[i]);
            socket.bind(address);

            //注册 accept 事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听端口号：" + ports[i]);
        }

        while (true) {
            // todo 样板2 含义是？
            int num = selector.select();
            System.out.println("numbers:" + num);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectionKeys" + selectionKeys);
            Iterator<SelectionKey> iter = selectionKeys.iterator();

            // todo 样板3
            // acceptable ServerSocketChannel 与 readable SocketChannel 区别？
            while (iter.hasNext()) {
                SelectionKey selectionKey = iter.next();
                // 接受新建立的连接
                if (selectionKey.isAcceptable()) {

                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ);
                    iter.remove();
                    System.out.println("获取到客户端连接" + socketChannel);

                }
                // 读取client内容
                else if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    int byteRead = 0;
                    while (true) {
                        ByteBuffer buffer = ByteBuffer.allocate(512);
                        buffer.clear();
                        int read = socketChannel.read(buffer);
                        if (read <= 0) {
                            break;
                        }
                        buffer.flip();
                        socketChannel.write(buffer);
                        byteRead += read;
                    }
                    iter.remove();
                    System.out.println("读取" + byteRead + " ,来自于" + socketChannel);
                }
            }
        }

    }

}
