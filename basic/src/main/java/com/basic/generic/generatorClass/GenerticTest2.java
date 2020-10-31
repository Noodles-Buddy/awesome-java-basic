package com.basic.generic.generatorClass;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 泛型继承、通配符
 *
 * @Author: zhenhua.zhang
 * @Date: 2020-10-30 08:10
 */
public class GenerticTest2 {

    /**
     * A 是 B 的父类， 但 G<A> 和 G<B> 间不具备父子继承关系。
     *
     * A a = null;
     * B b = null;
     * //编译通过
     * a = b;
     */
    public void test1() {
        List<Object> list1 = null;
        List<String> list2 = null;
        //编译失败
        //list1 = list2;
    }

    /**
     * A 是 B 的父类， 但 A<G> 和 B<G> 间具备父子继承关系。
     *
     * AbstractList<G> list1 = null;
     * List<G> list2 = null;
     * ArrayList<G> list3 = null;
     * //编译通过
     * list1 = list3;
     * list2 = list3;
     */
    public void test2() {
        AbstractList<String> list1 = null;
        List<String> list2 = null;
        ArrayList<String> list3 = null;
        //编译通过
        list1 = list3;
        list2 = list3;
    }

    /**
     * 泛型通配符
     *
     * A 是 B 的父类， 且已知 G<A> 和 G<B> 间不具备父子继承关系，但 G<?> 是 G<A> 和 G<B> 共同的父类。
     */
    public void test3() {
        List<Object> list1 = null;
        List<String> list2 = null;
        List<?> list3 = null;

        list3 = list1;
        list3 = list2;
    }

    /**
     * 泛型通配符 是 集合级别的继承， 该方法被不同的类型的 List 使用
     *
     * @param list
     */
    public void test(List<?> list) {
        Iterator<?> iterator = list.iterator();
        while(iterator.hasNext()) {
            Object obj = iterator.next();
            System.out.println(obj);
        }
    }

    /**
     * 有限制条件的通配符
     * ? extends Person  范围：(-∞, Person]  说明: G<? extends A>, 可作为 G<A> 和 G<B> 的父类，其中 A 是 B 的父类。
     * ? super Person    范围：[Person, +∞)  说明: G<? super A>,   可作为 G<A> 和 G<B> 的父类，其中 B 是 A 的父类。
     *
     * 知识点：
     * 1.理解 ? extends 和 ? super 含义： G<A> 和 G<B> 级别的概念。 （即 list1 = list5 和 list2 = list3 编译失败)
     * 2.通配符修饰可赋值(list1 = list3)、获得值(list1.get(0))，但不能add() - 针对 List 而言
     */
    public void test4() {
        List<? extends Person> list1 = new ArrayList<>();
        List<? super Person> list2 = new ArrayList<>();

        List<Student> list3 = new ArrayList<>();
        List<Person> list4 = new ArrayList<>();
        List<Object> list5 = new ArrayList<>();

        // 向 ? extends 赋值
        list1 = list3;
        list1 = list4;
        //编译不通过，Object 超出上界
        //list1 = list5;

        // 向 ? super 赋值
        //编译不通过，Student 超出下界
        //list2 = list3;
        list2 = list4;
        list2 = list5;

        //读取数据 (todo <? extends>不强转情况，认为 list 只能赋给 Person 或者 更高级别，如：ManKind 或 更高)
        //Student s = list1.get(0);
        Person p = list1.get(0);
        ManKind m = list1.get(0);
        Object o = list1.get(0);

        //读取数据 todo <? super>不强转情况，认为 list 只能赋给 Object，因为没有类型边界)
        //Student s1 = list2.get(0);
        //Person p1 = list2.get(0);
        Object o1 = list2.get(0);

        //写入数据 todo <? extends> 代表的含义：(-∞, Person]， 实际实例化时可能为更低级的子类，故这里不能直接赋值。
        //list1.add(new Person());
        //list1.add(new Student());

        //写入数据 todo <? extends> 代表的含义：[Person, +∞)， 实际实例化时最低边界为Person，故可以成功；add Student 是多态的体现。
        list2.add(new Person());
        list2.add(new Student());
    }
}
