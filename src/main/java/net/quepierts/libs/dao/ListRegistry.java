package net.quepierts.libs.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListRegistry<T> {
    protected final Map<String, List<T>> registry;
    protected boolean locked = false;

    public ListRegistry() {
        registry = new HashMap<>();
    }

    public boolean create(String id) {
        if (!locked) {
            if (!registry.containsKey(id)) {
                registry.put(id, new ArrayList<>());
            }
        }

        return false;
    }

    public boolean register(String id, T object) {
        if (!locked) {
            create(id);

            if (!registry.get(id).contains(object)) {
                registry.get(id).add(object);
                return true;
            }
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

    public void reset(Map<String, List<T>> registry) {
        this.registry.clear();
        this.registry.putAll(registry);
        this.locked = false;
    }

    public boolean containsKey(String id) {
        return registry.containsKey(id);
    }

    public List<T> get(String id) {
        if (registry.containsKey(id)) {
            return new ArrayList<>(this.registry.get(id));
        } else {
            return null;
        }
    }
}
