package lambda.filter;

import dto.Employee;

/**
 *
 * Created by zhangzhenhua on 2020-01-04 10:03
 */
public class FilterEmployeeByAge implements MyPredicate<Employee> {
    /**
     *
     * @param t
     * @return
     */
    @Override
    public boolean test(Employee t) {
        return t.getAge() >= 35;
    }
}
