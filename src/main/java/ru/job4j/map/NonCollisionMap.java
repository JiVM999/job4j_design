package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((double) count / capacity >= 0.75) {
            expand();
        }
        MapEntry<K, V> entry = new MapEntry<>(key, value);
        int hashCode = hash(Objects.hashCode(key));
        int index = indexFor(hashCode);
        if (table[index] == null) {
            table[index] = entry;
            count++;
            modCount++;
            return true;
        }
        return false;
    }

    private int findByKey(K key) {
        int hashCode = hash(Objects.hashCode(key));
        int index = indexFor(hashCode);
        if (table[index] != null
                && hashCode == Objects.hashCode(table[index].key)
                && Objects.equals(key, table[index].key)) {
            return index;
        }
        return -1;
    }

    @Override
    public V get(K key) {
        int idx = findByKey(key);
        return idx != -1 ? table[idx].value : null;
    }

    @Override
    public boolean remove(K key) {
        int idx = findByKey(key);
        if (idx != -1) {
            table[idx] = null;
            count--;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {

            private int index;
            private int mod = modCount;

            @Override
            public boolean hasNext() {
                if (mod != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index != table.length && table[index] == null) {
                    index++;
                }
                return index != table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                int hashCode = hash(Objects.hashCode(table[i].key));
                int index = indexFor(hashCode);
                if (newTable[index] == null) {
                    newTable[index] = table[i];
                }
            }
        }
        table = newTable;
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
