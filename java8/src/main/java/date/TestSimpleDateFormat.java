package date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 一、
 * 1.8中的日期、时间处理，均为 线程安全 （DateTimeFormatter、 LocalDate、 ）
 *
 * 二、
 *
 *
 */
public class TestSimpleDateFormat {

    /**
     * 线程池 方式
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

        Callable<LocalDate> task = new Callable<LocalDate>(){

            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("20200125", dtf);
            }
        };

        //todo 线程池future作用？
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<LocalDate>> result = new ArrayList<>();

        for(int i=0; i<10; i++){
            result.add(pool.submit(task));
        }

        for(Future<LocalDate> future : result){
            System.out.println(future.get());
        }
    }

}
