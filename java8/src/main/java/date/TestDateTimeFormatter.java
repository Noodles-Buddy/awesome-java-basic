package date;

import org.junit.Test;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;


/**
 * 1.DateTimeFormatter 时间格式转换
 *
 * 2.ZoneId 时区
 *
 */
public class TestDateTimeFormatter {

    @Test
    public void test(){
        //ISO_DATE 日期时间标准库
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt = LocalDateTime.now();
        String strDate = ldt.format(dtf);
        System.out.println(strDate);

        System.out.println("------------------------");

        //自定义格式
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String str2 = dtf1.format(ldt);
        System.out.println(str2);

        //逆向转换
        LocalDateTime ldt2 = ldt.parse(str2, dtf1);
        System.out.println(ldt2);
    }

    @Test
    public void test1(){
        //展示所有时区
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);
    }


    @Test
    public void test2(){
        //取某个时区内到系统时间
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Australia/Lindeman"));
        System.out.println(ldt);

        //系统时间转换到其他时区
        LocalDateTime ldt2 = LocalDateTime.now();
        ZonedDateTime zdt = ldt2.atZone(ZoneId.of("Europe/Nicosia"));
        System.out.println(zdt);
    }
}
