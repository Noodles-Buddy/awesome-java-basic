package com.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-06-27 17:01
 */
public class NioTest4 {

    public static void main(String[] args) throws Exception{
        FileInputStream inputStream = new FileInputStream("NioTest2.txt");
        FileOutputStream outputStream = new FileOutputStream("NioTest4.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);

        while (true) {
            buffer.clear();
            int read = inputChannel.read(buffer);
            System.out.println(read);
            if (-1 == read) {
                break;
            }
            buffer.flip();

            outputChannel.write(buffer);
        }
        inputStream.close();
        outputStream.close();
    }

}
