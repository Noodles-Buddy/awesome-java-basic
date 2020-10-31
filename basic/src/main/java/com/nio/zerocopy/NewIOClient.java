package com.nio.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-08-09 19:03
 */
public class NewIOClient {

    /**
     *
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("192.127.0.1", 8899));
        socketChannel.configureBlocking(true);

        String fileName = "/Users/zhangzhenhua/Desktop/Netty实战.pdf";
        long startTime = System.currentTimeMillis();
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        fileChannel.close();
        System.out.println("发送总字节： " + transferCount + " 总耗时：" + (System.currentTimeMillis() - startTime));
    }
}
