package com.basic.generic.generatorClass.partice;

import java.util.Objects;

/**
 * @Author: zhenhua.zhang
 * @Date: 2020-10-31 09:47
 */
public class User {
    private String no;
    private Integer age;
    private String name;

    public User() {
    }

    public User(String no, Integer age, String name) {
        this.no = no;
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(no, user.no) &&
                Objects.equals(age, user.age) &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, age, name);
    }

    @Override
    public String toString() {
        return "User{" +
                "no='" + no + '\'' +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
