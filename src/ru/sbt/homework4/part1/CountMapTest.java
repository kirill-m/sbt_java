package ru.sbt.homework4.part1;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kirill
 */
public class CountMapTest {
    public static void main(String[] args) {
        CountMap<Integer> map = new CountMapImpl<>();

        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        printMap(map);

        System.out.println("map size = " + map.size());

        CountMap<Integer> map2 = new CountMapImpl<>();

        map2.add(15);
        map2.add(15);
        map2.add(1);
        map2.add(1);
        map2.add(1);
        map2.add(10);

        map.addAll(map2);

        System.out.println();
        printMap(map);

        System.out.println();
        System.out.println(map.remove(15));
        printMap(map);

        Map<Integer, Integer> pureMap = map.toMap();
        System.out.println();
        printMap(pureMap);

        pureMap = new HashMap<>();
        map2.toMap(pureMap);
        System.out.println();
        printMap(pureMap);



    }

    private static void printMap(CountMap<Integer> map) {
        for (Integer i : map) {
            System.out.println(i + " : " + map.getCount(i));
        }
    }

    private static void printMap(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
