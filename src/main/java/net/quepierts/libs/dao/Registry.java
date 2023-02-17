package net.quepierts.libs.dao;

import java.util.HashMap;
import java.util.Map;

public class Registry<T> {
    protected final Map<String, T> registry;
    protected boolean locked = false;

    public Registry() {
        this.registry = new HashMap<>();
    }

    public Registry(HashMap<String, T> registry) {
        this.registry = registry;
    }

    public void add(HashMap<String, T> registry) {
        if (!locked) {
            this.registry.putAll(registry);
        }
    }

    public boolean register(String id, T object) {
        if (!(locked && registry.containsKey(id))) {
            registry.put(id, object);
            return true;
        }

        return false;
    }

    public void lock() {
        this.locked = true;
    }

    public void reset() {
        this.registry.clear();
        this.locked = false;
    }

    public void reset(Map<String, T> registry) {
        this.registry.clear();
        this.registry.putAll(registry);
        this.locked = false;
    }

    public boolean containsKey(String id) {
        return registry.containsKey(id);
    }

    public T get(String id) {
        return registry.get(id);
    }
}
