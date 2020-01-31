package lambda.filter;

import dto.Employee;

/**
 * Created by zhangzhenhua on 2020-01-04 15:21
 */
public class FilterEmployeeBySalary implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() > 5000;
    }
}
