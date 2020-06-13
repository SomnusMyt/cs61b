import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private ArrayList<Entry>[] maps;
    private int initialSize;
    private double loadFactor;
    private int size = 0;
    private HashSet<K> keySet;

    private class Entry {
        K key;
        V value;
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    public MyHashMap() {
        initialSize = 16;
        loadFactor = 0.75;
        maps = new ArrayList[initialSize];
        for (int i = 0; i < initialSize; i++) {
            maps[i] = new ArrayList<Entry>();
        }
        keySet = new HashSet<>();
    }
    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        loadFactor = 0.75;
        maps = new ArrayList[initialSize];
        for (int i = 0; i < initialSize; i++) {
            maps[i] = new ArrayList<Entry>();
        }
        keySet = new HashSet<>();
    }
    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        maps = new ArrayList[initialSize];
        for (int i = 0; i < initialSize; i++) {
            maps[i] = new ArrayList<Entry>();
        }
        keySet = new HashSet<>();
    }
    @Override
    /** Removes all of the mappings from this map. */
    public void clear() {
        size = 0;
        keySet = new HashSet<>();
        maps = new ArrayList[initialSize];
        for (int i = 0; i < initialSize; i++) {
            maps[i] = new ArrayList<Entry>();
        }
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        int hashcode = (key.hashCode() & 0x7FFFFFFF) % maps.length;
        for (Entry map : maps[hashcode]) {
            if (map.key.equals(key)) {
                return map.value;
            }
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value) {
        if (containsKey(key)) {
            int hashcode = (key.hashCode() & 0x7FFFFFFF) % maps.length;
            for (Entry map : maps[hashcode]) {
                if (map.key.equals(key)) {
                    map.value = value;
                }
            }
        }
        else {
            Entry temp = new Entry(key, value);
            int hashcode = (key.hashCode() & 0x7FFFFFFF) % maps.length;
            maps[hashcode].add(temp);
            size++;
            keySet.add(key);
            if ((double) size / maps.length > loadFactor) {
                resize(maps.length * 2);
            }
        }
    }

    private void resize(int capacity) {
        ArrayList<Entry> temp = new ArrayList<>();
        for (K key : keySet) {
            temp.add(new Entry(key, get(key)));
        }
        maps = new ArrayList[capacity];
        for (int i = 0; i < capacity; i++) {
            maps[i] = new ArrayList<Entry>();
        }
        for (Entry entry : temp) {
            put(entry.key, entry.value);
        }

    }

    @Override
    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        return keySet;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {
        //throw new UnsupportedOperationException();
        if (containsKey(key)) {
            int hashcode = (key.hashCode() & 0x7FFFFFFF) % maps.length;
            for (Entry map : maps[hashcode]) {
                if (map.key.equals(key)) {
                    V val = map.value;
                    maps[hashcode].remove(map);
                    size--;
                    keySet.remove(key);
                    return val;
                }
            }
        }

        return null;

    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value) {
        //throw new UnsupportedOperationException();
        if (containsKey(key)) {
            int hashcode = (key.hashCode() & 0x7FFFFFFF) % maps.length;
            for (Entry map : maps[hashcode]) {
                if (map.key.equals(key) && map.value.equals(value)) {
                    V val = map.value;
                    maps[hashcode].remove(map);
                    size--;
                    keySet.remove(key);
                    return val;
                }
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

}
