package com.basic.generic.entity;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-10-12 12:19
 */
public class Coffee {

    private static long counter;

    private final long id = counter++;

    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }

}
