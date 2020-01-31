package dto;

/**
 * Created by zhangzhenhua on 2019-12-24 21:23
 */
public class Employee {
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 薪水
     */
    private Double salary;
    /**
     * 状态
     */
    private Status status;

    public Employee(){}

    public Employee(Integer age){
        this.age = age;
    }

    public Employee(String name, Integer age, Double salary){
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Employee(String name, Integer age, Double salary, Status status){
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString(){
        return "Employee [name=" + name + ", age=" + age + ",salary=" + salary + ",status=" + status + "]";
    }

    public enum Status{
        FREE,
        BUSY,
        VOCATION;
    }
}
