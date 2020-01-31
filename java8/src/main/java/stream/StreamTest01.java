package stream;

import dto.Employee;
import optional.OptionalUtilUtility;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * 创建Stream
 *
 * Created by zhangzhenhua on 2019-12-24 21:05
 */
public class StreamTest01 {

    public static void test(){
        //1.通过Collection的stream(), parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2.通过Arrays中静态方法stream() 获取数组流
        Employee[] emps = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(emps);

        //3.Stream 中静态方法of()
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");

        //4.创建无限流
        //迭代
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        stream4.limit(10).forEach(System.out::println);

        //生成
        Stream.generate(() -> Math.random())
                .limit(10)
                .forEach(System.out::println);

        String s = new String();
    }

    public static void main(String[] args) {
        test();
    }
}
