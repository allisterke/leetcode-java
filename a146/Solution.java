package a146;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

class LRUCache {
    private LinkedHashMap<Integer, Integer> lru = new LinkedHashMap<>();
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Integer value = lru.get(key);
        if(value == null) {
            return -1;
        }
        else {
            lru.remove(key);
            lru.put(key, value);
            return value;
        }
    }

    public void put(int key, int value) {
        Integer ov = lru.get(key);
        if(ov != null) {
            lru.remove(key);
        }
        else {
            while(lru.size() >= capacity) {
                lru.remove(lru.keySet().iterator().next());
            }
        }
        lru.put(key, value);
    }
}

public class Solution {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assert cache.get(1) == 1;
        cache.put(3, 3);
        assert cache.get(2) == -1;
        cache.put(4, 4);
        assert cache.get(1) == -1;
        assert cache.get(3) == 3;
        assert cache.get(4) == 4;
    }
}