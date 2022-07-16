package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        int hashCode = (key == null) ? 0 : key.hashCode();
        int index = indexFor(hash(hashCode));
        if (count >= LOAD_FACTOR * capacity) {
            expand();
        }
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] anotherTable = table;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> map : anotherTable) {
            if (map != null) {
                put(map.key, map.value);
            }
        }
        modCount++;
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash((key == null) ? 0 : key.hashCode()));
        return (table != null && table[index] != null && Objects.equals(table[index].key, key))
                ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexFor(hash((key == null) ? 0 : key.hashCode()));
        if (table[index] != null && Objects.equals(table[index].key, key)) {
            table[index] = null;
            rsl = true;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < capacity && table[point] == null) {
                    point++;
                }
                return point < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw  new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
