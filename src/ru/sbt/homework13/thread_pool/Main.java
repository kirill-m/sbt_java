package ru.sbt.homework13.thread_pool;

/**
 * Created by kirill on 01.09.16
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int i = 0;
            for (int j = 0; j < 1_000_000_000; j++) {
                i += j;
            }
            System.out.println(i);
        };

        ThreadPool pool = new ScalableThreadPool(5, 7);

        pool.execute(runnable);
        pool.execute(runnable);
        pool.execute(runnable);
        pool.execute(runnable);
        pool.execute(runnable);

        pool.start();

        pool.execute(runnable);
        pool.execute(runnable);
        pool.execute(runnable);
        pool.execute(runnable);


    }
}
