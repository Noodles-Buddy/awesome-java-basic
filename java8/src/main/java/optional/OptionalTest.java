package optional;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.Properties;

/**
 * Java 8 实战
 */
public class OptionalTest {
    Properties props;

    @Before
    public void before(){
        props = new Properties();
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-1");
    }

    @Test
    public void propTest(){
        readDuration(props, "a");
    }

    /**
     * 获取 全局变量props中，key为name的值，并转为int，取不到返回0.
     * @param props
     * @param name
     * @return
     */
    public int readDuration(Properties props, String name){
        String value = props.getProperty(name);
        if( value != null){
            try {
                int i = Integer.parseInt(value);
                if(i > 0){
                    return i;
                }
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * todo 优化： 包装Optional后，filter + orElse模式
     * 1.ofNullable 转换Optional对象
     * 2.flatMap 扁平化映射
     * 3.filter + lambda 过滤负数
     * 4.返回默认值
     *
     * @param props
     * @param name
     * @return
     */
    public int readDuration2(Properties props, String name){
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(OptionalUtilUtility::stringToInt)
                .filter(i -> i > 0)
                .orElse(0);
    }

}
