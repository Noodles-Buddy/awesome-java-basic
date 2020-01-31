package lambda;

import dto.Employee;
import lambda.filter.FilterEmployeeByAge;
import lambda.filter.FilterEmployeeBySalary;
import lambda.filter.MyPredicate;

import java.util.*;

/**
 * lambda 与匿名函数对比
 *
 * Created by zhangzhenhua on 2020-01-03 21:28
 */
public class LambdaTest01 {

    /**
     * 原来的匿名内部类
     */
    public void test01(){
        Comparator<Integer> com = new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    /**
     * Lambda 表达式
     * test01 等效写法
     */
    public void test02(){
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    /**
     * todo 需求： 1。获取当前员工中，年龄大于35的人  2。获取工资大于5000的人
     */
    static List<Employee> employees = Arrays.asList(
            new Employee("张一",26,1226.66),
            new Employee("王二",77,3456.66),
            new Employee("张三",16,226.66),
            new Employee("李四",31,666.66),
            new Employee("王五",25,623.66),
            new Employee("赵六",40,546.66),
            new Employee("陈七",33,666.66),
            new Employee("马八",55,999.66),
            new Employee("刘九",29,666.66),
            new Employee("周十",37,768.66)
    );

    /**
     * 过滤 "35岁以上 + 薪资高于5000" 的员工
     * @param args
     */
    public static void main(String[] args) {
        //todo 传统方式
        //遍历过滤 年龄
        List<Employee> list = filterEmployeesAge(employees);
        for(Employee employee : list){
            System.out.println(employee);
        }
        //遍历过滤 薪资
        List<Employee> list1 = filterEmployeesSalary(employees);
        for(Employee employee : list1){
            System.out.println(employee);
        }

        System.out.println("-------------------------");

        /**
         * todo 优化1：策略模式
         * 优点，新增需求时，无需新建方法，利用多态，无需重复代码
         * 缺点：虽避免了重复代码，但新增接口实现类
         */
        //策略模式 - 过滤年龄
        List<Employee> list2 = filterEmpByStrategy(employees, new FilterEmployeeByAge());
        for(Employee employee : list2){
            System.out.println(employee);
        }

        //策略模式 - 过滤薪资
        List<Employee> list3 = filterEmpByStrategy(employees, new FilterEmployeeBySalary());
        for(Employee employee : list3){
            System.out.println(employee);
        }

        System.out.println("-------------------------");

        /**
         * todo 优化2：策略模式 + 匿名内部类实现接口
         * 优点：避免了重复代码， 且无需新增接口实现类
         */
        List<Employee> list4 = filterEmpByStrategy(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() < 5000;
            }
        });
        for(Employee employee : list4){
            System.out.println(employee);
        }

        System.out.println("-------------------------");

        /**
         * todo 优化3：lambda 表达式
         * 优点：代码简洁，只需定义接口，无需实现类 或 匿名实现类
         * 1.filter方法代码减少
         * 2.for循环更加简洁
         */
        List<Employee> list5 = filterEmpByStrategy(employees, (e) -> e.getSalary() <= 5000);
        list5.forEach(System.out::println);

        System.out.println("-------------------------");

        /**
         * todo 优化4： Stream Api（假设除Employee实体，其他都没有）
         * 优点：
         */
        employees.stream()
                .filter((e) -> e.getSalary() <= 5000)
                .limit(2)
                .forEach(System.out::println);
    }

    /**
     * 第一版：遍历 过滤年龄
     *
     * @param list
     * @return
     */
    public static List<Employee> filterEmployeesAge(List<Employee> list){
        List<Employee> emps = new ArrayList<>();

        for(Employee emp : list){
            if(emp.getAge() >= 35){
                emps.add(emp);
            }
        }
        return emps;
    }

    /**
     * 第一版：遍历 过滤薪资
     *
     * @param list
     * @return
     */
    public static List<Employee> filterEmployeesSalary(List<Employee> list){
        List<Employee> emps = new ArrayList<>();

        for(Employee emp : list){
            if(emp.getSalary() >= 5000){
                emps.add(emp);
            }
        }
        return emps;
    }

    /**
     * 第二版：优化 策略模式
     *
     * @param list
     * @return
     */
    public static List<Employee> filterEmpByStrategy(List<Employee> list, MyPredicate<Employee> mp){
        List<Employee> emps = new ArrayList<>();

        for(Employee employee : list){
            if(mp.test(employee)){
                emps.add(employee);
            }
        }
        return emps;
    }



}
