package net.quepierts.libs.configuration;

public class Entry<T> {
    private final Class<T> clazz;
    private final Branch[] branches;

    public Entry(Class<T> clazz, Branch[] branches) {
        this.clazz = clazz;
        this.branches = branches;
    }
}
