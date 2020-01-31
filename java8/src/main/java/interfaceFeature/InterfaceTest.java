package interfaceFeature;

/**
 * 一、1.8前 接口 中的内容可以为：
 * 1.全局静态常量
 * 2.抽象方法
 *
 * 二、接口新特性：
 * 1.接口除了有上述2中元素，可以有默认方法 - default 修饰符
 * 2.接口中，可包含静态方法
 *
 * 三、测试
 * 1.父类 和 接口 都有getName() 方法，实例化子类时，调用的是父类的方法， 类优先原则
 * SubClass extends MyClass implements MyFun
 *
 * 2.父类 和 多个接口 都有getName() 方法时，依然 类优先原则
 * SubClass extends MyClass implements MyFun, MyInterface
 *
 * 3.仅实现多个接口时，子类方法需指明实现哪个接口的方法
 * public class SubClass implements MyFun, MyInterface {
 *     @Override
 *     public String getName() {
 *         return MyFun.super.getName();
 *     }
 * }
 *
 */
public class InterfaceTest {

    public static void main(String[] args) {
        SubClass sb = new SubClass();
        System.out.println(sb.getName());

        MyFun.show();
    }

}
