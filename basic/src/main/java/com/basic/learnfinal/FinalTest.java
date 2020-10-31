package com.basic.learnfinal;

import java.util.Random;

/**
 * final 测试
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-06-20 10:37
 */
public class FinalTest {

    //==============================测试1======================================
    private static Random rand = new Random();

    private final int a1 = rand.nextInt(10);
    private static final int a2 = rand.nextInt(10);
    private static final String str = "abc";

    /**
     * a1 final 修饰的成员变量，表明值不可改变
     * a2 static final 修饰成员变量，表明不可变同时，a2为类变量，加载初始化1次，new 新的实例后，该字段值不变
     *
     * 结果：a1值在不同对象不同；a2值在不同对象，值相同
     *
     * @param args
     */
    public static void main(String[] args){
        FinalTest fdata = new FinalTest();
        System.out.println("实例化对象调用a1的值："+fdata.a1);
        System.out.println("实例化对象调用a2的值："+fdata.a2);

        //实例化另外一个对象
        FinalTest fdata2 = new FinalTest();
        System.out.println("重新实例化对象调用a1的值："+fdata2.a1);
        System.out.println("重新实例化对象调用a2的值："+fdata2.a2);
    }

}