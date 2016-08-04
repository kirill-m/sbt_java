package ru.sbt.homework2;

/**
 * Created by kirill
 */
public class PersonTest {
    public static void main(String[] args) {
        Person mike = new Person(true, "Mike");
        Person helen = new Person(false, "Helen");
        Person john = new Person(true, "John");
        Person lisa = new Person(false, "Lisa");

        mike.marry(helen);
        System.out.println(mike.getName() + " is husband of " + mike.getSpouse().getName());
        System.out.println(helen.getName() + " is wife of " + helen.getSpouse().getName());

        mike.marry(lisa);
        System.out.println(mike.getName() + " is husband of " + mike.getSpouse().getName());
        System.out.println(lisa.getName() + " is wife of " + lisa.getSpouse().getName());
        System.out.println("Helen is divorced : " + (helen.getSpouse() == null));

        john.marry(helen);
        System.out.println("Helen is wife of John : " + helen.getSpouse().getName().equals("John"));

        lisa.divorce();
        System.out.println("Lisa is divorced: " + (lisa.getSpouse() == null));
        System.out.println("Mike is divorced: " + (lisa.getSpouse() == null));
    }
}
