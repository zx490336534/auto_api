package com.zhongxin.reflect;

public class Student {
    public String name;
    private int age;

    public void eat() {
        System.out.println("Student.eat");
    }

    private void study() {
        System.out.println("Student.study");
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
