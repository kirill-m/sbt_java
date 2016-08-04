package ru.sbt.homework4.part2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kirill
 */
public class CollectionUtilsTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
            if(i % 2 == 0)
                list2.add(i);
        }
        System.out.println(list2);

        CollectionUtils.add(list, 100);
        System.out.println();
        System.out.println(list);
        System.out.println("Index of 100: " + CollectionUtils.indexOf(list, 100));

        List<String> strings1 = CollectionUtils.newArrayList();
        List<String> strings2 = CollectionUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            strings1.add("string" + i);
        }
        CollectionUtils.addAll(strings1, strings2);
        System.out.println();
        System.out.println(strings2);

        strings2 = CollectionUtils.limit(strings2, 5);
        CollectionUtils.removeAll(strings1, strings2);
        System.out.println(strings1);

        System.out.println("string1 contains all from string2: " + CollectionUtils.containsAll(strings1, strings1));
        CollectionUtils.add(strings1, strings2.get(0));
        System.out.println("string1 contains any from string2: " + CollectionUtils.containsAny(strings1, strings2));

        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(2,5,6,8,3,9,4));
        System.out.println();
        System.out.println(CollectionUtils.range(integers, 2, 6));
    }

    private static <T> void printList(List<T> list) {
        for (T item : list) {
            System.out.println(item);
        }
    }
}

