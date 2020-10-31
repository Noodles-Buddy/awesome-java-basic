package com.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件中读取
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-06-27 11:29
 */
public class NioTest2 {

    public static void main(String[] args) throws Exception {
        // 从文件中读取
        FileInputStream stream = new FileInputStream("NioTest2.txt");
        FileChannel channel = stream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(10);
        channel.read(buffer);
        // 输入、输出 转换
        buffer.flip();

        while (buffer.hasRemaining()) {
            byte b = buffer.get();
            System.out.println("Character:" + (char)b);
        }
        stream.close();
    }

}
