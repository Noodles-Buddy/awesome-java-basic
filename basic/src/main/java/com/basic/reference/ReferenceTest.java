package com.basic.reference;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-06-21 10:34
 */
public class ReferenceTest {

    private static void test(A a) {
        a.setName("李四");
    }

    private static void test1(int b) {
        b = 20;
    }

    private static void test2(A a) {
        a = null;
    }

    private static void test3(A a) {
        a.setName(null);
    }

    public static void main(String[] args) {
        // 对象
        A a = new A("张三");
        test(a);
        System.out.println(a.getName());

        // 值
        int b = 10;
        test1(b);
        System.out.println(b);

        // 测试 根可达 失效
        test2(a);
        System.out.println(a.getName());

        // 测试 根可达 失效
        test3(a);
        System.out.println(a.getName());

    }
}


class A {

    A (String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}