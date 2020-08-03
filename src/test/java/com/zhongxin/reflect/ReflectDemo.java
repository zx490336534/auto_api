package com.zhongxin.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectDemo {
    public static void main(String[] args) throws Exception {
        // 反射：java代码在 「运行时」 「动态」 获取一个类的属性和方法，或者调用一个对象的属性和方法
        Student s = new Student();
        // 实现反射：必须要有字节码对象
        // Class 字节码对象 约等于 .class 文件
        // 拿到字节码对象就相当于拿到了整个类所有信息

        // 1
        Class clazz1 = Student.class;
        // 2
        Class clazz2 = s.getClass();
        // 3
        String className = "com.zhongxin.reflect.Student";
        Class clazz3 = Class.forName(className);

        System.out.println(clazz1 == clazz2);
        System.out.println(clazz1 == clazz3);


        // 创建对象 newInstance 调用空参构造
        Object o = clazz3.newInstance();
        System.out.println(o);
        // Student{name='null', age=0}
        // com.zhongxin.reflect.Student@8efb846

        // 调用属性
        Field field = clazz3.getField("name");
        field.set(o, "张三");
        System.out.println(o);

        // 调用方法
        Method method = clazz3.getMethod("eat");
        method.invoke(o);

        // 暴力反射,获取属性和方法
        Field field2 = clazz3.getDeclaredField("age");
        field2.setAccessible(true);
        field2.set(o, 22);
        System.out.println(o);


    }
}
