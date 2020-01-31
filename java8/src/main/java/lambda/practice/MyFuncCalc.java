package lambda.practice;

/**
 * Created by zhangzhenhua on 2020-01-12 11:11
 */
@FunctionalInterface
public interface MyFuncCalc<T,R> {
    public R getValue(T t1, T t2);
}
