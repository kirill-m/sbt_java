package ru.sbt.homework14.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by kirill on 05.09.16
 */
public class EqualityLockService implements Service {
    private final Service service;
    private final ConcurrentMap<Object, Object> objects = new ConcurrentHashMap<>();

    public EqualityLockService(Service service) {
        this.service = service;
    }

    @Override
    public void run(Object o) {
        while (objects.containsKey(o)) {
            synchronized (objects.get(o)) {
                try {
                    objects.get(o).wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException("Thread has been interrupted while waiting", e);
                }
            }
        }
        objects.put(o, o);
        service.run(o);

        synchronized (objects.get(o)) {
            objects.get(o).notify();
            objects.remove(o);
        }
    }
}
