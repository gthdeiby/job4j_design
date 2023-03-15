package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        SoftReference<V> softValue = new SoftReference<V>(value);
        cache.put(key, softValue);
    }

    public final V get(K key) {
        SoftReference<V> softValue = cache.getOrDefault(key, new SoftReference<>(null));
        V value = softValue.get();
        if (value == null) {
            value = load(key);
            put(key, value);
        }
        return value;
    }

    protected abstract V load(K key);
}