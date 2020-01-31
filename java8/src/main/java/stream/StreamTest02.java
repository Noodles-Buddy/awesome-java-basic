package stream;

import dto.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 中间操作
 *
 * 1.筛选 切片 代码无终止操作，不执行中间操作
 *   filter 接收lambda流，从中排除元素
 *   limit 截断流，使其元素不超过某个数量
 *   skip(n) 跳过元素，使其返回一个扔掉前n个值的流
 *   distinct 筛选，通过流所生成元素的hashCode()和equal()方法 去重
 *
 * 2.映射
 *   map 接收lambda流，将元素转化成其他形式或提取信息。接收一个函数作为参数，该函数会作用到每个元素上，并将其映射为一个新的元素。
 *   flatMap 接收一个函数作为参数，将流中每个值都换成另一个流，然后把所有流连接成一个流
 *
 * 3.排序
 *   sorted() 自然排序(Comparable)
 *   sorted(Comparator com) 定制排序(Comparator)
 *
 * Created by zhangzhenhua on 2019-12-24 21:39
 */
public class StreamTest02 {

    static List<Employee> employees = Arrays.asList(
        new Employee("张三",16,226.66),
        new Employee("李四",31,666.66),
        new Employee("王五",25,623.66),
        new Employee("赵六",40,546.66),
        new Employee("田七",33,666.66),
        new Employee("田七",55,999.66),
        new Employee("田七",29,666.66),
        new Employee("田七",37,768.66),
        new Employee("田七",26,1226.66),
        new Employee("田七",77,34566.66)
    );

    /**
     * 内部迭代，迭代操作由Stream Api 完成
     */
    public static void test01(){
        Stream<Employee> stream = employees.stream()
                .filter((e)-> e.getAge()>35);
        //终止操作，一次性完成，"惰性求值"
        stream.forEach(System.out::println);
    }

    /**
     * 外部迭代，
     */
    public static void test02(){
        Iterator<Employee> it = employees.iterator();

        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

    /**
     * 短路，limit 和 filter 之间，类似 &&
     */
    public static void test03(){
        employees.stream()
            .filter((e)->{
                System.out.println("短路");
                return e.getSalary()>1000;
            })
            .limit(2)
            .forEach(System.out::println);
    }

    /**
     * skip， 跳过
     */
    public static void test04(){
        employees.stream()
            .filter((e)->{
                System.out.println("短路");
                return e.getSalary()>1000;
            })
            .skip(2)
            .forEach(System.out::println);
    }

    /**
     * distinct, 去重，需重写hashCode 和 equals
     */
    public static void test05(){
        employees.stream()
            .filter((e)->{
                System.out.println("短路");
                return e.getSalary()>1000;
            })
            .distinct()
            .forEach(System.out::println);
    }

    /**
     * map 映射
     */
    public static void test06(){
        List<String> list = Arrays.asList("aaa", "bbb", "ccc");

        list.stream()
            .map((str) -> str.toUpperCase())
            .forEach(System.out::println);

        System.out.println("--------------------");

        employees.stream()
            .map(Employee::getName)
            .forEach(System.out::println);

        System.out.println("--------------------");

        /**
         * map示例
         * 接收一个函数作为参数，该函数会作用到每个元素上，并将其映射为一个新的元素。
         */
        Stream<Stream<Character>> stream = list.stream()
            .map(StreamTest02::filterCharacter);
        stream.forEach((sm) -> {
            sm.forEach(System.out::println);
        });

        /**
         * flatMap示例
         *
         * 区别map 类似于 list.add() && list.addAll()
         */
        Stream<Character> sm = list.stream()
                .flatMap(StreamTest02::filterCharacter);
        sm.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();

        for(Character ch : str.toCharArray()){
            list.add(ch);
        }
        return list.stream();
    }

    /**
     * add() 和 addAll() 异同
     */
    public static void addAndAll(Boolean allFlag){
        List<String> list = Arrays.asList("aaa", "bbb", "ccc");
        List list2 = new ArrayList();
        list2.add(22);
        list2.add(11);
        list2.add(33);

        if(allFlag){
            list2.addAll(list);
        }else{
            list2.add(list);
        }
        System.out.println(list2);
    }

    /**
     * 排序
     */
    public static void test07(){
        List<String> list = Arrays.asList("aaa", "bbb", "ccc");
        //todo 1.自然排序
        list.stream()
            .sorted()
            .forEach(System.out::println);

        //todo 2.定制排序
        employees.stream()
                .sorted((e1,e2) -> {
                    if(e1.getAge() == e2.getAge()){
                        return e1.getName().compareTo(e2.getName());
                    }else{
                        return e1.getAge().compareTo(e2.getAge());
                    }
                })
                .forEach(System.out::println);
    }


    public static void main(String[] args) {
        test07();

        addAndAll(true);
    }
}
