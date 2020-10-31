package com.nio.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-08-09 19:03
 */
public class OldIOClient {

    /**
     *
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 8899);

        String fileName = "/Users/zhangzhenhua/Desktop/Netty实战.pdf";
        InputStream inputStream = new FileInputStream(fileName);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096];
        long readCount;
        long total = 0;

        long startTime = System.currentTimeMillis();

        while ((readCount = inputStream.read(buffer)) > 0) {
            total += readCount;
            dataOutputStream.write(buffer);
        }
        System.out.println("发送总字节数： " + total + " 总耗时： " + (System.currentTimeMillis() - startTime));
        dataOutputStream.close();
        socket.close();
        inputStream.close();
    }

}
