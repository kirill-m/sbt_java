package ru.sbt.homework12.execution_manager.manager;

import ru.sbt.homework12.execution_manager.context.Context;

/**
 * Created by kirill on 28.08.16
 */
public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}
