package parallel;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * StreamApi可以声明性地通过 parallel() 与 sequential() 在并行流和顺序流之间切换
 *
 * 举例：
 * Fork/Join 框架 -> 在数据量特别大的情况下，其效率高于for循环，且cpu为高负载（并行）；而for的cpu负载一直不高（串行）
 *
 * 扩展：
 * fork/join 多核cpu 并行执行原理
 */
public class ParallelTest {

    public static void main(String[] args) {
        Instant start = Instant.now();

        LongStream.rangeClosed(0, 100000000000L)
                //切换并行执行
                .parallel()
                .reduce(0, Long::sum);

        Instant end = Instant.now();

        System.out.println("消耗时间：" + Duration.between(start,end).toMillis());
    }

}
