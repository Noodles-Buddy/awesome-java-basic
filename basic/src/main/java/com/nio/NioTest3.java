package com.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-06-27 11:36
 */
public class NioTest3 {

    public static void main(String[] args) throws Exception {
        FileOutputStream stream = new FileOutputStream("NioTest3.txt");
        FileChannel channel = stream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);
        byte[] bytes = "hello world!".getBytes();
        // 读到buffer
        for (int i = 0; i < bytes.length; ++i) {
            buffer.put(bytes[i]);
        }
        // 输入、输出 转换
        buffer.flip();
        // 输出到 txt
        channel.write(buffer);
        stream.close();
    }

}
