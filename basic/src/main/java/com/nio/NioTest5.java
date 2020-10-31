package com.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-07-04 09:37
 */
public class NioTest5 {

    /**
     * ByteBuffer提供存入其他类型api，最终转为byte[]
     *
     * <p>
     * 存入顺序，必须与获取顺序对应
     * </p>
     *
     * @param args
     */
    /*
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(3);
        buffer.putDouble(1.0);
        buffer.putLong(123123l);
        buffer.putChar('a');
        buffer.putShort((short) 4);

        buffer.flip();
        System.out.println(buffer.getInt());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
    }
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
