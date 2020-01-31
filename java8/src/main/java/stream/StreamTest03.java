package stream;

import dto.Employee;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * ---Stream 终止操作---
 *
 * *** 查找与匹配 ***
 * 1.allMatch 是否匹配所有元素
 * 2.anyMatch 是否匹配至少一个元素
 * 3.noneMatch
 * 4.findFirst 返回当前流中第一个元素
 * 5.findAnd 返回当前流中任意元素
 * 5.count 返回数量
 * 6.max 返回最大值
 * 7.min 返回最小值
 *
 *
 * *** 规约 reduce ***
 * 1.reduce(T identity, BinaryOperation)
 * 2.reduce(BinaryOperation)
 * 可以将流中元素反复结合起来，得到一个值
 *
 *
 * *** 收集 ***
 * 将流转化为其他形式，接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
 * 1.Collectors 工具类 如：收集List<Entity> 集合， Entity中某个字段的属性值，构成新的集合(List\Map\)
 *
 * *** 分组 ***
 * 1.普通分组
 * 2.多级分组
 *
 * *** 分区 ***
 * 1.
 *
 *
 *
 * Created by zhangzhenhua on 2020-01-01 19:06
 */
public class StreamTest03 {

    static List<Employee> employees = Arrays.asList(
            new Employee("张三",16,226.66, Employee.Status.BUSY),
            new Employee("李四",31,666.66, Employee.Status.FREE),
            new Employee("王五",25,623.66, Employee.Status.VOCATION),
            new Employee("赵六",40,546.66, Employee.Status.BUSY),
            new Employee("田七",33,666.66, Employee.Status.FREE),
            new Employee("田七",55,999.66, Employee.Status.VOCATION),
            new Employee("田七",29,666.66, Employee.Status.BUSY),
            new Employee("田七",37,768.66, Employee.Status.FREE),
            new Employee("田七",26,1226.66, Employee.Status.VOCATION),
            new Employee("田七",77,34566.66, Employee.Status.BUSY)
    );

    /**
     * 是否全部都匹配
     */
    public static void test01(){
        boolean b1 = employees.stream()
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);
    }

    /**
     * 是否至少匹配一个
     */
    public static void test02(){
        boolean b1 = employees.stream()
                .anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);
    }

    /**
     * 是否没有匹配的元素
     */
    public static void test03(){
        boolean b1 = employees.stream()
                .noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);
    }

    /**
     * 获取第一个元素
     */
    public static void test04(){
        Optional<Employee> employee = employees.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(employee);
    }

    /**
     * 返回当前流中任意元素
     */
    public static void test05(){
        Optional<Employee> employee = employees.stream()
                .filter((e) -> e.getStatus().equals(Employee.Status.VOCATION))
                .findAny();
        System.out.println(employee);
    }

    /**
     * parallelStream 并行执行
     * 返回当前流中任意元素
     */
    public static void test06(){
        Optional<Employee> employee = employees.parallelStream()
                .filter((e) -> e.getStatus().equals(Employee.Status.VOCATION))
                .findAny();
        System.out.println(employee);
    }

    /**
     * 获取数量
     */
    public static void test07(){
        Long count = employees.stream()
                .count();
        System.out.println(count);
    }

    /**
     * 获取最大值
     */
    public static void test08(){
        Optional<Employee> opt = employees.stream()
                .max((e1,e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(opt.get());
    }

    /**
     * 获取最小值
     */
    public static void test09(){
        Optional<Double> salary = employees.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(salary);
    }

    /**
     * 规约
     * .reduce(T identity, BinaryOperation)
     * .reduce(起始值, 表达式);
     */
    public static void reduce01(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
        Integer sum = list.stream()
            .reduce(0, (x,y) -> x + y);
        System.out.println(sum);
    }

    /**
     * todo 规约
     * .reduce(BinaryOperation)
     * .reduce(表达式);
     *
     * map-reduce 经典模式
     */
    public static void reduce02(){
        Optional<Double> sum = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(sum);
    }

    /**
     * 收集
     * 1.将employees中的name列，收集到list中
     * 2.将employees中的name列，收集到set中
     */
    public static void collect01(){
        List<String> list = employees.stream()
            .map(Employee::getName)
            .collect(Collectors.toList());
        list.forEach(System.out::println);

        System.out.println("-------------------");

        Set<String> sets = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        sets.forEach(System.out::println);

        System.out.println("-------------------");

        //收集到特殊到HashSet中
        HashSet<String> hs = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        hs.forEach(System.out::println);
    }

    /**
     * 收集
     * 1.总数
     * 2.
     * 3.
     *
     */
    public static void collect02(){
        //总数
        Long count = employees.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        //平均值
        Double average = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(average);

        //总和
        Double sum = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);

        //最大值
        Optional<Employee> max = employees.stream()
                .collect(Collectors.maxBy((e1,e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(max);

        //最小值
        Optional<Double> min = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(min);
    }

    /**
     * 按照 status 分组
     */
    public static void group01(){
        Map<Employee.Status,List<Employee>> group = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(group);
    }

    /**
     * 多级分组
     */
    public static void group02(){
        /*Map<Employee.Status, Map<Employee.Status,List<Employee>>> group = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if(((Employee)e).getAge() <= 35){
                        return "青年";
                    }else if(((Employee)e).getAge() <= 55){
                        return "中年";
                    }else {
                        return "老年";
                    }
                })));
        System.out.println(group);*/
    }

    /**
     * 分区
     * 1.收入是否大于8000元
     */
    public static void partition(){
        Map<Boolean, List<Employee>> map =  employees.stream()
                .collect(Collectors.partitioningBy((e)-> e.getSalary() > 8000));
        System.out.println(map);
    }

    /**
     * 分区
     * DoubleSummaryStatistics 统计方式
     */
    public static void partition01(){
        String str =  employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(","));
        System.out.println(str);
    }

    /**
     * 分区
     * join
     */
    public static void partition02(){
        DoubleSummaryStatistics dss =  employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss.getMax());
        System.out.println(dss.getAverage());
        System.out.println(dss.getCount());
    }

    public static void main(String[] args) {
        partition();
    }
}
