package ru.sbt.homework12.execution_manager.manager;

import ru.sbt.homework12.execution_manager.ThreadPool;
import ru.sbt.homework12.execution_manager.context.Context;
import ru.sbt.homework12.execution_manager.context.ContextImpl;

/**
 * Created by kirill on 28.08.16
 */
public class ExecutionManagerImpl implements ExecutionManager {
    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        return new ContextImpl(new ThreadPool(), callback, tasks);
    }
}
