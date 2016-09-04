package ru.sbt.homework12.execution_manager;

import ru.sbt.homework12.execution_manager.context.Context;
import ru.sbt.homework12.execution_manager.manager.ExecutionManager;
import ru.sbt.homework12.execution_manager.manager.ExecutionManagerImpl;

/**
 * Created by kirill on 28.08.16
 */
public class ExecutionManagerMain {
    static Runnable runnable = () -> {
        Long number = 0L;
        for (int i = 0; i < 100_000_000; i++) {
            number += i;
        }
    };

    static Runnable runnableLong = () -> {
        Long number = 0L;
        for (int i = 0; i < 1_000_000_000; i++) {
            number += i;
        }
    };

    static Runnable runnableWithException = () -> {
        throw new RuntimeException("Synthetic exception");
    };

    static Runnable[] runnables = {runnable, runnableLong, runnableWithException, runnableLong, runnableLong, runnable, runnable};

    static Runnable callback = () -> System.out.println("Callback!");

    public static void main(String[] args) throws InterruptedException {
        ExecutionManager executionManager = new ExecutionManagerImpl();
        Context context = executionManager.execute(callback, runnables);
        Thread.sleep(5000);
        context.interrupt();
        System.out.println("Failed: " + context.getFailedTaskCount());
        System.out.println("Completed: " + context.getCompletedTaskCount());
        System.out.println("Interrupted: " + context.getInterruptedTaskCount());
        Thread.sleep(5000);
    }
}
