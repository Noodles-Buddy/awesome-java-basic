package com.nio.zerocopy;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-08-09 19:02
 */
public class OldIOServer {

    /**
     * 传统server，读取client数据
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8899);

        while (true) {
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            try {
                byte[] byteArrays = new byte[4096];
                while (true) {
                    int readCount = dataInputStream.read(byteArrays);
                    System.out.println("读取字节数" + readCount);
                    if (-1 == readCount) {
                        break;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
