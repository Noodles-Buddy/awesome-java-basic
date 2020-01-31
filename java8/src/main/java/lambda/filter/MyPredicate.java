package lambda.filter;

/**
 *
 * Created by zhangzhenhua on 2020-01-04 10:01
 */
public interface MyPredicate<T> {

    public boolean test(T t);

}
