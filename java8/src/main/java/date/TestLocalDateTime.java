package date;

import org.junit.Test;

import java.time.*;

/**
 * 1.LoalDate && LocalTime && LocalDateTime (系统时间)
 *
 * 2.Instant （默认UTC时区）
 * 时间戳：以Unix元年 1970.01.01 00:00:00 为基准到某个时间的毫秒值
 *
 * 3.
 * Duration: 计算两个时间之间的间隔
 * Period: 计算两个日期之间的间隔
 *
 *
 *
 */
public class TestLocalDateTime {


    public static void main(String[] args) {

    }

    @Test
    public void test01(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.of(2020,01,25,21,0,2);
        System.out.println(ldt2);

        //计算：增2年
        LocalDateTime ldt3 = ldt.plusYears(3);
        System.out.println(ldt3);

        //计算：减3月
        LocalDateTime ldt4 = ldt.minusMonths(3);
        System.out.println(ldt4);

        System.out.println(ldt4.getYear());
        System.out.println(ldt4.getMonthValue());
        System.out.println(ldt4.getDayOfMonth());
        System.out.println(ldt4.getHour());
        System.out.println(ldt4.getMinute());
        System.out.println(ldt4.getSecond());
    }

    @Test
    public void test02(){
        //默认UTC时区
        Instant ins1  = Instant.now();
        System.out.println(ins1);

        //带偏移量的时间，时区相差8小时
        OffsetDateTime odt = ins1.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);

        //毫秒值
        System.out.println(ins1.toEpochMilli());

        //Unix元年 偏移量
        Instant ins2 = Instant.ofEpochSecond(1000);
        System.out.println(ins2);


    }

    @Test
    public void test03(){
        Instant ins1 = Instant.now();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        Instant ins2 = Instant.now();
        Duration duration = Duration.between(ins1, ins2);
        System.out.println(duration.getSeconds());

        System.out.println("--------------------------");

        LocalDate ld1 = LocalDate.of(2020,01,25);
        LocalDate ld2 = LocalDate.of(2020,01,20);
        Period period = Period.between(ld1, ld2);
        System.out.println(period);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }
}
