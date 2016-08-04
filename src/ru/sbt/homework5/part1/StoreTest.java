package ru.sbt.homework5.part1;

import java.util.List;

/**
 * Created by kirill on 04.08.16
 */
public class StoreTest {
    public static void main(String[] args) {
        final String fileName = "/Users/kirill/Desktop/file.txt";
        Store store = new FileStore(fileName);
        store.save("test_str\n");
        store.save("test_str2\n");
        store.save("test_str3\n");

        List<String> list = store.getAll();
        for (String s : list) {
            System.out.println(s);
        }
    }
}
