package ru.sbt.homework12.task;

/**
 * Created by kirill on 27.08.16
 */
public class TaskExamples {
    public static void main(String[] args) throws InterruptedException {
         Task<Long> task = new Task<>(() -> {
            Long number = 0L;
            for (int i = 0; i < 100_000_000; i++) {
                number += i;
            }
            return number;
        });

         Runnable run = () ->
            System.out.println(task.get() + " " + Thread.currentThread().getName());


        new Thread(run).start();
        new Thread(run).start();
        Thread.sleep(2000);
        new Thread(run).start();
        new Thread(run).start();
        new Thread(run).start();
    }
}
