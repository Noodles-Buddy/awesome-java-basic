package lambda;

import dto.Employee;
import lambda.practice.MyFun;
import lambda.practice.MyFuncCalc;
import lambda.practice.MyFuncUpper;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 一、Lambda表达式基础语法 ： "->" 箭头操作符、
 * 左侧：lambda表达式参数列表 - 对应"函数式接口"参数
 * 右侧：lambda表达式所需执行的功能，lambda体 - 对应"函数式接口"实现
 *
 * 语法格式一：无参数，无返回值
 * () -> System.out.println("Hello Lambda!");
 *
 * 语法格式二：有一个参数，无返回值
 *
 * 语法格式三：只有一个参数，小括号可以不写
 *
 * 语法格式四：有两个以上的参数，有返回值，并且 Lambda 体中有多条语句
 *
 * 语法格式五：若 Lambda 体中只有一条语句，大括号 和 return 可不写
 *
 * 语法格式六：Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即 "类型判断" (参考 test04)
 * (Integer x, Integer y) -> Integer.compare(x,y);
 *
 * String[] strs = {"aaa", "bbb", "ccc"};
 * List<String> list = new ArrayList<>();
 *
 *
 * 总结：
 * 左右遇一括号省
 * 左侧推断类型省
 *
 *
 * 二、Lambda 表达式需要"函数式接口"的支持
 * 函数式接口： 接口中只有一个抽象方法的接口，称为函数式接口。（@FunctionalInterface 注解修饰）
 *
 * 实战：
 *
 *
 *
 * Created by zhangzhenhua on 2020-01-04 16:02
 */
public class LambdaTest02 {

    public static void main(String[] args) {
        test08();
    }

    /**
     * 语法格式1
     */
    public static void test01(){
        //jdk 1.7前，必须是final; 1.8 默认 也是 final.
        int  num = 0;
        //老方式：匿名内部类
        Runnable run = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World" + num);
            }
        };
        run.run();

        System.out.println("--------------------");

        //新方式
        Runnable run1 = () -> System.out.println("Hello Lambda");
        run1.run();
    }

    /**
     * 语法格式2
     */
    public static void test02(){
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("神州优车！");
    }

    /**
     * 语法格式四
     */
    public static void test03(){
        Comparator<Integer> com = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x,y);
        };
    }

    /**
     * 语法格式五
     */
    public static void test04(){
        Comparator<Integer> com = (x, y) -> Integer.compare(x,y);
    }

    /**
     * todo 实战1 对一个数进行运算
     */
    public static void test05(){
        Integer num = operation(100, (x) -> x * x);
        System.out.println(num);

        Integer num1 = operation(200, (x) -> x + 200);
        System.out.println(num1);
    }

    /**
     * 函数式接口应用
     * @param num
     * @param mf
     * @return
     */
    public static Integer operation(Integer num, MyFun mf){
        return mf.getValue(num);
    }

    /**
     * todo 实战2 按照姓名、年龄（递减）排序
     */
    public static void test06(){
        Collections.sort(emps, (e1, e2) -> {
            if(e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            } else {
                return -Integer.compare(e1.getAge(), e2.getAge());
            }
        });
        for (Employee emp : emps){
            System.out.println(emp);
        }
    }

    static List<Employee> emps = Arrays.asList(
            new Employee("张三",16,226.66, Employee.Status.BUSY),
            new Employee("李四",31,666.66, Employee.Status.FREE),
            new Employee("王五",25,623.66, Employee.Status.VOCATION),
            new Employee("赵六",40,546.66, Employee.Status.BUSY),
            new Employee("田七",77,34566.66, Employee.Status.BUSY)
    );

    /**
     * todo 实战3 字符串 去空格 转大写 截取
     */
    public static void test07(){
        String str = strHandler("abcabc", (x) -> x.trim());
        System.out.println(str);

        String str1 = strHandler("abcabc", (x) -> x.toUpperCase());
        System.out.println(str1);
    }

    public static String strHandler(String str, MyFuncUpper mf){
        return mf.getValue(str);
    }

    /**
     * todo 实战4 对2个 Long 类型数据处理
     */
    public static void test08(){
        System.out.println(opera(100L, 200L, (x, y) -> x + y));
    }

    /**
     *
     * @param l1
     * @param l2
     * @param mf
     * @return
     */
    public static Long opera(Long l1, Long l2, MyFuncCalc<Long, Long> mf){
        return mf.getValue(l1, l2);
    }
}