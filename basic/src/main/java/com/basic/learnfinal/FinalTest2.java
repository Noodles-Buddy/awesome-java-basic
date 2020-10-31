package com.basic.learnfinal;

/**
 *
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-06-20 11:16
 */
public class FinalTest2 {

    public static int a;
    int b;

    /*
     * Java关键字this只能用于方法方法体内。当一个对象创建后，Java虚拟机（JVM）就会给这个对象分配一个引用自身的指针，这个指针的名字就是this。
     * 因此，this只能在类中的非静态方法中使用，静态方法和静态的代码块中绝对不能出现this，并且this只和特定的对象关联，而不和类关联，同一个类的不同对象有不同的this。
     * Static 修饰方法：不属于对象，属于类，用类名直接调用
     */
    public static void main(String[] args) {
        a = 3;
        // b 无static修饰，属于对象的属性；结合类加载机制，'加载'->'连接'
        //b = 4;

        // 修改 方法区 静态变量name（单份类变量，不会复制； 未加final，允许改变其值）
        Sta.action();

        Sta sta = new Sta();
        System.out.println("我的名字是：" + sta.getName());

        Sta s1 = new Sta();

        // 第2个对象创建，改变方法区类变量hh值，后续new新对象，hh值亦改变
        s1.hh = 88;

        Sta s2 = new Sta();
        System.out.println(s2.hh);

    }

}


class Sta {

    private static String name = "duck";
    public int age = 21;


    static int hh = 99;

    public void say() {
        System.out.println("我 是一只可爱的鸭子");
    }
    public  String getName() {
        return name;
    }

    public static void action() {
        name = "my name is not duck";
        System.out.println("我是静态方法");
    }

}
