package lambda.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 内置四大核心"函数式接口"
 *
 * 1.Consumer<T> 消费型接口
 *      void accept(T t)
 *
 * 2.Supplier<T> 供给型接口
 *      T get()
 *
 * 3.Function<T,R> 函数型接口
 *      R apply(T t)
 *
 * 4.Predicate<T> 断言型接口
 *      boolean test(T t)
 *
 * todo 拓展接口，参考 网上资料
 *
 * Created by zhangzhenhua on 2020-01-12 11:18
 */
public class LambdaTest03 {

    public static void main(String[] args) {
        test4();
    }

    /**
     * todo 消费型
     */
    public static void test1() {
        happy(10000d, (m) -> System.out.println("旅游消费：" + m + "元；"));
    }

    public static void happy(Double money, Consumer<Double> con) {
        con.accept(money);
    }


    /**
     * todo 供给型
     */
    public static void test2() {
        List<Integer> numList = getNumList(10, () -> (int)(Math.random() * 100));
        for(Integer num : numList){
            System.out.println(num);
        }
    }

    //产生指定个数整数，并放入集合中
    public static List<Integer> getNumList(int num, Supplier<Integer> sup){
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<num; i++){
            Integer n = sup.get();
            list.add(n);
        }
        return list;
    }


    /**
     * 函数型接口
     */
    public static void test3() {
        //去空格
        String rst = strHandler("\t\t\t java8编程威武", (str) -> str.trim());
        System.out.println(rst);

        //截取
        String rst1 = strHandler("java8编程威武", (str) -> str.substring(2,5));
        System.out.println(rst1);
    }

    //处理字符串
    public static String strHandler(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    /**
     * 断言型接口
     */
    public static void test4() {
        List<String> list = Arrays.asList("Hello", "vincent", "Lambda", "www", "ok");
        List<String> rst = filterStr(list, (s) -> s.length() > 3);
        for(String str : rst){
            System.out.println(str);
        }
    }

    //需求：将满足条件的字符串放入集合中
    public static List<String> filterStr(List<String> list, Predicate<String> pre){
        List<String> strList = new ArrayList<>();
        for (String str : list){
            if (pre.test(str)) {
                strList.add(str);
            }
        }
        return strList;
    }

    /**
     *
     */
    public static void test5() {

    }

    /**
     *
     */
    public static void test6() {

    }
}
