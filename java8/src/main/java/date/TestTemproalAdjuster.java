package date;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

/**
 * 时间校正器 （如将时间调整到下周日）
 *
 * TemporalAdjusters 类中大量静态方法，提供了大量 TemproalAdjuster 实现。
 */
public class TestTemproalAdjuster {



    @Test
    public void test(){
        //当天时间
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        //当月10号
        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);

        //下一个周五
        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        System.out.println(ldt3);

        //自定义时间
        LocalDateTime ldt4 = ldt.with((l) -> {
            LocalDateTime ldt5 = (LocalDateTime)l;
            DayOfWeek dow = ldt5.getDayOfWeek();

            if(dow.equals(DayOfWeek.FRIDAY)){
                return ldt5.plusDays(3);
            }else if (dow.equals(DayOfWeek.SATURDAY)){
                return ldt5.plusDays(2);
            }else {
                return ldt5.plusDays(1);
            }
        });

        System.out.println(ldt4);
    }

}
