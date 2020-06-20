package com.zyan.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache extends LinkedHashMap<Integer, Integer> {
    private int capacity;
    public LRUCache(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor, true);
        this.capacity = initialCapacity;
    }

    @Override
    public Integer get(Object key) {
        return super.getOrDefault(key, -1);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }

    @Override
    public Integer put(Integer key, Integer value) {
        return super.put(key, value);
    }
}
