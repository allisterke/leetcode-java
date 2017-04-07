package a460;

import java.util.*;

public class LFUCache {
    Frequency zero, ultimate;
    HashMap<Integer, Integer> kv;
    HashMap<Integer, Frequency> kf;

    public static class Frequency {
        int f;
        LinkedHashSet<Integer> set;
        Frequency previous, next;

        public Frequency(int f) {
            this.f = f;
            set = new LinkedHashSet<>();
            previous = next = null;
        }
    }

    int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;

        zero = new Frequency(0);
        ultimate = new Frequency(Integer.MAX_VALUE);
        zero.next = ultimate;
        ultimate.previous = zero;

        kv = new HashMap<>();
        kf = new HashMap<>();
    }

    public int get(int key) {
        if(!kv.containsKey(key)) {
            return -1;
        }

        int value = kv.get(key);
        Frequency f = kf.get(key);
        if(f.next.f != f.f + 1) {
            Frequency nf = new Frequency(f.f + 1);
            nf.previous = f;
            nf.next = f.next;
            f.next.previous = nf;
            f.next = nf;
        }
        f.next.set.add(key);
        f.set.remove(key);
        kf.put(key, f.next); // update kf

        if(f.set.isEmpty()) {
            f.previous.next = f.next;
            f.next.previous = f.previous;
        }

        return value;
    }

    public void put(int key, int value) {
        if(capacity == 0) {
            return;
        }
        if(!kv.containsKey(key)) {
            if(kv.size() < capacity) { // just insert
                kv.put(key, value); // update kv

                if(zero.next.f != 1) {
                    Frequency one = new Frequency(1);
                    one.previous = zero;
                    one.next = zero.next;
                    one.next.previous = one;
                    one.previous.next = one;
                }

                zero.next.set.add(key);

                kf.put(key, zero.next); // update kf
            }
            else { // evict one key
                int evict = zero.next.set.iterator().next();
                kv.remove(evict);
                zero.next.set.remove(evict);

                if(zero.next.f != 1 && zero.next.set.isEmpty()) { // delete if not zero
                    zero.next = zero.next.next;
                    zero.next.previous = zero;
                }

                put(key, value);
            }
        }
        else {
            kv.put(key, value);
            get(key);
        }
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache( 2 /* capacity */ );

        cache.put(1, 1);
        cache.put(2, 2);
        assert 1 == cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        assert -1 == cache.get(2);       // returns -1 (not found)
        assert 3 == cache.get(3);       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        assert -1 == cache.get(1);       // returns -1 (not found)
        assert 3 == cache.get(3);       // returns 3
        assert 4 == cache.get(4);       // returns 4
    }
}