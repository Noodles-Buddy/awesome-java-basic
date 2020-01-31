package lambda;

import dto.Employee;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、方法引用：若 lambda 体中的内容有方法已经实现了，我们可以使用"方法引用"（可理解方法引用是lambda表达式的另外一种表现形式）
 *
 * 三种语法格式：
 * 1) 对象 :: 实例方法名
 * 2) 类 :: 静态方法名
 * 3) 类 :: 实例方法名
 *
 * 注意：
 * 1.Lambda体中调用方法的"参数列表"与"返回值类型"，需与函数式接口中抽象方法的"函数列表"和"返回值类型" 保持一致。
 * 2.todo 若 Lambda 参数列表中的第一个参数是 实例方法的调用者，而第二个参数是实例方法的参数时， 可以使用 ClassName :: method 参考test4()
 *
 *
 * 二、构造器引用
 * 格式：
 * ClassName :: new
 *
 * 注意：
 * 需要调用的构造器参数列表 要与 函数式接口中的抽象方法的参数列表 保持一致。
 *
 *
 * 三、数组引用
 * Type :: new
 *
 * Created by zhangzhenhua on 2020-01-12 15:22
 */
public class TestMethodRef {

    public static void main(String[] args) {
        test2();
    }

    /**
     * 对象 :: 实例方法名
     */
    public static void test1(){
        //消费型接口 Consumer中只有1个抽象方法accept， 这种写法，右边表达式的参数要和接口参数保持一致
        Consumer<String> con = (x) -> System.out.println(x);

        //等效 （对象:: 实例方法）
        PrintStream ps = System.out;
        Consumer<String> con1 = ps :: println;

        //等效 （类::实例方法名）
        //接口实现
        Consumer<String> con2 = System.out::println;
        //接口调用
        con2.accept("abcabc");
    }

    /**
     * 对象 :: 实例方法名
     */
    public static void test2(){
        Employee emp = new Employee("vincent",28,24.00d);
        Supplier<String> sup = () -> emp.getName();
        String strName = sup.get();
        System.out.println(strName);

        //等效
        Supplier<String> sup2 = emp::getName;
        String strName1 = sup2.get();
        System.out.println(strName1);

        //扩展
        Supplier<Integer> sup3 = emp::getAge;
        Integer age = sup3.get();
        System.out.println(age);
    }

    /**
     * 类::静态方法名 (lambda体 参数 和 接口参数列表 需保持一致)
     */
    public static void test3(){
        Comparator<Integer> com = (x,y) -> Integer.compare(x, y);
        //等效
        Comparator<Integer> com1 = Integer::compareTo;

    }

    /**
     * 类::实例方法名
     * todo (第一个参数是实例方法的"调用者"，第二个参数是实例方法的"参数")
     */
    public static void test4(){
        BiPredicate<String, String> bp = (x,y) -> x.equals(y);
        //等效
        BiPredicate<String, String> bp2 = String::equals;
    }

    /**
     * 构造器引用
     */
    public static void test5(){
        Supplier<Employee> sup = () -> new Employee();

        //构造器引用方式
        Supplier<Employee> sup2 = Employee::new;
        Employee emp = sup2.get();
        System.out.println(emp);
    }

    /**
     * 构造器引用
     */
    public static void test6(){
        Function<Integer, Employee> fun = (x) -> new Employee(x);

        Function<Integer, Employee> fun2 = Employee::new;
        Employee emp = fun2.apply(101);
        System.out.println(emp);
    }


    /**
     * 数组引用
     */
    public static void test7(){
        Function<Integer, String[]> fun = (x) -> new String[x];
        String[] strs = fun.apply(10);
        System.out.println(strs.length);

        Function<Integer, String[]> fun2 = String[]::new;
        String[] strs2 = fun2.apply(20);
        System.out.println(strs2.length);
    }

}
