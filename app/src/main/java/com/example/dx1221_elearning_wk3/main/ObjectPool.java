package com.example.dx1221_elearning_wk3.main;

import java.util.ArrayList;
import java.util.List;

public class ObjectPool<T> {
    private final List<T> availableObjects = new ArrayList<>();
    private final ObjectFactory<T> factory;

    public interface ObjectFactory<T> {
        T create();
    }

    public ObjectPool(ObjectFactory<T> factory, int initialSize) {
        this.factory = factory;
        for (int i = 0; i < initialSize; i++) {
            availableObjects.add(factory.create());
        }
    }

    public T acquire() {
        if (availableObjects.isEmpty()) {
            return factory.create();
        }
        return availableObjects.remove(availableObjects.size() - 1);
    }

    public void release(T obj) {
        availableObjects.add(obj);
    }

    public void clear() {
        availableObjects.clear();
    }
}
