package ru.sbt.homework6.part1;

import java.util.Date;

/**
 * Created by kirill
 */
public class BeanUtilsTest {
    public static void main(String[] args) {
        Date date1 = new Date();
        Date date2 = new Date(1_422_844_257_220L);
        System.out.println("Before:");
        System.out.println(date1.getMonth());
        System.out.println(date2.getMonth());

        System.out.println("After:");
        BeanUtils.assign(date2, date1);
        System.out.println(date1.getMonth());
        System.out.println(date2.getMonth());
        System.out.println(date1.getTime() == date2.getTime());
    }
}
