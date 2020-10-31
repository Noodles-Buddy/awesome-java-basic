package com.nio;

import com.sun.xml.internal.bind.v2.schemagen.XmlSchemaGenerator;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 *
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-07-04 12:00
 */
public class NioTest9 {

    /**
     * Scattering 与 Gathering 的应用
     *
     * <p>
     * telnet 或 nc 测试
     * </p>
     *
     * @param args
     */
    public static void main(String[] args) throws Exception{
        // nio 监听端口
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(address);

        // 定义消息长度9，分散给3个buffer
        int mesLength = 2 + 3 + 4;

        ByteBuffer[] buffers = new ByteBuffer[3];
        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true) {
            //读 buffer
            int bytesRead = 0;
            //读不够9个字节时，不执行后续流程
            while (bytesRead < mesLength) {
                long r = socketChannel.read(buffers);
                bytesRead += r;
                Arrays.asList(buffers).stream()
                    .map((buffer) ->  "position:" + buffer.position() + ", limit:" + buffer.limit())
                    .forEach(System.out::println);
            }

            //读写转换
            Arrays.asList(buffers).forEach(buffer -> { buffer.flip(); });

            //写 buffer
            long bytesWritten = 0;

            while (bytesWritten < mesLength) {
                long r =socketChannel.write(buffers);
                bytesWritten += r;
            }

            //buffer归位
            Arrays.asList(buffers).forEach(buffer -> buffer.clear());
            System.out.println("bytesRead:" + bytesRead + ", bytesWrite:" + bytesWritten + ",messageLength " + mesLength);
        }

    }

}
