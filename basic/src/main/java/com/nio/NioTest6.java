package com.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-07-04 09:42
 */
public class NioTest6 {

    /**
     * slice 分片，参考slice api
     *
     * <p>
     * Creates a new byte buffer whose content is a shared subsequence of this buffer's content.
     *
     * 1.The new buffer will start at this buffer's current position.
     * 2.Changes to this buffer's content will be visible in the new buffer, and vice versa;
     * 3.The two buffers' position, limit, and mark values will be independent.
     * </p>
     *
     * @param args
     */
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        //buffer初始化
        for (int i = 0; i<buffer.capacity(); i++) {
            buffer.put((byte)i);
        }
        //buffer切片，获取slice buffer
        buffer.position(2);
        buffer.limit(6);
        ByteBuffer slice = buffer.slice();

        //切片部分改变内容
        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);
            b *= 2;
            slice.put(b);
        }

        //buffer恢复其位置
        buffer.position(0);
        buffer.limit(buffer.capacity());

        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.get(i));
        }

    }
}
