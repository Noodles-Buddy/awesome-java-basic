package com.basic.generic.generator;

import com.basic.generic.entity.*;

import java.util.Iterator;
import java.util.Random;

/**
 * 咖啡生成器，泛型接口实现
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-10-12 12:25
 */
public class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {

    private Class[] types = {Americano.class, Breve.class, Cappuccino.class, Latte.class, Mocha.class};

    private static Random random = new Random(47);

    /**
     * 无参构造
     */
    public CoffeeGenerator() {};

    private int size;

    /**
     * 带参构造，边界哨兵
     *
     * @param size 生成个数
     */
    public CoffeeGenerator(int size)
    {
        this.size = size;
    }

    /**
     * 咖啡生成，核心方法
     *
     * @return
     */
    public Coffee next() {
        try {
            return (Coffee) types[random.nextInt(types.length)].newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 迭代器内部，边界判断、生成咖啡
     */
    class CoffeeIterator implements Iterator<Coffee> {
        int count = size;

        public boolean hasNext() {
            return count > 0;
        }

        public Coffee next() {
            count--;
            return CoffeeGenerator.this.next();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * 迭代器
     *
     * @return
     */
    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        CoffeeGenerator generator = new CoffeeGenerator();
        for (int i = 0; i < 5; i++) {
            System.out.println(generator.next());
        }
        //todo 增强for循环，实质 调用Iterator接口？
        for (Coffee c : new CoffeeGenerator(5)) {
            System.out.println(c);
        }
    }
}
