package ru.sbt.homework4.part1;

import java.util.Map;

/**
 * Created by kirill
 */
public interface CountMap<T> extends Iterable<T> {
    // добавляет элемент в этот контейнер.
    void add(T o);

    //Возвращает количество добавлений данного элемента
    int getCount(T o);

    //Удаляет элемент из контейнера и возвращает количество его добавлений(до удаления)
    int remove(T o);

    //количество разных элементов
    int size();

    //Добавить все элементы из source в текущий контейнер, при совпадении ключей,     суммировать значения
    void addAll(CountMap<? extends T> source);

    //Вернуть java.util.Map. ключ - добавленный элемент, значение - количество его добавлений
    Map<T, Integer> toMap();

    //Тот же самый контракт как и toMap(), только всю информацию записать в destination
    void toMap(Map<? super T, Integer> destination);

}
