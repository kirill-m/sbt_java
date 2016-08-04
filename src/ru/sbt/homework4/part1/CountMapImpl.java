package ru.sbt.homework4.part1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by kirill
 */
public class CountMapImpl<T> implements CountMap<T> {
    private Map<T, Integer> map = new HashMap<>();

    @Override
    public void add(T o) {
        if (map.containsKey(o))
            map.put(o, map.get(o) + 1);
        else
            map.put(o, 1);
    }

    @Override
    public int getCount(T o) {
        return map.get(o);
    }

    @Override
    public int remove(T o) {
        return map.remove(o);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap<? extends T> source) {
        Map<? extends T, Integer> src = source.toMap();

        for (Map.Entry<? extends T, Integer> entry : src.entrySet()) {
            if (map.containsKey(entry.getKey()))
                map.put(entry.getKey(), map.get(entry.getKey()) + src.get(entry.getKey()));
            else
                map.put(entry.getKey(), src.get(entry.getKey()));
        }
    }

    @Override
    public Map<T, Integer> toMap() {
        return map;
    }

    @Override
    public void toMap(Map<? super T, Integer> destination) {
        destination.putAll(map);
    }

    @Override
    public Iterator<T> iterator() {
        return map.keySet().iterator();
    }
}
