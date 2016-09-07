package ru.sbt.homework14.service;

/**
 * Created by kirill on 07.09.16
 */
public class ServiceMain {
    public static void main(String[] args) throws InterruptedException {
        Service service = new EqualityLockService(o -> {
            long i = 0;
            for (long j = 0; j < 1_000_000_000L; j++) {
                i += j;
            }
            System.out.println(i);
        });

        new Thread(() -> {service.run("str");}).start();
        new Thread(() -> {service.run(1);}).start();
        new Thread(() -> {service.run(2);}).start();
        Thread.sleep(10);
        new Thread(() -> {service.run(1);}).start();
        new Thread(() -> {service.run(2);}).start();
        new Thread(() -> {service.run("str");}).start();
        new Thread(() -> {service.run(2);}).start();
    }
}
