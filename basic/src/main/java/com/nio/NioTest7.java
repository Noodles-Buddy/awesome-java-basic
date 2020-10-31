package com.nio;

import java.nio.ByteBuffer;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-07-04 10:05
 */
public class NioTest7 {

    /**
     * 普通buffer可转只读buffer； 但只读buffer不能转换为读写buffer.
     *
     * @param args
     */
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        //buffer初始化
        for (int i = 0; i<buffer.capacity(); i++) {
            buffer.put((byte)i);
        }
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(buffer.getClass());
        System.out.println(readOnlyBuffer.getClass());

        readOnlyBuffer.position(3);
        //报错，不允许更改
        readOnlyBuffer.put((byte)2);
    }
}
