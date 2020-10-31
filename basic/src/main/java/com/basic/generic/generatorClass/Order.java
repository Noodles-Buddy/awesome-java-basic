package com.basic.generic.generatorClass;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单泛型类
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-10-22 07:54
 */
@ToString
public class Order<T> {

    int orderId;

    String orderName;

    T orderT;

    public Order() {}

    public Order(int orderId, String orderName, T orderT) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderT = orderT;
    }

    /**
     * 这个是泛型方法，因为E的类型，与泛型类中的T 不一致。
     *
     * todo: 注意 泛型方法可以声明为static, 因为其类型在使用时才定义；但与泛型类相同的 "T" 的方法，不能声明为 static，实例化时就需要确定其类型。
     *
     * @param
     * @param <E>
     * @return
     */
    public <E> List<E> copyArrayToList(E[] array) {
        List<E> list = new ArrayList<>();
        for (E e : array) {
            list.add(e);
        }
        return list;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    /**
     * 非泛型方法
     * @return
     */
    public T getOrderT() {
        return orderT;
    }

    /**
     * 非泛型方法
     * @return
     */
    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }
}
