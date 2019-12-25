package com.chunjin.util;

import java.util.Map;

public class test2 {

    public static void main(String[] args) {


        Entry entry= new Entry();
        JavaBean bean =entry.getBean();
        Integer  s = bean.getAge();
        s= 32;


        System.out.println(entry.getBean().getAge());

    }
}
