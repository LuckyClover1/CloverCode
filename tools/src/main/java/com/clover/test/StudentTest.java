package com.clover.test;

import java.sql.Struct;

public class StudentTest {
    public static void main(String[] args) {
        Student sd = new Student("dfs");
        Student ss = (Student)sd.clone();
        System.out.println(ss.getName() + ss);
        System.out.println(sd.getName() + sd);
    }
}
