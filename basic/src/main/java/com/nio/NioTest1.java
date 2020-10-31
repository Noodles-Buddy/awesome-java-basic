package com.nio;

import java.math.BigDecimal;
import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * nio buffer 输入、输出
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-06-27 11:22
 */
public class NioTest1 {

    public static void main(String[] args) {

        BigDecimal b1 = new BigDecimal(1);
        System.out.println(b1);

        BigDecimal b2 = new BigDecimal(1.0);
        System.out.println(b2);

        BigDecimal b3 = new BigDecimal(0);
        System.out.println(b3);

        BigDecimal b4 = new BigDecimal(0.0);
        System.out.println(b4);

        BigDecimal b5 = new BigDecimal("0");
        System.out.println(b5);

        /*IntBuffer buffer = IntBuffer.allocate(10);
        // 输入 10  个
        for (int i = 0; i < buffer.capacity(); i++) {
            int randomNum = new SecureRandom().nextInt(20);
            buffer.put(randomNum);
        }
        // 输入、输出 转换
        buffer.flip();
        // 输出 10 个
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }*/
    }
}
