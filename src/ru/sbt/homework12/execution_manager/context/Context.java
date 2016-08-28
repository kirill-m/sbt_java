package ru.sbt.homework12.execution_manager.context;

/**
 * Created by kirill on 28.08.16
 */
public interface Context {
    int getCompletedTaskCount();

    int getFailedTaskCount();

    int getInterruptedTaskCount();

    void interrupt();

    boolean isFinished();
}
