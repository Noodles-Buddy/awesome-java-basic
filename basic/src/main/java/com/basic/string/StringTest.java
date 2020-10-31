package com.basic.string;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-06-20 17:00
 */
public class StringTest {

    public static void main(String[] args) {
        String s1 = "1";
        String s2 = new String("1");
        String s3 = "1" + "2" + "3";
        String s4 = "123";
        String s5 = "1" + "3" + new String("1") + "4";

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        System.out.println(s3 == s4);
        System.out.println(s3.equals(s4));
    }
}
