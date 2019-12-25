package com.chunjin.util;

public class JavaBean {
    private String name;
    private String sex;
    private Integer age;

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

    public JavaBean(String name, String sex) {
        this.name = name;
        this.sex = sex;
        this.age = new Integer(11);
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
