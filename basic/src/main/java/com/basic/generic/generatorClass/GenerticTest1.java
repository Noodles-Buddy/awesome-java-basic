package com.basic.generic.generatorClass;

import com.sun.tools.corba.se.idl.constExpr.Or;
import org.junit.Test;

import java.util.List;

/**
 * 如何自定义泛型
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-10-22 08:01
 */
public class GenerticTest1 {

    @Test
    public void test() {
        /**
         * 不使用泛型，认为orderT为Object类型，拆装箱
         */
        Order order = new Order();
        order.setOrderId(1);
        order.setOrderName("订单1");
        order.setOrderT("附加说明");
        System.out.println(order.toString());

        /**
         * 使用泛型，
         */
        Order<String> order1 = new Order<>();
        order1.setOrderId(1);
        order1.setOrderName("订单1");
        order1.setOrderT("附加说明");
        System.out.println(order1.toString());
    }

    @Test
    public void test2() {
        /**
         * Order子类，继承父类时声明了泛型类型为Integer，实例化无需再次声明泛型类型
         */
        SubOrder1 order1 = new SubOrder1();
        order1.setOrderId(2);
        order1.setOrderName("子订单");
        order1.setOrderT(123);
        System.out.println(order1.toString());
    }

    @Test
    public void test3() {
        /**
         * Order子类，不使用泛型
         */
        SubOrder2 order1 = new SubOrder2();
        order1.setOrderId(2);
        order1.setOrderName("子订单1");
        order1.setOrderT(123);
        System.out.println(order1.toString());

        /**
         * Order子类，使用泛型
         */
        SubOrder2<Integer> order2 = new SubOrder2();
        order2.setOrderId(3);
        order2.setOrderName("子订单2");
        order2.setOrderT(321);
        System.out.println(order2.toString());
    }

    /**
     * 泛型方法 测试
     * 泛型类申明为String，泛型方法申明为Integer.
     */
    @Test
    public void test4() {
        Order<String> orders = new Order<>();
        Integer[] arr = new Integer[]{1,2,3,4};
        //泛型方法调用时，指明其类型
        List<Integer> list = orders.copyArrayToList(arr);
    }
}
