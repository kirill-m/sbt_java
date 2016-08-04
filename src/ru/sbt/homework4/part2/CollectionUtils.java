package ru.sbt.homework4.part2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by kirill
 */
public class CollectionUtils {

    public static<T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static<T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static<T> int indexOf(List<? extends T> source, T o) {
        return source.indexOf(o);
    }

    public static<T> List<T> limit(List<T> source, int size) {
        return source.subList(0, size - 1);
    }

    public static<T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static<T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static<T> boolean containsAll(List<? super T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    public static<T> boolean containsAny(List<? super T> c1, List<? extends T> c2) {
        boolean containsAny = false;
        for (T item : c2) {
            System.out.println(item);
            if(c1.contains(item)) {
                containsAny = true;
                break;
            }
        }

        return containsAny;
    }

    public static<T extends Comparable<? super T>> List<T> range(List<? extends T> list, T min, T max) {
        List<T> copy =  new ArrayList<>(list);
        Collections.sort(copy);
        return copy.subList(copy.indexOf(min), copy.indexOf(max) + 1);
    }

    public static<T> List<T> range(List<? extends T> list, T min, T max, Comparator<? super T> comparator) {
        List<T> copy =  new ArrayList<>(list);
        Collections.sort(copy, comparator);
        return copy.subList(copy.indexOf(min), copy.indexOf(max) + 1);
    }

}
