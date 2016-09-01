package ru.sbt.homework13.thread_pool;

/**
 * Created by kirill on 01.09.16
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ThreadPool pool = new FixedThreadPool(5);

        pool.execute(()-> System.out.println(1));
        pool.execute(()-> System.out.println(2));

        pool.start();

        pool.execute(()-> System.out.println(3));
        pool.execute(()-> System.out.println(3));
        pool.execute(()-> System.out.println(3));
        pool.execute(()-> System.out.println(3));
    }
}
