package com.chunjin.util;

public class Entry {

    JavaBean[] a = new JavaBean[10];

    public Entry() {
       for(int i=0;i<10;i++)
       {

           a[i]=new JavaBean("name"+i,"Sex"+i);
       }
    }

    JavaBean getBean()
    {
        return a[2];
    }
}
